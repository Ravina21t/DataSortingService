package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseAndSortUtil {
	
    private static final String ACTIVE = "Active";
	private static final String AVERAGE = "Average";
	private static final String AGE = "Age";
	private static final String NAME = "Name";

	public String parse(String data) {
		if (data.equalsIgnoreCase(""))
		{
			return "";
		}
        JSONParser parser = new JSONParser();
            //Parse the input JSON
            JSONObject jsonObj = new JSONObject();
			try {
				jsonObj = (JSONObject) parser.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
				return "Invalid JSON";
			}
			
            JSONArray itemArray = (JSONArray) jsonObj.get("Items");
            ArrayList <JSONObject> itemList = new ArrayList<JSONObject>();
            
            for (int i = 0; i < itemArray.size(); i++) {
                itemList.add((JSONObject) itemArray.get(i));
            }
            
            //Sort by Name
            ArrayList <JSONObject> itemsSortedByName = itemList;
            Collections.sort(itemsSortedByName, new NameComparator());
            
            //Sort by Name and Age
            ArrayList <JSONObject> itemsSortedByNameAge = itemsSortedByName;
            Collections.sort(itemsSortedByNameAge, new AgeComparator());
            
            //Sort by Name,age and Average
            ArrayList <JSONObject> itemsSortedByNameAgeAverage = itemsSortedByNameAge;
            Collections.sort(itemsSortedByNameAgeAverage, new AverageComparator());
            
            String response=convertToCSVFormat(itemsSortedByNameAgeAverage);
            
            return response;
    }

    // Convert to parsed JSON to a CSV format
    public String convertToCSVFormat( ArrayList <JSONObject> itemsSortedByNameAgeAverage){
        //Add headers to the response
		JSONObject headerObj = itemsSortedByNameAgeAverage.get(0);
		ArrayList<String> list = new ArrayList<String>(headerObj.keySet());
		Collections.reverse(list);
		Set<String> resultSet = new LinkedHashSet<String>(list);
		String response = resultSet.toString().replaceAll("[\\[\\]]","").replaceAll(" ", "");
		//Add values to the response
		for (JSONObject obj: itemsSortedByNameAgeAverage) {
			if(obj.get(NAME)!=null && obj.get(AGE)!=null && obj.get(AVERAGE)!=null && obj.get(ACTIVE)!=null)
			{
			response =  response + "\n" + obj.get(NAME).toString() + "," + obj.get(AGE).toString() + "," + obj.get(AVERAGE).toString() + "," + obj.get(ACTIVE).toString();
			}
			else
			{
				return "invalid JSON";
			}
		}		
		return response;	
    }
}

//Compare name field  
class NameComparator implements Comparator < JSONObject > {
	
	private static final String NAME = "Name";
	
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        String v1 = (String)(o1.get(NAME));
        String v3 = (String)(o2.get(NAME));
        return v1.compareTo(v3);
    }
}

//If name filed of two objects is the same then compare and sort the age field
class AgeComparator implements Comparator < JSONObject > {
	
	private static final String AGE = "Age";
	private static final String NAME = "Name";
	
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        if (o1.get(NAME).equals(o2.get(NAME))) {
            long v1 = (long)(o1.get(AGE));
            long v3 = (long)(o2.get(AGE));
            return (int)(v1 - v3);
        }
        return 0;
    }
}

//If name and age field of two objects are the same then compare and sort average field
class AverageComparator implements Comparator < JSONObject > {
	
	private static final String AGE = "Age";
	private static final String AVERAGE = "Average";
	private static final String NAME = "Name";
	
    @Override
    public int compare(JSONObject o1, JSONObject o2) {
        if (o1.get(NAME).equals(o2.get(NAME)) && (o1.get(AGE).equals(o2.get(AGE)))) {
        	Double v1 = (Double)(o1.get(AVERAGE));
        	Double v3 = (Double)(o2.get(AVERAGE));
            return (int)(v1 - v3);
        }
        return 0;
    }
}

