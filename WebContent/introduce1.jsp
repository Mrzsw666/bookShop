<html>
 <head>
 	<title>书的详情</title>
    
 	<style type="text/css">
 	*{padding:0px 0px 0px 0px;}
 	 body{width:1000px;margin: 0px auto;text-align: center;padding: 0px 0px;background-color: #f8f8f8}
 	 #top{height: 30px;background-color: #e3e4e5;line-height: 30px;text-align: center;} 
 	 div#top li{ float:right; list-style:none; margin: 0px 3px; } 
 	 div#top  a:link{color:#999999;text-decoration:none;}
     div#top  a:visited{color:#999999;text-decoration:none;}
     div#top  a:hover{color:red;text-decoration:none;font-weight:bold;}
     #logo{height: 80px;}
     #empty{height: 15px;}
     #main{margin: 0px auto;height: 410px;width: 1000px;border-style: 1px solid gray;}
     #left{float: left;height: 400px;width: 300px;margin: 5px;background-color: #f9f9f9;}
     #right{float: right;height: 400px;width: 650px;margin: 5px;background-color: #f9f9f9;}
     div#right .sml{height: 120px;width: 630px;margin: 9px 10px;background-color: white}
     #down{height: 100px;}
     #foot{height: 30px;background-color:#e3e4e5;line-height: 30px;text-align: center;}
     div#foot a:link{color:#999999;text-decoration:none;}
     div#foot a:visited{color:#999999;text-decoration:none;}
     div#foot a:hover{color:#8d928d;text-decoration:none;font-weight:bold;}
     .button {  
    background-color: white;  
    border: 1px solid gray;  
    color: #536277;  
    display: inline-block;  
    float: left;  
    margin-top: 60px;
    font-size: 20px;  
    padding: 8px 21px;  
    text-align: center;  
    text-decoration: none;  
    }  
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
         .bright_content { 
            display: none; 
            position: absolute; 
            top: 25%; 
            left: 25%; 
            width: 55%; 
            height: 55%; 
            padding: 20px; 
            border: 10px solid orange; 
            background-color: white; 
            z-index:1003; 
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
  
                adult.onclick=function(){     
                count++;  
                adcount.innerHTML=count;  
                  adco.onclick=function(){  
                if(count>1){  
                   count--;  
                adcount.innerHTML=count;   
            }else{  
                adcount.innerHTML=1;  
            }  
       }  
                 
    }    
     
        child.onclick=function(){  
                count1++;  
                chcount.innerHTML=count1;  
            chco.onclick=function(){  
                if(count1>1){  
                count1--;  
                chcount.innerHTML=count1;    
                }else{  
                chcount.innerHTML=1;  
                }  
            }           
                  
        }  
    }  
    </script>

 </head>

 <body>
 	<div id="top"> 		
 		<ul style="background-color:black">	
      <li><a href="#">管理入口</a></li>	
 	  <li><a href="#">我的信息</a></li>
      <li><a href="#">我的订单</a></li>
 	  <li><a href="#">我的购物车</a></li>
 	  <li><a href="modify_pwd.jsp">修改密码</a></li>
 			<li>
                <a href="#">请登录！</a><a href="#">免费注册</a>
            </li>           
 			<li><a href="#">您好，</a></li>
 		</ul>
 	</div>
 	<div id="logo">
 		<a href="#"><img src="./Pic/logo.png" style="float:left;"></a>
        <form action="#" method=post>
        	<br><input type=text name="search" size=9 style="width:300px;height:40px;font-size:20px;">
        	<input id="button" type="submit" value="搜索" name="submit" style="width:60px;height:40px;font-size:20px;background-color:#715a52;color:white;">
        </form>
 	</div>
 	<div id="empty"></div>
 	<div id="main">
 		<div id="left">
            <img src="#">    
        </div>
        <div id="right">
            <div class="sml"></div>
            <div class="sml"></div>
            <div class="sml" style="width:450px;float:right;">
                <span class="btngroup">  
                        <button id="btn3" class="button" >-</button>
                        <button id="btn2" class="button"><strong>1</strong></button>  
                        <button id="btn1" class="button"><strong>+</strong></button>
                </span>  
                <input id="car" type="submit" value="加入购物车" name="car" 
                  onclick = "document.getElementById('bright').style.display='block';document.getElementById('fade').style.display='block'";
                style="width:120px;height:40px;font-size:20px;background-color:red;color:white;margin-top:60px;border:0px;">
                <input id="buy" type="submit" value="购买" name="buy"
                  onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'";
                 style="width:60px;height:40px;font-size:20px;background-color:red;color:white;margin-top:60px;boder:0px;">
            </div>
            <div id="light" class="white_content">
                  <a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'" style="float:right;text-decoration:none;">返回</a>
                  <p style="margin-top:50px;color:red;font-size:bold;font-size:50px;">购买成功，感谢您的支持！</p>
            </div>
            <div id="bright" class="bright_content">
                  <a href = "javascript:void(0)" onclick = "document.getElementById('bright').style.display='none';document.getElementById('fade').style.display='none'" style="float:right;text-decoration:none;">返回</a>
                  <p style="margin-top:50px;color:red;font-size:bold;font-size:50px;">已成功添加至您的购物车！</p>
            </div>
            <div id="fade" class="black_overlay"></div>
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