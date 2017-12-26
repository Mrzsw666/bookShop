import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class purchase_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		book_Bean purchase_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("purchase.jsp");
		try{
			purchase_Bean=(book_Bean)request.getAttribute("purchase_Bean");
			if(purchase_Bean==null){
				purchase_Bean=new book_Bean();
				request.setAttribute("purchase_Bean",purchase_Bean);
			}
		}
		catch(Exception exp){
			purchase_Bean=new book_Bean();
			request.setAttribute("purchase_Bean",purchase_Bean);
		}
		request.setCharacterEncoding("gb2312");
		String dataBase="bookshop";
		String tableName="book";
		String tableNameA="order";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String at=request.getParameter("amount");
		String ct=request.getParameter("cost");
		String ix=request.getParameter("index");
		int a=Integer.parseInt(ix);
		float c=Float.parseFloat(ct);
		int b=Integer.parseInt(at);
		if(at==null||at.length()==0){
			purchase_Bean.setResult("购买数量不得小于0！");
			dispatcher.forward(request,response);
			return;
		}
	
		String condition="SELECT*FROM "+tableName+" WHERE index = "+a;
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery(condition);
			rs.first();
			String amounto=rs.getString(8);
			int na=Integer.parseInt(amounto);
			if(b>na){
				purchase_Bean.setResult("库存不足，购买失败！");
				dispatcher.forward(request,response);
				return;
			}
			else{
				na=na-b;
				float tc=b*c;
				sql.executeUpdate("UPDATE "+tableName+" SET amount = "+na+" WHERE user = "+"'"+ur+"'"+" and index ="+c);
				sql.executeUpdate("INSERT INTO "+tableNameA+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+tc+","+b+")");}
			con.close();
			purchase_Bean.setResult("购买成功！");
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			purchase_Bean.setResult("购买失败！");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
