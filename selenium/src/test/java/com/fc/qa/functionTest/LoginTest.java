package com.fc.qa.functionTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

//@Test
public class LoginTest extends BaseTestCase{
	public void login() throws InterruptedException{
		String clientId="15211111116";
		String password="12345678a";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		
		float currentBalance=myFinancePage.currentBalance();
		Assert.assertEquals(currentBalance, Float.parseFloat("0"));
	}

}
