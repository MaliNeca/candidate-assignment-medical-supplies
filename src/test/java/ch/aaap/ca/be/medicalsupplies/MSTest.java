package ch.aaap.ca.be.medicalsupplies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MSTest {

    /* The system under test */
    MSApplication sut = new MSApplication();

    @Test
    public void returnsCorrectNumberOfUniqueGenericNames() {
        assertEquals("Number of unique generic names", 7255, sut.numberOfUniqueGenericNames());
    }

    @Test
    public void returnsCorrectNumberOfDuplicateGenericNames() {
        assertEquals("Number of duplicate generic names", 12, sut.numberOfDuplicateGenericNames());
    }

    @Test
    public void returnsCorrectNumberOfMSProductsWithGenericName() {
        assertEquals("Number of products with generic name", 36966, sut.numberOfMSProductsWithGenericName());
    }

    @Test
    public void returnsCorrectNumberOfMSProductsWithoutGenericName() {
        assertEquals("Number of products without generic name", 3638, sut.numberOfMSProductsWithoutGenericName());
    }

    @Test
    public void returnsCorrectCompanyNameForMostNumberOfMSProducts() {
        assertEquals("Company name that is both licence holder and producer for most number of products", "NEOMEDICA DOO NIŠ",
                sut.nameOfCompanyWhichIsProducerAndLicenseHolderForMostNumberOfMSProducts());
    }

    @Test
    public void returnsCorrectNumberOfMSProductsByCompanyName() {
        assertEquals("Number products by company name", 1124, sut.numberOfMSProductsByProducerName("roche"));
    }

    @Test
    public void returnsCorrectNumberOfMSProductsByGenericNameCategory() {
        assertEquals("Number of products whose generic name has a category", 2671,
                sut.findMSProductsWithGenericNameCategory("05 - Bolnička, aparaturna oprema").size());
    }
}
