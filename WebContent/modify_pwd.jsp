<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改密码</title>
    <style type="text/css">
		#foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
		div#foot a:link{color:#999999;text-decoration:none;}
     	div#foot a:visited{color:#999999;text-decoration:none;}
     	div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
	</style>

</head>
<body style="background-color: #f9f9f9">
    <div>
			<img src="./Pic/logo.png" style="float:left;"><br/>
		</div><br/><br/><br/><br/><br/><br/>

    <form action="modifypwd_Servlet" method="post">
	<div style="text-align: center;">
		<label style="font-size: 20px">修改密码</label><br/><br/>
		<label style="font-size: 20px">请输入旧密码：</label>
		<input type="password" name= "Password1"  maxlength="30" style="font-size:15px;"/><br/><br/>
		<label style="font-size: 20px">请输入新密码：
		<input type="password" name= "Password2"  maxlength="30" style="font-size:15px;"/><br/><br/>
		<label style="font-size: 20px">请确认新密码：</label>
        <input type="password" name="Password3" id="form-pwd" class="field" maxlength="30"
         style="font-size:15px;"/><br/>
	</div><br/><br/>

	<div style="text-align: center;">
		<input type="submit" name="modify" value="提交" style="font-size:18px;width: 80px"/>
	</div>
	</form><br/>
	<br>
		<% String s=null,ss=null; %>
	<% if(request.getAttribute("modifypwd_Bean")!=null){  %>
		<jsp:useBean id="modifypwd_Bean" class="mybean.User_Bean" scope="request"/>
    <%s=modifypwd_Bean.getResult();
	  if(s.length()>20)ss=s.substring(21,s.length());
	  else ss=s.substring(0,s.length());
	} 
	 %>
	 <a style="font-weight:bold;color:red;"><center> <% if(s!=null) %><%=ss %> </center></a>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
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