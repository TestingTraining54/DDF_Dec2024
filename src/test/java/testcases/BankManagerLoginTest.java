package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;

public class BankManagerLoginTest extends BaseTest {

	@Test
	public void doBankManagerLogin() {
		click("bankMngrLogin_XPATH");
		Assert.assertTrue(isElementPresent("addCstmr_XPATH"),"Element is not present");
	}
}
