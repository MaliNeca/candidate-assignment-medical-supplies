package ch.aaap.ca.be.medicalsupplies;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ch.aaap.ca.be.medicalsupplies.data.*;
import org.apache.commons.lang3.StringUtils;

public class MSApplication {

    private final Set<MSGenericNameRow> genericNames;
    private final Set<MSProductRow> registry;

    private Set<MSProductIdentityRow> msProductIdentityRowSet;

    public MSApplication() {
        genericNames = CSVUtil.getGenericNames();
        registry = CSVUtil.getRegistry();
        msProductIdentityRowSet = (Set<MSProductIdentityRow>) this.createModel(genericNames, registry);
    }

    public static void main(String[] args) {
        MSApplication main = new MSApplication();

        System.err.println("generic names count: " + main.genericNames.size());
        System.err.println("registry count: " + main.registry.size());

        System.err.println("1st of generic name list: " + main.genericNames.iterator().next());
        System.err.println("1st of registry list: " + main.registry.iterator().next());
    }

    /**
     * Create a model / data structure that combines the input sets.
     *
     * @param genericNameRows
     * @param productRows
     * @return
     */
    public Object createModel(Set<MSGenericNameRow> genericNameRows, Set<MSProductRow> productRows) {

        //create map of unique generic names
        Map<String, MSGenericNameRow> uniqueGenericNameRowMap = genericNameRows.stream()
                .filter(distinctByKey(MSGenericNameRow::getName))
                .collect(Collectors.toMap(MSGenericNameRow::getName, e -> e));

        //create set of duplicated generic names
        Set<MSGenericNameRow> duplicatedGenericNameRows = genericNameRows.stream()
                .collect(Collectors.groupingBy(genericNameRow -> genericNameRow.getName(), Collectors.toList()))
                .values()
                .stream()
                .filter(genericNameRowList -> genericNameRowList.size() > 1)
                .flatMap(msGenericNameRows -> msGenericNameRows.stream())
                .collect(Collectors.toSet());

        //go through all products and map into MSProductIdentityRow with related MSGenericNameRow
        Set<MSProductIdentityRow> productIdentityRowsSet = productRows.stream()
                .map(productRow -> {

                    MSProductIdentityRow.MSProductIdentityRowBuilder msProductIdentityRowBuilder
                            = new MSProductIdentityRow.MSProductIdentityRowBuilder();

                    msProductIdentityRowBuilder
                            .withProductId(productRow.getId())
                            .withGenericName(productRow.getGenericName())
                            .withPrimaryCategory(productRow.getPrimaryCategory())
                            .withProducerName(productRow.getProducerName())
                            .withLicenseHolderName(productRow.getLicenseHolderName());

                    //if generic name for product exists in the genericNameRows, populate additional data
                    if (uniqueGenericNameRowMap.containsKey(productRow.getGenericName())) {
                        MSGenericNameRow msGenericNameRow = uniqueGenericNameRowMap.get(productRow.getGenericName());
                        msProductIdentityRowBuilder
                                .withId(msGenericNameRow.getId())
                                .withName(msGenericNameRow.getName())
                                .withCategory1(StringUtils.isNotBlank(msGenericNameRow.getCategory1()) ? msGenericNameRow.getCategory1() : null)
                                .withCategory2(StringUtils.isNotBlank(msGenericNameRow.getCategory2()) ? msGenericNameRow.getCategory2() : null)
                                .withCategory3(StringUtils.isNotBlank(msGenericNameRow.getCategory3()) ? msGenericNameRow.getCategory3() : null)
                                .withCategory4(StringUtils.isNotBlank(msGenericNameRow.getCategory4()) ? msGenericNameRow.getCategory4() : null);
                    }
                    return msProductIdentityRowBuilder.build();
                }).collect(Collectors.toSet());

        //create unique product map to match already inserted genericNames
        Map<String, MSProductIdentityRow> uniqueProductRowsMap = productIdentityRowsSet.stream()
                .filter(distinctByKey(MSProductIdentityRow::getGenericName))
                .collect(Collectors.toMap(MSProductIdentityRow::getGenericName, e -> e));

        //add unique genericNames to the productIdentityRowsSet, which is not already added
        uniqueGenericNameRowMap.values().stream()
                .filter(e-> !uniqueProductRowsMap.containsKey(e.getName()))
                .forEach(msGenericNameRow -> addNewProductIdentityRow(productIdentityRowsSet, msGenericNameRow));

        //add all duplicated genericNames to the productIdentityRowsSet
        duplicatedGenericNameRows.stream()
                .forEach(msGenericNameRow -> addNewProductIdentityRow(productIdentityRowsSet, msGenericNameRow));

        return productIdentityRowsSet;
    }


