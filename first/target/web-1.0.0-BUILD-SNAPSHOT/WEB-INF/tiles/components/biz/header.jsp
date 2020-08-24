<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Header</h1>

<c:choose>
	<c:when test="${nickName != null}">
		<h2>'${nickName}'</h2>${check }
		<c:choose>
			<c:when test="${check=='naver'}">
				<h3><a href="/com/login/naLogout.do">로그아웃</a></h3>
			</c:when>
			<c:otherwise>
				<h3><a href="/com/login/kaLogout.do">로그아웃</a></h3>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<a href="/com/login/loginView.do">로그인</a>
	</c:otherwise>
</c:choose>

</body>
</html>