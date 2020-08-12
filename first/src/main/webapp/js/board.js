function bindEvent(){
	/* 입력화면  */
	$("#insertBoard").click(function(){
		$("#listForm").attr("action", "/com/insertView.do" ).submit();	
	});	
	
	/* 글 입력 */
	$("#insertBrd").click(function(){
		if(confirm("입력하시겠습니까?")){
			ckValue("I");
		}else{
			return false;
		}
	})
	
	/* 글 목록 */
	$("#brdList").click(function(){
		listBrd();
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
		if(confirm("수정하시겠습니까?")){
			ckValue("U");
		}else{
			return false;
		}
	})
	
	/* 취소 버튼 */
	$("#cancelBrd").click(function(){
		$("#listForm").attr("action", "/com/first/detailBrd.do").submit();
	})
	
	/* 삭제 */
	$("#deleteBrd").click(function(){
		if(confirm("정말 삭제하시겠습니까?")){
			var list = $("#listForm").serialize();
			var url = "/com/first/deleteBrd.do";
			ajaxBrd(url, list);
		}else{
			return false;
		}
	})
	
	/* 검색 */
	$("#searchBtn").click(function(){
		selChange();
	})
	
	$("#keyword").keydown(function(key){
		if(key.keyCode == 13){
			selChange();
		}
	})
	
}

function selChange(nowPage){
	var sel = $('#sel').val();
	var search = $('#search').val();
	if(nowPage != null){
		$("#nowPage").val(nowPage);
	}
	$("#cntPerPage").val(sel);
	$('#searchType').val(search);
	$("#listForm").attr("action", "/com/first/listBrd.do").submit();
}

function ckValue(value){
	var pattern = /^[A-Z|a-z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+$/;
	var bWriter = $("#bWriter").val();
	var bSubject = $("#bSubject").val();
	var summernote = $("#summernote").val();
	summernote = summernote.replace(/(<([^>]+)>)/gi, "");
	summernote = summernote.replace(/&nbsp;/gi, "");
	summernote = summernote.trim();
	if(bWriter=="" || bWriter.trim()==""){
		alert("작성자를 입력해주세요");
		$("#bWriter").focus();
		return false;
	}else if(bSubject=="" || bSubject.trim()==""){
		alert("제목을 입력해주세요.");
		$("#bSubject").focus();
		return false;
	}else if(summernote=="" || !pattern.test(summernote)){
		alert("내용을 입력해주세요");
		$("#summernote").focus();
		return false;
	}else{
		var list = $("#listForm").serialize();
		if(value=="U"){
			var url = "/com/first/updateBrd.do";
		}else if(value=="I"){
			var url = "/com/first/insertBrd.do";
		}
		ajaxBrd(url, list);
	}
}

function goDetail(bNo){
	$("#bNo").val(bNo);
	$("#listForm").attr("action", "/com/first/detailBrd.do").submit();
}

function listBrd(){
	$("#listForm").attr("action", "/com/first/listBrd.do").submit();
}


function ajaxBrd(url, list){
	$.ajax({
		url : url,
		type : "POST",
		dataType : "json",
		data : list,
		success : function(data){
			if(data.reValue == "I"){
				alert("글이 등록되었습니다.");
				listBrd();
			}else if(data.reValue=="U"){
				alert("글이 수정되었습니다.");
				goDetail(data.bNo);
			}else if(data.reValue=="D"){
				alert("삭제가 완료되었습니다.");
				listBrd();
			}else{
				alert("실패하였습니다.")
			}
		},
		error : function(error){
			console.log(error);
		}
	})
}

