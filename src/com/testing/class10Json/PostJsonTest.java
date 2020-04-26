package com.testing.class10Json;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class PostJsonTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client=HttpClients.custom().build();
		HttpPost jsonPost=new HttpPost("http://test.zhulogic.com/login");
		jsonPost.setHeader("Content-Type", "application/json;charset=UTF-8");
		jsonPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
		StringEntity jsonParam=new StringEntity("{\"phone\":19900000001,\"code\":\"1234\",\"unionid\":\"\",\"messageType\":3,\"channel\":\"zhulogic\"}");
		jsonParam.setContentType("application/json");
		jsonParam.setContentEncoding("charset=UTF-8");
		//设置post中发送的主体内容
		jsonPost.setEntity(jsonParam);
		HttpResponse jsonResponse=client.execute(jsonPost);
		String jsonResult=EntityUtils.toString(jsonResponse.getEntity());
		System.out.println(jsonResult);
	}

}
