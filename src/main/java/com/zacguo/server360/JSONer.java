package com.zacguo.server360;

import java.util.HashMap;

import org.quickconnectfamily.json.JSONUtilities;

public class JSONer {
	public HashMap JSON2HashMap(String JSONString) {
		
		HashMap parsedJSONMap = new HashMap();
		
		try {
			JSONUtilities jsonUtil = new JSONUtilities();
			
			parsedJSONMap = (HashMap) jsonUtil.parse(JSONString);
					
		}
		catch (Exception e) {
			System.out.print(e);;
		}
		
		return parsedJSONMap;
	}
	
	public String HashMap2JSON(HashMap aMap) {
		
		String jsonString = "";
		
		try {
			JSONUtilities jsonUtil = new JSONUtilities();
			
			System.out.println("tttt");
			jsonString = jsonUtil.stringify(aMap);	
			System.out.println("tttt");
		}
		catch (Exception e) {
			System.out.print(e);
		}
		
		return jsonString;
	}
}
