package com.fc.qa.functionTest;

import org.testng.Assert;
import org.testng.annotations.Test;
@Test
public class RybBuyTest extends BaseTestCase{
	
	/**
	 * 如意宝-银行卡购买
	 * @throws InterruptedException
	 */
	public void rybBank() throws InterruptedException{
		String clientId="15211111120";
		String password="12345678a";
		String amount="2000";
		String payPassword="111111";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		Float beforeBalance=myFinancePage.currentBalance();
		ProductPage productPage =myFinancePage.goCategory();
		RybListPage rybListPage = productPage.rybCategory();
		ProductDetailPage productDetailPage=rybListPage.rybProduct();
		RybOrderPage rybOrderPage =productDetailPage.rybInvest();
		rybOrderPage.addNum("1");
		PaymentPage paymentPage = rybOrderPage.gotoPay();
		paymentPage.payBank();
		PayPasswordPage passwordPage =paymentPage.confirmPay();
		PayFinishedPage payFinishedPage=passwordPage.inutPassword(payPassword);
		MyFinancePage myFinancePage2=payFinishedPage.myFinance();
		Float afterBalance = myFinancePage2.currentBalance();
		//验证总资产金额
		Assert.assertEquals(afterBalance, beforeBalance+200);
		
		
	}
	
	/**
	 * 如意宝-易钱包购买
	 * @throws InterruptedException
	 */
	
	public void rybYqb() throws InterruptedException{
		
		String clientId="15211111120";
		String password="12345678a";
		String amount="2000";
		String payPassword="111111";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		Float beforeBalance=myFinancePage.currentBalance();
		ProductPage productPage =myFinancePage.goCategory();
		RybListPage rybListPage = productPage.rybCategory();
		ProductDetailPage productDetailPage=rybListPage.rybProduct();
		RybOrderPage rybOrderPage =productDetailPage.rybInvest();
		rybOrderPage.addNum("1");
		PaymentPage paymentPage = rybOrderPage.gotoPay();
		paymentPage.payYqb();
		PayPasswordPage passwordPage =paymentPage.confirmPay();
		PayFinishedPage payFinishedPage=passwordPage.inutPassword(payPassword);
		MyFinancePage myFinancePage2=payFinishedPage.myFinance();
		Float afterBalance = myFinancePage2.currentBalance();
		//验证总资产金额
		Assert.assertEquals(afterBalance, beforeBalance);
	}
	
	/**
	 * 如意宝-银行卡购买（输入购买金额）
	 * @throws InterruptedException
	 */
	public void rybBankInutAmount() throws InterruptedException{
		String clientId="15211111120";
		String password="12345678a";
		String num="3";
		String payPassword="111111";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		Float beforeBalance=myFinancePage.currentBalance();
		ProductPage productPage =myFinancePage.goCategory();
		RybListPage rybListPage = productPage.rybCategory();
		ProductDetailPage productDetailPage=rybListPage.rybProduct();
		RybOrderPage rybOrderPage =productDetailPage.rybInvest();
		rybOrderPage.inputNum(num);;
		PaymentPage paymentPage = rybOrderPage.gotoPay();
		paymentPage.payBank();
		PayPasswordPage passwordPage =paymentPage.confirmPay();
		PayFinishedPage payFinishedPage=passwordPage.inutPassword(payPassword);
		MyFinancePage myFinancePage2=payFinishedPage.myFinance();
		Float afterBalance = myFinancePage2.currentBalance();
		//验证总资产金额
		Assert.assertEquals(afterBalance, beforeBalance+Float.parseFloat(num)*100);
		
		
	}
	
	
}
