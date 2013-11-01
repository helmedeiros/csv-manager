package br.com.helmed.csvmanager.writer.impl;

import br.com.helmed.csvmanager.model.data.GoogleSearchCSVData;
import br.com.helmed.csvmanager.writer.CSVGenericWriter;

/**
 * This class will use CSVGenericWriter, passing 
 * two types for the return.
 * 
 * The first parameter is the type of return for errorList
 * The second parameter is the type of return for successList
 *
 * @author helmedeiros
 */

public class CSVGoogleSearchDataWriter extends CSVGenericWriter<String, GoogleSearchCSVData> {

}
