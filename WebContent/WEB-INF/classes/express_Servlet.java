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
		RequestDispatcher dispatcher=request.getRequestDispatcher("order.jsp");
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
		String tableName="bill";
		String ur=request.getParameter("user");
		if(ur==null||ur.length()==0){
			express_Bean.setResult("请登录账户！");
			dispatcher.forward(request,response);
			return;
		}
		String conditionA="SELECT*FROM user WHERE username= "+"'"+ur+"'";
		Connection con;
		int s=0;
		int q=0;
		try{
			String uri="jdbc:mysql://127.0.0.1/"+dataBase+"?"+"user=root&password=123456&characterEncoding=utf-8&useSSL=true";			
			con=DriverManager.getConnection(uri);
			Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rsA=sql.executeQuery(conditionA);
			rsA.first();
			String ur_id=rsA.getString(1);
			int u_id=Integer.parseInt(ur_id);
			String condition="SELECT*FROM "+tableName+" WHERE user_id = "+u_id;
			
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
				for(int k=0;k<columnCount;k++){
					tableRecord[i][k]=rs.getString(k+1);
					System.out.println(tableRecord[i][k]);
					}
				tableRecord[i][0]=ur_na[i];
				i++;
				s++;
			}
			express_Bean.setBookNumber(s);
			express_Bean.setTableRecord(tableRecord);
			con.close();
			dispatcher.forward(request,response);
		}
		catch(SQLException ee){
			System.out.println(ee);
			express_Bean.setResult("��ѯ����ʧ�ܣ�");
			dispatcher.forward(request,response);
			return ;
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{	
		doPost(request,response);
	}
	
}
