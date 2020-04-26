package com.testing.class10Json;

import com.testing.keyword.HttpClientKw;

public class CookieTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientKw kw=new HttpClientKw();
		kw.saveCookie();
		try {
			String loginResult=kw.doPost("http://localhost:8080/VIP03Login1/LoginClass", "loginName=kaka&passWord=kaka");
			System.out.println("第一次登录："+loginResult);
			String loginResult2=kw.doPost("http://localhost:8080/VIP03Login1/LoginClass", "loginName=kaka&passWord=kaka");
			System.out.println("第二次登录："+loginResult2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
