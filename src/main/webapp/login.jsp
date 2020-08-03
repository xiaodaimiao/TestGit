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
<h3>这里是login.jsp页面</h3>
<form action="uc/login" method="post" >
	用户名：<input type="text" value="zhangsan" name="loginname" /><br/>
	密码： <input type="password" value="123" name="loginpwd" /><br/>
	<input type="submit" value="保存" />
	
</form>
</body>
</html> 