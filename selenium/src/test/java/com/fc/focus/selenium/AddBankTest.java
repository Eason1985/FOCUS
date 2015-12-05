package com.fc.focus.selenium;

import java.io.IOException;

import com.fc.focus.selenium.pages.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

/**
 * 绑卡
 * @author Administrator
 *
 */
@Test

public class AddBankTest extends BaseTestCase{
	public void addBankTest() throws InterruptedException, IOException{
		String filePath="E:\\autoData\\addBank.xlsx";
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb = new XSSFWorkbook(filePath);  
		// 读取第一章表格内容  
		XSSFSheet sheet = xwb.getSheetAt(0);  
		String clientId=sheet.getRow(1).getCell(0).toString().replace("/", "");;
		String password=sheet.getRow(1).getCell(1).toString().replace("/", "");;
		//绑卡信息
		String bankCard=sheet.getRow(1).getCell(2).toString().replace("/", "");
		String cardName=sheet.getRow(1).getCell(3).toString();
	    String identity=sheet.getRow(1).getCell(4).toString().replace("/", "");
	    String bankIphone=sheet.getRow(1).getCell(5).toString().replace("/", "");
				
	    //鉴权验证码
		String veriCode=sheet.getRow(1).getCell(6).toString().replace("/", "");
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
        BankListPage bankListPage = myFinancePage.bankList();
        AddBankPage addBankPage = bankListPage.addBankCard();
        addBankPage.inputCardCode(bankCard);
        addBankPage.inputCardName(cardName);
        addBankPage.inputID(identity);
        addBankPage.inputBankIphone(bankIphone);
        addBankPage.addCard();
        GetVeriCodePage getVeriCodePage =addBankPage.redirectCode();
        getVeriCodePage.inputVeriCode(veriCode);
        BankListPage bankListPage2 = getVeriCodePage.confirm2();
        
        
	}

}
