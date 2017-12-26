import mybean.User_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest; 


public class register_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf8");
		User_Bean register_Bean=null;
		register_Bean=(User_Bean)request.getAttribute("register_Bean");
		register_Bean=new User_Bean();
		request.setAttribute("register_Bean",register_Bean);
		try{
		String UserName=request.getParameter("UserName");
		String Pwd=request.getParameter("Password");
		String Address=request.getParameter("Address");
		String Email=request.getParameter("Email");
		String P=request.getParameter("Phone");
		String Password=getMD5(Pwd);
		String dataBase="bookshop";
		String tableName="user";
		if(UserName.matches("^[A-Za-z0-9]+$")==false){
		    throw new Exception("用户名只能包含字母或数字");
		}
		if(Email.matches("^[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){0,4}@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){0,4}$")==false){
			throw new Exception("您的邮箱输入有误");
		}
		if(P.matches("^0\\d{2,3}\\d{7,8}$|^1[358]\\d{9}$|^147\\d{8}")==false){
			throw new Exception("你的电话输入有误");
		}
		int isSuper = 0;
		int Phone=Integer.parseInt(P);
		if(UserName==null||UserName.length()==0||Password==null||Password.length()==0||Address==null||Address.length()==0||Email==null||Email.length()==0||P==null||P.length()==0){
			throw new Exception("您输入的信息不完整！");
		}
		String condition="INSERT INTO "+tableName+" VALUES"+"("+UserName+","+Password+","+Email+",'"+Phone+"','"+Address+"','"+null+"','"+isSuper+")";
		Connection con;
	    String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		sql.executeUpdate(condition);
		String idea="注册成功";
		register_Bean.setResult(idea);
		}
		catch(Exception e){
		   System.out.println(e);
		   String idea=e.toString();
		   register_Bean.setResult(idea);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		   return ;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request,response);
		
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
	public static String getMD5(String message) {  
	    String md5str = "";  
	    try {   
	        MessageDigest md = MessageDigest.getInstance("MD5");   
	        byte[] input = message.getBytes();   
	        byte[] buff = md.digest(input);   
	        md5str = bytesToHex(buff);  
	  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return md5str;  
	    }  
	    public static String bytesToHex(byte[] bytes) {  
	    StringBuffer md5str = new StringBuffer();   
	    int digital;  
	    for (int i = 0; i < bytes.length; i++) {  
	        digital = bytes[i];  
	  
	        if (digital < 0) {  
	        digital += 256;  
	        }  
	        if (digital < 16) {  
	        md5str.append("0");  
	        }  
	        md5str.append(Integer.toHexString(digital));  
	    }  
	    return md5str.toString().toUpperCase();  
	    }  
}
