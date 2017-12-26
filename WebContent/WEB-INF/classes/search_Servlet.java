import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Calendar;

public class search_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		request.setCharacterEncoding("utf-8");
		String ix=request.getParameter("index");
		String be=request.getParameter("bookname");
		String ar=request.getParameter("author");
		int a=Integer.parseInt(ix);
		try{
			book_Bean search_bean=null;
			search_bean=(book_Bean)request.getAttribute("search_bean");
			search_bean=new book_Bean();
			request.setAttribute("search_bean",search_bean);
			if(ix==null&&be==null&&ar==null){
				throw new Exception("您填写的信息不完整!");
			}
			Connection con;
			ResultSet rs;
			PreparedStatement sql;
			String dataBase="bookshop";
			String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";
			con=DriverManager.getConnection(url);
			String tableName="book";
			if(ix!=null){
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE index="+a);}
			else if(be!=null){
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE bookname='"+be+"'");}
			else {
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE author='"+ar+"'");}
			rs=sql.executeQuery();
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String[][]tableRecord=search_bean.getTableRecord();
			String[] columnName=new String [columnCount];
			for(int i=0;i<columnCount;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			search_bean.setColumnName(columnName);
			if(!rs.next()){
				throw new Exception("未找到对应设备");
			}
			rs.last();
			int rowNumber=rs.getRow();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++){	
					tableRecord[i][k]=rs.getString(k+1);
				}
				i++;
			}
			search_bean.setTableRecord(tableRecord);
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
			book_Bean search_bean=null;
			search_bean=(book_Bean)request.getAttribute("search_bean");
			search_bean=new book_Bean();
			request.setAttribute("search_bean",search_bean);
			search_bean.setResult(e.toString());
			response.sendRedirect("search.jsp");
			return;
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("search.jsp");
		dispatcher.forward(request,response);
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}