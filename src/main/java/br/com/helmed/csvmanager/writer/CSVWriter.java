package br.com.helmed.csvmanager.writer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Define an CSV writer specialist.
 * 
 * @author helmedeiros
 */
public interface CSVWriter<T, K> {

    /**
     * Write any kind of CSV data and return it's corresponding annotated object .
     * 
     * @param pathFileName
     * @param fileHeader
     * @throws FileNotFoundException
     */
    void write(String pathFileName, String fileHeader, List<K> dataToWriteList) throws IOException;
    
    // The List for rows with success 
    public abstract List<K> getSuccessList();
    
    // The List for error messages on write rows
    public abstract List<T> getErrorList();

}