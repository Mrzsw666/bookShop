<%@page contentType="text/html;charset=utf-8" %>
<html>
 <head>
 	<title>我的订单</title>
 	<style type="text/css">
 	*{padding:0px 0px 0px 0px;}
 	 body{width:1000px;margin: 0px auto;text-align: center;padding: 0px 0px;background-color: #f8f8f8;}
 	 #top{height: 30px;background-color: #e3e4e5;line-height: 30px;text-align: center;} 
 	 div#top li{ float:right; list-style:none; margin: 0px 3px; } 
 	 div#top  a:link{color:#999999;text-decoration:none;}
     div#top  a:visited{color:#999999;text-decoration:none;}
     div#top  a:hover{color:red;text-decoration:none;font-weight:bold;}
     #logo{height: 80px;}
     #empty{height: 15px;}
     #main{margin: 0px auto;min-height: 410px;width: 1000px;}
     #title{height: 20px;line-height: 20px;}
     div#title li{float: left;text-decoration: none;list-style:none;font-size: 15px;background-color: green;margin: 0px 8px;}
     div.goods{display: inline-block;width: 1000px;}
     div.goods li{float: left;text-decoration: none;list-style:none;font-size: 20px;height: 100px;line-height: 100px; background-color: white;margin: 0px 8px;font-family: "Adobe Gothic Heiti Std B";}
     #down{height: 100px;}
     #foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
     div#foot a:link{color:#999999;text-decoration:none;}
     div#foot a:visited{color:#999999;text-decoration:none;}
     div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
 	</style>
 </head>

 <body>
 	<div id="top"> 		
 		<ul style="background-color:black">		
 			<li><a href="modify_idea.jsp">我的信息</a></li>
 			<li><a href="trolley.jsp">我的购物车</a></li>
            <li>
                
                <%
                String username=null;
            
                if(request.getAttribute("users_Bean")==null){
                  %>
                  <jsp:forward page="user_Servlet">
                        <jsp:param name="web" value="order.jsp"></jsp:param>
                  </jsp:forward> 
                 <% }else{ 
                     %>
                  <jsp:useBean id="users_Bean" class="mybean.User_Bean" scope="request"/>
                       <%
                      username=users_Bean.getUserName();
       
                       %>
<% } %>
                
                <a href="modify_idea.jsp"><%=username %></a>
                   <form action="express_Servlet" method=post>
                 <input type="hidden" name="user" value="<%=username %>">  
                </form>
            </li>
 			<li><a href="#">您好，</a></li>
 		</ul>
 	</div>
 	<div id="logo">
 		<a href="main.jsp"><img src="./Pic/logo.png" style="float:left;"></a>
        <form action="search_Servlet" method=post>
        	<br><input type="text" name="search" size=9 style="width:300px;height:40px;font-size:20px;">
        	<input id="search" type="submit" value="搜索" name="search" style="width:60px;height:40px;font-size:20px;background-color:#715a52;color:white;">
        </form>
        
 	</div>
 	<div id="empty"></div>
 	<div id="main">
 		<div id="title">
            <ul>
                <li style="width:500px;">商品信息</li>
                <li style="width:100px;">单价</li>
                <li style="width:100px;">数量</li>
                <li style="width:100px;">金额</li>
                <li style="width:120px;">我要退款</li>
            </ul>
        </div>
        <% String[][] book=new String[100][100]; 
 		   int num=0;
 		%>
        <%if(request.getAttribute("express_Bean")!=null){ %>
          <jsp:useBean id="express_Bean" class="mybean.book_Bean" scope="request"/>
        	<%  book=express_Bean.getTableRecord();
 			num=express_Bean.getBookNumber();
 			 } 
 			for(int i=0;i<num;i++){
 		%>        
        <div class="goods">
            <ul>
                <li style="width:500px;"><%=book[i][5] %></li>
                <li style="width:100px;color:red;.10"><%=book[i][2] %></li>
                <li style="width:100px;">
                    <%=book[i][7] %>
                </li>
                <%
                float i7=Float.parseFloat(book[i][7]);
                float i2=Float.parseFloat(book[i][2]);
                float ian=i2*i7;
                %>
           
                <li style="width:100px;color:red;"><%=ian %></li>
                <li style="width:120px;">
                    <input id="delete" type="submit" value="删除" name="delete" style="width:100px;height:40px;font-size:20px;background-color:white;color:black;border:none;">
                </li>
            </ul>
        </div>
       <%} %> 
    </div>
 	<div id="down">
 		<img src="./Pic/down.jpg">
 	</div>
 	<div id="foot">
 		<p style="text-align:center;">
            <a href="#">版权声明</a>&nbsp&nbsp
            <a href="#">免责声明</a>&nbsp&nbsp
            <a href="#">关于我们</a>&nbsp&nbsp
            <a href="#">联系我们</a>
        </p>
 	</div>
 </body>