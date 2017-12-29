import mybean.book_Bean;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class express_Servlet extends HttpServlet{
	public void init(ServletConfig config)throws ServletException{
		super.init(config);
		try{ Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		book_Bean express_Bean=null;
		RequestDispatcher dispatcher=request.getRequestDispatcher("express.jsp");
		try{
			express_Bean=(book_Bean)request.getAttribute("express_Bean");
			if(express_Bean==null){
				express_Bean=new book_Bean();
				request.setAttribute("express_Bean",express_Bean);
			}
		}
		catch(Exception exp){
			express_Bean=new book_Bean();
			request.setAttribute("express_Bean",express_Bean);
		}
		request.setCharacterEncoding("utf-8");
		String dataBase="bookshop";
		String tableName="order";
		String ur=request.getParameter("user");
		if(ur==null||ur.length()==0){
			express_Bean.setResult("ÇëµÇÂ¼ÕËºÅ£¡");
			dispatcher.forward(request,response);
			return;
		}
	
		String condition="SELECT*FROM "+tableName+" WHERE user = "+"'"+ur+"'";	
		Connection con;
		
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=sql.executeQuery(condition);
			ResultSetMetaData metaData=rs.getMetaData();
			int columnCount=metaData.getColumnCount();
			String []columnName=new String[columnCount];
			for(int i=0;i<columnName.length;i++){
				columnName[i]=metaData.getColumnName(i+1);
			}
			express_Bean.setColumnName(columnName);
			rs.last();
			int rowNumber=rs.getRow();
			String [][]tableRecord=express_Bean.getTableRecord();
			tableRecord=new String[rowNumber][columnCount];
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				for(int k=0;k<columnCount;k++)
					tableRecord[i][k]=rs.getString(k+1);
				i++;
			}
			express_Bean.setTableRecord(tableRecord);
			con.close();
			dispatcher.forward(request,response);
		}
		catch(SQLException ee){
			System.out.println(ee);
			express_Bean.setResult("²éÑ¯¶©µ¥Ê§°Ü£¡");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
