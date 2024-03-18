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
 * Servlet implementation class quesintodatabase
 */
public class quesintodatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quesintodatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=null;
		PrintWriter pr=res.getWriter();
		String qn=req.getParameter("n1"),qus=req.getParameter("n2"),opt1=req.getParameter("n3"),
				opt2=req.getParameter("n4"),opt3=req.getParameter("n5"),opt4=req.getParameter("n6"),
						ans=req.getParameter("n7")
				;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			System.out.println("CONNECTED-----------");

			Statement st=con.createStatement();
			String query="insert into questionpaper1 values('"+qn+"','"+qus+"','"+opt1+"','"+opt2+"','"+opt3+"','"+opt4+"','"+ans+"')";
			int ins=st.executeUpdate(query);
			if(ins>0) {
				System.out.println("insert sucess");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/exampaper.html");
		        dispatcher.forward(req, res);
			}
			else {
				pr.print("insert fail");
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
