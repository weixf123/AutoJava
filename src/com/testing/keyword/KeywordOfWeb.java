package com.testing.keyword;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JList.DropLocation;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.autocommon.AutoLogger;
import com.testing.driverself.GoogleDriver;

public class KeywordOfWeb {
	public WebDriver driver=null;
	
	public KeywordOfWeb() {
		
	}
	public void openBrowser(String browserType) {
		try {
			switch (browserType) {
			case "chrome":
				GoogleDriver gg=new GoogleDriver("ExeDriver/chromedriver.exe");
				driver=gg.getdriver();
				invisibleWait();
				break;
			case "IE":
				GoogleDriver ie=new GoogleDriver("ExeDriver/IEDriver.exe");
				driver=ie.getdriver();
				invisibleWait();
				break;

			default:
				GoogleDriver chrome=new GoogleDriver("ExeDriver/chromedriver.exe");
				driver=chrome.getdriver();
				invisibleWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void visitWeb(String url) {
		try {
			driver.get(url);
			AutoLogger.log.info("访问"+url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}
	
	public void inputByName(String name,String content) {
		try {
			WebElement element=driver.findElement(By.name(name));
			element.clear();
			element.sendKeys(content);
			AutoLogger.log.info("向"+name+"元素中输入"+content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void inputByXpath(String xpath,String content) {
		try {
			WebElement element=driver.findElement(By.xpath(xpath));
			element.clear();
			element.sendKeys(content);
			AutoLogger.log.info("向"+xpath+"元素中输入"+content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			saveScrShot("inputByXpath");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}
	
	public void inputByLocator(By by,String content) {
		try {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void click(String xpath) {
		try {
			WebElement element=driver.findElement(By.xpath(xpath));
			element.click();
			AutoLogger.log.info("点击"+xpath+"元素");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			saveScrShot("click");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}
	public String  getTitle() {
		
		return driver.getTitle();
	}
	//显式等待
	public void visibleWait(String xpath) {
		WebDriverWait eWait=new WebDriverWait(driver, 10);
		eWait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				WebElement element=d.findElement(By.xpath(xpath));
				return element; 
			}
		});
	}
	//隐式等待，一次设置，在driver生命周期内永久有效
	public void invisibleWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	//强制等待，单位由毫秒转换成秒
	public void halt(String waittime) {
		try {
			int time=0;
			time=Integer.parseInt(waittime);
			Thread.sleep(time*1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void assertContains(String expectResult) {
		String title=getTitle();
		if(title.contains(expectResult)) {
			System.out.println("测试通过！");
		}else {
			System.out.println("测试失败！");
		}
	}
	
	public  void  assertElementTextIs(String xpath,String expect) {
		String text=driver.findElement(By.xpath(xpath)).getText();
		String attr=driver.findElement(By.xpath(xpath)).getAttribute("href");
		System.out.println(text);
		System.out.println("href属性是："+attr);
		if(text.equals(expect)) {
			System.out.println("测试成功！");
		}else {
			System.out.println("测试失败！");
		}
	}
	//断言源码页面中包含某个内容
	public void assertPageContains(String target) {
		String pageContent=driver.getPageSource();
		if(pageContent.contains(target)) {
			System.out.println("测试成功！");
		}else {
			System.out.println("测试失败！");
		}
	}
	//悬停方法
	public void hover(String xpath) {
		try {
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(xpath))).perform();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//通过窗口标题切换窗口
	public void switchWindow(String target) {
		String targetHandle=null;
		Set<String> handleSet=driver.getWindowHandles();
		for(String t:handleSet) {
			System.out.println(t);
			if(driver.switchTo().window(t).getTitle().equals(targetHandle)) {
				targetHandle=t;
			}
		}
		try {
			driver.switchTo().window(targetHandle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("切换浏览器窗口失败！");
		}
	}
	//关闭旧窗口
	public void closeOldWindow() {
		//因为set是无序的，所以先把set中的每个句柄取出来放到list中，然后再通过list的下标来进行操作
		List<String> handleList=new ArrayList<String>();
		Set<String> handlSet=driver.getWindowHandles();
		Iterator<String> it=handlSet.iterator();
		while(it.hasNext()) {
			handleList.add(it.next().toString());
		}
		//关闭正在操作的窗口
		driver.close();
		//切换到新窗口
		try {
			driver.switchTo().window(handleList.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("切换浏览器窗口失败！");
		}
	}
	
	//关闭新窗口
	public void closeNewWindow() {
		List<String> handle=new ArrayList<String>();
		Set<String> s=driver.getWindowHandles();
		Iterator<String> it=s.iterator();
		while(it.hasNext()) {
			handle.add(it.next().toString());
		}
		try {
			//切换到新窗口，关闭
			driver.switchTo().window(handle.get(1));
			//关闭切换后的新窗口
			driver.close();
			//切换到旧窗口
			driver.switchTo().window(handle.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//进入iframe子页面
	public void intoIframe(String xpath) {
		try {
			WebElement ifram=driver.findElement(By.xpath(xpath));
			driver.switchTo().frame(ifram);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("切换到iframe失败！");
		}
	}
	
	//通过name属性进入iframe
	public void intoIframeByName(String name) {
		try {
			driver.switchTo().frame(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("切换到iframe失败！");
		}
	}
	//退出子页面
	public void outIframe() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("切换回主页面失败！");
		}
	}
	
	//获取js运行结果
	public String  getJs(String text) {
		String t="";
		JavascriptExecutor js=(JavascriptExecutor) driver;
		try {
			t=js.executeScript("return "+text).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("JS脚本执行失败");
		}
		return t;
	}
	
	
	//执行无返回的js脚本
	public void runJs(String text) {
		//强转driver为js执行器类型
		JavascriptExecutor js=(JavascriptExecutor) driver;
		try {
			js.executeScript(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("JS脚本执行失败");
		}
	}
	
	//执行浏览器滚动
	public  void scrollWindowStraight(String height) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		try {
			String jsCmd="window.scrollTo(0,"+height+")";
			js.executeScript(jsCmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作浏览器滚动条失败");
		}
	}
	
	//select通过value属性作为选择标准完成select选择
	public void selectValue(String xpath,String selector) {
		try {
			WebElement ele=driver.findElement(By.xpath(xpath));
			Select sel=new Select(ele);
			sel.selectByValue(selector);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//select通过text作为选择标准完成select选择
		public void selectText(String xpath,String selector) {
			try {
				WebElement ele=driver.findElement(By.xpath(xpath));
				Select sel=new Select(ele);
				sel.selectByVisibleText(selector);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		//upload上传文件
		public void upload(String xpath,String filePath) {
			try {
				driver.findElement(By.xpath(xpath)).sendKeys(filePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	//获取一组元素
	public void multiElementText(String xpath) {
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		for(WebElement ele:elements) {
			System.out.println(ele.getText());
		}		
	}
	
	public void saveScrShot(String method) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd+HH-mm-ss");
		String createdate=sdf.format(date);
		String srcName="log/srcShot"+method+createdate+".png";
		
		File scrShot=new File(srcName);
		File tmp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(tmp, scrShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AutoLogger.log.info("保存截图失败");
		}
	}

	
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
