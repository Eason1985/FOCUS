package com.fc.qa.functionTest;

import org.testng.annotations.Test;
/**
 * 我的如意宝列表
 * @author Administrator
 *
 */

@Test
public class MyRybListTest extends BaseTestCase{
	public void myRybList() throws InterruptedException{
		String clientId="15211111120";
		String password="12345678a";
		int height=100000;
		LoginPage page =new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = page.login(clientId, password);
		MyRybListPage myRybListPage = myFinancePage.myRyb();
		myRybListPage.unConfirmed(height);
		
		
	}

}
