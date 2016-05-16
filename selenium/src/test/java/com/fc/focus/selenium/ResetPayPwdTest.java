package com.fc.focus.selenium;

import java.io.IOException;

import com.fc.focus.selenium.pages.LoginPage;
import com.fc.focus.selenium.pages.MyFinancePage;
import com.fc.focus.selenium.pages.ResetPayPwdPage;
import com.fc.focus.selenium.pages.SettingPage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

/**
 * 重置支付密码
 * @author Administrator
 *
 */

@Test
public class ResetPayPwdTest extends BaseTestCase{
	
	public void resetPayPwd() throws InterruptedException, IOException{
		String filePath="E:\\autoData\\resetPayPwd.xlsx";
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径  
		XSSFWorkbook xwb = new XSSFWorkbook(filePath);  
		// 读取第一章表格内容  
		XSSFSheet sheet = xwb.getSheetAt(0);  
		
		String clientId=sheet.getRow(1).getCell(0).toString().replace("/", "");
		String password=sheet.getRow(1).getCell(1).toString().replace("/", "");
//		String amount="2000";
//		String payPassword="123456";
		
		String originPayPwd=sheet.getRow(1).getCell(2).toString().replace("/", "");
		String newPayPwd=sheet.getRow(1).getCell(3).toString().replace("/", "");
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		MyFinancePage myFinancePage = loginPage.login(clientId, password);
		SettingPage settingPage = myFinancePage.setting();
		ResetPayPwdPage resetPayPwdPage=settingPage.resetPayPwd();
		resetPayPwdPage.inputOriginPayPwd(originPayPwd);
		resetPayPwdPage.nextConfirm();
		resetPayPwdPage.inputNewpayPwd(newPayPwd);
		resetPayPwdPage.nextStepBtn();
		resetPayPwdPage.inputSecondPwd(newPayPwd);
		resetPayPwdPage.confirmPayPasswordBtn();
		
	}

}
