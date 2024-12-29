package testcases;


import org.testng.annotations.Test;
import baseTest.BaseTest;
import utility.DataUtils;


public class LoginFacebook extends BaseTest{

	@Test(dataProviderClass = DataUtils.class,dataProvider = "data")
	public void doFBLogin(String username,String password) {
		type("emailTxtbox_ID",username);
		type("passwordTxtbox_NAME",password);
		click("loginBtn_XPATH");
	}
	
	
	
}
