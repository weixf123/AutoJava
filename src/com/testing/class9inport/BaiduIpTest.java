package com.testing.class9inport;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

public class BaiduIpTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client=HttpClients.custom().build();
		HttpGet ipGet=new HttpGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=1.1.1.1&co=&resource_id=6006&ie=utf8&oe=gbk");
		HttpResponse ipresponse=client.execute(ipGet);
		System.out.println("ipresponse:"+ipresponse);
		//解析返回主题内容
		HttpEntity ipEntity=ipresponse.getEntity();
		System.out.println("ipEntity:"+ipEntity);
		//将实体转换成字符串
		String ipresult=EntityUtils.toString(ipEntity);
		System.out.println("ipresult:"+ipresult);
		String jsonResult=JsonPath.read(ipresult, "$.data[0].location");
		if(jsonResult.equals("澳大利亚")) {
			System.out.println("测试通过");
		}else {
			System.out.println("测试失败");
		}
	
	}

}
