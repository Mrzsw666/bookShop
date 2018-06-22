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
		request.setCharacterEncoding("utf-8");
		String dataBase="bookshop";
		String tableName="trolley";
		String ur=request.getParameter("user");
		String be=request.getParameter("bookname");
		String at=request.getParameter("amount");
		String ct=request.getParameter("cost");
		String ix=request.getParameter("index");
		String de=request.getParameter("delete");
		System.out.println(ur);
		int id=0;
		int a=0;
		long c=0;
		float b=0;
		if(at!=null){
		    a=Integer.parseInt(at);}
		if(ct!=null){
			b=Float.parseFloat(ct);
		}
		if(ix!=null){
			c=Long.parseLong(ix);
		}
		float tc=b*a;
		String conditionA="SELECT*FROM user WHERE username= "+"'"+ur+"'";
		Connection con;		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			System.out.println(at+"?"+ct+"?"+ix);
			
			ResultSet rsA=sql.executeQuery(conditionA);
			rsA.first();
			String ur_id=rsA.getString(1);
			int u_id=Integer.parseInt(ur_id);
			
			String condition="SELECT * FROM "+tableName+" WHERE user_id = "+u_id;	
			int s=0;
			if(at==null||at.length()==0){
				int q=0;
				ResultSet rsG=sql.executeQuery(condition);			
				rsG=sql.executeQuery(condition);
				rsG.first();
				int[] ur_k=new int[100];
				String ur_int=rsG.getString(4);
				ur_k[q]=Integer.parseInt(ur_int);
				q++;
				while(rsG.next()){
					ur_int=rsG.getString(4);
					ur_k[q]=Integer.parseInt(ur_int);
				    q++;
				}
				String[] ur_na=new String[100];
				for(int r=0;r<q;r++){				
				    String conditionS="SELECT*FROM book WHERE bookkey= "+ur_k[r];				
				    ResultSet rsS=sql.executeQuery(conditionS);
				    rsS.first();
				    ur_na[r]=rsS.getString(2);
				}
				ResultSet rs=sql.executeQuery(condition);
				rs=sql.executeQuery(condition);
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
					for(int k=0;k<columnCount;k++){
						tableRecord[i][k]=rs.getString(k+1);
						}
					tableRecord[i][0]=ur_na[i];
					System.out.println(tableRecord[i][0]);
					i++;
					s++;
				}
				trolley_Bean.setBookNumber(s);
				trolley_Bean.setTableRecord(tableRecord);
				con.close();
				RequestDispatcher dispatcher=request.getRequestDispatcher("trolley.jsp");
				dispatcher.forward(request,response);
				return;
			}
			else {
				ResultSet rs=sql.executeQuery(condition);
				System.out.println("jjjj");
				if(de!=null) {
					if(de.equals("1")){
					    sql.executeUpdate("DELETE FROM "+tableName+" WHERE user_id = "+u_id+" and book_bookkey ="+c);}
					rs=sql.executeQuery("SELECT * FROM "+tableName+" WHERE user_id = "+u_id);
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
						for(int k=0;k<columnCount;k++){
							tableRecord[i][k]=rs.getString(k+1);
						    System.out.println(tableRecord[i][k]);}
						i++;
						s++;
					}
					
					trolley_Bean.setBookNumber(s);
					trolley_Bean.setTableRecord(tableRecord);
					con.close();
					RequestDispatcher dispatcher=request.getRequestDispatcher("trolley.jsp");
					dispatcher.forward(request,response);
					return;}
				else {System.out.println("bbbb");
					rs=sql.executeQuery("SELECT * FROM "+tableName+" WHERE user_id = "+u_id+" and book_bookkey ="+c);
					System.out.println("nmb"+ur);
					System.out.println("cnm"+c);
					if(!rs.next()) {
						sql.executeUpdate("INSERT INTO "+tableName+" VALUES"+"("+id+","+a+","+b+","+c+","+u_id+")");
					}
					 else {
						System.out.println(c);
						rs.first();
						String amounto=rs.getString(2);
						System.out.println(amounto);
						String costo=rs.getString(3);
						System.out.println(costo);
						int na=Integer.parseInt(amounto);
						float nc=Float.parseFloat(costo);
						na=na+a;
						nc=nc+tc;
						sql.executeUpdate("UPDATE "+tableName+" SET amount = "+na+" WHERE user_id = "+u_id+" and book_bookkey ="+c);
						sql.executeUpdate("UPDATE "+tableName+" SET cost = "+nc+" WHERE user_id = "+u_id+" and book_bookkey ="+c);}
					    rs=sql.executeQuery("SELECT * FROM "+tableName+" WHERE user_id = "+u_id+" and book_bookkey ="+c);
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
							for(int k=0;k<columnCount;k++){
								tableRecord[i][k]=rs.getString(k+1);
							    System.out.println(tableRecord[i][k]);}
							i++;
							s++;
						}
						trolley_Bean.setBookNumber(s);
						trolley_Bean.setTableRecord(tableRecord);
				        
			        con.close();
			        RequestDispatcher dispatcher=request.getRequestDispatcher("trolley.jsp");
					dispatcher.forward(request,response);
					return;
			}}
		}
		catch(SQLException ee){
			System.out.println(ee);
			RequestDispatcher dispatcher=request.getRequestDispatcher("trolley.jsp");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
