package com.first.web;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.first.service.FirstService;
import com.first.service.FirstVO;
import com.first.service.PagingVO;

@Controller
public class FirstController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	JSONObject json = new JSONObject();
	HashMap<String, Object> map = new HashMap<String, Object>();
	String reValue = "";

	@Autowired
	private FirstService firstService;

	
	  /* 글 목록 */
	  @RequestMapping(value = "/com/first/listBrd.do") 
	  public String firstList(PagingVO paging, Model model) { 
	  log.debug("글 목록 조회"); 
	  int nowPage = paging.getNowPage(); 
	  int cntPerPage = paging.getCntPerPage(); 
	  String keyword = paging.getKeyword(); 
	  String searchType = paging.getSearchType();
	  
	  int total = firstService.cntBrd(paging);
	  
	  if (nowPage == 0 && cntPerPage == 0) { 
		  nowPage = 1; 
		  cntPerPage = 5; 
	  }else if(nowPage == 0) { 
		  nowPage = 1; 
	  }
	  
	  paging = new PagingVO(total, nowPage, cntPerPage, keyword, searchType);
	  List<FirstVO> vo = firstService.listBrd(paging);
	  
	  model.addAttribute("paging", paging); 
	  model.addAttribute("vo", vo);
	  
	  return "jsp/boardList"; 
	  
	  }
	 

	/* 글 목록 */
	/*
	 * @RequestMapping(value = "/com/first/listBrd.do") public String
	 * firstList(@ModelAttribute("paging")FirstVO paging, Model model) {
	 * log.debug("글 목록 조회"); int total = firstService.cntBrd(paging);
	 * 
	 * paging.calcLastPage(total, paging.getCntPerPage());
	 * paging.calcStartEndPage(paging.getNowPage(), 5);
	 * paging.calcStartEnd(paging.getNowPage(), paging.getCntPerPage());
	 * 
	 * List<FirstVO> vo = firstService.listBrd(paging);
	 * 
	 * model.addAttribute("paging", vo);
	 * 
	 * return "jsp/boardList"; }
	 */
	/* 입력화면 출력 */
	@RequestMapping(value = "/com/insertView.do")
	public String insertBoard(@ModelAttribute("paging") PagingVO paging) {

		return "jsp/boardRegist";
	}

	/* 글 입력 */
	@RequestMapping(value = "/com/first/insertBrd.do")
	@ResponseBody
	public Object insertBrd(FirstVO vo) {
		int result = firstService.insertBrd(vo);

		reValue = valuePro(result, "I");

		map.put("reValue", reValue);

		return map;
	}

	/* 섬머노트 이미지 업로드 */
	@RequestMapping(value = "/com/first/summernoteUpload.do")
	@ResponseBody
	public Object summernoteUpload(MultipartHttpServletRequest req) {
		String fileRoot = req.getSession().getServletContext().getRealPath("/summernoteImage/");
		log.info(fileRoot);
		MultipartFile file = req.getFile("file");

		String oriFileName = file.getOriginalFilename();
		String extension = oriFileName.substring(oriFileName.lastIndexOf(".")); // 확장자 자르기 String
		String savedFileName = UUID.randomUUID() + extension;

		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = file.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			map.put("url", "/summernoteImage/" + savedFileName);
		} catch (Exception e) {
			FileUtils.deleteQuietly(targetFile);
			map.put("resCode", "error");
			e.printStackTrace();
		}
		return map;
	}

	/* 상세보기 */
	@RequestMapping(value = "/com/first/detailBrd.do")
	public String detailBrd(@ModelAttribute("paging") PagingVO paging, HttpServletRequest req, Model model) {
		FirstVO vo = firstService.detailBrd(Integer.parseInt(req.getParameter("bNo")));

		model.addAttribute("vo", vo);

		return "jsp/boardDetail";
	}

	/* 수정화면 */
	@RequestMapping(value = "/com/first/updateView.do")
	public String updateView(@ModelAttribute("paging") PagingVO paging, HttpServletRequest req, Model model) {
		FirstVO vo = firstService.detailBrd(Integer.parseInt(req.getParameter("bNo")));

		model.addAttribute("vo", vo);

		return "jsp/boardUpdate";
	}

	/* 수정 */
	@RequestMapping(value = "/com/first/updateBrd.do")
	@ResponseBody
	public Object updateBrd(@ModelAttribute("paging") PagingVO paging, FirstVO vo) {
		int result = firstService.updateBrd(vo);

		reValue = valuePro(result, "U");

		map.put("reValue", reValue);
		map.put("bNo", vo.getbNo());

		return map;
	}

	/* 삭제 */
	@RequestMapping(value = "/com/first/deleteBrd.do")
	@ResponseBody
	public Object deleteBrd(@ModelAttribute("paging") PagingVO paging, HttpServletRequest req) {
		int result = firstService.deleteBrd(Integer.parseInt(req.getParameter("bNo")));

		reValue = valuePro(result, "D");

		map.put("reValue", reValue);

		return map;
	}

	public String valuePro(int value, String guBun) {
		String result = "";
		if (value < 1) {
			result = "X";
		} else {
			result = guBun;
		}
		return result;
	}
}
