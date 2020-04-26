package com.testing.class13datadriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.testing.keyword.KeywordOfInter;

public class SoapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KeywordOfInter key=new KeywordOfInter();
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:auth></soap:auth></soapenv:Body></soapenv:Envelope>");
		key.saveParam("token", "$.token");
		key.addHeader("{\"token\":\"{token}\"}");
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:login><arg0>testx</arg0><arg1>123456</arg1></soap:login></soapenv:Body></soapenv:Envelope>");
		key.saveParam("idValue", "$.userid");
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:getUserInfo><arg0>{idValue}</arg0></soap:getUserInfo></soapenv:Body></soapenv:Envelope>");
		key.testPostSoap("http://www.testingedu.com.cn/inter/SOAP?wsdl", "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soap=\"http://soap.testingedu.com/\"><soapenv:Header/><soapenv:Body><soap:logout></soap:logout></soapenv:Body></soapenv:Envelope>");
	}

}
