package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 注册绑卡成功页
 * @author Administrator
 *
 */

public class RegisterSuccessPage extends BasePageObject{

	public RegisterSuccessPage(GetVeriCodePage getVeriCodePage) {
		// TODO Auto-generated constructor stub
		super(getVeriCodePage);
	}
	
	public RegisterSuccessPage(FirstAddBankPage firstAddBankPage) {
		// TODO Auto-generated constructor stub
		super(firstAddBankPage);
	}

	public RegisterSuccessPage(AddBankPage addBankPage) {
		// TODO Auto-generated constructor stub
		super(addBankPage);
	}

	public ProductPage toMakeMoney(){
		String xpath="//*[@id='registerSuccessWin']/div[2]/div[2]/a";
		driver.findElement(By.xpath(xpath)).click();
		waitForLoad(By.id("toYQBLink"));
		return new ProductPage(this);
	}
}
