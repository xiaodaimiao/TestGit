<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript" src="<%=basepath %>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basepath %>js/jquery-1.8.3.js" ></script>
<script type="text/javascript">
/* onload = function(){
	
	showAccount();
	
}

function showAccount(){
	//1.创建核心对象
	createXmlHttpRequest();
	//2.设置提交方式和提交路径
	xmlhttp.open("post","uc/showaccount");
	//3.设置回调函数
	xmlhttp.onreadystatechange=showAccountCallback;
	//4.发送请求
	xmlhttp.send();
}

function showAccountCallback(){
	// readyState：请求的状态
	//      每一次请求，状态都是从0-1-2-3-4
	//  0 : 表示核心对象创建
	//  1 : 表示客户端开始发送请求
	//  2 : 表示客户端请求发送结束
	//  3 : 表示服务端开始响应请求
	//  4 : 表示服务端响应结束
	if(xmlhttp.readyState==4){
		//浏览器的状态码，200表示一切正常
		if(xmlhttp.status==200){
			var text = xmlhttp.responseText;
			//console.log(text);
			
			//需要把字符串，转变为json数组
			var arr = eval("("+text+")");
			console.log(arr);
			
			var select = document.getElementById("accid");
			for(var i=0;i<arr.length;i++){
				select.add(new Option(arr[i].accid,arr[i].accid),null);
			}
			
		}
	}
}


var xmlhttp;

function createXmlHttpRequest(){
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
 */

 
 $(function(){
	 
	 //$.ajax();
	// $.post();
	 //$.get();
	 
	 //$.post("路径",{要传递的参数},function(data){回调函数},"返回数据的类型");
	 $.post("uc/showaccount",{},function(data){
		 //console.log(data);
		$.each(data,function(i,n){
			 $("#accid").append("<option value='"+n.accid+"'>"+n.accid+"</option>");
		});
	 },"json");
	 
 });
 	
 function showbalance(accid){
	 
	 $.ajax({
		 type:"POST",
		 url:"uc/showbalance",
		 data:{"accid":accid},
		 success:function(data){
			 $("#balanceinfo").html("￥"+data+"元");
		 }
	 });
	 
 }
 

</script>
</head>
<body>
<h3>这里是book_order.jsp页面</h3>
<form action="bc/buybook" method="post" >
	<input type="hidden" value="${book.bookid }" name="bookid" id="bookid" />
	欢迎您：${sessionScope.myuser.realname }
	<hr/>
	您选择的书籍：<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;书名：${book.bookName }<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出版社：${book.publicDept }<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格：${book.price }<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${book.auth }<br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;封面：<img src="${book.imgPath }" border="1" width="100" /><br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;简介：${book.summary }<br/>
	<hr/>
	
	库存数量：${count }<br/>
	<hr/>
	
	请选择一个扣款账号：
	<select name="accid" id="accid" onchange="showbalance(this.value);" >
		<option value="0">===请选择一个账号===</option>
	</select>
	,您选择的账户余额是：<span id="balanceinfo"></span>
	
	<hr/>
	
	
	<!-- 点击支付时，需要完成购买
		要求：1.库存数量减一;
			2. 要求用户选择的账户余额减少(书籍价格是多少，就减多少)
	 -->
	<input type="submit" value="支付" />
	
</form>
</body>
</html> 