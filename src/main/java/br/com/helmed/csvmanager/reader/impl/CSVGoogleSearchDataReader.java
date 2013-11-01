/**
 * 
 */
package br.com.helmed.csvmanager.reader.impl;

import br.com.helmed.csvmanager.model.data.GoogleSearchCSVData;
import br.com.helmed.csvmanager.reader.CSVGenericReader;

/**
 * This class will use CSVGenericReader, passing 
 * two types for the return.
 * 
 * The first parameter is the type of return for errorList
 * The second parameter is the type of return for successList
 *
 * @author helmedeiros
 */
public class CSVGoogleSearchDataReader extends CSVGenericReader<String, GoogleSearchCSVData> {

}
