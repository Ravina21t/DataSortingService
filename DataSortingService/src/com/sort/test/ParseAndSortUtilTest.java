package com.sort.test;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.sort.ParseAndSortUtil;

import static org.junit.Assert.*;

import java.util.ArrayList;


public class ParseAndSortUtilTest {
	
    @Test
    public void testParseAndSortFunction() {
    	
    	String testInputJSON = "{\"Items\": [{\"Name\": \"Alice\",\"Age\": 32,\"Average\": 14.998,\"Active\": true},{\"Name\": \"Frank\",\"Age\": 27,\"Average\": 6.333,\"Active\": false},{\"Name\": \"Frank\",\"Age\": 63,\"Average\": 1.009,\"Active\": false},{\"Name\": \"Beth\",\"Age\": 32,\"Average\": 5.998,\"Active\": true}]}";
    	String expectedOutputJSON =  "Name,Age,Average,Active\nAlice,32,14.998,true\nBeth,32,5.998,true\nFrank,27,6.333,false\nFrank,63,1.009,false";
    	ParseAndSortUtil sortObj = new ParseAndSortUtil();
    	assertEquals(expectedOutputJSON,sortObj.parse(testInputJSON));	
    }
    
    @Test
    public void testForEmptyInput() {
    	
    	String testInputJSON = "";
    	String expectedOutputJSON =  "";
    	ParseAndSortUtil sortObj = new ParseAndSortUtil();
    	assertEquals(expectedOutputJSON,sortObj.parse(testInputJSON));	
    }
    
    @Test
    public void testConvertToCSVFunction() {
    	String expectedOutputJSON =  "Name,Age,Average,Active\nPam,15,10.0,true\nJim,32,14.998,true";
    	ArrayList <JSONObject> testInputList = new ArrayList <JSONObject>();
    	JSONObject itemObj1 = new JSONObject();
    	itemObj1.put("Name","Pam");
    	itemObj1.put("Age",15);
    	itemObj1.put("Average",10.0);
    	itemObj1.put("Active", true);
    	testInputList.add(itemObj1);
    	JSONObject itemObj2 = new JSONObject();
    	itemObj2.put("Name","Jim");
    	itemObj2.put("Age",32);
    	itemObj2.put("Average",14.998);
    	itemObj2.put("Active", true);
    	testInputList.add(itemObj2);
    	ParseAndSortUtil sortObj = new ParseAndSortUtil();
    	assertEquals(expectedOutputJSON,sortObj.convertToCSVFormat(testInputList));	
    }
}
