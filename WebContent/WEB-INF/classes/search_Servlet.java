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
		String tt=request.getParameter("text");
		String te=request.getParameter("type");
		int a=0;
		if(ix!=null){
		    a=Integer.parseInt(ix);}
		try{
			book_Bean search_Bean=null;
			search_Bean=(book_Bean)request.getAttribute("search_Bean");
			search_Bean=new book_Bean();
			request.setAttribute("search_Bean",search_Bean);
			if(ix==null&&tt==null&&te==null){
				throw new Exception("您填写的信息不完整!");
			}
			Connection con;
			ResultSet rs;
			PreparedStatement sql;
			String dataBase="bookshop";
			String url="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";
			con=DriverManager.getConnection(url);
			String tableName="book";
			int s=0;
			String[][] tableRecord=new String[100][100];
			if(ix!=null){
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE bookkey="+a);
				rs=sql.executeQuery();
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();
				tableRecord=search_Bean.getTableRecord();
				String[] columnName=new String [columnCount];
				for(int i=0;i<columnCount;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				search_Bean.setColumnName(columnName);
				if(!rs.next()){
					throw new Exception("未找到对应书籍");
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
					s++;
				}
				search_Bean.setBookNumber(s+1);
				search_Bean.setTableRecord(tableRecord);
				con.close();
				RequestDispatcher dispatcher=request.getRequestDispatcher("introduce.jsp");
				dispatcher.forward(request,response);				
			}
			else if(te!=null){
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE type='"+te+"'");
				rs=sql.executeQuery();
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();
				tableRecord=search_Bean.getTableRecord();
				String[] columnName=new String [columnCount];
				for(int i=0;i<columnCount;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				search_Bean.setColumnName(columnName);
				if(!rs.next()){
					throw new Exception("未找到对应书籍");
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
					s++;
				}
				search_Bean.setBookNumber(s+1);
				search_Bean.setTableRecord(tableRecord);
				con.close();
				RequestDispatcher dispatcher=request.getRequestDispatcher("introduce.jsp");
				dispatcher.forward(request,response);
				
			}
			else{
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE bookname = '"+tt+"'");
				rs=sql.executeQuery();
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();
				//tableRecord=search_Bean.getTableRecord();
				String[] columnName=new String [columnCount];
				for(int i=0;i<columnCount;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				search_Bean.setColumnName(columnName);
				rs.last();
				int rowNumber=rs.getRow();
				//tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				while(rs.next()){
					for(int k=0;k<columnCount;k++){	
						tableRecord[s][k]=rs.getString(k+1);
					}
					s++;
				}
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE author = '"+tt+"'");
				rs=sql.executeQuery();
				metaData=rs.getMetaData();
				columnCount=metaData.getColumnCount();
				columnName=new String [columnCount];
				for(int i=0;i<columnCount;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				search_Bean.setColumnName(columnName);
				rs.last();
				rowNumber=rs.getRow();
				//tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				while(rs.next()){
					for(int k=0;k<columnCount;k++){	
						tableRecord[s][k]=rs.getString(k+1);
					}
					s++;
				}
				sql=con.prepareStatement("SELECT * FROM "+tableName+" WHERE type = '"+tt+"'");
				rs=sql.executeQuery();
				metaData=rs.getMetaData();
				columnCount=metaData.getColumnCount();
				columnName=new String [columnCount];
				for(int i=0;i<columnCount;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				search_Bean.setColumnName(columnName);
				rs.last();
				rowNumber=rs.getRow();
				//tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				while(rs.next()){
					for(int k=0;k<columnCount;k++){	
						tableRecord[s][k]=rs.getString(k+1);
					}
					s++;
				}
				search_Bean.setBookNumber(s);
				search_Bean.setTableRecord(tableRecord);
				con.close();
				RequestDispatcher dispatcher=request.getRequestDispatcher("introduce.jsp");
				dispatcher.forward(request,response);			
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
			book_Bean search_Bean=null;
			search_Bean=(book_Bean)request.getAttribute("search_Bean");
			search_Bean=new book_Bean();
			request.setAttribute("search_Bean",search_Bean);
			search_Bean.setResult(e.toString());
			response.sendRedirect("introduce.jsp");
			return;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException ,IOException{
		doPost(request,response);
	}
}