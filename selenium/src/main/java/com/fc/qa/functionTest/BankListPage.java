package com.fc.qa.functionTest;

import org.openqa.selenium.By;

/**
 * 银行卡列表
 * @author Administrator
 *
 */

public class BankListPage extends BasePageObject{

	public BankListPage(PaymentPage paymentPage) {
		// TODO Auto-generated constructor stub
		super(paymentPage);
	}

	public BankListPage(YqbRedemPage yqbRedemPage) {
		// TODO Auto-generated constructor stub
		super(yqbRedemPage);
	}
   
	
	public BankListPage(MyFinancePage myFinancePage) {
		// TODO Auto-generated constructor stub
		super(myFinancePage);
	}
    
	public BankListPage(GetVeriCodePage getVeriCodePage) {
		// TODO Auto-generated constructor stub
		super(getVeriCodePage);
	}

	/**
	 * 易钱包购买选择银行卡后返回易钱包
	 * @param xpath
	 * @return
	 */
	public YqbRedemPage chooseBankToYqb(String xpath){
		xpath="//*[@id='paymentList']/li[2]/div[2]";
		driver.findElement(By.xpath(xpath)).click();
		waitForLoad(By.id("showMoneyTipIco"));
		return new YqbRedemPage(this);
	}
	
	public AddBankPage addBankCard(){
		String id="addBankCardBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("selDocuments"));
		return new AddBankPage(this);
	}
}
