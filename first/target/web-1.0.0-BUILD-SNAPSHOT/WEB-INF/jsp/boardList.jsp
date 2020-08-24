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
	<input type="hidden" name="bNo" id="bNo" value="0"/>
	<input type="hidden" name="nowPage" id="nowPage" value="${paging.nowPage}"/>
	<input type="hidden" name="cntPerPage" id="cntPerPage" value="${paging.cntPerPage }"/>
	<input type="hidden" name="searchType" id="searchType" value="${paging.searchType}"/>
	<input type="hidden" name="ck" id="ck" value=""/>
<div class="container">
	<div class="row">
		<div class="col-2">
			<select class="form-control" id="sel" name="sel" style="width:61%;" onchange="selChange(${paging.nowPage})">
  				<option value="5" <c:if test="${paging.cntPerPage == 5 }">selected</c:if>>5줄</option>
  				<option value="10" <c:if test="${paging.cntPerPage == 10 }">selected</c:if>>10줄</option>
			</select>
		</div>
		<div class="col-5"></div>
		<div class="col-2" style="padding : 0px 0px">
			<select id="search" name="search" class="form-control" style="width:61%; float:right;">
  				<option value="S" <c:if test="${paging.searchType == 'S' }">selected</c:if>>제목</option>
  				<option value="C" <c:if test="${paging.searchType == 'C' }">selected</c:if>>내용</option>
  				<option value="W" <c:if test="${paging.searchType == 'W' }">selected</c:if>>작성자</option>
			</select>
		</div>
		<div class="col-3" >
			<div class="input-group mb-3">
				<input type="text" name="keyword" id="keyword" value="${paging.keyword}" class="form-control" placeholder="검색어" aria-label="Recipient's username" aria-describedby="button-addon2">
			<div class="input-group-append">
				<button id="searchBtn" class="btn btn-outline-secondary" type="button" id="button-addon2">검색</button>
			</div>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<table class="table" id="tableList">
		<thead>
			<tr>
				<th style="width:10%" scope="col">번호</th>
				<th style="width:*" scope="col">제목</th>
				<th style="width:10%" scope="col">작성자</th>
				<th style="width:15%" scope="col">작성날짜</th>
				<th style="width:10%" scope="col">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="num" value="${paging.total-((paging.nowPage-1)*paging.cntPerPage) }"/>
			<c:forEach var="list" items="${vo}">
				<tr>
				<td scope="row">${num}</td>
				<td><a href="#" onclick="goDetail(${list.bNo}, 'U')">${list.bSubject}</a></td>
				<td>${list.bWriter}</td>
				<td>${list.bDate}</td>
				<td>${list.bCnt}</td>
			</tr>
			<c:set var="num" value="${num-1}"></c:set>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="container">
	<div class="row">
		<div class="col-sm"></div>
		<div class="col-sm"></div>
		<div class="col-sm">
			<button id="insertBoard" type="button" class="btn btn-primary">글 등록</button>
			<button id="popup" type="button" class="btn btn-primary">팝업창</button>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<nav aria-label="Page navigation example" style="margin: 0 auto;">
			<ul class="pagination">
				<c:if test="${paging.startPage != 1}">
					<li class="page-item"><a class="page-link"onclick="selChange(${paging.startPage-1})">Previous</a></li>
				</c:if>
				<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
<%-- 					<c:choose>
						<c:when test="${p == paging.nowPage}">
							<li class="page-item active"><a class="page-link" href="#">${p}</a></li>
						</c:when>
						<c:when test="${p != paging.nowPage}">
							<li class="page-item"><a class="page-link" href="#" onclick="selChange(${p})">${p}</a></li>
						</c:when>
					</c:choose>	 --%>
					<li class="page-item ${(p==paging.nowPage)?'active':''} "><a class="page-link" href="#" onclick="selChange(${p})">${p}</a></li>
				</c:forEach>
				<c:if test="${paging.endPage != paging.lastPage}">
					<li class="page-item"><a class="page-link" href="#" onclick="selChange(${paging.endPage+1})">Next</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</div>
</form>
</body>
</html>