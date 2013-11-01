/**
 * 
 */
package br.com.helmed.csvmanager.reader;

/**
 * Contract to objects that need its fields information after a csv import.
 * @author helmedeiros
 */
public interface CSVFieldsInformer {
	
	/**
	 * Set the line number where the object was read.
	 * @param lineNumber - line number to be set
	 */
	public void setLineNumber(int lineNumber);
	
	/**
	 * Get the line number where the object was read.
	 * @return
	 */
	public int getLineNumber();
	
	/**
	 * Set the column number where the object was read.
	 * @param columnNumber - column number to be set
	 */
	public void setColumnNumber(int columnNumber);
	
	/**
	 * Get the column number where the object was read.
	 * @return
	 */
	public int getColumnNumber();

}
