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
		System.out.println("damn");
		book_Bean purchase_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("introduce.jsp");
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
		request.setCharacterEncoding("utf-8");
		String dataBase="bookshop";
		String tableName="book";
		String tableNameA="bill";
		String tableNameB="user";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String at=request.getParameter("amount");
		String ct=request.getParameter("cost");
		String ix=request.getParameter("index");
		System.out.println(ur);
		int id=0;
		long a=Long.parseLong(ix);
		float c=Float.parseFloat(ct);
		int b=Integer.parseInt(at);
		if(at==null||at.length()==0){
			return;
		}
	
		String condition="SELECT*FROM "+tableName+" WHERE bookkey= "+a;
		String conditionA="SELECT*FROM "+tableNameB+" WHERE username= "+"'"+ur+"'";
		Connection con;
		System.out.println(condition);
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery(condition);
			rs.first();
			String amounto=rs.getString(8);
			int na=Integer.parseInt(amounto);
			if(b>na){
				purchase_Bean.setResult("库存不足");
				dispatcher.forward(request,response);
				return;
			}
			else{
				System.out.println(conditionA);
				ResultSet rsA=sql.executeQuery(conditionA);
				System.out.println("/..");
				rsA.first();
				String ur_id=rsA.getString(1);
				int u_id=Integer.parseInt(ur_id);
				System.out.println(ur_id);
				na=na-b;
				float tc=b*c;
				System.out.println("ddccd");
				sql.executeUpdate("UPDATE "+tableName+" SET amount = "+na+" WHERE bookkey ="+c);
				System.out.println("dddd");
				String conditionB="SELECT*FROM "+tableNameA+" WHERE book_bookkey= "+a+" and user_id= "+u_id;
				ResultSet rsB=sql.executeQuery(conditionB);
				rsB.first();
				if(!rsB.next()) {
				    sql.executeUpdate("INSERT INTO "+tableNameA+" VALUES"+"("+id+","+b+","+c+","+a+","+u_id+")");}
				else {
					String am=rsB.getString(2);
					System.out.println("???"+am);
					int u_am=Integer.parseInt(am);
					b=b+u_am;
					sql.executeUpdate("UPDATE "+tableNameA+" SET amount = "+b+" WHERE book_bookkey ="+a+" and user_id= "+u_id);
				}
			}
			con.close();
			purchase_Bean.setResult("购买成功");
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			purchase_Bean.setResult("库存不足");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
