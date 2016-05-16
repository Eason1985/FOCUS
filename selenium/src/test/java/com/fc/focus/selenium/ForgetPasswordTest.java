package com.fc.focus.selenium;

import com.fc.focus.selenium.pages.ForgetPasswordPage;
import com.fc.focus.selenium.pages.LoginPage;
import com.fc.focus.selenium.pages.ResetLoginPswordPage;
import org.testng.annotations.Test;

@Test
public class ForgetPasswordTest  extends BaseTestCase{
	
	public void forgetPsword() throws InterruptedException{
		String clientId="15211111120";
		String password="12345678a";
		String smsCaptcha="888888";
		String newpassword="123456789a";
		LoginPage loginPage = new LoginPage(driver, baseUrl);
		ForgetPasswordPage forgetPasswordPage=loginPage.forgetPassword();
		forgetPasswordPage.inputIphone(clientId);
		forgetPasswordPage.getSmsCaptcha();
		forgetPasswordPage.inputSmsCaptcha(smsCaptcha);
		ResetLoginPswordPage resetLoginPswordPage=forgetPasswordPage.resetPassword();
		resetLoginPswordPage.inputFirsPsword(newpassword);
		resetLoginPswordPage.inputSecPsword(newpassword);
		resetLoginPswordPage.confirm();
		
			
	}

}
