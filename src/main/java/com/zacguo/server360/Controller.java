package com.zacguo.server360;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.quickconnectfamily.json.*;

public class Controller {
	@SuppressWarnings("unchecked")
	public String action(String postedString) {
		
		JSONer json = new JSONer();
		Hibernator hibe = new Hibernator();
		
		HashMap dataMap = json.JSON2HashMap(postedString);
		
		System.out.println("in controller");
		
		System.out.println(dataMap);
		
		String falseString = "{\"result\":\"false\"}";
		
		HashMap<String, String> authMap = (HashMap<String, String>)dataMap.get("auth");
		
		System.out.println((String)authMap.get("email"));
		System.out.println((String)authMap.get("password"));
		
		if(hibe.authenticate((String)authMap.get("email"), (String)authMap.get("password"))) {
			
			System.out.println("authenticated");
			
			switch((String)dataMap.get("request")) {
			case "list":
				
				HashMap<String, String> aHashMap = hibe.listNotes();
				
				//System.out.println(aList);
				
				if(aHashMap.size() > 0) {
					HashMap returnMap = new HashMap();
					returnMap.put("result", "success");
					returnMap.put("data", aHashMap);
					
					System.out.println("tttt");
					
					return json.HashMap2JSON(returnMap);
				}
				break;
			case "read":
				HashMap<String, String> aMap = hibe.getNote(Integer.parseInt((String)dataMap.get("data")));
				if(aMap.size()>0) {
					HashMap <String, Object> returnMap = new HashMap<String, Object>();
					returnMap.put("result", "success");
					returnMap.put("data", aMap);
					
					return json.HashMap2JSON(returnMap);
				}

				break;
			case "create":
				
				boolean result = hibe.insertNote((HashMap)dataMap.get("data"));
				if(result) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return json.HashMap2JSON(returnMap);
				}
				
				break;
			case "update":
				
				HashMap innerDataMap = (HashMap)dataMap.get("data");
				
				boolean updateResult = hibe.updateNote((String)innerDataMap.get("noteId"), (String)innerDataMap.get("title"), (String)innerDataMap.get("content"));
				if(updateResult) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return json.HashMap2JSON(returnMap);
				}
				break;
			case "delete":
				
				boolean deleteResult = hibe.deleteNote((String)dataMap.get("data"));
				if(deleteResult) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return json.HashMap2JSON(returnMap);
				}
				break;
			}

		}
		else {
			return falseString;
		}
		return falseString;
	}
}
