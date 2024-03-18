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
 * Servlet implementation class teacherloginvalidation
 */
public class teacherloginvalidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherloginvalidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email=req.getParameter("n1"),pass=req.getParameter("n2");
		//String email="tej@gmail.com",pass="1234";
		PrintWriter pr=res.getWriter();
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			System.out.println("CONNECTED-----------");

			Statement st=con.createStatement();
			String query="Select * from teacherdata  where EMAIL='"+email+"'";
			ResultSet rs=st.executeQuery(query);
			//pr.println("hello");
			boolean tr=true;
			while(rs.next()) {
				tr=false;
//				if(rs.getString("Password").equals(pass)) pr.print("login successful");
//				else pr.print("invalid password");
				if(rs.getString("PassWord").equals(pass)) {
					pr.print("login succesfull");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/teacherloginsucess.html");
			        dispatcher.forward(req, res);
				}
				else pr.print("Invalid password");
			}
			if(tr) {
				pr.println("Invalid email!! please register");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/teachersignin.html");
		        dispatcher.forward(req, res);
			}
			
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
