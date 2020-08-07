function bindEvent(){
	/* 입력화면  */
	$("#insertBoard").click(function(){
		$("#listForm").attr("action", "/com/insertView.do" ).submit();	
	});	
	
	/* 글 입력 */
	$("#insertBrd").click(function(){
		if(confirm("입력하시겠습니까?")){
			if($("#bWriter").val()==""){
				alert("작성자를 입력해주세요");
				$("#bWriter").focus();
				return false;
			}else if($("#bSubject").val()== ""){
				alert("제목을 입력해주세요.");
				$("#bSubject").focus();
				return false;
			}else if($("#summernote").val()==""){
				alert("내용을 입력해주세요");
				$("#summernote").focus();
				return false;
			}else{
				$("#insertForm").attr("action", "/com/first/insertBrd.do").submit();
			}
		}else{
			return false;
		}
	})
	
	/* 글 목록 */
	$("#brdList").click(function(){
		$("#insertForm").attr("action", "/com/first/listBrd.do").submit();
	})
	
	/* 상세보기 */
	$("#brdDetail").click(function(){
		$("#listForm").attr("action", "/com/first/detailBrd.do").submit();
	})
	
	/* 수정화면 */
	$("#updateBrdView").click(function(){
		$("#listForm").attr("action", "/com/first/updateView.do").submit();
	})
	
	/* 수정 */
	$("#updateBrd").click(function(){
		$("#updateForm").attr("action", "/com/first/updateBrd.do").submit();
	})
	
	/* 삭제 */
	$("#deleteBrd").click(function(){
		$("#listForm").attr("action", "/com/first/deleteBrd.do").submit();
	})
	
}
