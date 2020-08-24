package com.login.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.login.service.KakaoApi;
import com.login.service.MemberService;
import com.login.service.MemberVO;
import com.login.service.NaverApi;

@Controller
public class LoginController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KakaoApi kakao;
	@Autowired
	private NaverApi naver;
	@Autowired
	public MemberService memberService;
	

	/* 로그인페이지 */
	@RequestMapping(value = "/com/login/loginView.do")
	public String loginView(Model model, HttpSession session) {
		String kakaoUrl = kakao.getKakaoAuthUrl();
		String naverUrl = naver.getNaverAuthUrl(session);
		
		model.addAttribute("kakaoUrl", kakaoUrl);
		model.addAttribute("naverUrl", naverUrl);
		
		return "jsp/login/login";
	}
	
	/* 카카오로그인 */
	@RequestMapping(value="/com/login/kakaoLogin.do")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
		String access_token = kakao.getAccessToken(code);
		String nickName = kakao.getUserInfo(access_token);
		
		if(nickName != null) { 
			session.setAttribute("nickName", nickName);
			session.setAttribute("access_Token", access_token);
			session.setAttribute("check", "kakao");
		}else {
			return "jsp/error/error";
		}
		return "redirect:/com/first/listBrd.do";
	}
	
	/* 네이버 로그인 */
	@RequestMapping(value="/com/login/naverLogin.do")
	public String naverLogin(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session) {
		String access_token = "";
		String nickName = "";
		if(!state.equals(session.getAttribute("state"))) {
			return "error/error";
		}else {
			access_token = naver.getAccessToken(code, state);
			nickName = naver.getUserInfo(access_token);
		}
		
		if(nickName != null) { 
			session.setAttribute("nickName", nickName);
			session.setAttribute("access_Token", access_token); 
			session.setAttribute("check", "naver");
		}else {
			return "jsp/error/error";
		}
		
		return "redirect:/com/first/listBrd.do";
	}
	
	/* 네이버 로그아웃 */
	@RequestMapping(value="/com/login/naLogout.do")
	public String naLogout(HttpSession session) {
		
		if(session!=null) {
			session.removeAttribute("access_Token");
			session.removeAttribute("nickName");
			session.removeAttribute("check");
		}else {
			return "jsp/error/error";
		}
		
		return "redirect:/com/first/listBrd.do";
	}
	
	/* 카카오 로그아웃 */
	@RequestMapping(value="/com/login/kaLogout.do")
	public String kaLogout(HttpSession session) {
		if(session!=null) {
			kakao.oauthLogOut();
			session.removeAttribute("access_Token");
			session.removeAttribute("nickName");
			session.removeAttribute("check");
		}else {
			return "jsp/error/error";
		}
		
		return "redirect:/com/first/listBrd.do";
	}
	
	/* 아이디 체크 */
	@RequestMapping(value="/com/login/emailCk.do")
	@ResponseBody
	public int emailCk(@RequestParam("email") String email) {
		int result = 0;
		try {
			 result = memberService.emailCk(email);
		} catch (Exception e) {
			log.error("아이디 체크 중 오류발생" + e.getMessage());
			e.printStackTrace();
			result = -1;
		}
		return result;
	}
	
	/* 회원가입 페이지 */
	@RequestMapping(value="/com/login/membershipView.do")
	public String membershipView() {
		
		return "jsp/login/membership";
	}
	
	/* 회원가입 */
	@RequestMapping(value="/com/login/membership.do")
	public void membership(@ModelAttribute("member")MemberVO member, HttpServletResponse res) throws Exception{
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		memberService.membership(member);
		
		out.println("<script>alert('회원가입에 성공하셨습니다.'); location.href='/com/login/loginView.do' </script>");
		out.flush();
		out.close();
	}

}
