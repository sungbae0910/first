<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/js/membership.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		memEvent();
	});	
</script>
</head>
<body>
<div class="contatiner">
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<form id="membershipForm" method="post" name="membershipForm">
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="inputEmail4">이메일</label>
						<input type="email" class="form-control" id="email" name="id">
						<div class="emailCk" id="emailCk"></div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="userName">이름</label>
						<input type="text" class="form-control" id="userName" name="userName">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputPassword4">비밀번호</label>
						<input type="password" class="form-control" id="pwd" name="password">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">비밀번호확인</label>
						<input type="password" class="form-control" id="pwdCk">
					</div>
					<div class="pwdCkText" id="pwdCkText"></div>
				</div>
				<div class="row">
					<label for="zipCode" style="margin-left: 15px">주소</label>
				</div>
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="우편번호" aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button" id="button2" onclick="daumAdd()">Button</button>
					</div>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="addr" name="addr" placeholder="주소">
				</div>
				<button type="button" class="btn btn-primary" id="memRegi">회원가입</button>
			</form>			
		</div>
		<div class="col-4"></div>
	</div>
</div>

</body>
</html>