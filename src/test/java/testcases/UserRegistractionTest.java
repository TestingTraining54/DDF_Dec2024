package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseTest;
import utility.DataUtils;

public class UserRegistractionTest extends BaseTest{

	@Test(dataProviderClass = DataUtils.class,dataProvider = "data")
	public void doUserReg(String fname,String lname,String address) {
		System.out.println(fname + " " + lname + " " + address);
		
	}
	
	
}
