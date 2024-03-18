package servelets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class teacherexamset
 */
public class teacherexamset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherexamset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			System.out.println("CONNECTED-----------");
			String sql = "SELECT * FROM questionpaper1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
           
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            // Create an HTML form
            out.println("<html><body><form  action='anservalisation' >");

            // Display data rows
            int i=0;
            while (resultSet.next()) {
                String question = resultSet.getString("QUES");
                String option1 = resultSet.getString("OP1");
                String option2 = resultSet.getString("OP2");
                String option3 = resultSet.getString("OP3");
                String option4 = resultSet.getString("OP4");
                i++;
                String que="question"+i;
                out.println("<fieldset>");
                out.println("<legend>" + question + "</legend>");
                //out.println("<p>" + question + "</p>");
                out.println("<label><input type='radio' name='" + que + "' value='" + option1 + "'>" + option1 + "</label><br>");
                out.println("<label><input type='radio' name='" + que + "' value='" + option2 + "'>" + option2 + "</label><br>");
                out.println("<label><input type='radio' name='" + que + "' value='" + option3 + "'>" + option3 + "</label><br>");
                out.println("<label><input type='radio' name='" + que + "' value='" + option4 + "'>" + option4 + "</label><br>");
                out.println("</fieldset>");
                out.println("quesss"+que);
            }

            out.println("<input type='submit' value='Submit'></form></body></html>");
            
            
            
            // Set the result set as a request attribute
//            request.setAttribute("resultSet", rs);
//            while(rs.next()) {
//            	System.out.println(rs.getString("ques")+"  "+rs.getString("op1"));
//            }
            // Forward the request to the JSP page
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/display.jsp");
//            dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException |SQLException e) {
			System.out.println("connction failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
