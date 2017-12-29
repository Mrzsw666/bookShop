<%@page contentType="text/html;charset=gb2312" pageEncoding="utf-8"%>  

<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>用户注册</title>
	<style type="text/css">
		#foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
		div#foot a:link{color:#999999;text-decoration:none;}
     	div#foot a:visited{color:#999999;text-decoration:none;}
     	div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
	</style>  
 
</head>  
<body style="background-color: #f9f9f9">   
	<div>
        <a href="main.jsp"><img src="./Pic/logo.png"/></a><br/><br/>
	</div><br/><br/><br/><br/><br/><br/>
    <form action="register_Servlet" method="post">
    <div class="form-item form-item-account" id="form-item-account" style="text-align: center;">
        <label style="font-size: 20px">用&thinsp; 户&thinsp; 名：</label>
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
    	&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="return" value="返回" style="font-size:18px;">
    </div>
	</form><br><br>
	<% String s=null,ss=null; %>
	<% if(request.getAttribute("register_Bean")!=null){  %>
		<jsp:useBean id="register_Bean" class="mybean.User_Bean" scope="request"/>
    <%s=register_Bean.getResult();
	  ss=s.substring(21,s.length());
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