package ch.aaap.ca.be.medicalsupplies.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import ch.aaap.ca.be.medicalsupplies.MSException;

/**
 * This is a helper class to read the provided CSV
 *
 * You don't have adapt anything within this class!
 */
public final class CSVUtil {

    private static final String MS_GENERIC_NAME_CSV = "/msGenericName.csv";
    private static final String MS_REGISTRY_CSV = "/msRegistry.csv";

    private CSVUtil() {
    }

    public static Set<MSGenericNameRow> getGenericNames() throws MSException {
        try {
            InputStream is = CSVUtil.class.getResourceAsStream(MS_GENERIC_NAME_CSV);
            Reader reader = new InputStreamReader(is);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'));

            Set<MSGenericNameRow> genericNames = new HashSet<>();

            for (final CSVRecord record : parser) {
                // Create a ms generic name row
                MSGenericNameRow currentRow = new MSGenericNameRow(Long.valueOf(record.get("id")), record.get("name"),
                        record.get("category1"), record.get("category2"), record.get("category3"),
                        record.get("category4"));
                genericNames.add(currentRow);
            }
            parser.close();
            return genericNames;

        } catch (IOException e) {
            throw new MSException(MSException.PARSE_GENERIC_NAME_CSV_MESSAGE, e);
        }
    }

    public static Set<MSProductRow> getRegistry() throws MSException {
        try {
            InputStream is = CSVUtil.class.getResourceAsStream(MS_REGISTRY_CSV);
            Reader reader = new InputStreamReader(is);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter('\t'));

            Set<MSProductRow> registry = new HashSet<>();

            for (final CSVRecord record : parser) {
                // Create a ms product row
                MSProductRow currentRow = new MSProductRow(record.get("id"), record.get("name"),
                        record.get("genericName"), record.get("primaryCategory"), record.get("producerId"),
                        record.get("producerName"), record.get("producerAddress"), record.get("licenseHolderId"),
                        record.get("licenseHolderName"), record.get("licenseHolderAddress"));
                registry.add(currentRow);
            }
            parser.close();
            return registry;

        } catch (IOException e) {
            throw new MSException(MSException.PARSE_REGISTRY_CSV_MESSAGE, e);
        }
    }
}
