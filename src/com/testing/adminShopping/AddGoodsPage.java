package com.testing.adminShopping;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.testing.keyword.KeywordOfWeb;

public class AddGoodsPage {
	public KeywordOfWeb kw;
	public String Url="http://112.74.191.10:8000/index.php/Admin/Index/index";
	
	@FindBy(xpath = "//a[text()='商城']")
	public WebElement shop;
	
	@FindBy(xpath = "//iframe[@name='workspace']")
	public WebElement workSpaceIframe;
	

	@FindBy(xpath = "//div[@title='添加商品']")
	public WebElement addGoodsBtn;
	
	@FindBy(xpath = "//input[@name='goods_name']")
	public WebElement GoodName;

	@FindBy(xpath = "//select[@name='cat_id']")
	public WebElement select1;
	
	@FindBy(xpath = "//select[@name='cat_id_2']")
	public WebElement select2;

	@FindBy(xpath = "//select[@name='cat_id_3']")
	public WebElement select3;
	
	@FindBy(xpath = "//input[@name='shop_price']")
	public WebElement sPrice;

	@FindBy(xpath = "//input[@name='market_price']")
	public WebElement mPrice;
	
	@FindBy(xpath = "//input[@id='imagetext']/following-sibling::input[2]")
	public WebElement uploadFile;

	@FindBy(xpath = "//iframe[contains(@id,'layui-layer-iframe')]")
	public WebElement layUiIframe;
	
	@FindBy(xpath = "//div[text()='点击选择文件']/following-sibling::div/input[@name='file']")
	public WebElement clickChoseFile;
	
	@FindBy(xpath = "//div[text()='确定使用']")
	public WebElement sureUse;
	
	@FindBy(xpath = "//label[@id='is_free_shipping_label_1']")
	public WebElement freeShipping;
	
	@FindBy(xpath = "//input[@name='keywords']")
	public WebElement keyWord;
	
	@FindBy(xpath = "//iframe[@id='ueditor_0']")
	public WebElement editIframe;
	
	@FindBy(xpath = "//a[text()='确认提交']")
	public WebElement sureSub;
	
	public AddGoodsPage(KeywordOfWeb kweb) {
		kw=kweb;
		PageFactory.initElements(kw.driver, this);
	}
	
	public void load() {
		kw.visitWeb(Url);
	}
	
	public void addGoods() {
		try {
			shop.click();
			kw.driver.switchTo().frame(workSpaceIframe);
			addGoodsBtn.click();
			GoodName.sendKeys("美好时光海苔");
			Select cat1=new Select(select1);
			cat1.selectByVisibleText("食品");
			kw.halt("1");
			Select cat2=new Select(select2);
			cat2.selectByVisibleText("休闲零食");
			kw.halt("1");
			Select cat3=new Select(select3);
			cat3.selectByVisibleText("营养零食");
			kw.halt("2");	
			sPrice.sendKeys("100");
			mPrice.sendKeys("50");
			uploadFile.click();
			kw.driver.switchTo().frame(layUiIframe);
			clickChoseFile.sendKeys("C:\\Users\\Administrator\\Desktop\\图片\\150150.png");
			sureUse.click();
			kw.halt("1");
			kw.driver.switchTo().defaultContent();
			kw.driver.switchTo().frame(workSpaceIframe);
			keyWord.sendKeys("海苔");
			kw.driver.switchTo().frame(editIframe);
			kw.runJs("document.getElementsByTagName(\"p\")[0].innerText=\"55845455\"");
			kw.driver.switchTo().defaultContent();
			kw.driver.switchTo().frame(workSpaceIframe);
			freeShipping.click();
			sureSub.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
