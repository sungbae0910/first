package com.login.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoApi {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String K_CLIENT_ID = "764d12a6de64805755aace73ffa7bfd3";
	private String K_REDIRECT_URI = "http://localhost:8080/com/login/kakaoLogin.do";
	private String k_LOGOUT_REDIRECT_URI = "http://localhost:8080/com/login/kaLogout.do";
	
	public String getKakaoAuthUrl() {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"
					+ "client_id=" + K_CLIENT_ID + "&redirect_uri="
					+ K_REDIRECT_URI + "&response_type=code";
		return kakaoUrl;
	}
	

	public String getAccessToken(String autorize_code) {
		String access_token = "";
		String requestUrl = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			// post요청을 하기 위해 setDoOutPut을 true
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id="+K_CLIENT_ID);
			sb.append("&redirect_uri="+K_REDIRECT_URI);
			sb.append("&code="+autorize_code);
			bw.write(sb.toString());
			bw.flush();
			
			//200이면 성공
			int responseCode = conn.getResponseCode();
			if(responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String result = "";
				String line = "";
				
				while((line = br.readLine()) != null) {
					result += line;
				}
				
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				access_token = element.getAsJsonObject().get("access_token").getAsString();
				br.close();
				bw.close();
			}else {
				throw new IOException("응답코드 :"+responseCode);
			}
			
		} catch (IOException e) {
			log.error("카카오토큰 발급 중 오류발생"+e.getMessage());
			e.printStackTrace();
		}
		
		
		return access_token;
	}

	
	public String getUserInfo (String access_token) {
		String nickName="";
	    String requestUrl = "https://kapi.kakao.com/v2/user/me";
	    try {
	        URL url = new URL(requestUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);
	        
	        int responseCode = conn.getResponseCode();
	        
	        if(responseCode==200) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        
		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		        
		        nickName = properties.getAsJsonObject().get("nickname").getAsString();
		        br.close();
	        }else {
	        	throw new IOException("응답코드 " + responseCode);
	        }
	        
	    } catch (IOException e) {
	    	log.error("카카오유저 정보 출력 중 오류발생"+e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return nickName;
	}

	/*
	public void kakaoLogout(String access_token) {
		String requestUrl = "https://kapi.kakao.com/v1/user/logout";
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer "+access_token);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public void oauthLogOut() {
		String requestUrl = "https://kauth.kakao.com/oauth/logout";
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("client_id="+K_CLIENT_ID);
			sb.append("&logout_redirect_uri="+k_LOGOUT_REDIRECT_URI);
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			
			int responseCode = conn.getResponseCode();
			if(responseCode!=200){
				throw new Exception("응답코드 " + responseCode);
			}
		} catch (Exception e) {
			log.error("카카오계정 로그아웃 중 오류발생 "+e.getMessage());
			e.printStackTrace();
		}
	}
}
