package com.fc.qa.functionTest;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

/**
 * 重置登录密码
 * @author Administrator
 *
 */

@Test
public class ResetLoginPwdTest extends BaseTestCase{
	
	public void resetLoginPwd() throws InterruptedException, IOException{
		String filePath="E:\\autoData\\resetLoginPwd.xlsx";
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb = new XSSFWorkbook(filePath);  
		// 读取第一章表格内容  
		XSSFSheet sheet = xwb.getSheetAt(0);  
		// 定义 row、cell  
		XSSFRow row;  
		String cell;  
	    System.out.println(sheet.getRow(1).getCell(1).toString());
		String clientId =sheet.getRow(1).getCell(0).toString().replace("/", "");
		String password =sheet.getRow(1).getCell(1).toString();
		String oldPwd = sheet.getRow(1).getCell(2).toString();
		String firstpwd = sheet.getRow(1).getCell(3).toString();
		String secondpwd = sheet.getRow(1).getCell(4).toString();
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		SettingPage settingPage =myFinancePage.setting();
		ResetLoginPwdPage resetLoginPwdPage =settingPage.resetLoginPwd();
		resetLoginPwdPage.inputOldPwd(oldPwd);
		resetLoginPwdPage.nextStep();
		resetLoginPwdPage.inputFirstpwd(firstpwd);
		resetLoginPwdPage.inputSecondpwd(secondpwd);
		resetLoginPwdPage.confirm();
		MyFinancePage myFinancePage2=resetLoginPwdPage.iKnow();
	}

}
