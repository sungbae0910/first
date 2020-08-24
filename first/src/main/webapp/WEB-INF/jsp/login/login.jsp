<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4">
			<form>
				<div class="form-group">
					<label for="exampleInputEmail1">Email address</label>
					<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label>
					<input type="password" class="form-control" id="exampleInputPassword1">
				</div>
				<div style="margin: 0 auto;">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
				<div class="form-group form-check">
					<a href="/com/login/membershipView.do">회원가입</a>
				</div>
			</form>
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login" style="text-align:center; margin-top:5px;">
				<a href="${naverUrl}">
				<img width="100%" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
				</a>
			</div>
			<div id="kakao_id_login" style="text-align:center; margin-top:5px;">
				<a href="${kakaoUrl}">
				<img width="100%" src="${pageContext.request.contextPath}/images/kakao_login_medium_wide.png"/>
				</a>
			</div>
		</div>
		<div class="col-4"></div>
	</div>
</div>
	

</body>
</html>