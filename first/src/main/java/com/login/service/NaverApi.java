package com.login.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class NaverApi {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String N_CLIENT_ID = "Jq09WLINW1Ej3Mv3wzO6";
	private String N_REDIRECT_URI = "http://localhost:8080/com/login/naverLogin.do";
	private String N_CLIENT_SECRET = "qgA15KTzlq";

	/* state생성을 위한 난수 생성 */
	public String randomString() {
		return UUID.randomUUID().toString();
	}
	
	public String getNaverAuthUrl(HttpSession session) {
		String state = randomString();
		
		session.setAttribute("state", state);
		
		String naverUrl = "https://nid.naver.com/oauth2.0/authorize?"
					+"client_id="+ N_CLIENT_ID + "&response_type=code"
					+"&redirect_uri="+ N_REDIRECT_URI + "&state=" + state; 
		
		return naverUrl;
	}
	
	/* 네이버 토큰발급 */
	public String getAccessToken(String code, String state) {
		String access_token = "";
		String requestUrl = "https://nid.naver.com/oauth2.0/token";
		
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			// post요청을 하기 위해 setDoOutPut을 true
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("client_id="+N_CLIENT_ID);
			sb.append("&client_secret="+N_CLIENT_SECRET);
			sb.append("&grant_type=authorization_code");
			sb.append("&state="+state);
			sb.append("&code="+code);
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
				throw new IOException("응답코드 : "+responseCode);
			}
		} catch (IOException e) {
			log.error("네이버 토큰 발급 중 오류발생"+e.getMessage());
			e.printStackTrace();
		}
		
		return access_token;
	}
	
	/* 네이버 유저 정보 */
	public String getUserInfo(String access_token) {
		String nickName="";
	    String requestUrl = "https://openapi.naver.com/v1/nid/me";
	    try {
	        URL url = new URL(requestUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_token);
	        
	        int responseCode = conn.getResponseCode();
	        if(responseCode == 200) {
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(result);
		        
		        JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();
		        
		        nickName = response.getAsJsonObject().get("name").getAsString();
		        br.close();
	        }else {
	        	throw new IOException("응답코드 :"+ responseCode);
	        }
	    } catch (IOException e) {
	    	log.error("네이버유저 정보 출력 중 오류발생"+e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return nickName;		
	}
	
	
}
