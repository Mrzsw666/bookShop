<%@page contentType="text/html;charset=utf-8" %>
<html>
 <head>
 	<title>您的购物车</title>
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
     .button{float: left; height: 20px;width:20px;padding:0px;}
     .black_overlay{ 
            display: none; 
            position: absolute; 
            top: 0%; 
            left: 0%; 
            width: 100%; 
            height: 100%; 
            background-color: black; 
            z-index:1001; 
            -moz-opacity: 0.8; 
            opacity:.80; 
            filter: alpha(opacity=88); 
        } 
        .white_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1002; 
            overflow: auto; 
        } 
 	</style>

    <script type="text/javascript"> 

    window.onload=function(){  
            var count=1;  
             var count1=1;  
            var adult=document.getElementById("btn1");  
            var adcount=document.getElementById("btn2");  
            var adco=document.getElementById("btn3");  
  
            var child=document.getElementById("btn4");  
            var chcount=document.getElementById("btn5");  
            var chco=document.getElementById("btn6");  
            var num=document.getElementById("num1");
                adult.onclick=function(){
                document.form1.action="trolley_Servlet";
                num.value="1";     
                count++;  
                adcount.innerHTML=count; 
                document.form1.submit();
                } 
                adco.onclick=function(){  
                if(count>1){  
                   count--;  
                adcount.innerHTML=count;
                num.value="-1";
                out.println(count);
                }else{ 
            	num.value="-1";
                adcount.innerHTML=1;  
            }
                
                document.form1.submit();
            }  
     
        child.onclick=function(){ 
        	    document.form1.action="trolley_Servlet";
                count1++;  
                chcount.innerHTML=count1;
                num.value="1";
                document.form1.submit();
        }
            chco.onclick=function(){  
                if(count1>1){  
                count1--;  
                chcount.innerHTML=count1;
                num.value="-1";
                }else{  
                chcount.innerHTML=1;
                num.value="-1";
                }
                document.form1.submit();
            }   
                
    }   
    function jj(){
    	var form1=document.form1;
    	form1.action="payed_Servlet";
    	form1.submit();
    }
    </script>
 </head>

 <body>
 	<div id="top"> 		
 		<ul style="background-color:black">		
 			<li><a href="modify_idea">我的信息</a></li>
            <li><a href="order.jsp">我的订单</a></li>

            <li>
                 <%
                String username=null;
            
                if(request.getAttribute("users_Bean")==null){
                  %>
                  <jsp:forward page="user_Servlet">
                        <jsp:param name="web" value="trolley.jsp"></jsp:param>
                  </jsp:forward> 
                 <% }else{ 
                     %>
                  <jsp:useBean id="users_Bean" class="mybean.User_Bean" scope="request"/>
                       <%
                      username=users_Bean.getUserName();
                       if(request.getAttribute("trolley_Bean")==null){
                       %>
                       <jsp:forward page="trolley_Servlet">
                        <jsp:param name="user" value="<%=username %>"></jsp:param>
                       </jsp:forward>   
<%} } %>
                
                <a href="modify_idea"><%=username %></a>
                
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
 	<form action="" method="post" name="form1">
 		<div id="title">
            <ul>
                <li style="width:500px;">商品信息</li>
                <li style="width:100px;">单价</li>
                <li style="width:100px;">数量</li>
                <li style="width:100px;">金额</li>
                <li style="width:120px;">操作</li>
            </ul>
        </div>
        <%  String[][] book=new String[100][100]; 
            int num=0;
        %>
        <%if(request.getAttribute("trolley_Bean")!=null){ %>
        		 <jsp:useBean id="trolley_Bean" class="mybean.book_Bean" scope="request"/>
        		<% book=trolley_Bean.getTableRecord();
 			       num=trolley_Bean.getBookNumber();
 		  } 
 		 
 			for(int i=0;i<num;i++){
 		%>        
        <div class="goods">
            <ul>
            
            <input type="checkbox" name="checkbox" >
              <% 
                float i4=Float.parseFloat(book[i][4]);
                float i3=Float.parseFloat(book[i][3]);
                float ian=i4/i3;
                 
                %>
                <li style="width:500px;"><%=book[i][2] %></li>
                <li style="width:100px;color:red;"><%=ian %></li>
                <li style="width:100px;">
                    <div class="btngroup" style="margin:40px 20px;"> 
                  
                    <input type="hidden" name="cost" value="<%=ian %>">
                    <input type="hidden" name="user" value="<%=username%>">    
                   <input type="hidden" name="index" value="<%=book[i][5] %>"> 
                   <input type="hidden" id="num1" name="amount" value="-1">  
                   <input id="btn3" type="button" class="button" value="-" >   
                
                          <input id="btn2" type="button" class="button" value="<%=book[i][3] %>" >
                     
                        <input id="btn1" type="button" class="button" value="+" > 
                          
                    </div>
                </li>
                
                <li style="width:100px;color:red;"><%=book[i][4] %></li>
                <li style="width:120px;">
                    <input id=<%=i %> type="button" value="删除" name="delete" style="width:60px;height:40px;font-size:20px;background-color:white;color:black;border:none;" onclick=function(){this.value="del"; document.all.submit();}>
                </li>
            </ul> 
        </div>
        <% } %>
        
        <div style="height:60px;width:950px;margin-bottom:15px;background-color:yellow;">
         <input type="hidden" name="user" value="<%=username%>">    
                  
            <input id="buy" type="button" value="购买" name="buy" 
                  onclick = "jj()"
                 style="float:right;width:100px;height:40px;font-size:20px;background-color:red;color:white;boder:none;margin:20px;">
        </div>
        <div id="light" class="white_content">
                  <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'" style="float:right;text-decoration:none;">返回</a>
                  <p style="margin-top:50px;color:red;font-size:bold;font-size:50px;">购买成功，感谢您的支持！</p>
        </div>
        <div id="fade" class="black_overlay"></div>
        </form>
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
 
