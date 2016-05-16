package com.fc.focus.selenium;

import com.fc.focus.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
@Test
public class YqbBuyTest extends BaseTestCase {

	
	public void yqbBuy() throws InterruptedException {
		String clientId="15211111120";
		String password="12345678a";
		String amount="2000";
		String payPassword="111111";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		Float beforeBalance=myFinancePage.currentBalance();
		YqbPage yqbPage = myFinancePage.goYqb();
		Float yqbAmount1=yqbPage.totalAmount();
		YqbBuyPage yqbBuyPage= yqbPage.rollIn();
		
		PaymentPage paymentPage=yqbBuyPage.createOrder(amount);
		paymentPage.payBank();
		PayPasswordPage passwordPage =paymentPage.confirmPay();
		PayFinishedPage payFinishedPage=passwordPage.inutPassword(payPassword);
		MyFinancePage myFinancePage2=payFinishedPage.myFinance();
		Float afterBalance = myFinancePage2.currentBalance();
		YqbPage yqbPage2 = myFinancePage2.goYqb();
		//转入后我的易钱包总额
		Float yqbAmount2=yqbPage2.totalAmount();
		//易钱包交易列表
		YqbListPage yqbListPage=yqbPage2.yqbList();
		yqbListPage.rollInList();
		Assert.assertEquals(yqbListPage.rollInAmount(), Float.parseFloat(amount));
		YqbOrderDetailPage yqbOrderDetailPage=yqbListPage.rollInDetail();
		Assert.assertEquals(yqbOrderDetailPage.amount(), Float.parseFloat(amount));
		
		//验证总资产金额
		Assert.assertEquals(afterBalance, beforeBalance+Float.parseFloat(amount));
		//验证易钱包金额
		Assert.assertEquals(yqbAmount2, yqbAmount1+Float.parseFloat(amount));
		
		
	}

}
