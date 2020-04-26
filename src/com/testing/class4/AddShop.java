package com.testing.class4;

import com.testing.keyword.KeywordOfWeb;

public class AddShop {
	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("chrome");
		addGoods(kw);
	}
	private static void addGoods(KeywordOfWeb kw) {
		kw.click("//a[text()='商城']");
		kw.intoIframe("//iframe[@name='workspace']");
		kw.click("//div[@title='添加商品']");
		kw.inputByXpath("//input[@name='goods_name']", "星巴克新增商品");
		kw.selectText("//select[@name='cat_id']", "食品");
		kw.halt("2");
		kw.selectText("//select[@name='cat_id_2']", "休闲零食");
		kw.halt("2");
		kw.selectText("//select[@name='cat_id_3']", "营养零食");
		kw.inputByXpath("//input[@name='shop_price']", "50");
		kw.inputByXpath("//input[@name='market_price']", "55");		
		kw.click("//input[@id='imagetext']/following-sibling::input[2]");
		kw.halt("1");
		kw.intoIframe("//iframe[contains(@id,'layui-layer-iframe')]");
		kw.upload("//div[text()='点击选择文件']/following-sibling::div/input[@name='file']", "C:\\Users\\Administrator\\Desktop\\图片\\150150.png");
		kw.click("//div[text()='确定使用']");
		kw.outIframe();
		kw.intoIframe("//iframe[@name='workspace']");
		kw.click("//label[@id='is_free_shipping_label_1']");
		kw.inputByXpath("//input[@name='keywords']", "星巴克");
		kw.intoIframe("//iframe[@id='ueditor_0']");
		kw.runJs("document.getElementsByTagName(\"p\")[0].innerText=\"55845455\"");
		
		kw.outIframe();
		kw.intoIframe("//iframe[@name='workspace']");
		kw.click("//a[text()='确认提交']");
		kw.halt("2");
	}

}
