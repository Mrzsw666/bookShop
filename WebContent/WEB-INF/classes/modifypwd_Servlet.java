import mybean.User_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest; 


public class modifypwd_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf8");
		User_Bean modifypwd_Bean=null;
		modifypwd_Bean=(User_Bean)request.getAttribute("modifypwd_Bean");
		modifypwd_Bean=new User_Bean();
		request.setAttribute("modifypwd_Bean",modifypwd_Bean);
		try{
		String pwd1=request.getParameter("Password1");
		String pwd2=request.getParameter("Password2");
		String pwd3=request.getParameter("Password3");
		if(!pwd2.equals(pwd3)){
			throw new Exception("新密码不一致");
		}
		String dataBase="bookshop";
		String tableName="login";
		String password1=getMD5(pwd1);
		String password2=getMD5(pwd2);
		ResultSet rs;
		Connection con;
		PreparedStatement sql;
	    String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		sql=con.prepareStatement("SELECT * FROM "+tableName);
		rs=sql.executeQuery();
		rs.first();
		String userName=rs.getString(2);
		tableName="user";
		System.out.println(password1);
		System.out.println(password2);
		sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE password='"+password1+"' and username='"+userName+"'");
		rs=sql.executeQuery();
		rs.last();
		int row=rs.getRow();
		if(row==0){
			throw new Exception("原密码错误");
		}else{
		sql=con.prepareStatement("UPDATE user SET password='"+password2+"' where userName='"+userName+"'");
		sql.executeUpdate();
		String idea="密码修改成功";
		modifypwd_Bean.setResult(idea);
		}
		}
		catch(Exception e){
		   System.out.println(e);
		   String idea=e.toString();
		   modifypwd_Bean.setResult(idea);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("modify_pwd.jsp");
			dispatcher.forward(request,response);
		   return ;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("modify_pwd.jsp");
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
