<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/board.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/summernote/summernote-lite.css" rel="stylesheet" >

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/summernote/summernote-lite.js"></script>
<script src="${pageContext.request.contextPath}/js/board.js"></script>

<script>
$(document).ready(function(){
	$("#summernote").summernote({
		height: 300,
		minHeignt: null,
		maxHeight: null,
		focus: true,
		lang: "ko-KR",
		placeholder: "내용을 입력해주세요."
	});
	
	bindEvent();
})
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<nav aria-label="breadcrumb" style="margin: 0 auto;">
			<ol class="breadcrumb">
				<li class="breadcrumb-item active" aria-current="page">글 수정</li>
			</ol>
		</nav>
	</div>
</div>

<div class="container">
	<form method="post" id="updateForm">
		<div class="form-group row">
			<label for="bWriter" class="col-sm-2 col-form-label">작성자</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="bWriter" name="bWriter" placeholder="이름">
			</div>
		</div>
		<div class="form-group row">
			<label for="bSubject" class="col-sm-2 col-form-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="bSubject" name="bSubject" placeholder="제목을 입력해주세요.">
			</div>
		</div>
		<div class="form-group row">
			<label for="bContent" class="col-sm-2 col-form-label">내용</label>
			<div class="col-sm-10">
				<textarea id="summernote" name="editordata"></textarea>
			</div>
		</div>
	</form>	
</div>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col">
			<div class="btnList">
				<button id="updateBrd" type="button" class="btn btn-primary">수정완료</button>
				<button id="brdList" type="button" class="btn btn-secondary">목록</button>
			</div>
		</div>
		<div class="col"></div>
	</div>
</div>


</body>
</html>