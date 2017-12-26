import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.util.UUID;

public class add_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		book_Bean add_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("add.jsp");
		try{
			add_Bean=(book_Bean)request.getAttribute("add_Bean");
			if(add_Bean==null){
				add_Bean=new book_Bean();
				request.setAttribute("add_Bean",add_Bean);
			}
		}
		catch(Exception exp){
			add_Bean=new book_Bean();
			request.setAttribute("add_Bean",add_Bean);
		}
		request.setCharacterEncoding("gb2312");
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
		Part part=request.getPart("pic");
		String name=part.getHeader("content-disposition");
		String root=request.getServletContext().getRealPath("/Pic");
		String str=name.substring(name.lastIndexOf("."), name.length()-1);
		String filename=root+"\\"+UUID.randomUUID().toString()+str;
		part.write(filename);
		
		int a=Integer.parseInt(ix);
		float b=Float.parseFloat(ct);
		int c=Integer.parseInt(at);
		if(be==null||be.length()==0){
			add_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return;
		}
		String condition="INSERT INTO "+tableName+" VALUES"+"("+a+",'"+be+"',"+b+",'"+ar+"','"+ps+"','"+ie+"','"+de+"',"+b+",'"+filename+"')";
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			sql.executeUpdate(condition);
			ResultSet rs=sql.executeQuery("SELECT*FROM "+tableName);
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String []columnName=new String[columnCount];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			add_Bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String [][]tableRecord=add_Bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++)
					tableRecord[i][k]=rs.getString(k+1);
				i++;
			}
			add_Bean.setTableRecord(tableRecord);
			con.close();
			add_Bean.setResult("³É¹¦£¡");
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			add_Bean.setResult("Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
