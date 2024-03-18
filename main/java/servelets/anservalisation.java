package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class anservalisation
 */
public class anservalisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public anservalisation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=null;
		int score=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
			System.out.println("CONNECTED-----------");
			String sql = "SELECT * FROM questionpaper1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
            	String queno=resultSet.getString("QUESNO");
            	String answer=request.getParameter(queno);
        		System.out.println(resultSet.getString("ANSWER"));
        		if(resultSet.getString("ANSWER").equals(answer)) {
        			score++;
        			System.out.println(answer);
        		}
            	//ResultSet irs=stmt.executeQuery("select * from questionpaper1 where QUESNO='"+ queno+"'");
//            	while(irs.next()) {
//            		System.out.println(irs.getString("ANSWER")+"  "+queno+"  "+answer);
//            		if(irs.getString("ANSWER").equals(answer)) {
//            			score++;
//            			System.out.println(answer);
//            		}
//            	}
            }
            HttpSession session = request.getSession(false);
            
            if (session != null) {
                String userEmail = (String) session.getAttribute("userEmail");
                PreparedStatement stm = conn.prepareStatement(sql);
                String qu="insert into markdet values('"+userEmail+"','"+score +"')";
                int inss=stm.executeUpdate(qu);
                if(inss>0) System.out.println("insertion success");
            } else {
                // Redirect back to login page with an error message
                System.out.println("insertion fail");
            }
            PrintWriter prr=response.getWriter();
            prr.print(score);
		}
		catch(Exception e) {
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
