import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class logout_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		try{
		request.setCharacterEncoding("utf8");
		Connection con;
		PreparedStatement sql;
		String dataBase="Device";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		String sqlstr="delete from login;";
		sql=con.prepareStatement(sqlstr);
		sql.executeUpdate();
		RequestDispatcher dispatcher=request.getRequestDispatcher("logout.jsp");
		dispatcher.forward(request,response);
		
	}catch(Exception e){
		RequestDispatcher dispatcher=request.getRequestDispatcher("logout.jsp");
		dispatcher.forward(request,response);
	}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
