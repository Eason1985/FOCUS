package com.fc.focus.selenium;

import com.fc.focus.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 易钱包转出
 * @author Administrator
 *
 */

@Test

public class YqbRedemTest extends BaseTestCase{
	public void yqbRedem() throws InterruptedException{
		String clientId="15211111120";
		String password="12345678a";
		String amount="20";
		String payPassword="111111";
		LoginPage loginPage =new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage =loginPage.login(clientId, password);
		//转出前我的财富总额
		Float beforeBalance=myFinancePage.currentBalance();
		YqbPage yqbPage = myFinancePage.goYqb();
		//转出前我的易钱包总额
		Float yqbAmount1=yqbPage.totalAmount();
		
		YqbRedemPage yqbRedemPage = yqbPage.rollOut();
		yqbRedemPage.inputAmount(amount);
		PayPasswordPage payPasswordPage = yqbRedemPage.confirm();
		PayFinishedPage payFinishedPage = payPasswordPage.inutPassword(payPassword);
		payFinishedPage.finished();
		MyFinancePage myFinancePage2=payFinishedPage.myFinance();
		//转出后我的财富总额
		Float afterBalance = myFinancePage2.currentBalance();
		YqbPage yqbPage2 = myFinancePage2.goYqb();
		//转出后我的易钱包总额
		Float yqbAmount2=yqbPage2.totalAmount();
		//易钱包交易列表
		YqbListPage yqbListPage=yqbPage2.yqbList();
		yqbListPage.rollOut();
		Assert.assertEquals(yqbListPage.rollOutAmount(), Float.parseFloat(amount));
		YqbOrderDetailPage yqbOrderDetailPage=yqbListPage.rollOutDetail();
		Assert.assertEquals(yqbOrderDetailPage.amount(), Float.parseFloat(amount));
		
		//验证总资产金额
		Assert.assertEquals(afterBalance, beforeBalance-Float.parseFloat(amount));
		//验证易钱包金额
		Assert.assertEquals(yqbAmount2,yqbAmount1-Float.parseFloat(amount));
	}

}
