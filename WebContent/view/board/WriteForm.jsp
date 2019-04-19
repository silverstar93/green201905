<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>WriteForm</h1>
<form action="WriteReg" method="post" enctype="multipart/form-data">
	<table border="" width="100%">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname" /></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pw" /></td>
		</tr>
		<tr>
			<td>파일</td>
			<td><input type="file" name="upfile" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" cols="30" rows="5">남기실 말</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="작성" />
				<input type="reset" value="초기화" />
				<a href="List?page=${param.page }">목록으로</a>
			</td>
		</tr>
	</table>
</form>