import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.util.UUID;
import java.util.Calendar;


public class alter_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		book_Bean alter_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("admin.jsp");
		try{
			alter_Bean=(book_Bean)request.getAttribute("alter_Bean");
			if(alter_Bean==null){
				alter_Bean=new book_Bean();
				request.setAttribute("alter_Bean",alter_Bean);
			}
		}
		catch(Exception exp){
			alter_Bean=new book_Bean();
			request.setAttribute("alter_Bean",alter_Bean);
		}
		request.setCharacterEncoding("utf-8");
		String dataBase="bookshop";
		String tableName="book";
		String ix=request.getParameter("index");
		String be=request.getParameter("bookname");
		String ct=request.getParameter("cost");
		String ar=request.getParameter("author");
		String ps=request.getParameter("press");
		String ie=request.getParameter("introduce");
		String de=request.getParameter("date");
		String at=request.getParameter("amount");
		String te=request.getParameter("type");
		/*Part part=request.getPart("pic");
		String name=part.getHeader("content-disposition");
		String root=request.getServletContext().getRealPath("/Pic");
		String str=name.substring(name.lastIndexOf("."), name.length()-1);
		String filename=root+"\\"+UUID.randomUUID().toString()+str;
		part.write(filename);*/
		if(de!=null){
			if(de.length()!=8){
				alter_Bean.setResult("请输入正确的日期！");
				dispatcher.forward(request,response);
				return;} 
			String year=de.substring(0,4);
			String month=de.substring(4,6);
			String day=de.substring(6,8);
			System.out.println(year);
			System.out.println(month);
			int y=Integer.parseInt(year);
			int m=Integer.parseInt(month);
			int d=Integer.parseInt(day);
			Calendar cal=Calendar.getInstance();
			int tyear=cal.get(Calendar.YEAR);
			if(y>tyear){
				alter_Bean.setResult("请输入正确的日期！");
				dispatcher.forward(request,response);
				return;} 
			int n=y%4;
			switch(m){
				case 1:if(d<32) break;
				case 2:if(n==0){
						if(d<30)
							break;}
						else {if(d<29) break;}
				case 3:if(d<32) break;
				case 4:if(d<31) break;
				case 5:if(d<32) break;
				case 6:if(d<31) break;
				case 7:if(d<32) break;
				case 8:if(d<32) break;
				case 9:if(d<31) break;
				case 10:if(d<32) break;
				case 11:if(d<31) break;
				case 12:if(d<32) break;
				default:
					alter_Bean.setResult("请输入正确的日期！");
	    			dispatcher.forward(request,response);
	    			return;
			}
		}
			
		String filename=null;
		int id=0;
		System.out.println(ix.length());
		if(ix.length()!=13){
			alter_Bean.setResult("请输入13位索引号！");
			dispatcher.forward(request,response);
			return;}
		long a=Long.parseLong(ix);
		/*try{
			s=a+"";
			if(s.length()!=13){
				alter_Bean.setResult("请输入13位索引号！");
				dispatcher.forward(request,response);}}
		catch(NumberFormatException e){
			alter_Bean.setResult("请输入数字！");
			dispatcher.forward(request,response);}*/

		float b=Float.parseFloat(ct);
		int c=Integer.parseInt(at);
		if(ix==null||ix.length()==0){
			alter_Bean.setResult("失败！");
			dispatcher.forward(request,response);
			return;
		}
		String condition="SELECT * FROM "+tableName+" WHERE bookkey="+a;
		Connection con;
		System.out.println(condition);
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery(condition);
            if(!rs.next()){
            	System.out.println("F");
            	sql.executeUpdate("INSERT INTO "+tableName+" VALUES"+"("+id+","+a+",'"+be+"',"+b+",'"+ar+"','"+ps+"','"+ie+"','"+de+"',"+c+",'"+filename+"','"+te+"')");
            	System.out.println("U");
            }
            else {System.out.println("C");
            	if(be!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET bookname = "+"'"+be+"'"+" WHERE bookkey ="+a);}
            	if(ct!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET cost = "+b+" WHERE bookkey ="+a);}
            	if(ar!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET author = "+"'"+ar+"'"+" WHERE bookkey ="+a);}
            	if(ps!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET press = "+"'"+ps+"'"+" WHERE bookkey ="+a);
            	}
            	if(ie!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET introduce = "+"'"+ie+"'"+" WHERE bookkey ="+a);
            	}
            	if(de!=null){            		
            		sql.executeUpdate("UPDATE "+tableName+" SET date = "+"'"+de+"'"+" WHERE bookkey ="+a);
            	}
            	if(at!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET amount = "+c+" WHERE bookkey ="+a);
            	}
            	if(te!=null){
            		sql.executeUpdate("UPDATE "+tableName+" SET type = "+"'"+te+"'"+" WHERE bookkey ="+a);
            	}

            }			
			alter_Bean.setResult("成功！");
			con.close();
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			alter_Bean.setResult("失败！");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
