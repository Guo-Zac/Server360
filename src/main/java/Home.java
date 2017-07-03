import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class Home extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>Home</h1>");
      out.println("<h2>Please put your name here, so we can welcome you.</h2>");
      out.println("<form method='post' action='Alink'>");
      out.println("Name <input type='text' name='user' required>");
      out.println("<input type='submit' value='submit'>");
      out.println("</form>");
  }
}
