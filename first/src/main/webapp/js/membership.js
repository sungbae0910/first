/**
 * 
 */
/* 다음주소 api */
function daumAdd(){
	new daum.Postcode({
		oncomplete:function(data){
			$('#zipCode').val(data.zonecode);
			$('#addr').val(data.address);
		}
	}).open();
}

/* 회원가입 */
function membership(){
	$("#membershipForm").attr("action", "/com/login/membership.do").submit();
}

$("#email").blur(function(){
	var email = $("#email").val();
	$.ajax({
		url : "/com/login/emailCk.do",
		type : "POST",
		data : {"email" : email},
		success : function(data){
			if(data==1){
				$("#emailCk").text("아이디가 중복되었습니다.");
				$("#emailCk").css("color", "red");
				return false;
			}else if(data==-1){
				$("#emailCk").text("오류발생");
			}else{
				$("#emailCk").text("");
			}
		},
		error:function(error){
			console.log(error);
		}
	})
})

$("#pwdCk").blur(function(){
	var pwd = $("#pwd").val();
	var pwdCk = $("#pwdCk").val();
	
	if(pwd != pwdCk){
		$("#pwdCkText").text("비밀번호가 다릅니다.");
		$("#pwdCkTextu").css("color", "red");
		return false;
	}else{
		$("#pwdCkText").text("");
	}
})


function memEvent(){
	$("#memRegi").click(function(){
		membership();
	})
}