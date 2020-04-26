package com.testing.class9inport;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class LoginTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client =HttpClients.custom().build();
		HttpPost loginPost=new HttpPost("http://localhost:8080/VIP03Login1/LoginClass?loginName=kaka&passWord=kaka");
		loginPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		loginPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
		HttpResponse loginRes=client.execute(loginPost);
		HttpEntity loginEntity=loginRes.getEntity();
		String loginResult=EntityUtils.toString(loginEntity);
		System.out.println(loginResult);
		

	}

}
