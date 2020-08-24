package com.first.web;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
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

	@Autowired
	private FirstService firstService;

	/* 글 목록 */
	@RequestMapping(value = "/com/first/listBrd.do")
	public String firstList(@ModelAttribute("paging") FirstVO pag, Model model) {
		log.error("글 목록 조회");

		try {
			int total = firstService.cntBrd(pag);

			pag.calcLastPage(total, pag.getCntPerPage());
			pag.calcStartEndPage(pag.getNowPage(), 5);
			pag.calcStartEnd(pag.getNowPage(), pag.getCntPerPage());

			List<FirstVO> vo = firstService.listBrd(pag);

			model.addAttribute("vo", vo);
		} catch (Exception e) {
			log.error("게시판 리스트 조회 중 오류발생");
			e.printStackTrace();
			model.addAttribute("exception", "게시판 리스트 조회 중 오류발생");
			return "error/error";
		}

		return "jsp/boardList";
	}

	/*
	 * 글 목록
	 * 
	 * @RequestMapping(value = "/com/first/listBrd.do") public String
	 * firstList(PagingVO paging, Model model) { log.debug("글 목록 조회"); int nowPage =
	 * paging.getNowPage(); int cntPerPage = paging.getCntPerPage(); String keyword
	 * = paging.getKeyword(); String searchType = paging.getSearchType();
	 * 
	 * int total = firstService.cntBrd(paging);
	 * 
	 * if (nowPage == 0 && cntPerPage == 0) { nowPage = 1; cntPerPage = 5; }else
	 * if(nowPage == 0) { nowPage = 1; }
	 * 
	 * paging = new PagingVO(total, nowPage, cntPerPage, keyword, searchType);
	 * List<FirstVO> vo = firstService.listBrd(paging);
	 * 
	 * model.addAttribute("paging", paging); model.addAttribute("vo", vo);
	 * 
	 * return "jsp/boardList";
	 * 
	 * }
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			firstService.insertBrd(vo);

			map.put("reValue", "I");
		} catch (Exception e) {
			log.error("글 입력 중 오류발생");
			e.printStackTrace();
			map.put("reValue", "X");
		}

		return map;
	}

	/* 섬머노트 이미지 업로드 */
	@RequestMapping(value = "/com/first/summernoteUpload.do")
	@ResponseBody
	public Object summernoteUpload(MultipartHttpServletRequest req) {
		HashMap<String, Object> map = new HashMap<String, Object>();
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
	public String detailBrd(@ModelAttribute("paging") FirstVO vo, HttpServletRequest req, Model model) {
//		FirstVO vo = firstService.detailBrd(Integer.parseInt(req.getParameter("bNo")));
		try {
			vo = firstService.detailBrd(vo.getbNo(), req);
			model.addAttribute("vo", vo);
		} catch (Exception e) {
			log.error("상세보기 출력 중 오류발생");
			e.printStackTrace();
			model.addAttribute("exception", "상세보기 출력 중 오류발생");
			return "error/error";
		}
		return "jsp/boardDetail";
	}

	/*
	 * 상세보기 조회수 증가 x
	 * 
	 * @RequestMapping(value = "/com/first/detailBrd2.do") public String
	 * detailBrd2(@ModelAttribute("paging") FirstVO vo, Model model) { // FirstVO vo
	 * = firstService.detailBrd(Integer.parseInt(req.getParameter("bNo"))); try { vo
	 * = firstService.detailBrd2(vo.getbNo()); model.addAttribute("vo", vo); } catch
	 * (Exception e) { log.error("조회수 증가 중 오류발생"); e.printStackTrace();
	 * model.addAttribute("exception", "상세보기 출력 중 오류발생"); return "error/error"; }
	 * 
	 * return "jsp/boardDetail"; }
	 */

	/* 수정화면 */
	@RequestMapping(value = "/com/first/updateView.do")
	public String updateView(@ModelAttribute("paging") FirstVO vo, Model model) {
//		FirstVO vo = firstService.detailBrd(Integer.parseInt(req.getParameter("bNo")));

		try {
			vo = firstService.detailBrd2(vo.getbNo());
			model.addAttribute("vo", vo);
		} catch (Exception e) {
			log.error("수정화면 출력 중 오류발생");
			e.printStackTrace();
			model.addAttribute("exception", "수정화면 출력 중 오류발생");
			return "error/error";
		}

		return "jsp/boardUpdate";
	}

	/* 수정 */
	@RequestMapping(value = "/com/first/updateBrd.do")
	@ResponseBody
	public Object updateBrd(@ModelAttribute("paging") FirstVO vo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			firstService.updateBrd(vo);

			map.put("reValue", "U");
			map.put("bNo", vo.getbNo());

		} catch (Exception e) {
			log.error("수정 중 오류발생");
			e.printStackTrace();
			map.put("reValue", "X");
		}

		return map;
	}

	/* 삭제 */
	@RequestMapping(value = "/com/first/deleteBrd.do")
	@ResponseBody
	public Object deleteBrd(@ModelAttribute("paging") FirstVO vo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			firstService.deleteBrd(vo.getbNo());

			map.put("reValue", "D");

		} catch (Exception e) {
			log.error("삭제 중 오류발생");
			e.printStackTrace();
			map.put("reValue", "X");
		}

		return map;
	}
	/*
	 * public String valuePro(int value, String guBun) { String result = ""; if
	 * (value < 1) { result = "X"; } else { result = guBun; } return result; }
	 */
	/* 팝업창 */
	@RequestMapping(value="/com/first/popup.do")
	public String popup() {
		return "jsp/popup/poptest";
	}
}
