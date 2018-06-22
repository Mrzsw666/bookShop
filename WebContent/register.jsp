<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>  

<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>用户注册</title>
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
<body style="background-color: #f9f9f9">   
<%
 String username=null;
 int isSuper=0;
if(request.getAttribute("users_Bean")==null){
%>
    <jsp:forward page="user_Servlet">
         <jsp:param name="web" value="register.jsp"></jsp:param>
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
            <li><a href="order.jsp">我的订单</a></li>
            <li><a href="trolley.jsp">我的购物车</a></li>
            <li><a href="modify_pwd.jsp">修改密码</a></li>
            <li>              
                <a style="font-size:16px;color:#999999;"><%=username %></a>&nbsp;
                 <form action="logout_Servlet" method="post" style="margin:0px;display:inline;"> 
                <input type="hidden" name="web" value="main.jsp" style="margin:0px;display:inline;">      	   
        	     <input id="out" type="submit" value="退出" name="out" style="display:inline-block;color:#999999;font-size:16px;background-color: #e3e4e5;line-height: 30px;border:none;">
                 </form>               
            </li>
            <li><a href="#">您好，</a></li>
            <% }else{ %>
            <li><a href="admin.jsp">管理入口</a></li>       
            <li>                
                <a href="#"><%=username %></a>&nbsp; 
                <form action="logout_Servlet" method="post" style="float:right;">        	   
        	    <input id="out" type="submit" value="退出" name="out" style="height: 30px;background-color: #e3e4e5;line-height: 30px;border:none;">
                </form>  
                             
            </li>
            <li><a href="#">您好，</a></li>
            <% } %>
 		</ul>
 	</div>


	<div>
        <a href="main.jsp"><img src="./Pic/logo.png" style="float:left;"/></a><br/><br/>
	</div><br/><br/><br/><br/><br/><br/>
	
    <form action="register_Servlet" method="post">
    <div class="form-item form-item-account" id="form-item-account" style="text-align: center;">
        <label style="font-size: 20px">用&thinsp;&thinsp;&thinsp;户&thinsp;&thinsp;&thinsp;名：</label>
        <input type="text" id="form-account" name="UserName" class="field" autocomplete="off" maxlength="30"
        placeholder="请输入用户名" style="font-size: 15px"/>
    </div><br/>

 	<div class="form-item" style="text-align: center;">
        <label style="font-size: 20px">密&emsp;&emsp;码：</label>
        <input type="password" name="Password1" id="form-pwd" class="field" maxlength="30" placeholder="请输入密码" style="font-size: 15px"/>
        <i class="i-status"></i>
    </div><br/>

     <div class="form-item" style="text-align: center;">
        <label style="font-size: 20px">确认密码：</label>
        <input type="password" name="Password2" id="form-pwd" class="field" maxlength="30" placeholder="请再次输入密码" style="font-size: 15px"/>
        <i class="i-status"></i>
    </div><br/>
    
    <div style="text-align: center;">
    	<label style="font-size: 20px">邮&emsp;&emsp;箱：</label>
    	<input type="text" name="Email" placeholder="邮箱" maxlength="30" style="font-size: 15px">
    </div><br/>

    <div style="text-align: center;">
    	<label style="font-size: 20px">地&emsp;&emsp;址：</label>
    	<input type="text" name="Address" placeholder="地址" maxlength="30" style="font-size: 15px">
    </div><br/>

    <div style="text-align: center;">
    	<label style="font-size: 20px">手机号码：</label>
    	<input type="text" name="Phone" placeholder="手机号码" maxlength="30" style="font-size: 15px">
    </div><br/>

    <div style="text-align: center;">
    	<input type="submit" name="register" value="提交" style="font-size:18px;">&nbsp;&nbsp;&nbsp;&nbsp;	
    	&nbsp;&nbsp;
    	</form>	
    	<a href="main.jsp"><input type="button" formaction="main.jsp" value="返回" style="font-size:18px;"></a>
    </div>
	<br><br>
	<% String s=null,ss=null; %>
	<% if(request.getAttribute("register_Bean")!=null){  %>
		<jsp:useBean id="register_Bean" class="mybean.User_Bean" scope="request"/>
    <%s=register_Bean.getResult();
    if(s.length()>20)ss=s.substring(21,s.length());
	  else ss=s.substring(0,s.length());
	} 
	 %>
	 <a style="font-weight:bold;color:red;"><center> <% if(s!=null) %><%=ss %> </center></a>
    <br/><br/><br/><br/><br/><br/><br/><br/><br/>
    <div id="foot">
 		<p style="text-align:center;">
            <a href="#">版权声明</a>&nbsp;&nbsp;
            <a href="#">免责声明</a>&nbsp;&nbsp;
            <a href="#">关于我们</a>&nbsp;&nbsp;
            <a href="#">联系我们</a>
        </p>
 	</div>



</body>  
</html>  