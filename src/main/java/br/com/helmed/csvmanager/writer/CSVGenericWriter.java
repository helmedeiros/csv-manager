package br.com.helmed.csvmanager.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.helmed.csvmanager.model.transaction.ReturnMessage;
import br.com.helmed.csvmanager.util.ReflectionUtils;
import org.apache.log4j.Logger;
import org.jsefa.DeserializationException;
import org.jsefa.Serializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

/**
 * Auxiliary class to CSV writing.
 * 
 * @author helmedeiros
 * @param <K>
 */
public class CSVGenericWriter<T, K> implements CSVWriter<T, K> {

    private static final char CSV_SEPARATOR = ',';

    private static final Logger LOGGER = Logger.getLogger(CSVGenericWriter.class);

    private ThreadLocal<List<T>> csvErrorsList = new ThreadLocal<List<T>>();
    private ThreadLocal<List<K>> csvSuccessList =  new ThreadLocal<List<K>>();

    /**
     * Returns the parameterized <T> class. 
     * @return the parameterized class. 
     */
    private Class<K> getClasse() {
        return ReflectionUtils.getParamType(this);
    }
    
    public CSVGenericWriter() {
        csvErrorsList.set(new ArrayList<T>());
        csvSuccessList.set(new ArrayList<K>());
    }

    @Override
    public void write(String pathFileName, String fileHeader, List<K> dataToWriteList) {
        csvErrorsList.set(new ArrayList<T>());
        csvSuccessList.set(new ArrayList<K>());
        
        Serializer serializer = null;
        try {
            LOGGER.debug("Create a new file on " + pathFileName);
            // Create a new File using the full path parameter
            FileWriter writer = new FileWriter(pathFileName);

            LOGGER.debug("Create a new Serializer with " + this.getClasse() + " class and using: " + CSV_SEPARATOR + " as separator.");
            // Generate a CSVSerializer based on class used as parameter
            serializer = getCsvSerializer((Class<K>) this.getClasse(), CSV_SEPARATOR);

            LOGGER.debug("Insert a header: " + fileHeader);
            // Insert the header first.
            writer.append(fileHeader);

            // Open the writer to populate data.
            serializer.open(writer);

            // Loop the list and write the data 
            for (K obj : dataToWriteList) {
                try {
                    // Try write to file the Object, and add to list of success 
                    serializer.write(obj);
                    this.csvSuccessList.get().add(obj);

                } catch (DeserializationException e) {
                    ReturnMessage rm = new ReturnMessage();
                    rm.setMessage("DeserializationException the message: " + e.getCause() + " - " + e.getLocalizedMessage());
                    LOGGER.debug(rm.getMessage());
                    LOGGER.error(rm.getMessage());
                    this.csvErrorsList.get().add((T) rm.getMessage());

                } catch (Exception e) {
                    ReturnMessage rm = new ReturnMessage();
                    rm.setMessage("Generic Exception on deserialization: " + e.getCause() + " - " + e.getLocalizedMessage());
                    LOGGER.debug(rm.getMessage());
                    LOGGER.error(rm.getMessage());
                    this.csvErrorsList.get().add((T) rm.getMessage());
                }
            }
            // Clean and close the FileWriter
            writer.flush();
            writer.close();

        } catch (IOException e) {
            ReturnMessage rm = new ReturnMessage();
            rm.setMessage("IO Exception on Use FileWriter: " + e.getCause() + " - " + e.getLocalizedMessage());
            LOGGER.debug(rm.getMessage());
            LOGGER.error(rm.getMessage());
            this.csvErrorsList.get().add((T) rm.getMessage());
        } catch (Exception e) {
            ReturnMessage rm = new ReturnMessage();
            rm.setMessage("Generic Exception on Use FileWriter: : " + e.getCause() + " - " + e.getLocalizedMessage());
            LOGGER.debug(rm.getMessage());
            LOGGER.error(rm.getMessage());
            this.csvErrorsList.get().add((T) rm.getMessage());
        } finally {
            if (serializer != null) {
                serializer.close(true);
            }
        }

    }

    /**
     * @param classToSerialize
     * @param fieldDelimiter
     * @return defined Serializer object
     */
    private Serializer getCsvSerializer(Class<?> classToSerialize, char fieldDelimiter) {
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setFieldDelimiter(fieldDelimiter);
        Serializer serializer = CsvIOFactory.createFactory(csvConfiguration, classToSerialize).createSerializer();
        return serializer;
    }

    @Override
    public List<K> getSuccessList() {
        return this.csvSuccessList.get();
    }

    @Override
    public List<T> getErrorList() {
        return this.csvErrorsList.get();
    }

}
