<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<h1>리스트</h1>

<table border="" >
	<tr>
		<td width="50px">번호</td>
		<td width="300px">제목</td>
		<td width="200px">작성자</td>
		<td width="200px">작성일</td>
		<td width="50px">조회수</td>
	</tr>
<c:forEach var="dto" items="${data}" varStatus="no">	
	<tr>
		<td>${start+no.index+1 }</td>
		<td>
			<c:if test="${dto.level>0 }">
				<c:forEach begin="1" end="${dto.level }" step="1">
				&nbsp;&nbsp;
				</c:forEach>
			└</c:if>
			<a href="Detail?bid=${dto.bid }&page=${nowPage}">${dto.title }</a>
		</td>
		<td>${dto.pname }</td>
		<td>${dto.regdateStr }</td>
		<td>${dto.no }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5" align="center">
			<c:if test="${startPage>1}">
				<a href="?page=${startPage-1 }">이전</a>
			</c:if>
			<c:forEach begin="${startPage }" end="${endPage }" var="i">
				<c:choose>
					<c:when test="${i==nowPage }">
						[${i }]
					</c:when>
					<c:otherwise>
						<a href="?page=${i }">${i }</a>	
					</c:otherwise>
				</c:choose>
				
			</c:forEach>
			<c:if test="${endPage<totalPage}">
				<a href="?page=${endPage+1 }">다음</a>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="5" align="right">
			<a href="WriteForm?page=${nowPage }">글쓰기</a>
		</td>
	</tr>
</table>
