/**
 * MSProductRow
 */
package ch.aaap.ca.be.medicalsupplies.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MSProductRow {

    private final String id;
    private final String name;
    private final String genericName;
    private final String primaryCategory;
    private final String producerId;
    private final String producerName;
    private final String producerAddress;
    private final String licenseHolderId;
    private final String licenseHolderName;
    private final String licenseHolderAddress;

    public MSProductRow(String id, String name, String genericName, String primaryCategory, String producerId,
            String producerName, String producerAddress, String licenseHolderId, String licenseHolderName,
            String licenseHolderAddress) {
        super();
        this.id = id;
        this.name = name;
        this.genericName = genericName;
        this.primaryCategory = primaryCategory;
        this.producerId = producerId;
        this.producerName = producerName;
        this.producerAddress = producerAddress;
        this.licenseHolderId = licenseHolderId;
        this.licenseHolderName = licenseHolderName;
        this.licenseHolderAddress = licenseHolderAddress;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public String getProducerId() {
        return producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public String getProducerAddress() {
        return producerAddress;
    }

    public String getLicenseHolderId() {
        return licenseHolderId;
    }

    public String getLicenseHolderName() {
        return licenseHolderName;
    }

    public String getLicenseHolderAddress() {
        return licenseHolderAddress;
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
