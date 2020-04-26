package com.testing.adminShopping;

import com.testing.autocommon.AutoLogger;
import com.testing.keyword.KeywordOfWeb;

public class AdminTestPO {

	public static void main(String[] args) {
		KeywordOfWeb kw=new KeywordOfWeb();
		kw.openBrowser("chrome");
		AutoLogger.log.info("=========后台登录用例=========");
		AdminLoginPage adlogin=new AdminLoginPage(kw);
		adlogin.load();
		adlogin.loginAdmin();
		AutoLogger.log.info("=========后台添加商品用例=========");
		AddGoodsPage adg=new AddGoodsPage(kw);
		adg.load();
		adg.addGoods();
		AutoLogger.log.info("=========执行完成=========");
		kw.closeBrowser();

	}

}
