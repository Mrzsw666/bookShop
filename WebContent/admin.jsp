<%@page contentType="text/html;" pageEncoding="utf-8"%>  


<!DOCTYPE html>
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
	</style>  
</head>

<body style="background-color: #f9f9f9">
		<div>
			<a href="main.jsp"><img src="./Pic/logo.png"/></a><br/><br/>
		</div><br/><br/><br>


	<table width="700" border="1" style="margin: 10px auto;">
		<tr>
		<td width="100" height="300"><a href="javascript:funcShow(1)" style=" text-decoration: none;color:#2F4F4F">上传书籍信息 </a></td>
		<td width="550" colspan="4" rowspan="5">
		<div id="div1" style="display:block;text-align: center;">
			<form action="alter_Servlet" method="post">
				请输入书籍的信息：<br/><br>
				索引号：<input type="text" name="index" maxlength="20"><br/><br/>
				书&nbsp;&nbsp;&nbsp;名：<input type="text" name="bookname" maxlength="50"><br/><br/>
				书籍种类：
				<select name="" style="width: 140px">
					<option value="....">....</option>
					<option value="特色书籍">特色书籍</option>
					<option value="文学馆">文学馆</option>
					<option value="教育馆">教育馆</option>
					<option value="生活馆">生活馆</option>
					<option value="艺术馆">艺术馆</option>
					<option value="科技馆">科技馆</option>
					<option value="其他图书">其他图书</option>
				</select><br><br>
				价&nbsp;&nbsp;&nbsp;格：<input type="text" name="cost" maxlength="10"><br/><br/>
				作&nbsp;&nbsp;&nbsp;者：<input type="text" name="author" maxlength="50"><br/><br/>
				出版社：<input type="text" name="press" maxlength="50"><br/><br/>
				日&nbsp;&nbsp;&nbsp;期：<input type="text" name="date" maxlength="20"><br/><br/>
				数&nbsp;&nbsp;&nbsp;量：<input type="text" name="amount" maxlength="10"><br/><br/>
				<yy>
					书籍简介：<br/>
				</yy>
				<textarea rows="3" cols="30" ></textarea><br/><br/>
				<xx>
					<input name="pic" type="file" id="imgfile" accept="image/gif,image/jpg,image/jpeg,image/png" size="10" onchange="viewmypic(showimg,this.form.imgfile);" />
				</xx><br><br>
				<input type="submit" name="提交">
			</form>
			<br>  
			<% if (request.getAttribute("alter_Bean")!=null){ %> 
    		<jsp:useBean id="alter_Bean" class="mybean.alter_Bean" scope="request"/>
    			<% String str=alter_Bean.getResult();%>
    			<%=str %>
    		<% }  %>  
		</div>

		<div id="div2" style="display:none;text-align: center;">
			<form action="alter_Servlet" method="post">
				请输入修改的信息：<br/><br>
				索引号：<input type="text" name="index" maxlength="20"><br/><br/>
				书&nbsp;&nbsp;&nbsp;名：<input type="text" name="bookname" maxlength="50"><br/><br/>
				书籍种类：
				<select name="" style="width: 140px">
					<option value="....">....</option>
					<option value="特色书籍">特色书籍</option>
					<option value="文学馆">文学馆</option>
					<option value="教育馆">教育馆</option>
					<option value="生活馆">生活馆</option>
					<option value="艺术馆">艺术馆</option>
					<option value="科技馆">科技馆</option>
					<option value="其他图书">其他图书</option>
				</select><br><br>
				价&nbsp;&nbsp;&nbsp;格：<input type="text" name="cost" maxlength="10"><br/><br/>
				作&nbsp;&nbsp;&nbsp;者：<input type="text" name="author" maxlength="50"><br/><br/>
				出版社：<input type="text" name="press" maxlength="50"><br/><br/>
				日&nbsp;&nbsp;&nbsp;期：<input type="text" name="date" maxlength="20"><br/><br/>
				数&nbsp;&nbsp;&nbsp;量：<input type="text" name="amount" maxlength="10"><br/><br>
				<yy>
					书籍简介：<br/>
				</yy>
				<textarea rows="3" cols="30"></textarea><br/><br/>
				<xx>
					<input name="pic" type="file" id="imgfile" accept="image/gif,image/jpg,image/jpeg,image/png" size="10" onchange="viewmypic(showimg,this.form.imgfile);" />
				</xx><br><br>
				<input type="submit" name="提交">
			</form><br>  
			<% if (request.getAttribute("alter_Bean")!=null){ %> 
    		<jsp:useBean id="alter_Bean" class="mybean.alter_Bean" scope="request"/>
    			<% String str=alter_Bean.getResult();%>
    			<%=str %>
    		<% }  %>
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