<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${msg != null}">
<script>
alert("${msg}");
</script>
</c:if>

<script>




function fileDelete(){
	frm.action = "FileDelete";
	frm.submit();
}
</script>
    
<h1>ModifyForm</h1>
<form name="frm" action="ModifyReg" method="post" enctype="multipart/form-data">
<input type="hidden" name="bid" value="${dto.bid }" />

<input type="hidden" name="page" value="${nowPage }" />
	<table border="" width="100%">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${dto.title }"/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname" value="${dto.pname }"/></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw" /></td>
		</tr>
		
		
		<c:choose>
			<c:when test="${dto.seq>0 }">
				<input type="hidden" name="upfile" value="${dto.upfile }" />
			</c:when>
			<c:otherwise>
			
			
		<tr>
			<td>파일</td>
			<td>
			<c:choose>
				<c:when test="${dto.upfile!=null }">
					${dto.upfile }<input type="button" value="파일삭제" onclick="fileDelete()"/>
					<input type="hidden" name="upfile" value="${dto.upfile }" />	
				</c:when>
				<c:otherwise>
					<input type="file" name="upfile" />	
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		
		
		</c:otherwise>
		</c:choose>
		

		<tr>
			<td>내용</td>
			<td><textarea name="content" cols="30" rows="5">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정" />
				<a href="ModifyForm?bid=${dto.bid }">초기화</a>
				<a href="Detail?bid=${dto.bid }&page=${nowPage }">뒤로</a>
			</td>
		</tr>
	</table>
</form>