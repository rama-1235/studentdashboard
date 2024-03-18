package servelets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class teachervalidation
 */
public class teachervalidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teachervalidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=req.getParameter("n1"),email=req.getParameter("n2"),pass=req.getParameter("n3");
		PrintWriter pw=res.getWriter();
		pw.print(id+"  "+email+pass);
		if(!id.equals("zero")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html");
			dispatcher.forward(req, res);
		}
		else {
			Connection con=null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
				System.out.println("CONNECTED-----------");
				Statement st=con.createStatement();
				String sti="insert into teacherdata values('"+id+"','"+email+"','"+pass+"')";
				int ress=st.executeUpdate(sti);
				if(ress>0) pw.print("sign in suceesfully please login");
				else pw.print("login fail!!!!!");
				
				
			} catch (ClassNotFoundException |SQLException e) {
				System.out.println("connction failed");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
