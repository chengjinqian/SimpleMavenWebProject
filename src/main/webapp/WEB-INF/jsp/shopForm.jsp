<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>欢迎[${sessionScope.user.username }]来访我店</h3>
	<br>
	<table>
	<c:forEach items="${requestScope.books }" var="book">
	 	<tr>
	 		<td>${book.bookname }</td>
	 	</tr>
	</c:forEach>
	</table>
</body>
</html>