package com.zacguo.server360;

import java.util.HashMap;
import org.quickconnectfamily.json.*;

public class Controller {
	public String action(String postedString) {
		
		String jsonString = "";
		
		
		
		return jsonString;
	}
	
	private HashMap JSONRead(String JSONString) {
		
		JSONUtilities jsonUtil = new JSONUtilities();
		
		HashMap parsedJSONMap = (HashMap) jsonUtil.parse(JSONString);
				
		return parsedJSONMap；
	}
}
