<%@page contentType="text/html; charset=gb2312" pageEncoding="utf-8"%> 

<!DOCTYPE html>
<html>
<head>
	<title>个人信息修改</title>
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


    <form action="modifyidea_Servlet" method="post">
	<div style="text-align: center;">
		<label style="font-size: 20px">请输入修改的信息：</label><br/><br/>
		<label style="font-size: 20px">邮&thinsp;&nbsp;&nbsp;箱：</label>
		<input type="text" name= "Email"  maxlength="30" style="font-size:15px;"/><br/><br/>
		<label style="font-size: 20px">电话号码：</label>
		<input type="text" name= "Phone"  maxlength="11" style="font-size:15px;"/><br/><br/>
		<label style="font-size: 20px">地&thinsp;&nbsp;&nbsp;址：
		<input type="text" name= "Address"  maxlength="100" style="font-size:15px;"/><br/><br/>

	</div><br/>

	<div style="text-align: center;">
		<input type="submit" name="modify" value="提交" style="font-size:18px;width: 80px"/>
	</div>
	<br>
	<% String s=null,ss=null; %>
	<% if(request.getAttribute("modifyidea_Bean")!=null){  %>
		<jsp:useBean id="modifyidea_Bean" class="mybean.User_Bean" scope="request"/>
    <%s=modifyidea_Bean.getResult();
	  if(s.length()>20)ss=s.substring(21,s.length());
	  else ss=s.substring(0,s.length());
	} 
	 %>
	 <a style="font-weight:bold;color:red;"><center> <% if(s!=null) %><%=ss %> </center></a>
	</form><br/>

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