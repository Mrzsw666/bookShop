import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mybean.User_Bean;

public class user_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		try{
	    String weburl=request.getParameter("web");  
		User_Bean users_Bean=null;
		users_Bean=(User_Bean)request.getAttribute("users_Bean");
		users_Bean=new User_Bean();
		request.setAttribute("users_Bean",users_Bean);
		request.setCharacterEncoding("utf8");
		Connection con;
		ResultSet rs;
		PreparedStatement sql;
		String dataBase="bookshop";
		String tableName="login";
		String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		sql=con.prepareStatement("SELECT * FROM "+tableName);
		rs=sql.executeQuery();
		rs.last();
		if(rs.getRow()==0){
			users_Bean.setUserName(null);
			System.out.println("no");
		}else{
		String index = rs.getString(2);
		int user_id=Integer.parseInt(index);
		sql=con.prepareStatement("SELECT * FROM "+"user"+" where id="+user_id);
		rs=sql.executeQuery();
		rs.last();
		String userName=rs.getString(2);
		String is=rs.getString(7);
		int isSuper=Integer.parseInt(is);
		users_Bean.setUserName(userName);
		users_Bean.setisSuper(isSuper);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(weburl);
		dispatcher.forward(request,response);
		
	}catch(Exception e){
		String weburl=request.getParameter("web");  
		RequestDispatcher dispatcher=request.getRequestDispatcher(weburl);
		dispatcher.forward(request,response);
	}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}
