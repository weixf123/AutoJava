package com.testing.class13datadriver;

import com.testing.keyword.KeywordOfInter;

public class RestTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		key.testPost("http://www.testingedu.com.cn/inter/REST/auth", "");
		key.saveParam("tokenValue", "$.token");
		key.addHeader("{\"token\":\"{tokenValue}\"}");
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/testx/123456", "");
		key.saveParam("idValue", "$.userid");
		key.testPost("http://www.testingedu.com.cn/inter/REST/login/{idValue}", "");
		key.testPost("http://www.testingedu.com.cn/inter/REST/login", "");

	}

}
