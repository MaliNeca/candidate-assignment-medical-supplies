package ch.aaap.ca.be.medicalsupplies.data;

public class MSProductIdentityRow implements MSProductIdentity {

    private final Long id;
    private final String name;
    private final String category1;
    private final String category2;
    private final String category3;
    private final String category4;
    private final String productId;
    private final String genericName;
    private final String primaryCategory;
    private final String producerName;
    private final String licenseHolderName;

    public MSProductIdentityRow(Long id, String name, String category1, String category2, String category3, String category4, String productId, String genericName, String primaryCategory, String producerName, String licenseHolderName) {
        this.id = id;
        this.name = name;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.productId = productId;
        this.genericName = genericName;
        this.primaryCategory = primaryCategory;
        this.producerName = producerName;
        this.licenseHolderName = licenseHolderName;
    }

    public Long getId() {
        return id;
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

    public String getProductId() {
        return productId;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public String getProducerName() {
        return producerName;
    }

    public String getLicenseHolderName() {
        return licenseHolderName;
    }

    @Override
    public String getID() {
        return productId;
    }

    @Override
    public String getName() {
        return name;
    }

    public static class MSProductIdentityRowBuilder {
        private Long id;
        private String name;
        private String category1;
        private String category2;
        private String category3;
        private String category4;
        private String productId;
        private String genericName;
        private String primaryCategory;
        private String producerName;
        private String licenseHolderName;

        public MSProductIdentityRowBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public MSProductIdentityRowBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public MSProductIdentityRowBuilder withCategory1(String category1) {
            this.category1 = category1;
            return this;
        }

        public MSProductIdentityRowBuilder withCategory2(String category2) {
            this.category2 = category2;
            return this;
        }

        public MSProductIdentityRowBuilder withCategory3(String category3) {
            this.category3 = category3;
            return this;
        }

        public MSProductIdentityRowBuilder withCategory4(String category4) {
            this.category4 = category4;
            return this;
        }

        public MSProductIdentityRowBuilder withProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public MSProductIdentityRowBuilder withGenericName(String genericName) {
            this.genericName = genericName;
            return this;
        }

        public MSProductIdentityRowBuilder withPrimaryCategory(String primaryCategory) {
            this.primaryCategory = primaryCategory;
            return this;
        }

        public MSProductIdentityRowBuilder withProducerName(String producerName) {
            this.producerName = producerName;
            return this;
        }

        public MSProductIdentityRowBuilder withLicenseHolderName(String licenseHolderName) {
            this.licenseHolderName = licenseHolderName;
            return this;
        }

        public MSProductIdentityRow build() {
            return new MSProductIdentityRow(
                    id,
                    name,
                    category1,
                    category2,
                    category3,
                    category4,
                    productId,
                    genericName,
                    primaryCategory,
                    producerName,
                    licenseHolderName
            );
        }
    }
}
