package ch.aaap.ca.be.medicalsupplies;

import java.util.Set;

import ch.aaap.ca.be.medicalsupplies.data.CSVUtil;
import ch.aaap.ca.be.medicalsupplies.data.MSGenericNameRow;
import ch.aaap.ca.be.medicalsupplies.data.MSProductIdentity;
import ch.aaap.ca.be.medicalsupplies.data.MSProductRow;

public class MSApplication {

    private final Set<MSGenericNameRow> genericNames;
    private final Set<MSProductRow> registry;

    public MSApplication() {
        genericNames = CSVUtil.getGenericNames();
        registry = CSVUtil.getRegistry();
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
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /* MS Generic Names */
    /**
     * Method find the number of unique generic names.
     * 
     * @return
     */
    public Object numberOfUniqueGenericNames() {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /**
     * Method finds the number of generic names which are duplicated.
     * 
     * @return
     */
    public Object numberOfDuplicateGenericNames() {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /* MS Products */
    /**
     * Method finds the number of products which have a generic name which can be
     * determined.
     * 
     * @return
     */
    public Object numberOfMSProductsWithGenericName() {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /**
     * Method finds the number of products which have a generic name which can NOT
     * be determined.
     * 
     * @return
     */
    public Object numberOfMSProductsWithoutGenericName() {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /**
     * Method finds the name of the company which is both the producer and license holder for the
     * most number of products.
     * 
     * @return
     */
    public Object nameOfCompanyWhichIsProducerAndLicenseHolderForMostNumberOfMSProducts() {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /**
     * Method finds the number of products whose producer name starts with
     * <i>companyName</i>.
     * 
     * @param companyName
     * @return
     */
    public Object numberOfMSProductsByProducerName(String companyName) {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }

    /**
     * Method finds the products whose generic name has the category of interest.
     * 
     * @param category
     * @return
     */
    public Set<MSProductIdentity> findMSProductsWithGenericNameCategory(String category) {
        throw new MSException(MSException.DEFAULT_MESSAGE);
    }
}
