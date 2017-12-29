import mybean.User_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest; 


public class modifyidea_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf8");
		User_Bean modifyidea_Bean=null;
		modifyidea_Bean=(User_Bean)request.getAttribute("modifyidea_Bean");
		modifyidea_Bean=new User_Bean();
		request.setAttribute("modifyidea_Bean",modifyidea_Bean);
		try{
		String Email=request.getParameter("Email");
		String Ph=request.getParameter("Phone");
		String Address=request.getParameter("Address");
        long Phone=Long.parseLong(Ph);
		String dataBase="bookshop";
		String tableName="login";
		if(Email.matches("[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){0,4}@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+){0,4}$")==false){
			throw new Exception("您的邮箱输入有误");
		}
		if(Ph.matches("0\\d{2,3}\\d{7,8}$|^1[358]\\d{9}$|^147\\d{8}")==false){
			throw new Exception("你的电话输入有误");
		}
		ResultSet rs;
		Connection con;
		PreparedStatement sql;
	    String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		sql=con.prepareStatement("SELECT * FROM "+tableName);
		rs=sql.executeQuery();
		rs.first();
		String userName=rs.getString(2);
		sql=con.prepareStatement("UPDATE user SET email='"+Email+"',"+"phone="+Phone+","+"address='"+Address+"' where userName='"+userName+"'");
		sql.executeUpdate();
		String idea="修改成功";
		modifyidea_Bean.setResult(idea);
		}
		catch(Exception e){
		   System.out.println(e);
		   String idea=e.toString();
		   modifyidea_Bean.setResult(idea);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("modify_idea.jsp");
			dispatcher.forward(request,response);
		   return ;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("modify_idea.jsp");
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
