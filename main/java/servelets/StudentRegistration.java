package servelets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metods.method_implementation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Servlet implementation class StudentRegistration
 */
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		method_implementation m=new method_implementation();
		int con=m.insert(req.getParameter("n1"),req.getParameter("n2"));
		PrintWriter pr=res.getWriter();
		if(con>0) pr.print("Account Created successfully");
		else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/login.html");
	        dispatcher.forward(req, res);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
	}

}
