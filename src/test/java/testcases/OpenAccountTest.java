package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utility.DataUtils;

public class OpenAccountTest extends BaseTest {

	@Test(dataProvider = "data",dataProviderClass = DataUtils.class)
	public void openAccount(String fname,String lname,String currency) {
		click("openAccountBtn_XPATH");
		select("customerName_ID", fname+" " + lname);
		select("currency_ID",currency);
		click("processBtn_XPATH");
		Alert al = driver.switchTo().alert();
		String alertText=al.getText();
		System.out.println(alertText);
		Assert.assertTrue(alertText.contains("Account created successfully"),"Account is not created");
		al.accept();
	}
}
