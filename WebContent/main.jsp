<%@page contentType="text/html;charset=utf-8" %>
<html>
 <head>
 	<title>止战之殇图书馆</title>
 	<style type="text/css">
 	*{padding:0px;}
 	 body{width:1000px;margin: 0px auto;text-align: center;padding: 0px 0px;background-color: #f8f8f8}
 	 #top{height: 30px;background-color: #e3e4e5;line-height: 30px;text-align: center;} 
 	 div#top li{ float:right; list-style:none; margin: 0px 3px; } 
 	 div#top  a:link{color:#999999;text-decoration:none;}
     div#top  a:visited{color:#999999;text-decoration:none;}
     div#top  a:hover{color:red;text-decoration:none;font-weight:bold;}
     #logo{height: 80px;}
     #empty{height: 15px;}
     #main{margin: 0px auto;height: 410px;width: 1000px;}
     #left{float: left;margin: 5px auto;height: 400px;width:150px; background-color: #487a6f;}     
     div#left li{float: left; list-style:none;margin: 2px auto; width: 148px;height:40px; text-align: center;background-color: white;line-height: 40px;font-size: 25px;} 
 	 div#left .btn{width:150px;height:39px;font-size:20px;background-color:white;color:#487a6f;border-style: 1px dotted white;font-weight: bold;}  
     #right{float: right;margin: 5px auto;height: 400px;width: 800px;background-color: #f9f9f9;}   
     div#right .detail{height: 190px;width: 390px;float: left;margin: 5px;}
               .tiltle{height: 30px;text-align: left;background-color:#f9f9f9; line-height: 30px;font-size: 25px;font-weight: bold;font-family: "SimHei"}
               .book img{width:100%;height:100%;}
               .p2p{float: left;height: 150px;width: 120px;margin: 5px;background-color: white;}
     #down{height: 100px;}
     #foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
     div#foot a:link{color:#999999;text-decoration:none;}
     div#foot a:visited{color:#999999;text-decoration:none;}
     div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
 	</style>
 </head>

 <body>
 <%
 String username=null;
 int isSuper=0;
if(request.getAttribute("users_Bean")==null){
%>
    <jsp:forward page="user_Servlet">
         <jsp:param name="web" value="main.jsp"></jsp:param>
    </jsp:forward> 
<% }else{ 
%>
    <jsp:useBean id="users_Bean" class="mybean.User_Bean" scope="request"/>
    <%
        username=users_Bean.getUserName();
        isSuper=users_Bean.getisSuper();
    %>
<% } %>
 	<div id="top"> 		
 		<ul style="background-color:black">
            <% if(username==null){ %>
            <li>                
                <a href="login.jsp">请登录！</a><a href="register.jsp">免费注册</a>               
            </li>
 			<li><a href="#">您好，</a></li>
            <% }else if(isSuper==0){ %>      
            <li><a href="modify_idea.jsp">我的信息</a></li>
            <li><a href="order.jsp">我的订单</a></li>
            <li><a href="trolley.jsp">我的购物车</a></li>
            <li>              
                <a href="modify_idea.jsp"><%=username %></a>&nbsp
                 <form action="logout_Servlet" method="post" style="margin:0px;display:inline;"> 
                <input type="hidden" name="web" value="main.jsp" style="margin:0px;display:inline;">      	   
        	     <input id="out" type="submit" value="退出" name="out" style="display:inline-block;background-color: #e3e4e5;line-height: 30px;border:none;">
                 </form>               
            </li>
            <li><a href="#">您好，</a></li>
            <% }else{ %>
            <li><a href="admin.jsp">管理入口</a></li>       
            <li>                
                <a href="#"><%=username %></a>&nbsp 
                <form action="logout_Servlet" method="post" style="float:right;">        	   
        	    <input id="out" type="submit" value="退出" name="out" style="height: 30px;background-color: #e3e4e5;line-height: 30px;border:none;">
                </form>  
                             
            </li>
            <li><a href="#">您好，</a></li>
            <% } %>
 		</ul>
 	</div>
 	<div id="logo">
 		<a href="main.jsp"><img src="./Pic/logo.png" style="float:left;"></a>
        <form action="search_Servlet" method="post">
        	<br><input type=text name="text" size=9 style="width:300px;height:40px;font-size:20px;">
        	<input id="search" type="submit" value="搜索" name="search" style="width:60px;height:40px;font-size:20px;background-color:#715a52;color:white;">
        </form>        
 	</div>
 	<div id="empty"></div>
 	<div id="main">
 		<div id="left">
 			<ul>
 				<li style="color:white;background-color:#487a6f">图书分类</li>
 				<form action="search_Servlet" method="post"> 
 				<li><input type="submit" value="特色书籍" name="tese" class="btn" ></li>
 				</form>
 				<form action="search_Servlet" method="post"> 
 				<li><input type="submit" value="文学馆" name="wenxue" class="btn" ></li>
 				</form>
 				<form action="search_Servlet" method="post"> 
                <li><input type="submit" value="教育馆" name="jiaoyu" class="btn" ></li>
                </form>
 				<form action="search_Servlet" method="post"> 
                <li><input type="submit" value="生活馆" name="shenghuo" class="btn" ></li>
                </form>
 				<form action="search_Servlet" method="post"> 
                <li><input type="submit" value="艺术馆" name="yishu" class="btn" ></li>
                </form>
 				<form action="search_Servlet" method="post"> 
                <li><input type="submit" value="科技馆" name="keji" class="btn" ></li>
                </form>
 				<form action="search_Servlet" method="post"> 
                <li><input type="submit" value="其他图书" name="qita" class="btn" ></li>
                </form>
 			</ul>
 		</div>
 		<div id="right">
 			<div class="detail">
                 <div class="tiltle">每日疯抢</div>
                 <div class="book">
                     <div class="p2p">
                        <a href="introduce.jsp"> <img src="./Pic/mrfq.jpg"></a>
                     </div>
                     <div class="p2p">
                         <img src="./Pic/6.jpg">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/8.jpg">
                     </div>
                 </div>
            </div>
 			<div class="detail">
                 <div class="tiltle">销量领先</div>
                 <div class="book">
                     <div class="p2p">
                         <img src="./Pic/xllx.png">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/9.jpg">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/15.jpg">
                     </div>
                 </div>
            </div>
            <div class="detail">
                 <div class="tiltle">新品上市</div>
                 <div class="book">
                     <div class="p2p">
                         <img src="./Pic/xpss.png">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/18.jpg">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/20.jpg">
                     </div>
                 </div>
            </div>
            <div class="detail">
                 <div class="tiltle">强烈推荐</div>
                 <div class="book">
                     <div class="p2p">
                         <img src="./Pic/qltj.png">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/26.jpg">
                     </div>
                     <div class="p2p">
                          <img src="./Pic/28.jpg">
                     </div>
                 </div>
            </div>
 		</div>
 	</div>
 	<div id="down">
 		<img src="./Pic/down.jpg">
 	</div>
 	<div id="foot">
 		<p style="text-align:center;">
            <a href="#">版权声明</a>&nbsp&nbsp
            <a href="#">免责声明</a>&nbsp&nbsp
            <a href="#">关于我们</a>&nbsp&nbsp
            <a href="#">联系我们</a>
        </p>
 	</div>
 </body>