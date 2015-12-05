package com.fc.focus.selenium;

import java.io.IOException;

import com.fc.focus.selenium.pages.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 新用户注册、设置密码、绑卡
 * @author Administrator
 *
 */

@Test
public class NewUserTest extends BaseTestCase{
	public void newUser() throws InterruptedException, IOException{
		String filePath="E:\\autoData\\newUser.xlsx";
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb = new XSSFWorkbook(filePath);  
		// 读取第一章表格内容  
		XSSFSheet sheet = xwb.getSheetAt(0);  
//	    System.out.println(sheet.getRow(1).getCell(1).toString());
		
	    
		//注册信息
		String iphoneNum=sheet.getRow(1).getCell(0).toString().replace("/", "");
		String password=sheet.getRow(1).getCell(1).toString();
		String phoneMsg=sheet.getRow(1).getCell(2).toString().replace("/", "");
		String email=sheet.getRow(1).getCell(3).toString();
		
		//支付密码
		String payPassword=sheet.getRow(1).getCell(4).toString().replace("/", "");
		
		//绑卡信息
		String bankCard=sheet.getRow(1).getCell(5).toString().replace("/", "");
		String cardName=sheet.getRow(1).getCell(6).toString();
		String identity=sheet.getRow(1).getCell(7).toString().replace("/", "");
		String bankIphone=sheet.getRow(1).getCell(8).toString().replace("/", "");
		
		
	    //鉴权验证码
		String veriCode=sheet.getRow(1).getCell(0).toString().replace("/", "");
		
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		RegisterPage registerPage=loginPage.register();
		registerPage.inputIphone(iphoneNum);
		registerPage.inputPassword(password);
		registerPage.getCode();
		registerPage.inputCode(phoneMsg);
		registerPage.inputMail(email);
		MyFinancePage myFinancePage = registerPage.confirmRegister();
//		MyFinancePage myFinancePage = loginPage.login(iphoneNum, password);
		float currentBalance=myFinancePage.currentBalance();
		System.out.println(currentBalance);
		Assert.assertEquals(currentBalance,Float.parseFloat("0"));
		
		SetPayPaswordPage setPayPaswordPage=myFinancePage.firstAddBank();
		setPayPaswordPage.firstPassword(payPassword);
		setPayPaswordPage.nextStep();
		setPayPaswordPage.secondPassword(payPassword);
		FirstAddBankPage firstAddBankPage=setPayPaswordPage.confirm();
		firstAddBankPage.inputCardCode(bankCard);
		firstAddBankPage.inputCardName(cardName);
		firstAddBankPage.inputID(identity);
		firstAddBankPage.inputBankIphone(bankIphone);
		firstAddBankPage.addCard();
		RegisterSuccessPage registerSuccessPage ;
//		System.out.println(firstAddBankPage.addCard());
//		System.out.println("hello");
		/*if (firstAddBankPage.addCard()) {
			registerSuccessPage = firstAddBankPage.redirectSuccess();
		} else {
			GetVeriCodePage getVeriCodePage=firstAddBankPage.redirectCode();
			//手机验证码
			
			getVeriCodePage.inputVeriCode(veriCode);
			registerSuccessPage = getVeriCodePage.confirm();
		}
		*/
		
		GetVeriCodePage getVeriCodePage=firstAddBankPage.redirectCode();
		//手机验证码
		
		getVeriCodePage.inputVeriCode(veriCode);
		registerSuccessPage = getVeriCodePage.confirm();
//		RegisterSuccessPage registerSuccessPage=firstAddBankPage.addCard();
		registerSuccessPage.toMakeMoney();
	}

}
