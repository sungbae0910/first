<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<tiles:insertAttribute name="header" />

<c:choose>
	<c:when test="${nickName!=null}">
		<div id="bodyTile"><tiles:insertAttribute name="content" /></div>	
	</c:when>
	<c:otherwise>
		<div>로그인 먼저 해주세요 ㅎㅎㅎ</div>
	</c:otherwise>
</c:choose>
<!-- content 위치 -->


<tiles:insertAttribute name="footer" />

</body>
</html>