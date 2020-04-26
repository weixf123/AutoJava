package com.testing.class10Json;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.jayway.jsonpath.JsonPath;

public class UploadFileTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient client=HttpClients.custom().build();
		HttpPost uploadPost=new HttpPost("http://www.testingedu.com.cn:8000/index.php/home/Uploadify/imageUp/savepath/head_pic/pictitle/banner/dir/images.html");
		MultipartEntityBuilder mtpEn=MultipartEntityBuilder.create();
		//将请求部分的参数进行封装
		File uploadFile=new File("E:\\1.png");
		mtpEn.addBinaryBody("file", uploadFile);
		mtpEn.addTextBody("id", "WU_FILE_0");
		mtpEn.addTextBody("name", "1.png");
		
		//通过multipartBuilder创建出实体
		HttpEntity uploadEntity=mtpEn.build();
		uploadPost.setEntity(uploadEntity);
		HttpResponse uploadResponse=client.execute(uploadPost);
		String uploadResult=EntityUtils.toString(uploadResponse.getEntity());
		System.out.println(uploadResult);
		String stateResult=JsonPath.read(uploadResult, "$.state");
		System.out.println("state的值是："+stateResult);
		if(stateResult.equals("SUCCESS")) {
			System.out.println("PASS");
		}else {
			System.out.println("Fail");
		}

	}

}
