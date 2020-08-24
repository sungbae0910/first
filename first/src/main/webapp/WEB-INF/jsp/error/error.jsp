<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/board.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	//화면에서 사용될 Event 
	bindEvent();
});
</script>
</head>
<body>
	에러 발생
	<h3>${exception}</h3>
	<div class="container">
		<div class="row">
			<button type="button" id="backBtn" class="btn btn-primary">되돌아가기</button>
		</div>
	</div>
</body>
</html>