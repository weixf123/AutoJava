package com.testing.class12PO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.testing.autocommon.ExcelReader;
import com.testing.autocommon.ExcelWriter;
import com.testing.keyword.KeywordOfInter;

public class ReaderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		SimpleDateFormat smdf=new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String createdate=smdf.format(date);
		ExcelReader excelReader=new ExcelReader("Cases/HTTPLogin.xlsx");
		ExcelWriter excelWriter=new ExcelWriter("Cases/HTTPLogin.xlsx","Cases/Result-HttPLogin"+createdate+".xlsx");
		KeywordOfInter keyword=new KeywordOfInter();
		for(int sheetNo=0;sheetNo<excelReader.getTotalSheetNo();sheetNo++) {
			excelReader.useSheetByIndex(sheetNo);
			excelWriter.useSheetByIndex(sheetNo);
			for(int line=0;line<excelReader.rows;line++) {
				List<String> linecontent=excelReader.readLine(line);
//				System.out.println(linecontent);
				if((linecontent.get(0).length()==0&&linecontent.get(1).length()==0)
						||(linecontent.get(0)==null&&linecontent.get(1)==null)) {
					switch (linecontent.get(3)) {
					case "post":
						String response=keyword.testPost(linecontent.get(4), linecontent.get(5));
						excelWriter.writeCell(line, 11, response);
						break;
					case "saveParam":
						keyword.saveParam(linecontent.get(4), linecontent.get(5));
						break;
					case "addHeader":
						keyword.addHeader(linecontent.get(4));
						break;

					}
					
					switch (linecontent.get(7)) {
					case "equal":
						boolean result=keyword.assertSame(linecontent.get(9), linecontent.get(8));
						if(result) {
							excelWriter.writeCell(line, 10, "PASS");
						}else {
							excelWriter.writeFailCell(line, 10, "Fail");
						}
						break;
					case "contains":
						boolean resultContains=keyword.assertSame(linecontent.get(9), linecontent.get(8));
						if(resultContains) {
							excelWriter.writeCell(line, 10, "PASS");
						}else {
							excelWriter.writeFailCell(line, 10, "Fail");
						}
						break;

		
					}
				}
				
			}
		}
		excelWriter.save();

	}

}
