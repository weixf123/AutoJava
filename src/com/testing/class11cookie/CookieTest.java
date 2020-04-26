package com.testing.class11cookie;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CookieTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//创建httpclient使用的cookiestore
		CookieStore cookieStore=new BasicCookieStore();
		//创建client
//		HttpClient client=HttpClients.createDefault();
		//创建httpclient对象时，指定使用已有的cookiestore
		HttpClient client=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		
		HttpPost loginPost=new HttpPost("http://localhost:8080/VIP03Login1/LoginClass?loginName=kaka&passWord=kaka");
		HttpResponse loginResponse=client.execute(loginPost);
		HttpEntity loginEntity=loginResponse.getEntity();
		String loginResult=EntityUtils.toString(loginEntity);
		System.out.println(loginResult);
		List<Cookie> cookies=cookieStore.getCookies();
		System.out.println("第一次之后的cookie值："+cookies);
		//基于已经创建的client继续发包，那么会使用上一次获取得到的cookie
		//创建一个新的httpclient实例，则不会使用上一次的cookie	
		//如果不想沿用上次发包过程中的cookie值，则创建client时不要指定cookiestore就可以在发包时不使用上一次的cookie值
		HttpClient client2=HttpClients.createDefault();
		//如果想沿用上次发包的cookie值，则创建的时候指定使用同一个cookiestore,实现cookie的传递
//		HttpClient client2=HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		HttpPost loginPost2=new HttpPost("http://localhost:8080/VIP03Login1/LoginClass?loginName=kaka&passWord=kaka");
		HttpResponse loginResponse2=client2.execute(loginPost2);
		HttpEntity loginEntity2=loginResponse2.getEntity();
		String loginResult2=EntityUtils.toString(loginEntity2);
		System.out.println(loginResult2);
		List<Cookie> cookies2=cookieStore.getCookies();
//		System.out.println("第一次之后的cookie值："+cookies2);
		for(Cookie c:cookies2) {
			System.out.println("第二次之后的cookie值："+c);
		}
		
		

	}

}
