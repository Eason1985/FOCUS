package com.fc.focus.selenium;

import com.fc.focus.selenium.pages.LoginPage;
import com.fc.focus.selenium.pages.MyFinancePage;
import com.fc.focus.selenium.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 新用户注册
 * @author Administrator
 *
 */
@Test
public class RegisterTest extends BaseTestCase{
	public void register() throws InterruptedException{
		String iphoneNum="15211111140";
		String password="12345678a";
		String phoneMsg="888888";
		String email="asdf@163.com";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		RegisterPage registerPage=loginPage.register();
		registerPage.inputIphone(iphoneNum);
		registerPage.inputPassword(password);
		registerPage.getCode();
		registerPage.inputCode(phoneMsg);
		registerPage.inputMail(email);
		MyFinancePage myFinancePage = registerPage.confirmRegister();
	
		float currentBalance=myFinancePage.currentBalance();
		System.out.println(currentBalance);
		Assert.assertEquals(currentBalance,Float.parseFloat("0"));
//		Assert.assertEquals(currentBalance, "0.0", "1");
		
	}

}
