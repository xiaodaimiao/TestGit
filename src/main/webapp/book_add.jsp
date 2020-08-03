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
</head>
<body>
<h3>这里是book_add.jsp页面</h3>
<form action="bc/add" method="post" enctype="multipart/form-data" >
	书名：<input type="text" value="java从入门到放弃" name="bookName" /><br/>
	出版社:<input type="text" value="人民教育出版社" name="publicDept" /><br/>
	价格：<input type="text" value="50.00" name="price" /><br/>
	出版日期:<input type="text" value="" onfocus="WdatePicker({'dateFmt':'yyyy-MM-dd','minDate':'2020-07-20 00:00:00','maxDate':'2020-07-27 00:00:00'})" readonly="readonly" name="publicDate" /><br/>
	作者：<input type="text" value="金庸" name="auth" /><br/>
	图片:<input type="file" name="pic" /><br/>
	简介：<textarea name="summary" rows="6" cols="50">
		hello world
	</textarea>
	<br/>
	<input type="submit" value="保存" />
	
</form>
</body>
</html> 