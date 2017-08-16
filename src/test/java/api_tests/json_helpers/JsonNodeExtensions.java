package test.java.api_tests.json_helpers;

import java.util.HashMap;

import org.codehaus.jackson.JsonNode;

public class JsonNodeExtensions {
	
	// recursive function that traverses the JSON tree starting at given @node using the provided @path and returns the value of the last element as a String
	public static String getStringValueFromPath(JsonNode node, String path) {
    	int nextPeriod = path.indexOf('.');
    	if(nextPeriod < 0) return node.path(path).asText();
    	
    	node = node.path(path.substring(0, nextPeriod));
    	path = path.substring(nextPeriod + 1);
    	
    	return getStringValueFromPath(node, path);
    }
	
	// returns true IFF the given @node contains ALL the expected property values specified in @properties, otherwise returns false
    // discards the first (parent) element from the property key specified in @properties - e.g. item.volumeInfo.Title -> volumeInfo.Title
	public static boolean matchesAllItemProperties(JsonNode node, HashMap<String, String> properties) {
    	boolean result = true;
    	for (String key : properties.keySet()) {
    		String expected = properties.get(key);
    		
    		String propertyPath = key.substring(key.indexOf('.') + 1);
    		String actual = JsonNodeExtensions.getStringValueFromPath(node, propertyPath);
    		
    		result &= expected.equals(actual);
    		if (!result) break;
    	}
    	
    	return result;
    }
}
