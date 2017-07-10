package com.zacguo.server360;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DataUtils {
//	   public static int getMaxCatId(Session session) {
//	       String sql = "Select max(e.categoryId) from " + Categories.class.getName() + " e ";
//	       Query<Number> query = session.createQuery(sql);
//	       Number value = query.getSingleResult();
//	       if (value == null) {
//	           return 1;
//	       }
//	       return value.intValue();
//	   }
	
	
	public static int getMaxNoteId(Session session) {
	       String sql = "Select max(e.noteId) from " + NoteLists.class.getName() + " e ";
	       Query<Number> query = session.createQuery(sql);
	       Number value = query.getSingleResult();
	       if (value == null) {
	           return 1;
	       }
	       return value.intValue();
	}
	
	
	public static int getMaxTextId(Session session) {
	       String sql = "Select max(e.textId) from " + NoteTexts.class.getName() + " e ";
	       Query<Number> query = session.createQuery(sql);
	       Number value = query.getSingleResult();
	       if (value == null) {
	           return 1;
	       }
	       return value.intValue();
	}
	
	public static NoteTexts findNoteTextById(Session session, String noteId) {
	       String sql = "Select e from " + NoteTexts.class.getName() + " e "//
	               + " Where e.noteId = " + noteId;
	       Query<NoteTexts> query = session.createQuery(sql);
	       //query.setParameter("noteId", noteId);
	       return query.getSingleResult();
	}
	
	public static NoteLists findNoteListById(Session session, String noteId) {
	       String sql = "Select e from " + NoteLists.class.getName() + " e "//
	               + " Where e.noteId = " + noteId;
	       Query<NoteLists> query = session.createQuery(sql);
	       //query.setParameter("noteId", noteId);
	       return query.getSingleResult();
	}
	
}
