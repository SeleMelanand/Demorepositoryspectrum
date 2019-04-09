package com.selenium.spectrum.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.spectrum.utility.DataInputProvider;
import com.selenium.spectrum.utility.SpectrumUtility;

public class Tc01LoanOrigination extends SpectrumUtility{

	public long starttime, endtime,duration;  

	@DataProvider(name = "getdatafromexcel")
	public Object[][] getData() {
		return DataInputProvider.getSheet("loandetails","Sheet1");
	}

	@Test(priority =1, dataProvider = "getdatafromexcel")
	public void loancreation(String LoanProcessorBranch,String LoanProcessor, String Loanofficerbranch, String Loanofficer, 
			String Loantype,String Loanprogram,String fnmdocument,String purchaseprice, 
			String loanamount, String downpayment,String propertyaddress,String propertycity,
			String propertystate, String propertyzipcode, String  propertyyearbuit ) throws InterruptedException {

			starttime = System.currentTimeMillis();
			

			WebDriverWait wait2 = new WebDriverWait(driver,240);
			wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[@class='nav-text'])[3]")));

			//Navigate to loan creation page
			driver.findElementByXPath("//div[@class='mat-button-focus-overlay']/following::div/ul[2]/li[3]/a").click();

			WebElement ddlpb = driver.findElementById("LoanProcessorBranch");
			Select  dropdownlpb = new Select(ddlpb);
			dropdownlpb.selectByVisibleText(LoanProcessorBranch);
			

			WebElement ddlp = driver.findElementById("LoanProcessor");
			Select  dropdownlp = new Select(ddlp);
			dropdownlp.selectByVisibleText(LoanProcessor);

			WebElement ddlob = driver.findElementById("LoanOfficerBranch");
			Select  dropdownlob = new Select(ddlob);
			dropdownlob.selectByVisibleText(Loanofficerbranch);

			WebElement ddlo = driver.findElementById("LoanOfficer");
			Select  dropdownlo = new Select(ddlo);
			dropdownlo.selectByVisibleText(Loanofficer);

			WebElement ddloantype = driver.findElementById("MortgageType");
			Select  dropdownloantype = new Select(ddloantype);
			dropdownloantype.selectByVisibleText(Loantype);

			System.out.println(Loanprogram);
			
			driver.findElementByXPath("//input[@formcontrolname='LoanProgramType']").click();
			driver.findElementByXPath("//input[@formcontrolname='LoanProgramType']").sendKeys(Loanprogram);
			driver.findElementByXPath("//input[@formcontrolname='LoanProgramType']").sendKeys(Keys.DOWN);
			driver.findElementByXPath("//input[@formcontrolname='LoanProgramType']").sendKeys(Keys.ENTER);

			driver.findElementByXPath("//input[@accept='.fnm']").sendKeys("D:/POM- sample/com.selenium.spectrum/testdata/"+fnmdocument+".fnm");
			//sendKeys("./testdata/"+fnmdocument+".fnm");
	
			driver.findElementByXPath("//button[@id='browseFNM']/following::button").click();		

			wait2.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//span[contains(@class,'col-blue')]"), "New Loan"));
			getloannumber();
			takesnapshot("loancreation");

			driver.findElementByXPath("//span[text()='Loan Application']").click();
			driver.findElementByXPath("(//span[text()='Subject Property'])[1]").click();
			wait2.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[text()='Term/Due in']")));
			
			driver.findElementByXPath("//i[text()='create']").click();
			
			Thread.sleep(5000);
			
			wait2.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='Save']")));
			
			Actions actionpp = new Actions(driver);
			actionpp.moveToElement(driver.findElementById("PurchasePrice"));
			actionpp.click();
			actionpp.sendKeys(Keys.CONTROL+"a");
			actionpp.sendKeys(Keys.DELETE);
			driver.findElementById("PurchasePrice").sendKeys(purchaseprice);	
			actionpp.build().perform();
			Thread.sleep(3000);
			
			/*Actions actionla = new Actions(driver);
			actionla.moveToElement(driver.findElementById("LoanAmount"));
			actionla.click();
			actionla.sendKeys(Keys.CONTROL+"a");
			actionla.sendKeys(Keys.DELETE);
			actionla.build().perform();
			driver.findElementById("LoanAmount").sendKeys(loanamount);
			Thread.sleep(3000);

			Actions actiondp = new Actions(driver);
			actiondp.moveToElement(driver.findElementById("DownPayment"));
			actiondp.click();
			actiondp.sendKeys(Keys.CONTROL+"a");
			actiondp.sendKeys(Keys.DELETE);
			actiondp.build().perform();*/
			
			driver.findElementById("DownPayment").sendKeys(downpayment);
			
			
			/*driver.findElementById("LoanAmount").click();
			driver.findElementById("LoanAmount").sendKeys(Keys.CONTROL+"a");
			driver.findElementById("LoanAmount").clear();
			driver.findElementById("LoanAmount").sendKeys(loanamount);*/
			
			/*driver.findElementById("DownPayment").click();
			driver.findElementById("DownPayment").sendKeys(Keys.CONTROL+"a");
			driver.findElementById("DownPayment").clear();
			driver.findElementById("DownPayment").sendKeys(downpayment);*/
			
			driver.findElementByXPath("//button[text()='Save']").click();
			
		//new LoanDataEdit(driver).loandetails(purchaseprice,loanamount,downpayment);
		//new LoanDataEdit(driver).propertydetailsedit(propertyaddress,propertycity,propertystate,propertyzipcode,propertyyearbuit);
		//	new Tc02OrderingCredit(driver).CreditOrder();

		//new Tc04OrderingUnderwritingDecision(driver).underwritingdecision();
	

	/*@Test(priority =2)
	public void timecalculate(){
	endtime = System.currentTimeMillis();
	duration = endtime- starttime;
	duration = duration/60000;
	System.out.println("Duration taken for loan process in spectrum:"+ duration+" minutes");
	}*/

	}


}