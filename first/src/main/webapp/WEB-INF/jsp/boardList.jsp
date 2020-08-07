<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/board.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/summernote/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/js/board.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	//화면에서 사용될 Event 
	bindEvent();
	
});


</script>
</head>
<body>
<div class="container">
	<div class="row">
		<nav aria-label="breadcrumb" style="margin: 0 auto;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active" aria-current="page">게시판 목록</li>
			</ol>
		</nav>
	</div>
</div>
<form method="post" name="listForm" id="listForm">
<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col"></div>
		<div class="col"></div>
		<div class="col">
			<div class="input-group mb-3">
				<input type="text" class="form-control" placeholder="검색어" aria-label="Recipient's username" aria-describedby="button-addon2">
			<div class="input-group-append">
				<button class="btn btn-outline-secondary" type="button" id="button-addon2">검색</button>
			</div>
			</div>
		</div>

	</div>
</div>
<div class="container">
	<table class="table" id="tableList">
		<thead>
			<tr>
				<th style="width:7%" scope="col">번호</th>
				<th style="width:7%" scope="col">제목</th>
				<th style="width:*" scope="col">내용</th>
				<th style="width:7%" scope="col">작성자</th>
				<th style="width:12%" scope="col">작성날짜</th>
				<th style="width:7%" scope="col">조회수</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td scope="row">1</td>
				<td>Mark</td>
				<td id="brdDetail"><a href="#" >Otto</a></td>
				<td>@mdo</td>
				<td>@mdo</td>
				<td>@mdo</td>
			</tr>
			<tr>
				<td scope="row">2</td>
				<td>Jacob</td>
				<td>Thornton</td>
				<td>@fat</td>
				<td>@mdo</td>
				<td>@mdo</td>
			</tr>
			<tr>
				<td scope="row">3</td>
				<td>Larry</td>
				<td>the Bird</td>
				<td>@twitter</td>
				<td>@mdo</td>
				<td>@mdo</td>
			</tr>
		</tbody>
	</table>
</div>
<div class="container">
	<div class="row">
		<div class="col-sm"></div>
		<div class="col-sm"></div>
		<div class="col-sm">
			<button id="insertBoard" type="button" class="btn btn-primary">글 등록</button>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<nav aria-label="Page navigation example" style="margin: 0 auto;">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>
	</div>
</div>
</form>
</body>
</html>