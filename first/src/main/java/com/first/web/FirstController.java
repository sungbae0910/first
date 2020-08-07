package com.first.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.first.service.FirstService;
import com.first.service.FirstVO;

@Controller
public class FirstController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FirstService firstService;
	
	/* 글 목록 */
	@RequestMapping(value="/com/first/listBrd.do")
	public String firstList(Model model) {
		log.debug("뭔데?");
		
		return "boardList";
	}

	/* 입력화면 출력 */
	@RequestMapping(value="/com/insertView.do")
	public String insertBoard() {
		
		return "boardRegist";
	}
	/* 글 입력 */
	@RequestMapping(value="/com/first/insertBrd.do")
	public String insertBrd(@ModelAttribute("FirstVO") FirstVO vo) {
		firstService.insertBrd(vo);
		
		
		return "";
	}
	
	/* 상세보기 */
	@RequestMapping(value="/com/first/detailBrd.do")
	public String detailBrd() {
		
		
		return "boardDetail";
	}
	
	/* 수정화면 */
	@RequestMapping(value="/com/first/updateView.do")
	public String updateView() {
		
		return "boardUpdate";
	}
	
	/* 수정 */
	@RequestMapping(value="/com/first/updateBrd.do")
	public String updateBrd(FirstVO vo) {
		
		return "";
	}
}
