package ch.aaap.ca.be.medicalsupplies;

public class MSException extends RuntimeException {
    private static final long serialVersionUID = -1933035576906581416L;

    public static final String DEFAULT_MESSAGE = "Method has not been implemented";
    public static final String PARSE_REGISTRY_CSV_MESSAGE = "Could not parse registry csv";
    public static final String PARSE_GENERIC_NAME_CSV_MESSAGE = "Could not parse generic name csv";

    public MSException(String message) {
        super(message);
    }

    public MSException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
