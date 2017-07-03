package com.zacguo.server360;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.quickconnectfamily.json.*;

public class Controller {
	public String action(String postedString) {
		
		HashMap dataMap = JSON2HashMap(postedString);
		
		System.out.println(dataMap);
		
		String falseString = "{\"result\":\"false\"}";
		
		HashMap authMap = (HashMap)dataMap.get("auth");
		if(authenticate((String)authMap.get("email"), (String)authMap.get("password"))) {
			switch((String)dataMap.get("request")) {
			case "list":
				
				ArrayList aList = listNotes();
				
				System.out.println(aList);
				
				if(aList.size() > 0) {
					HashMap returnMap = new HashMap();
					returnMap.put("result", "success");
					returnMap.put("data", aList);
					
					System.out.println("tttt");
					
					return HashMap2JSON(returnMap);
				}
				break;
			case "read":
				String text = getNote((int)dataMap.get("data"));
				if(text.length()>0) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");
					returnMap.put("data", text);
					
					return HashMap2JSON(returnMap);
				}

				break;
			case "create":
				
				boolean result = insertNote((String)dataMap.get("data"));
				if(result) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return HashMap2JSON(returnMap);
				}
				
				break;
			case "update":
				
				HashMap innerDataMap = (HashMap)dataMap.get("data");
				
				boolean updateResult = updateNote((int)innerDataMap.get("id"), (String)innerDataMap.get("text"));
				if(updateResult) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return HashMap2JSON(returnMap);
				}
				break;
			case "delete":
				
				boolean deleteResult = deleteNote((int)dataMap.get("data"));
				if(deleteResult) {
					HashMap <String, String> returnMap = new HashMap<String, String>();
					returnMap.put("result", "success");					
					return HashMap2JSON(returnMap);
				}
				break;
			}

		}
		else {
			return falseString;
		}
		
		return falseString;
	}
	
	private HashMap JSON2HashMap(String JSONString) {
		
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
	
	private String HashMap2JSON(HashMap aMap) {
		
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
	
	private ArrayList listNotes() {
	   	 SessionFactory factory = HibernateUtils.getSessionFactory();
		 
	     Session session = factory.getCurrentSession();

	     try {
	                  
	         session.getTransaction().begin();

	         String sql = "Select e from " + NoteLists.class.getName() + " e ";

	         // Create Query object.
	         Query<NoteLists> query = session.createQuery(sql);
	         
	         // Execute query.
	         List<NoteLists> notes = query.getResultList();

	        // System.out.println(notes);
	         
	         ArrayList aList = new ArrayList();
	         
	         for(NoteLists note : notes) {
	        	 aList.add(note.getNoteName());
	         }
	         
	         return aList;
	         
//	         for (Categories cat : categories) {
//	             System.out.println("Category: " + cat.getCategoryId() + " : " + cat.getCategoryName());
//	         }
//
//	         // Commit data.
	         //session.getTransaction().commit();
	     } catch (Exception e) {
	         e.printStackTrace();
	         // Rollback in case of an error occurred.
	         session.getTransaction().rollback();
	     }
	     
	     return new ArrayList();
	}
	
	private String getNote(int noteId) {
		
		
		return "";
	}
	
	private boolean authenticate(String email, String password) {
		
		return true;
	}
	
	private boolean insertNote(String noteText) {
		
		return true;
	}
	
	private boolean updateNote(int noteId, String noteText) {
		
		return true;
	}
	
	private boolean deleteNote(int noteId) {
		
		return true;
	}
}
