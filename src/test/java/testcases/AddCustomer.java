package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utility.DataUtils;

public class AddCustomer extends BaseTest{

	@Test(dataProviderClass = DataUtils.class,dataProvider = "data")
	public void addCustomer(String fname,String lname,String zip) {
		click("addCstmr_XPATH");
		type("firstName_XPATH",fname);
		type("lastName_XPATH",lname);
		type("postalCode_XPATH",zip);
		click("addCustomerBtn_XPATH");
		Alert al = driver.switchTo().alert();
		String alertText=al.getText();
		System.out.println(alertText);
		Assert.assertTrue(alertText.contains("Customer added successfully"),"Customer is not added");
		al.accept();
		
	}
}
