<%@page contentType="text/html;" pageEncoding="utf-8"%>  

<html>
<head>
	<title>管理员页面</title>
	<script type="text/javascript">
		function funcShow(id){
		for(var i=0;i<2;i++){
		var divInfo =document.getElementById('div'+(i+1));
		divInfo.style.display='none';
		}
		var div =document.getElementById('div'+id);
		div.style.display='block';
		}

		function viewmypic(mypic,imgfile) {        
		if (imgfile.value){        
		mypic.src=imgfile.value;        
		mypic.style.display="";        
		mypic.border=1;        
		}        
	}
	</script>
	<style type="text/css">
		#foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
		div#foot a:link{color:#999999;text-decoration:none;}
     	div#foot a:visited{color:#999999;text-decoration:none;}
     	div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
     	p{ text-indent:4em;}
     	yy{ position: relative; right:12% }
     	xx{ position: relative; left: 7% }
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
         <jsp:param name="web" value="admin.jsp"></jsp:param>
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
            <li><a href="modify_pwd.jsp">修改密码</a></li>
            <li>              
                <a href="modify_idea.jsp" style="font-size:16px;"><%=username %></a>&nbsp;
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
			<a href="main.jsp"><img src="./Pic/logo.png" style="float:left"/></a><br/><br/>
		</div><br/><br/><br>

	<table width="500" border="1" style="margin: 10px auto;">
		<tr>
		<td width="100" height="300"><a href="javascript:funcShow(1)" style=" text-decoration: none;color:#2F4F4F">上传书籍信息 </a></td>
		<td width="550" colspan="4" rowspan="5">
		<div id="div1" style="display:block;text-align: center;">
			<form action="alter_Servlet" method="post">
				请输入书籍的信息：<br/><br>
				索&thinsp;&thinsp;&thinsp;引&thinsp;&thinsp;&thinsp;号：<input type="text" name="index" maxlength="20"><br/><br/>
				书&emsp;&emsp;名：<input type="text" name="bookname" maxlength="50"><br/><br/>
				书籍种类：
				<select name="" style="width: 175px">
					<option value="....">....</option>
					<option value="特色书籍">特色书籍</option>
					<option value="文学馆">文学馆</option>
					<option value="教育馆">教育馆</option>
					<option value="生活馆">生活馆</option>
					<option value="艺术馆">艺术馆</option>
					<option value="科技馆">科技馆</option>
					<option value="其他图书">其他图书</option>
				</select><br><br>
				价&emsp;&emsp;格：<input type="text" name="cost" maxlength="10"><br/><br/>
				作&emsp;&emsp;者：<input type="text" name="author" maxlength="50"><br/><br/>
				出&thinsp;&thinsp;&thinsp;版&thinsp;&thinsp;&thinsp;社：<input type="text" name="press" maxlength="50"><br/><br/>
				日&emsp;&emsp;期：<input type="text" name="date" maxlength="20"><br/><br/>
				数&emsp;&emsp;量：<input type="text" name="amount" maxlength="10"><br/><br/>
				<yy>
				<label style="float:left;padding-left:70px">书籍简介：</label>
				</yy>
				<textarea rows="3" cols="20" style="float:left;" width="120px"></textarea>
				<br/><br/>
				<xx>
					<br><input name="pic" type="file" id="imgfile" accept="image/gif,image/jpg,image/jpeg,image/png" size="10" onchange="viewmypic(showimg,this.form.imgfile);" />
				</xx><br><br>
				<input type="submit" name="提交">
							<% String str= null; %>  
			<% if (request.getAttribute("alter_Bean")!=null){ %> 
    		<jsp:useBean id="alter_Bean" class="mybean.book_Bean" scope="request"/>
    			<% str=alter_Bean.getResult();%>
    		<% if(str!=null){
    			%><%=str %>
    		<% }%>
 <% }  %>
			</form>
			<br>
		</div>

		<div id="div2" style="display:none;text-align: center;">
			<form action="alter_Servlet" method="post">
				请输入修改的信息：<br/><br>
				索&thinsp;&thinsp;&thinsp;引&thinsp;&thinsp;&thinsp;号：<input type="text" name="index" maxlength="20"><br/><br/>
				书&emsp;&emsp;名：<input type="text" name="bookname" maxlength="50"><br/><br/>
				书籍种类：
				<select name="" style="width: 175px">
					<option value="....">....</option>
					<option value="特色书籍">特色书籍</option>
					<option value="文学馆">文学馆</option>
					<option value="教育馆">教育馆</option>
					<option value="生活馆">生活馆</option>
					<option value="艺术馆">艺术馆</option>
					<option value="科技馆">科技馆</option>
					<option value="其他图书">其他图书</option>
				</select><br><br>
				价&emsp;&emsp;格：<input type="text" name="cost" maxlength="10"><br/><br/>
				作&emsp;&emsp;者：<input type="text" name="author" maxlength="50"><br/><br/>
				出&thinsp;&thinsp;&thinsp;版&thinsp;&thinsp;&thinsp;社：<input type="text" name="press" maxlength="50"><br/><br/>
				日&emsp;&emsp;期：<input type="text" name="date" maxlength="20"><br/><br/>
				数&emsp;&emsp;量：<input type="text" name="amount" maxlength="10"><br/><br>
				<yy>
				<label style="float:left;padding-left:70px">书籍简介：</label>
				</yy>
				<textarea rows="3" cols="20" style="float:left;" width="120px" height="20px"></textarea>
				<br/><br/>
				<xx>
					<br><input name="pic" style type="file" id="imgfile" accept="image/gif,image/jpg,image/jpeg,image/png" size="10" onchange="viewmypic(showimg,this.form.imgfile);" />
				</xx><br><br>
				<input type="submit" name="提交">
					<% if (request.getAttribute("book_Bean")!=null){ %> 
    			<%=str %>
    		<% }  %>
			</form><br>  
		</div></td>
		</tr>
		<tr>
		<td height="300"><a href="javascript:funcShow(2)" style="text-decoration: none;color: #2F4F4F">修改书籍信息</a></td>
		</tr>
		</tr>
	</table>	

	<br/><br/>
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