    /* MS Generic Names */

    /**
     * Method find the number of unique generic names.
     *
     * @return
     */
    public Object numberOfUniqueGenericNames() {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow -> msProductIdentityRow.getName() != null)
                .collect(Collectors.groupingBy(msProductIdentityRow -> msProductIdentityRow.getName(), Collectors.toList()))
                .size();
    }

    /**
     * Method finds the number of generic names which are duplicated.
     *
     * @return
     */
    public Object numberOfDuplicateGenericNames() {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow -> msProductIdentityRow.getProductId() == null && msProductIdentityRow.getName() != null)
                .collect(Collectors.groupingBy(msProductIdentityRow -> msProductIdentityRow.getName(), Collectors.toList()))
                .values()
                .stream()
                .filter(i -> i.size() > 1)
                .flatMap(j -> j.stream())
                .collect(Collectors.toMap(
                        MSProductIdentityRow::getName, Function.identity(),
                        (left, right) -> left)).size();
    }

    /* MS Products */

    /**
     * Method finds the number of products which have a generic name which can be
     * determined.
     *
     * @return
     */
    public Object numberOfMSProductsWithGenericName() {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow -> msProductIdentityRow.getProductId() != null && msProductIdentityRow.getName() != null)
                .collect(Collectors.toList()).size();
    }

    /**
     * Method finds the number of products which have a generic name which can NOT
     * be determined.
     *
     * @return
     */
    public Object numberOfMSProductsWithoutGenericName() {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow -> msProductIdentityRow.getProductId() != null && msProductIdentityRow.getName() == null)
                .collect(Collectors.toList()).size();
    }

    /**
     * Method finds the name of the company which is both the producer and license holder for the
     * most number of products.
     *
     * @return
     */
    public Object nameOfCompanyWhichIsProducerAndLicenseHolderForMostNumberOfMSProducts() {
        Map<String, Long> producerNamesMap = msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow ->
                        msProductIdentityRow.getProductId() != null
                                && msProductIdentityRow.getProducerName().equals(msProductIdentityRow.getLicenseHolderName()))
                .collect(Collectors.groupingBy(MSProductIdentityRow::getProducerName, Collectors.counting()));
        return Collections.max(producerNamesMap.entrySet(), Map.Entry.comparingByValue()).getKey().toString();
    }

    /**
     * Method finds the number of products whose producer name starts with
     * <i>companyName</i>.
     *
     * @param companyName
     * @return
     */
    public Object numberOfMSProductsByProducerName(String companyName) {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow ->
                        msProductIdentityRow.getProductId() != null
                                && msProductIdentityRow.getProducerName().toLowerCase().startsWith(companyName.toLowerCase()))
                .collect(Collectors.toSet())
                .size();
    }

    /**
     * Method finds the products whose generic name has the category of interest.
     *
     * @param category
     * @return
     */
    public Set<MSProductIdentity> findMSProductsWithGenericNameCategory(String category) {
        return msProductIdentityRowSet.stream()
                .filter(msProductIdentityRow ->
                        msProductIdentityRow.getProductId() != null &&
                                ((msProductIdentityRow.getCategory1() != null && msProductIdentityRow.getCategory1().equalsIgnoreCase(category))
                                        || (msProductIdentityRow.getCategory2() != null && msProductIdentityRow.getCategory2().equalsIgnoreCase(category))
                                        || (msProductIdentityRow.getCategory3() != null && msProductIdentityRow.getCategory3().equalsIgnoreCase(category))
                                        || (msProductIdentityRow.getCategory4() != null && msProductIdentityRow.getCategory4().equalsIgnoreCase(category)))

                )
                .collect(Collectors.toSet());
    }

    private static void addNewProductIdentityRow(Set<MSProductIdentityRow> productIdentityRowsSet, MSGenericNameRow msGenericNameRow) {
        productIdentityRowsSet.add(
                new MSProductIdentityRow.MSProductIdentityRowBuilder()
                        .withId(msGenericNameRow.getId())
                        .withName(msGenericNameRow.getName())
                        .withCategory1(StringUtils.isNotBlank(msGenericNameRow.getCategory1()) ? msGenericNameRow.getCategory1() : null)
                        .withCategory2(StringUtils.isNotBlank(msGenericNameRow.getCategory2()) ? msGenericNameRow.getCategory2() : null)
                        .withCategory3(StringUtils.isNotBlank(msGenericNameRow.getCategory3()) ? msGenericNameRow.getCategory3() : null)
                        .withCategory4(StringUtils.isNotBlank(msGenericNameRow.getCategory4()) ? msGenericNameRow.getCategory4() : null)
                        .build()
        );
    }
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
