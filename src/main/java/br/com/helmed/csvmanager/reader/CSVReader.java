/**
 * 
 */
package br.com.helmed.csvmanager.reader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Define an CSV reading specialist.
 * 
 * @author helmedeiros
 */
public interface CSVReader<T, K> {

	/**
	 * Read any kind of CSV data and return it's corresponding object.
	 *  
	 * @param fileNameToLoad - the full path file to be read.
	 * @param headerSize - the size of the header to skip, i. e. number of lines
	 * @param hasFooter - true, if there is a footer to skip; false otherwise 
	 * @return
	 * @throws java.io.FileNotFoundException
	 */
	public abstract void read(String fileNameToLoad, int headerSize, Boolean hasFooter)
			throws FileNotFoundException;
	
	public abstract List<K> getSuccessList();
	
	public abstract List<T> getErrorList();
}