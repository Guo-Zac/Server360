package com.zacguo.server360;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class DataUtils {
	   public static int getMaxCatId(Session session) {
	       String sql = "Select max(e.categoryId) from " + Categories.class.getName() + " e ";
	       Query<Number> query = session.createQuery(sql);
	       Number value = query.getSingleResult();
	       if (value == null) {
	           return 1;
	       }
	       return value.intValue();
	   }
}
