<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
    <!-- css 가져오기 -->
    <link rel="stylesheet" type="text/css" href="/coco/resources/semantic.min.css">

    <style type="text/css">
        body {
            background-color: #DADADA;
        }

        body>.grid {
            height: 100%;
        }

        .image {
            margin-top: -100px;
        }

        .column {
            max-width: 450px;
        }

    </style>
</head>

<body>
    <div class="ui middle aligned center aligned grid">
        <div class="column">
            <h2 class="ui teal image header">
                회원가입 페이지
            </h2>
            <div class="ui large form">
                <div class="ui stacked segment">
                    <div class="field">
                        <div class="ui left icon input">
                            <input type="text" id="u_id" placeholder="아이디" autofocus autocomplete="off">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <input type="password" id="u_pw" placeholder="비밀번호">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui left icon input">
                            <input type="password" id="u_pw2" placeholder="비밀번호 확인">
                        </div>
                    </div>
                    <button class="ui fluid large teal submit button" id="register_btn">회원가입</button>
                </div>

                <div class="ui error message"></div>

            </div>

            <div class="ui message">
                로그인 할 계정이 있다면 <a href="/coco/">여기</a>를 눌러주세요.
            </div>
        </div>
    </div>
    <!-- js 가져오기 -->
    <script src="/coco/resources/jquery3.3.1.min.js"></script>
    <script src="/coco/resources/semantic.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#register_btn").click(function() {
                var json = {
                    u_id: $("#u_id").val(),
                    u_pw: $("#u_pw").val(),
                    u_pw2: $("#u_pw2").val()
                };

                for (var str in json) {
                    if (json[str].length == 0) {
                        alert($("#" + str).attr("placeholder") + " 정보를 입력해주세요.");
                        $("#" + str).focus();
                        return false;
                    }
                }

                $.ajax({
					type : "post",
					url : "register",
					data : json,
					success : function(data) {
						switch (Number(data)) {
						case 0:
							alert("정상적으로 회원가입 되었습니다.");
							window.location.href = "/coco";
							break;
						case 1:
							alert("아이디가 중복 되었습니다.");
							break;
						default:
							alert("알수없는 오류가 발생 했습니다. [Error Code :" + Number(data) + "]");
							break;
						}
					},
					error : function(error) {
						alert("오류 발생"+ error);
					}
				});
            });
        });

    </script>
</body>

</html>
