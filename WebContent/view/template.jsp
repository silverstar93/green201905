<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="">
	<tr>
		<td colspan="2">
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td width="100px"><jsp:include page="inc/menu.jsp"/></td>
		<td ><jsp:include page="${mainUrl }"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>