import mybean.User_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.security.MessageDigest; 


public class login_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf8");
		User_Bean login_Bean=null;
		login_Bean=(User_Bean)request.getAttribute("login_Bean");
		login_Bean=new User_Bean();
		request.setAttribute("login_Bean",login_Bean);
		try{
		String UserName=request.getParameter("UserName");
		String Pwd=request.getParameter("Password");
		System.out.println(UserName);
		System.out.println(Pwd);
		String Password=getMD5(Pwd);
		//String Password=Pwd;
		String dataBase="bookshop";
		String tableName="user";
		if(UserName==null||UserName.length()==0||Pwd==null||Pwd.length()==0){
			throw new Exception("您输入的信息不完整！");
		}
		ResultSet rs;
		Connection con;
		PreparedStatement sql;
	    String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
		con=DriverManager.getConnection(url);
		System.out.println("SELECT * FROM "+tableName+" WHERE username='"+UserName+"' and password='"+Password+"'");
		sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE username='"+UserName+"' and password='"+Password+"'");
		rs=sql.executeQuery();
		rs.last();
		int row=rs.getRow();
		String idea;
		if(row==0){
			throw new Exception("用户名或密码错误");
		}else{
			idea="登录成功";
		
		tableName="login";
		rs.first();
		String is=rs.getString(8);
		int isSuper=Integer.parseInt(is);
		String condition="INSERT INTO "+tableName+" VALUES"+"("+"'"+"0"+"','"+UserName+"','"+isSuper+"',"+null+","+null+","+null+")";
		Statement sqll=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		sqll.executeUpdate(condition);
		login_Bean.setResult(idea);
		}
		}
		catch(Exception e){
		   System.out.println(e);
		   String idea="用户名或密码错误";
		   login_Bean.setResult(idea);
		   RequestDispatcher dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		   return ;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("main.jsp");
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
