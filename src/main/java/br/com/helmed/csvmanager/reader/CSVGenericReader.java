package br.com.helmed.csvmanager.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import br.com.helmed.csvmanager.model.transaction.ReturnMessage;
import br.com.helmed.csvmanager.util.ReflectionUtils;
import org.apache.log4j.Logger;
import org.jsefa.DeserializationException;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

/**
 * Auxiliary class to CSV reading.
 *
 * @author helmedeiros
 * @param <K>
 */
public class CSVGenericReader<T, K extends CSVFieldsInformer> implements CSVReader<T, K> {
	
	private static final char CSV_SEPARATOR = ',';
	private static final Logger LOGGER = Logger.getLogger(CSVGenericReader.class);
	
	private ThreadLocal<List<T>> csvErrorsList = new ThreadLocal<List<T>>();
	private ThreadLocal<List<K>> csvSuccessList =  new ThreadLocal<List<K>>();

	/**
	 * Returns the parameterized <T> class. 
	 * @return the parameterized class. 
	 */
	private Class<K> getClasse() {
		return ReflectionUtils.getParamType(this);
	}
	
	public CSVGenericReader() {
	    csvErrorsList.set(new ArrayList<T>());
	    csvSuccessList.set(new ArrayList<K>());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void read(String fileNameToLoad, int headerSize, Boolean hasFooter) throws FileNotFoundException {
        csvErrorsList.set(new ArrayList<T>());
        csvSuccessList.set(new ArrayList<K>());

        LOGGER.debug("Create a new fileReader by path: " + fileNameToLoad);
		FileReader reader = new FileReader(fileNameToLoad);
		LOGGER.debug("Create a Deserializer using "+ this.getClasse() + " class, separator: " + CSV_SEPARATOR 
		        + " header size is: " + headerSize + " and hasFooter is:" + hasFooter.toString());
		Deserializer deserializer = getCsvDeserializer((Class<K>) this.getClasse(), CSV_SEPARATOR, headerSize);
		
		try{
			deserializer.open(reader);
			while (deserializer.hasNext()) {
			    try{
    				K rowData = (K)deserializer.next();
    				rowData.setLineNumber(deserializer.getInputPosition().getLineNumber());
    				rowData.setColumnNumber(deserializer.getInputPosition().getColumnNumber());
    				this.csvSuccessList.get().add((K) rowData);
			    
			    }catch (DeserializationException e) {
			        ReturnMessage rm = new ReturnMessage();
			        rm.setMessage("DeserializationException the message: " + e.getCause()+ " - "+e.getLocalizedMessage());
			        LOGGER.debug(rm.getMessage());
			        LOGGER.error(rm.getMessage());
			        this.csvErrorsList.get().add((T) rm.getMessage());
			        
			    }catch (Exception e){
			        ReturnMessage rm = new ReturnMessage();
			        rm.setMessage("Generic Exception on deserialization: " + e.getCause()+ " - "+e.getLocalizedMessage());
			        LOGGER.debug(rm.getMessage());
			        LOGGER.error(rm.getMessage());
			        this.csvErrorsList.get().add((T) rm.getMessage());
			    }
			}
			
		}
		finally{
			deserializer.close(true);
		}
		  
	}
	
    /**
	 *
	 * @param classToDeserialize
	 * @param fieldDelimiter
	 * @param headerSize
	 * @return
	 */
	private Deserializer getCsvDeserializer(Class<?> classToDeserialize, char fieldDelimiter, int headerSize){
        CsvConfiguration csvConfiguration = new CsvConfiguration();  
        
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(headerSize, false, true));
        
        csvConfiguration.setFieldDelimiter(fieldDelimiter);
        Deserializer deserializer = CsvIOFactory.createFactory(
                csvConfiguration,classToDeserialize).createDeserializer();
        return deserializer;
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
