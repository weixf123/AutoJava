package com.testing.class11cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

public class TokenTestNoMap {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client=HttpClients.createDefault();
		Map<String, String> paramMap=new HashMap<String, String>();
		//使用一个map来保存需要添加的头域键值对
		Map<String, String> headersMap=new HashMap<String, String>();
		headersMap.put("Accept", "*/*");
		headersMap.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
		headersMap.put("Content-Type", "x-www-form-urlencoded");
		Set<String> keySet=headersMap.keySet();
		//鉴权的接口auth
		HttpPost authPost=new HttpPost("http://www.testingedu.com.cn/inter/HTTP/auth");
		//通过foreach循环遍历map中所有的键值对，逐个进行头域添加
		
		for(String key:keySet) {
			authPost.addHeader(key,headersMap.get(key));
		}
		HttpResponse authResponse=client.execute(authPost);
		String authResult=EntityUtils.toString(authResponse.getEntity());
		System.out.println(authResult);
		//解析得到token值
		String tokenValue=JsonPath.read(authResult, "$.token");
		System.out.println("token的值："+tokenValue);
		headersMap.put("token", tokenValue);
		
		//注册接口
//		HttpPost registerPost=new HttpPost("http://www.testingedu.com.cn/inter/HTTP/register?username=testy&pwd=123456&nickname=testyy&describe=adadfad");
//		registerPost.setHeader("token",tokenValue);
//		HttpResponse registeResponse=client.execute(registerPost);
//		String registerResult=EntityUtils.toString(registeResponse.getEntity());
//		System.out.println(registerResult);
		
		//登录接口
		HttpPost loginPost=new HttpPost("http://www.testingedu.com.cn/inter/HTTP/login?username=testy&password=123456");
//		loginPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
//		loginPost.setHeader("token",tokenValue);
		for(String key:keySet) {
			loginPost.addHeader(key,headersMap.get(key));
		}
		HttpResponse loginResponse=client.execute(loginPost);
		String loginResult=EntityUtils.toString(loginResponse.getEntity());
		System.out.println(loginResult);
		//解析得到userid
//		String userID=JsonPath.read(loginResult, "$.userid");
		paramMap.put("userID", JsonPath.read(loginResult, "$.userid"));
		
		String url="http://www.testingedu.com.cn/inter/HTTP/getUserInfo?id={userID}";
		for(String key:paramMap.keySet()) {
			url=url.replaceAll("\\{"+key+"\\}", paramMap.get(key));
		}
		//用户接口
		HttpPost userPost=new HttpPost(url) ;
		userPost.setHeader("token",tokenValue);
		HttpResponse userResponse=client.execute(userPost);
		String userResult=EntityUtils.toString(userResponse.getEntity());
		System.out.println(userResult);
		
		//注销接口
		HttpPost logoutPost=new HttpPost("http://www.testingedu.com.cn/inter/HTTP/logout");
		logoutPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
		logoutPost.setHeader("token",tokenValue);
		HttpResponse logoutResponse=client.execute(logoutPost);
		String logoutResult=EntityUtils.toString(logoutResponse.getEntity());
		System.out.println(logoutResult);

		
		

	}
}
