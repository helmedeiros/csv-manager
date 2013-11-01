package br.com.helmed.csvmanager.reader.impl;

import br.com.helmed.csvmanager.model.data.GoogleSearchCSVData;
import br.com.helmed.csvmanager.reader.CSVReader;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link CSVGoogleSearchDataReader}
 * User: helmedeiros
 * Date: 11/1/13
 * Time: 12:13 PM
 */
public class CSVGoogleSearchDataReaderTest {
    private static final String FILE_NAME = "/CVSGoogleSearchData.csv";
    private static final int HEADER_SIZE = 1;
    private static final Boolean HAS_FOOTER = Boolean.FALSE;
    private Path resourcePath;
    private int valid_id;
    private CSVReader reader;

    @Before
    public void setUp() throws Exception {
        valid_id = 232955;
        reader = new CSVGoogleSearchDataReader();
        resourcePath = Paths.get(getClass().getResource(FILE_NAME).toURI());
    }

    @Test public void shouldProcessValidRegisters() throws Exception {
        reader.read(resourcePath.toAbsolutePath().toString(), HEADER_SIZE, HAS_FOOTER);

        List<GoogleSearchCSVData> allList = reader.getSuccessList();
        assertNotNull(allList);
        assertEquals(4, allList.size());

        for (GoogleSearchCSVData csvData : allList) {
            assertEquals("file", csvData.getCategory());
            assertEquals(String.valueOf(nextValidIds()), csvData.getDocId());

            System.out.println(csvData.toString());
        }
    }

    @Test public void shouldReturnErrorInValidRegisters() throws Exception {
        reader.read(resourcePath.toAbsolutePath().toString(), HEADER_SIZE, HAS_FOOTER);

        List<String> errList = reader.getErrorList();
        assertNotNull(errList);
        assertEquals(3, errList.size());

        for (String string : errList) {
            assertTrue(string.contains("Error while deserializing"));
            System.out.println(string);
        }

    }

    /** @return The valid id based in a simple logic, the odds are valid. */
    private int nextValidIds() {
        valid_id +=2;
        return valid_id;
    }
}
