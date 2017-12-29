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
		String weburl=request.getParameter("web");
		System.out.println(weburl);
		System.out.println("1");
		Connection con;
		PreparedStatement sql;
		String dataBase="bookshop";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		System.out.println("2");
		String sqlstr="delete from login;";
		System.out.println("3");
		sql=con.prepareStatement(sqlstr);
		sql.executeUpdate();
		RequestDispatcher dispatcher=request.getRequestDispatcher(weburl);
		dispatcher.forward(request,response);
	}catch(Exception e){
		String weburl=request.getParameter("web");  
		System.out.println(e.toString());
		RequestDispatcher dispatcher=request.getRequestDispatcher(weburl);
		dispatcher.forward(request,response);
	}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
