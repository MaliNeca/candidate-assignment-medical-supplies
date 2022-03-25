package ch.aaap.ca.be.medicalsupplies.data;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MSGenericNameRow {

    private final Long id;
    private final String name;
    private final String category1;
    private final String category2;
    private final String category3;
    private final String category4;

    public MSGenericNameRow(Long id, String name, String category1, String category2, String category3,
            String category4) {
        this.id = id;
        this.name = name;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }

    public String getCategory4() {
        return category4;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
