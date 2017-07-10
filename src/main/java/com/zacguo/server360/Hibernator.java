package com.zacguo.server360;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.zacguo.server360.HibernateUtils;

public class Hibernator {
	public HashMap listNotes() {
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		HashMap<String, String> returnHashMap = new HashMap<String,String>();

		System.out.println("in list");
		
		try {
			session.getTransaction().begin();

			String sql = "Select e from " + NoteLists.class.getName() + " e ";

			Query<NoteLists> query = session.createQuery(sql);

			List<NoteLists> notes = query.getResultList();

			//ArrayList<String> aList = new ArrayList<String>();

			for (NoteLists note : notes) {
				//aList.add(note.getNoteName());
				returnHashMap.put(note.getNoteId().toString(), note.getNoteName());
			}

			//returnList = aList;

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return returnHashMap;
	}

	public HashMap<String, String> getNote(int noteId) {

		//String returnString = "";
		HashMap<String, String> returnHashMap = new HashMap<>();
		
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			String sql = "Select e from " + NoteTexts.class.getName() + " e where e.noteId =" + noteId;

			Query<NoteTexts> query = session.createQuery(sql);

			List<NoteTexts> notes = query.getResultList();

			//ArrayList<String> aList = new ArrayList<String>();

			for (NoteTexts note : notes) {
				returnHashMap.put("content", note.getTextContent());
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		Session session2 = factory.getCurrentSession();
		
		try {
			session2.getTransaction().begin();
			
			String sql = "Select e from " + NoteLists.class.getName() + " e where e.noteId =" + noteId;

			Query<NoteLists> query = session2.createQuery(sql);

			List<NoteLists> notes = query.getResultList();

			//ArrayList<String> aList = new ArrayList<String>();

			for (NoteLists note : notes) {
				returnHashMap.put("title", note.getNoteName());
			}

			session2.getTransaction().commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
		}

		return returnHashMap;
	}

	public boolean authenticate(String email, String password) {

		boolean returnBool = false;

		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.getTransaction().begin();

			String sql = "Select e from " + Users.class.getName() + " e where e.email ='" + email + "'";

			Query<Users> query = session.createQuery(sql);

			List<Users> users = query.getResultList();

			System.out.println(users);
			
			ArrayList<String> aList = new ArrayList<String>();

			for (Users user : users) {

				System.out.println(user.getPassword());
				System.out.println(password);
				
				if (user.getPassword().equals(password)) {
					
					returnBool = true;
					
					System.out.println(returnBool);
				}
			}

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return returnBool;
	}

	public boolean insertNote(HashMap noteData) {

		Boolean returnBool = false;

		String title = (String)noteData.get("title");
		String content = (String)noteData.get("content");
		
		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session = factory.getCurrentSession();

		int noteId = 0;
		int textId = 0;
		
		NoteTexts note = null;
		try {
			
			session.getTransaction().begin();
			
			noteId = DataUtils.getMaxNoteId(session) + 1;
			textId = DataUtils.getMaxTextId(session) + 1;
			
			note = new NoteTexts();
			note.setNoteId(noteId);
			note.setTextContent(content);
			note.setTextId(textId);

			session.persist(note);

			session.getTransaction().commit();

			returnBool = true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
			returnBool = false;
		}
		
		Session session2 = factory.getCurrentSession();
		
		try {
			
			session2.getTransaction().begin();
			
			NoteLists noteList = new NoteLists();
			noteList.setNoteId(noteId);
			noteList.setNoteName(title);
			
			session2.persist(noteList);
			
			session2.getTransaction().commit();
			
			returnBool = true;
			
		}catch(Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
			
			returnBool = false;
		}
		
		
		return returnBool;
	}

	public boolean updateNote(String noteId, String noteTitle, String noteText) {

		Boolean returnBool = false;

		SessionFactory factory = HibernateUtils.getSessionFactory();

		Session session1 = factory.getCurrentSession();
		NoteTexts note = null;
		NoteLists noteList = null;
		try {
			session1.getTransaction().begin();

			note = DataUtils.findNoteTextById(session1, noteId);

			session1.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session1.getTransaction().rollback();
		}

		Session session2 = factory.getCurrentSession();

		try {
			session2.getTransaction().begin();
			
			note.setTextContent(noteText);

			session2.update(note);

			session2.flush();

			session2.getTransaction().commit();
			
			returnBool = true;
		} catch (Exception e) {
			e.printStackTrace();
			session2.getTransaction().rollback();
			
			returnBool = false;
		}

		Session session3 = factory.getCurrentSession();
		
		try {
			session3.getTransaction().begin();

			noteList = DataUtils.findNoteListById(session3, noteId);

			session3.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session3.getTransaction().rollback();
		}

		Session session4 = factory.getCurrentSession();

		try {
			session4.getTransaction().begin();
			
			noteList.setNoteName(noteTitle);;

			session4.update(noteList);

			session4.flush();

			session4.getTransaction().commit();
			
			returnBool = true;
		} catch (Exception e) {
			e.printStackTrace();
			session4.getTransaction().rollback();
			
			returnBool = false;
		}
		
		return returnBool;
	}

	public boolean deleteNote(String noteId) {

		Boolean returnBool = false;
		
	       SessionFactory factory = HibernateUtils.getSessionFactory();
	       
	       Session session2 = factory.getCurrentSession();
	       try {
	           session2.getTransaction().begin();
	           
	           String sql = "Delete " + NoteLists.class.getName() + " e " + " where e.noteId =" + noteId;
	           
	           Query query = session2.createQuery(sql);
	           
	           //query.setParameter("noteId", noteId);
	 
	           query.executeUpdate();
	 
	           session2.getTransaction().commit();
	           
	           returnBool = true;
	       } catch (Exception e) {
	           e.printStackTrace();
	           session2.getTransaction().rollback();
	       }
		
		return returnBool;
	}
}
