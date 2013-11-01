/* @(#)AllocationRuleExportData.java
 * 
 * Copyright (c) 2010 Dell Inc.
 * 
 * This software is the confidential and proprietary information of Dell Inc. 
 */
package br.com.helmed.csvmanager.model.data;

import br.com.helmed.csvmanager.reader.CSVFieldsInformer;
import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 * Data Transfer object to import/export AllocationRuleData in CSV format.
 *
 * @author helmedeiros
 */
@CsvDataType()
public class GoogleSearchCSVData implements CSVFieldsInformer {

    @CsvField(pos = 1) private String category;
    
    @CsvField(pos = 2) private String docId;
    
	@CsvField(pos = 3) private String city;

	@CsvField(pos = 4) private int postalCode;

    private int columnNumber;

    private int lineNumber;
    
    
    /**
     * Default constructor
     */
    public GoogleSearchCSVData() {
        
    }
    
    /**
     * @param category
     * @param docId
     * @param city
     * @param postalCode
     */
    public GoogleSearchCSVData(String category, String docId, String city, int postalCode) {
        super();
        this.category = category;
        this.docId = docId;
        this.city = city;
        this.postalCode = postalCode;
    }


    /**
     * Returns the category.
     * @return <code>String</code>
     */
    public String getCategory() {
        return category;
    }

    
    /**
     * The category to set.
     * @param category <code>String</code>
     */
    public void setCategory(String category) {
        this.category = category;
    }

    
    /**
     * Returns the docId.
     * @return <code>String</code>
     */
    public String getDocId() {
        return docId;
    }

    
    /**
     * The docId to set.
     * @param docId <code>String</code>
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    
    /**
     * Returns the city.
     * @return <code>String</code>
     */
    public String getCity() {
        return city;
    }

    
    /**
     * The city to set.
     * @param city <code>String</code>
     */
    public void setCity(String city) {
        this.city = city;
    }

    
    /**
     * Returns the postalCode.
     * @return <code>int</code>
     */
    public int getPostalCode() {
        return postalCode;
    }

    
    /**
     * The postalCode to set.
     * @param postalCode <code>int</code>
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public int getColumnNumber() {
        return this.columnNumber;
    }


    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }


    @Override
    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }


    @Override
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
