<%@page contentType="text/html; charset=gb2312" pageEncoding="utf-8"%>  

<html>  
	<head>  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
	<title>用户登录</title> 

	<style type="text/css">
		#foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
		div#foot a:link{color:#999999;text-decoration:none;}
     	div#foot a:visited{color:#999999;text-decoration:none;}
     	div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
     	p{ text-indent:4em;}
	</style>
 
	</head>  
	<body style="background-color: #f9f9f9">


		<div>
			<img src="./Pic/logo.png" style="float:left;"><br/>
		</div>

	 	<br/><br/><br/><br/><br/><br/><br/><br/><br/>
	 	<form action="login_Servlet" method="post">
		<div class="form-item form-item-account" id="form-item-account" style="text-align:center;">
                    <label style="font-size:20px;">用户名：</label>
                    <input type="text" id="form-account" name="UserName" class="field" autocomplete="off" maxlength="20"
                           placeholder="您的账户名和登录名" style="font-size:15px;"/>
    	</div><br/><br/>

 		<div class="form-item" style="text-align:center;">
                    <label style="font-size:20px;">密&nbsp;&thinsp;码：</label>
                    <input type="password" name="Password" id="form-pwd" class="field" maxlength="20"
                           placeholder="您的密码" style="font-size:15px;"/><br/>
    	</div><br/><br/>

    	<div style="text-align:center;">
    		<input type="submit" name="login" value="登录"  style="font-size:18px;"/>
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" style="font-size: 14px">忘记密码</a>
    	</div>
    	    <% String st=null; %>
	<% if(request.getAttribute("login_Bean")!=null){  %>
		<jsp:useBean id="login_Bean" class="mybean.User_Bean" scope="request"/>
        <%st=login_Bean.getResult();
	}
	 %>
	 <br>
	 <a style="font-weight:bold;color:red;"><center> <% if(st!=null) %><%=st %> </center></a>
    	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
    	</form>

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
