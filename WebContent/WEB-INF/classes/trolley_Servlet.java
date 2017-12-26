import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class trolley_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		book_Bean trolley_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("trolley.jsp");
		try{
			trolley_Bean=(book_Bean)request.getAttribute("trolley_Bean");
			if(trolley_Bean==null){
				trolley_Bean=new book_Bean();
				request.setAttribute("trolley_Bean",trolley_Bean);
			}
		}
		catch(Exception exp){
			trolley_Bean=new book_Bean();
			request.setAttribute("trolley_Bean",trolley_Bean);
		}
		request.setCharacterEncoding("gb2312");
		String dataBase="bookshop";
		String tableName="trolley";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String at=request.getParameter("amount");
		String ct=request.getParameter("cost");
		String ix=request.getParameter("index");
		String de=request.getParameter("delete");
		int a=Integer.parseInt(at);
		float b=Float.parseFloat(ct);
		int c=Integer.parseInt(ix);
		float tc=b*a;
	
		String condition="SELECT*FROM "+tableName+" WHERE user = "+"'"+ur+"'"+" and index ="+c;	
		Connection con;		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);			
			ResultSet rs=sql.executeQuery(condition);
			if(at==null||at.length()==0){
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();
				String []columnName=new String[columnCount];
				for(int i=0;i<columnName.length;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				trolley_Bean.setColumnName(columnName);
				rs.last();
				int rowNumber=rs.getRow();
				String [][]tableRecord=trolley_Bean.getTableRecord();
				tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				int i=0;
				while(rs.next()){
					for(int k=0;k<columnCount;k++)
						tableRecord[i][k]=rs.getString(k+1);
					i++;
				}
				trolley_Bean.setTableRecord(tableRecord);
				con.close();
				dispatcher.forward(request,response);
			}
			else {
				if(de.equals('1')) {
					sql.executeUpdate("DELETE FROM "+tableName+" WHERE user = "+"'"+ur+"'"+" and index ="+c);}
				else if(!rs.next()) {
						sql.executeUpdate("INSERT INTO "+tableName+" VALUES"+"("+"'"+ur+"','"+be+"',"+a+","+tc+","+c+")");
					}
					 else {
						rs.first();
						String amounto=rs.getString(3);
						String costo=rs.getString(4);
						int na=Integer.parseInt(amounto);
						float nc=Float.parseFloat(costo);
						na=na+a;
						nc=nc+tc;
						sql.executeUpdate("UPDATE "+tableName+" SET amount = "+na+" WHERE user = "+"'"+ur+"'"+" and index ="+c);
						sql.executeUpdate("UPDATE "+tableName+" SET cost = "+nc+" WHERE user = "+"'"+ur+"'"+" and index ="+c);}
				ResultSetMetaData metaData=rs.getMetaData();
				int columnCount=metaData.getColumnCount();
				String []columnName=new String[columnCount];
				for(int i=0;i<columnName.length;i++){
					columnName[i]=metaData.getColumnName(i+1);
				}
				trolley_Bean.setColumnName(columnName);
				rs.last();
				int rowNumber=rs.getRow();
				String [][]tableRecord=trolley_Bean.getTableRecord();
				tableRecord=new String[rowNumber][columnCount];
				rs.beforeFirst();
				int i=0;
				while(rs.next()){
					for(int k=0;k<columnCount;k++)
						tableRecord[i][k]=rs.getString(k+1);
					i++;
				}
				trolley_Bean.setTableRecord(tableRecord);
			}
			con.close();
			trolley_Bean.setResult("成功添加至购物车！");
			dispatcher.forward(request,response);
			return;
		}
		catch(SQLException ee){
			System.out.println(ee);
			trolley_Bean.setResult("添加至购物车失败！");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
