package servelets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import oracle.net.aso.a;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class logindetail
 */
public class logindetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logindetail() {
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
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
				System.out.println("CONNECTED-----------");

				Statement st=con.createStatement();
				String query="Select * from registration  where EmailId='"+email+"'";
				ResultSet rs=st.executeQuery(query);
				//pr.println("hello");
				boolean tr=true;
				while(rs.next()) {
					tr=false;
//					if(rs.getString("Password").equals(pass)) pr.print("login successful");
//					else pr.print("invalid password");
					if(rs.getString("PassWord").equals(pass)) {
						pr.print("login succesfull");
						HttpSession session = req.getSession(true);
			            session.setAttribute("userEmail", email);
			            if(!session.equals(null)) System.out.println("contains");
			            else System.out.println("contains null:");
						RequestDispatcher dispatcher = req.getRequestDispatcher("/loginss.html");
				        dispatcher.forward(req, res);
					}
					else pr.print("Invalid password");
				}
				if(tr) {
					pr.println("Invalid email!! please register");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/signin.html");
			        dispatcher.forward(req, res);
				}
				
			} catch (ClassNotFoundException |SQLException e) {
				System.out.println("connction failed");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
