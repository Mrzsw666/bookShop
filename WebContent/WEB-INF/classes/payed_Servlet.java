import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class payed_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		System.out.println("JBM");
		book_Bean payed_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("pay.jsp");
		try{
			payed_Bean=(book_Bean)request.getAttribute("payed_Bean");
			if(payed_Bean==null){
				payed_Bean=new book_Bean();
				request.setAttribute("payed_Bean",payed_Bean);
			}
		}
		catch(Exception exp){
			payed_Bean=new book_Bean();
			request.setAttribute("payed_Bean",payed_Bean);
		}
		request.setCharacterEncoding("utf-8");
		String dataBase="bookshop";
		String tableNameA="trolley";
		String tableNameB="book";
		String tableNameC="order";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String cx=request.getParameter("checkbox");
		int x=Integer.parseInt(cx);
		int id=0;
		for(int k=0;k<x;k++){		
			String at=request.getParameter("amount[k]");
			String ix=request.getParameter("index[k]");
			int a=Integer.parseInt(at);
			int i=Integer.parseInt(ix);			
			Connection con;
		
			try{
				String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
				con=DriverManager.getConnection(uri);
				Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				String condition="SELECT * FROM "+tableNameB+" WHERE bookkey ="+i;
				ResultSet rs=sql.executeQuery(condition);
				rs.first();	
				String amount1=rs.getString(8);
				int o=Integer.parseInt(amount1);
				if(a==o){
					sql.executeUpdate("UPDATE FROM "+tableNameB+" SET amount = "+0+" WHERE bookkey ="+i);}
				else if(o<a){
						payed_Bean.setResult("Ê§°Ü£¡¿â´æ²»×ã");
						dispatcher.forward(request,response);
						return;}
				else{
	            	o=o-a;
	            	sql.executeUpdate("UPDATE "+tableNameB+" SET amount = "+o+" WHERE bookkey = "+i);
				}			
			
				String conditionB="SELECT*FROM "+tableNameB+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i;
				ResultSet rsB=sql.executeQuery(conditionB);
				rsB.first();
				String amount2=rs.getString(3);
				int t=Integer.parseInt(amount2);
				String costp=rs.getString(4);
				float cp=Float.parseFloat(costp);
				float tc=a*cp;
				float c=cp;
				if(a==o){
					sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i);
					sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+id+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
				else if(a==t){
					sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i);
					sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+id+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
				else if(a>t){
					sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i);;
					sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+id+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
				else if(a<t){
					t=t-a;
					cp=cp*t;
					sql.executeUpdate("UPDATE "+tableNameA+" SET amount = "+t+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i);
					sql.executeUpdate("UPDATE "+tableNameA+" SET cost = "+cp+" WHERE user = "+"'"+ur+"'"+" and bookkey ="+i);
					sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+id+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
			
				con.close();
			}
			catch(SQLException ee){
				System.out.println(ee);
				payed_Bean.setResult("¹ºÂòÊ§°Ü£¡");
				dispatcher.forward(request,response);
			}	
		}
		payed_Bean.setResult("¹ºÂò³É¹¦£¡");
		dispatcher.forward(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}