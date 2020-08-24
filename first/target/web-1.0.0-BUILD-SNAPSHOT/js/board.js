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
	
	$("#backBtn").click(function(){
		history.back();
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
	
	/* 수정 취소 버튼 */
	$("#cancelBrd").click(function(){
		$("#ck").val("X");
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
		selChange(1);
	})
	
	$("#keyword").keydown(function(key){
		if(key.keyCode == 13){
			selChange(1);
		}
	})
	
	
	$("#popup").click(function(){
		popup();
	})
	
}

function popup(){
	var url="/com/first/popup.do";
	var name="popup test";
	var option = "width=500, height=500, top=100, left=200, location=no";
	window.open(url, name, option);
		
}

/*summernoteImage 등록 */
function sendFile(file, editor){
	var form_data = new FormData();
	form_data.append("file", file);
	$.ajax({
		data : form_data,
		dataType : "json",
		type : "POST",
		url : "/com/first/summernoteUpload.do",
		enctype : "multipart/form-data",
		contentType : false,
		processData : false,
		success : function(data){
			$(editor).summernote('insertImage', data.url);
		}
	});
} 

function selChange(nowPage){
	var sel = $('#sel').val();
	var search = $('#search').val();
	$("#nowPage").val(nowPage);
	$("#cntPerPage").val(sel);
	$('#searchType').val(search);
	$("#listForm").attr("action", "/com/first/listBrd.do").submit();
}

function ckValue(value){
	var bWriter = $("#bWriter").val();
	var bSubject = $("#bSubject").val();
	var summernote = $("#summernote").val();
	summernote = summernote.replace(/(<([^>]+)>)/g, "");
	summernote = summernote.replace(/&nbsp;/g, "");
	summernote = summernote.replace(/ /g, "");
	if(bWriter=="" || bWriter.trim()==""){
		alert("작성자를 입력해주세요");
		$("#bWriter").focus();
		return false;
	}else if(bSubject=="" || bSubject.trim()==""){
		alert("제목을 입력해주세요.");
		$("#bSubject").focus();
		return false;
	}else if(summernote==""){
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

function goDetail(bNo, ck){
	$("#ck").val(ck);
	$("#bNo").val(bNo);
	$("#listForm").attr("action", "/com/first/detailBrd.do").submit();
}

/* 상세보기 조회수 증가 x */
function goDetail2(bNo){
	$("#bNo").val(bNo);
	$("#listForm").attr("action", "/com/first/detailBrd2.do").submit();
}

function listBrd(){
	$("#listForm").attr("action", "/com/first/listBrd.do").submit();
}

//글 등록 했을시
function insertListBrd(){
	location.href="/com/first/listBrd.do";
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
				insertListBrd();
			}else if(data.reValue=="U"){
				alert("글이 수정되었습니다.");
				goDetail(data.bNo, "X");
			}else if(data.reValue=="D"){
				alert("삭제가 완료되었습니다.");
				listBrd();
			}else{
				alert("실패하였습니다.")
				$("#listForm").attr("action", "/com/first/detailBrd2.do").submit();
			}
		},
		error : function(error){
			console.log(error);
		}
	})
}

