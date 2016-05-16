package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 获取手机验证码页面（绑卡）
 * @author Administrator
 *
 */
public class GetVeriCodePage extends BasePageObject{

	public GetVeriCodePage(FirstAddBankPage firstAddBankPage) {
		// TODO Auto-generated constructor stub
		super(firstAddBankPage);
	}
	
	public GetVeriCodePage(AddBankPage addBankPage) {
		// TODO Auto-generated constructor stub
		super(addBankPage);
	}

	public void inputVeriCode(String veriCode) throws InterruptedException{
		Thread.sleep(2000);
		String id="codeInput";
		driver.findElement(By.id(id)).sendKeys(veriCode);
	}
	
	public RegisterSuccessPage confirm(){
		String id="confirmCodeBtn";
		driver.findElement(By.id(id)).click();
		waitForLoad(By.id("registerSuccessWin"));
		return new RegisterSuccessPage(this);
	}
	
	public BankListPage confirm2(){
		String id="confirmCodeBtn";
		driver.findElement(By.id(id)).click();
//		waitForLoad(By.id("registerSuccessWin"));
		return new BankListPage(this);
	}
}
