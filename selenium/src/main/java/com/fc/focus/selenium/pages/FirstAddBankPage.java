package com.fc.focus.selenium.pages;

import org.openqa.selenium.By;

/**
 * 首次绑卡页面
 * @author Administrator
 *
 */

public class FirstAddBankPage extends BasePageObject{

	public FirstAddBankPage(SetPayPaswordPage setPayPasword) {
		// TODO Auto-generated constructor stub
		super(setPayPasword);
	}
	
	/**
	 * 银行卡号
	 * @param cardCode
	 * @throws InterruptedException
	 */
	public void inputCardCode(String cardCode) throws InterruptedException{
		String id="cardCode";
		driver.findElement(By.id(id)).sendKeys(cardCode);
		Thread.sleep(2000);
	}
	
	/**
	 * 姓名
	 * @param cardName
	 * @throws InterruptedException
	 */
	public void inputCardName(String cardName) throws InterruptedException{
		String id="cardName";
		driver.findElement(By.id(id)).sendKeys(cardName);
		Thread.sleep(2000);
	}
	
	/**
	 * 身份证号
	 * @param identity
	 */
	public void inputID(String identity){
		String id="IDcard";
		driver.findElement(By.id(id)).sendKeys(identity);
	}
	
	/**
	 * 银行预留手机号
	 * @param iphoneNum
	 * @throws InterruptedException
	 */
	public void inputBankIphone(String iphoneNum) throws InterruptedException{
		String id="phonenum";
		driver.findElement(By.id(id)).sendKeys(iphoneNum);
		Thread.sleep(2000);
	}
	
	/**
	 * 添加
	 * @return 
	 * @return 
	 * @throws InterruptedException 
	 */
	/*public  boolean addCard() throws InterruptedException{
		String id="addBankCardBtn";
		driver.findElement(By.id(id)).click();
//		System.out.print("y");
		boolean f=waitForLoad(By.id("registerSuccessWin"));
	
//		Thread.sleep(2000);
//		if (f) {
//			System.out.print("1");
//	     
//		}
//		else  {
//			System.out.print("2");
//		}
		return f;
	}*/
	
	/**
	 * 添加
	 * @throws InterruptedException
	 */
	public  void addCard() throws InterruptedException{
		String id="addBankCardBtn";
		driver.findElement(By.id(id)).click();
		Thread.sleep(5000);
		}
	
	/**
	 * 跳转至鉴权页面
	 * @return
	 */
	public GetVeriCodePage redirectCode(){
		return new GetVeriCodePage(this);
	}
    
	/**
	 * 绑卡成功页
	 * @return
	 */
	public RegisterSuccessPage redirectSuccess(){
		return new RegisterSuccessPage(this);
	}

}
