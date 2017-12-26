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
		request.setCharacterEncoding("gb2312");
		String dataBase="bookshop";
		String tableNameA="trolley";
		String tableNameB="book";
		String tableNameC="order";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String at=request.getParameter("amount");
		String ix=request.getParameter("index");
		int a=Integer.parseInt(at);
		int i=Integer.parseInt(ix);
		if(at==null||at.length()==0){
			payed_Bean.setResult("«Î»∑»œπ∫¬Ú ˝¡ø£°");
			dispatcher.forward(request,response);
			return;
		}
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String condition="SELECT * FROM "+tableNameB+" WHERE index ="+i;
			ResultSet rs=sql.executeQuery(condition);
			rs.first();	
			String amount1=rs.getString(8);
			int o=Integer.parseInt(amount1);
			if(a==o){
				sql.executeUpdate("UPDATE FROM "+tableNameB+" SET amount = "+0+" WHERE index ="+i);}
			else if(o<a){
					payed_Bean.setResult(" ß∞‹£°ø‚¥Ê≤ª◊„");
					dispatcher.forward(request,response);
					return;}
			else{
	            		o=o-a;
				sql.executeUpdate("UPDATE "+tableNameB+" SET amount = "+o+" WHERE index = "+i);
			}			
			
			String conditionB="SELECT*FROM "+tableNameB+" WHERE user = "+"'"+ur+"'"+" and index ="+i;
			ResultSet rsB=sql.executeQuery(conditionB);
			rsB.first();
			String amount2=rs.getString(3);
			int t=Integer.parseInt(amount2);
			String costp=rs.getString(4);
			float cp=Float.parseFloat(costp);
			float tc=a*cp;
			float c=cp;
			if(a==o){
				sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and index ="+i);
				sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
			else if(a==t){
				sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and index ="+i);
				sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
			else if(a>t){
				sql.executeUpdate("DELETE FROM "+tableNameA+" WHERE user = "+"'"+ur+"'"+" and index ="+i);;
				sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
			else if(a<t){
				t=t-a;
				cp=cp*t;
				sql.executeUpdate("UPDATE "+tableNameA+" SET amount = "+t+" WHERE user = "+"'"+ur+"'"+" and index ="+i);
				sql.executeUpdate("UPDATE "+tableNameA+" SET cost = "+cp+" WHERE user = "+"'"+ur+"'"+" and index ="+i);
				sql.executeUpdate("INSERT INTO "+tableNameC+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+c+","+tc+")");}
			
			con.close();
			payed_Bean.setResult("π∫¬Ú≥…π¶£°");
			dispatcher.forward(request,response);
		}
		catch(SQLException ee){
			System.out.println(ee);
			payed_Bean.setResult("π∫¬Ú ß∞‹£°");
			dispatcher.forward(request,response);
		}
			
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}