package br.com.helmed.csvmanager.writer.impl;

import br.com.helmed.csvmanager.model.data.GoogleSearchCSVData;
import br.com.helmed.csvmanager.writer.CSVWriter;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * .
 * User: helmedeiros
 * Date: 11/1/13
 * Time: 1:48 PM
 */
public class CSVGoogleSearchDataWriterTest {

    private CSVWriter allocationRuleDataCVSWriter;
    private static final char CSV_SEPARATOR = ',';
    private static final String UPLOAD_FOLDER = "/upload";
    final String fileName = getFileName("FR");

    @Test public void testOKWriting() throws Exception {

        String pathFileName = getFilePath();
        allocationRuleDataCVSWriter = new CSVGoogleSearchDataWriter();
        List<GoogleSearchCSVData> allocationRuleDataList = new ArrayList<GoogleSearchCSVData>();

        GoogleSearchCSVData csvData = new GoogleSearchCSVData();
        csvData.setCategory("FR");
        csvData.setDocId("123");
        csvData.setCity("Paris");
        csvData.setPostalCode(76);

        allocationRuleDataCVSWriter.write(pathFileName, getFileHeader(CSV_SEPARATOR), allocationRuleDataList);

        assertNotNull(allocationRuleDataCVSWriter.getSuccessList());
        assertTrue(allocationRuleDataCVSWriter.getErrorList().isEmpty());

        System.out.println(pathFileName);
    }

    private String getFilePath() throws URISyntaxException {
        return String.valueOf(Paths.get(getClass().getResource("/").toURI()))+"/"+getFileName("FR");
    }

    private String getFileName(String isoCountryCode){
        StringBuilder fullName = new StringBuilder();

        String fileName = "Google-search.csv";
        String DATE_FORMAT = "yyyy-MM-dd_HH.mm.ss.SSS-a";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c1 = Calendar.getInstance();

        fullName.append(sdf.format(c1.getTime())).append("_").
                append(isoCountryCode).append("_").
                append(fileName);

        return fullName.toString();
    }

    /**
     * Make a single row of header with the columns names. Each column is a allocation information.
     */
    private String getFileHeader(char csvSeparator){
        StringBuilder fileHeader = new StringBuilder();
        fileHeader.append("CATEGORY").append(csvSeparator).
                append("DOC_ID").append(csvSeparator).
                append("CITY").append(csvSeparator).
                append("POSTAL_CODE").append(csvSeparator).
                append("\n");

        return fileHeader.toString();
    }
}
