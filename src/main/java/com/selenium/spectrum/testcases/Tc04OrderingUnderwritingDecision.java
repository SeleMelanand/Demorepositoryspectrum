package com.selenium.spectrum.testcases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.spectrum.utility.DataInputProvider;
import com.selenium.spectrum.utility.SpectrumUtility;

public class Tc04OrderingUnderwritingDecision extends SpectrumUtility{
	
	/*public Tc04OrderingUnderwritingDecision(RemoteWebDriver driver){
		this.driver= driver;
	}*/

	@DataProvider(name = "getdatafromexcel1")
	public Object[][] getData() {
		return DataInputProvider.getSheet("loandetails","Sheet2");
	}

	@Test(priority =1, dataProvider = "getdatafromexcel1")
	public void underwritingdecision(String Loannumber) throws InterruptedException{

		
		driver.findElementByXPath("(//i[contains(@class,'btn-icon-lg')])[3]").click();
		Thread.sleep(4000);
		//driver.findElementById("overlay-search-input").click();
		driver.findElementById("overlay-search-input").sendKeys(Loannumber,Keys.ENTER);
		
		driver.findElementByXPath("//li[@class='search-item ']/span").click();
		// till this line above code needs to be removed when it is merged to other testcases
		//Mainly this sleep is given to make system generate the MISMO file after credit is ordered.

		driver.findElementByXPath("(//a[contains(@class,'theme-link')])[3]").click();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@id='context-page-name']")));
		
		String duvalue = driver.findElementByXPath("(//p[@class='s-header'])[1]").getText();
		
		//if(duvalue.equalsIgnoreCase("Run DU")){		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("UserName")));	
		driver.findElementById("UserName").sendKeys("12");
		driver.findElementById("UserName").clear();

		driver.findElementById("UserName").sendKeys("4340862");
		driver.findElementById("Password").sendKeys("2SV378TM");
		
		driver.findElementByXPath("(//button[contains(@class,'raised-button')])[1]").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(@class,'decision-info')]/div/div[5]")));
		
		takesnapshot("AUSOrder");
		driver.findElementByXPath("//span[contains(@class,'pull-right')]/md-icon").click();
		getloannumber();
		System.out.println("DU ordered Successfully: "+ loannumber);

		//}else{
			/*wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//button[contains(@class,'mat-primary')])[1]/span")));
			driver.findElementByXPath("(//button[contains(@class,'mat-primary')])[1]/span").click();
			System.out.println("Out of scope");*/
		//}
				
	}
	

}

