function bindEvent(){
	$("#insertBoard").click(function(){
		$("#listForm").attr("action", "/com/insertView.do" ).submit();	
	});	
	
	$("#insertBrd").click(function(){
		$("#insertForm").attr("action", "/com/first/insertBrd.do").submit();
	})
	
	$("#brdList").click(function(){
		$("#insertForm").attr("action", "/com/first/firstList.do").submit();
	})
	
}