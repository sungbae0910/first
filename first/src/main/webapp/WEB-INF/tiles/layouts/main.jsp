<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
sad
<tiles:insertAttribute name="header" />

<!-- content 위치 -->
<div id="bodyTile"><tiles:insertAttribute name="content" /></div>

<tiles:insertAttribute name="footer" />

</body>
</html>