package test.java.data_helpers;

import java.util.HashMap;
import java.util.List;

import cucumber.api.DataTable;

public class DataMapping {
	
	// Maps the cucumber @table from the feature into a HashMap where each element has:
	// key = property to verify
	// value = expected value of property
	public static HashMap<String, String> getExpectedPropertyData(DataTable table){
    	HashMap<String,String> result = new HashMap<String, String>();
    	List<List<String>> data = table.raw();
    	
    	for (List<String> property : data ) {
    		try {
    			String key = property.get(0);
    			String value = property.get(1);
    			
    			result.put(key, value);
    		}
    		catch(IndexOutOfBoundsException exception) { /*simply ignore this data row and continue*/ } 
    	}
    	return result;
    }
}
