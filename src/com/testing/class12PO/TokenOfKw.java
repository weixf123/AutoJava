package com.testing.class12PO;

import com.testing.keyword.KeywordOfInter;

public class TokenOfKw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter keyW=new KeywordOfInter();
		keyW.addHeader("{\"Accept\": \"*/*\",\"Content-Type\":\"x-www-form-urlencoded\"}");
		keyW.testPost("http://www.testingedu.com.cn/inter/HTTP/auth", "");
		keyW.saveParam("tokenValue", "$.token");
		keyW.addHeader("{\"token\":\"{tokenValue}\"}");
		
		keyW.testPost("http://www.testingedu.com.cn/inter/HTTP/login", "username=testy&password=123456");
		keyW.assertContains("登录成功", "$.msg");
		keyW.saveParam("userID", "$.userid");
		
		keyW.testPost("http://www.testingedu.com.cn/inter/HTTP/getUserInfo", "id={userID}");
		keyW.assertSame("查询成功", "$.msg");
		keyW.testPost("http://www.testingedu.com.cn/inter/HTTP/logout", "");
		keyW.assertSame("用户已退出登录", "$.msg");
		
		
		
		
		
		
//		keyW.testPostUpload("http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html", "E:\\1.png");
//		keyW.assertSame("SUCCESS","$.state");

	}

}
