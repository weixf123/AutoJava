package com.testing.keyword;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.autocommon.AutoLogger;
import com.testing.driverself.AppDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import javafx.scene.chart.PieChart.Data;

public class KeywordOfApp {
	public AndroidDriver driver;
	public KeywordOfApp() {
		
	}
	public void wait(String time) {
		int t=1000;
		t=Integer.parseInt(time);
		try {
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void runCmd(String str) {
		String cmd="cmd /c start "+str;
		Runtime runtime=Runtime.getRuntime();
		try {
			AutoLogger.log.info("执行cmd命令"+str);
			runtime.exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error("cmd命令执行失败！");
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	
	public void startAppium(String port,String time) {
		AutoLogger.log.info("启动appiumserver服务");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		String creatdate=sdf.format(date);
		String appiumlogFile="log/"+creatdate+"AppiumLog.txt";
		String startAppiumCmd="appium -a 127.0.0.1 -p "+port+" --log "+appiumlogFile+" --local-timezone --log-timestamp";
		runCmd(startAppiumCmd);		
		try {
			int t=1000;
			t=Integer.parseInt(time);
			Thread.sleep(t*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void runAPP(String platformVersion, String deviceName, String appPackage,String appActivity,String appiumServerIP,String time) {
		try {
			AutoLogger.log.info("启动待测App");
			AppDriver app = new AppDriver(platformVersion, deviceName, appPackage, appActivity, appiumServerIP, time);
			driver = app.getdriver();
		} catch (Exception e) {
			AutoLogger.log.error("启动待测App失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
}
	public void input(String xpath,String content) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(content);
			AutoLogger.log.info("向"+xpath+"元素中输入"+content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			screenShot("input");
		}
	}
	public void click(String xpath) {
		try {
			explicityWait(xpath);
			driver.findElement(By.xpath(xpath)).click();
			AutoLogger.log.info("点击"+xpath+"元素");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			screenShot("click");
		}
	}
	
	public void explicityWait(String xpath) {
		try {
			WebDriverWait ewait=new WebDriverWait(driver, 10);
			ewait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	public void implicityWait() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
		}
	}
	public void screenShot(String method) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		String creatDate=sdf.format(date);
		String shotName="log/srcShot"+method+creatDate+".png";
		File srcShot=new File(shotName);
		File tmp=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tmp, srcShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			AutoLogger.log.error(e,e.fillInStackTrace());
			AutoLogger.log.error("截图失败");
		}
	}
	public void quitApp() {
		try {
			driver.closeApp();
		} catch (Exception e) {
			AutoLogger.log.error("关闭app失败");
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

}
