package com.testing.class10Json;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.testing.keyword.HttpClientKw;

public class Token {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw kw=new HttpClientKw();
		try {
			String authResult=kw.doPost("http://www.testingedu.com.cn/inter/HTTP/auth", "");
			String tokenValue=JsonPath.read(authResult,"$.token");
			System.out.println("token的值："+tokenValue);
			Map<String , String >hedMap =new HashMap<String, String>();
			hedMap.put("token", tokenValue);
			kw.addHeader(hedMap);
			kw.doPost("http://www.testingedu.com.cn/inter/HTTP/login", "username=Will&password=123456");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
