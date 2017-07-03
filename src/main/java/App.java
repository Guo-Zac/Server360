import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
    	Scanner reader = new Scanner(System.in);
    	
    	while(true){
    		outputData();
    		System.out.print("Add Category: ");
    		
    	//	System.out.println("Please input a website address include the protocol like https://google.com");
    		String input = reader.nextLine();
    		insertData(input);
    	}

    }
    
    private static void insertData(String categoryName){
    	  SessionFactory factory = HibernateUtils.getSessionFactory();
    	  
          Session session = factory.getCurrentSession();
          
          Categories cat = null;
          try {
              session.getTransaction().begin();
              
              // Create transient object
              cat = new Categories();
              cat.setCategoryName(categoryName);
              cat.setCategoryId(DataUtils.getMaxCatId(session) + 1);

              // Using persist(..)
              // Now 'cat' is managed by Hibernate.
              // it has Persistent status.
              // No action at this time with DB.
              session.persist(cat);

              // At this step the data is pushed to the DB.
              // Execute Insert statement.
              session.getTransaction().commit();
          } catch (Exception e) {
              e.printStackTrace();
              session.getTransaction().rollback();
          }
    }
    
    private static void outputData(){
   	 SessionFactory factory = HibernateUtils.getSessionFactory();
	 
     Session session = factory.getCurrentSession();

     try {
          
         // All the action with DB via Hibernate
         // must be located in one transaction.
         // Start Transaction.            
         session.getTransaction().begin();

  
          
         // Create an HQL statement, query the object.
         // Equivalent to the SQL statement:
         // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
         String sql = "Select e from " + Categories.class.getName() + " e ";

 
         // Create Query object.
         Query<Categories> query = session.createQuery(sql);

  
         // Execute query.
         List<Categories> categories = query.getResultList();

         
         
         for (Categories cat : categories) {
             System.out.println("Category: " + cat.getCategoryId() + " : " + cat.getCategoryName());
         }

         // Commit data.
         session.getTransaction().commit();
     } catch (Exception e) {
         e.printStackTrace();
         // Rollback in case of an error occurred.
         session.getTransaction().rollback();
     }
    }
}
