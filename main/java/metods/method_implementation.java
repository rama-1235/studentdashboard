package metods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class method_implementation {
	public  Connection getconnection()  
	{
		Connection con=null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
		System.out.println("CONNECTED-----------");
		
		
	} catch (ClassNotFoundException |SQLException e) {
		System.out.println("connction failed");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;

}
	public int insert(String name1,String name2)
	{
		Connection con=getconnection();
		int ress=0;
		try {
			Statement st=con.createStatement();
			String sti="insert into registration values('"+name1+"','"+name2+"')";
			 ress=st.executeUpdate(sti);
			 System.out.println(ress+"  "+name1+"  "+name2);
			//if(ress==1) sy
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ress;
	}
}
