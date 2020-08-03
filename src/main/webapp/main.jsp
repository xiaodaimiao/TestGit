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
<style type="text/css">
.on{
	text-decoration: none;
	font-size: 20px;
	color: red;
	font-weight: bold;
}

.off{
	text-decoration: none;
	font-size: 14px;
	color: green;
}
</style>
<script type="text/javascript">
function gopage(nowpage){
	var allpage = parseInt(document.getElementById("allpage").value);
	//  "123"<"24"   123>"24"  "123"+"24"="12324"
	//   ==  ===  区别
	console.log(nowpage);
	if(parseInt(nowpage)<1){
		alert("已经是第一页了");
	}else if(parseInt(nowpage)>allpage){
		alert("已经是最后一页了");
	}else{
		splitform.cp.value=nowpage;
		splitform.submit();
	}
	
}

</script>
</head>
<body>
<h3>这里是main.jsp页面</h3>
<form name="splitform" action="finddata" method="post" >
	<input type="hidden" value="${allpage }" name="allpage" id="allpage" />
	<input type="hidden" value="${count }" name="count" id="count" />
	<input type="hidden" value="${cp }" name="cp" id="cp" />
	
<div>
	书名：<input type="text" value="${bookname }" name="bookname" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	出版社：<input type="text" value="${deptname }" name="deptname"/><br/>
	价格：<input type="text" value="${loprice }" name="loprice"/> - <input type="text" value="${hiprice }" name="hiprice" /><br/>
	作者：<input type="text" value="${auth }" name="auth" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" onclick="gopage('1');" value="查询"/>
</div>
<br/>
<table id="tb" width="800" border="1" cellspacing="0" >
	<tr>
		<th>请选择</th>
		<th>书名</th>
		<th>出版社</th>
		<th>作者</th>
		<th>出版日期</th>
		<th>简介</th>
	</tr>
	<c:forEach items="${booklist }" var="bl" >
	<tr>
		<td>
			<input type="checkbox" value="${bl.bookid }" name="bookid" />
		</td>
		<td><a href="bc/showbook?bookid=${bl.bookid }">${bl.bookName }</a></td>
		<td>${bl.publicDept }</td>
		<td>${bl.auth }</td>
		<td> <fmt:formatDate value="${bl.publicDate }" pattern="yyyy-MM-dd" /> </td>
		<td>
			${fn:substring(bl.summary,0,15) }${fn:length(bl.summary)>15?"...":"" }
		</td>
	</tr>
	</c:forEach>
	
</table>

	<input type="button" value="首页" onclick="gopage('1');" />
	<input type="button" value="上一页" onclick="gopage('${cp-1}');" />
	
	<c:if test="${cp-3>0 }">
	<a class="off" href="javascript:gopage('${cp-3 }');">...</a>
	</c:if>
	<c:if test="${cp-2>0 }">
	<a class="off" href="javascript:gopage('${cp-2 }');">${cp-2 }</a>
	</c:if>
	<c:if test="${cp-1>0 }">
	<a class="off" href="javascript:gopage('${cp-1 }');">${cp-1 }</a>
	</c:if>
	
	<a class="on" href="javascript:void(0);">${cp }</a>
	
	<c:if test="${cp<allpage }">
	<a class="off" href="javascript:gopage('${cp+1 }');">${cp+1 }</a>
	</c:if>
	<c:if test="${cp+1<allpage }">
	<a class="off" href="javascript:gopage('${cp+2 }');">${cp+2 }</a>
	</c:if>
	<c:if test="${cp+2<allpage }">
	<a class="off" href="javascript:gopage('${cp+3 }');">...</a>
	</c:if>
	
	<input type="button" value="下一页" onclick="gopage('${cp+1}');" />
	<input type="button" value="尾页" onclick="gopage('${allpage}');" />
	<span style="">
		共查询出<b><i>${count }</i></b>条数据，分为<b><i>${allpage }</i></b>页，
		现在是第<b><i>${cp }</i></b>页，每页显示
		<select name="ps" onchange="gopage('1');">
			<option value="1" ${ps==1?"selected":"" } >1</option>
			<option value="3" ${ps==3?"selected":"" } >3</option>
			<option value="5" ${ps==5?"selected":"" }>5</option>
			<option value="10" ${ps==10?"selected":"" }>10</option>
		</select>
		行
	</span>
</form>
<hr/>
<a href="bc/add">新增</a>
</body>
</html> 