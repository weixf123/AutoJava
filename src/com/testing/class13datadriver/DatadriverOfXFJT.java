package com.testing.class13datadriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.testing.autocommon.ExcelReader;
import com.testing.autocommon.ExcelWriter;
import com.testing.keyword.DataDrivenOfInter;


public class DatadriverOfXFJT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//记录执行时间
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd-HH+mm+ss");
		String creatdate=sdf.format(date);
		//创建ExcelReader对象，打开用例文件
		ExcelReader OrigCase=new ExcelReader("Cases/HTTPXFJT.xlsx");
		//创建ExcelWriter对象，复制一份结果文件，并且打开它进行操作
		ExcelWriter resultCase=new ExcelWriter("Cases/HTTPXFJT.xlsx", "Cases/result-HTTPXFJT"+creatdate+".xlsx");
		DataDrivenOfInter key=new DataDrivenOfInter(resultCase);
		//遍历sheet页
		for(int sheetNo=0;sheetNo<OrigCase.getTotalSheetNo();sheetNo++) {
			//指定使用当前下标的sheet页
			OrigCase.useSheetByIndex(sheetNo);
			resultCase.useSheetByIndex(sheetNo);
			for(int rowNo=0;rowNo<OrigCase.rows;rowNo++) {
				//传递line,告知datadriver的方法，当前执行到哪一行，应该在哪一行写结果
//				key.line=rowNo;
				key.getline(rowNo);
				//调用方法完成测试的执行和结果的写入
				List<String> linecontent=OrigCase.readLine(rowNo);
				//判断第一列和第二列为空
				if((linecontent.get(0).length()==0&&linecontent.get(1).length()==0)
					||(linecontent.get(0)==null&&linecontent.get(1)==null)
					||(linecontent.get(0).trim().equals("")&&linecontent.get(1).trim().equals(""))){
					//通过第4列，关键字列，判断当前操作是什么
					switch (linecontent.get(3)) {
					case "post":
						String response=key.testPost(linecontent.get(4), linecontent.get(5));
						break;
					case "saveParam":
						key.saveParam(linecontent.get(4), linecontent.get(5));
						break;
					case "addHeader":
						key.addHeader(linecontent.get(4));
						break;

					}
					//执行校验列对应的断言
					switch (linecontent.get(7)) {
					case "equal":
						key.assertSame(linecontent.get(9), linecontent.get(8));					
					case "contains":
						key.assertSame(linecontent.get(9), linecontent.get(8));
						break;

		
					}
						
					}
			}
		}
		//完成结果文件的保存
		resultCase.save();
	}

}
