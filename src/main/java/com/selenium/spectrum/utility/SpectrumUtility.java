package com.selenium.spectrum.utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class SpectrumUtility {
		
		public RemoteWebDriver driver;
		public String loannumber,loantype;
		int number = 0;
		
		@BeforeTest
		public void browserlaunch() {
			// Launching chrome browser
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.get("https://uat.impacloans.com/home");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.findElementByLinkText("Login").click();
			driver.findElementById("UserName").sendKeys("Anand.S");
			driver.findElementById("Password").sendKeys("Demo@555");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement loginbutton;
			loginbutton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//form[@name='material_login_form']/fieldset/button/span")));
			
			loginbutton.click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[contains(@class,'text-underline')])[15]")));
			takesnapshot("loginpage");
			

			// Launching Internet Explorer
			/*
			 * System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe"); driver = new InternetExplorerDriver(); driver.manage().window().maximize();
			 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 */
		}

		/*public void spectrumlogin() throws InterruptedException {
			driver.get("https://uat.impacloans.com/home");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.findElementByLinkText("Login").click();
			driver.findElementById("UserName").sendKeys("Anand.S");
			driver.findElementById("Password").sendKeys("Demo@12345");
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement loginbutton;
			loginbutton = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//form[@name='material_login_form']/fieldset/button/span")));
			
			loginbutton.click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[contains(@class,'text-underline')])[15]")));
			takesnapshot("loginpage");
		}*/

		public void takesnapshot(String testcase) {
			number++;
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),new File("./reports/images/"+number + " -"+ testcase+".jpg"));
			} catch (WebDriverException e) {
				System.out.println("Webdriver exception occured while taking screenshot");
			} catch (IOException e) {
				System.out.println("File not found exception occured while takign screenshot");
			}

		}	
			
		public void getloantype(){
			
			String lpvalue = driver.findElementByXPath("(//p[contains(@class,'s-subheader')])[2]/following::div/div[2]/div").getText();
			System.out.println(lpvalue);
			if(lpvalue.charAt(0)=='C'){
				loantype ="conventional";
			}
			else if (lpvalue.charAt(0)=='F') {
				loantype ="fha";
			}
			else if (lpvalue.charAt(0)=='V') {
				loantype ="va";

			}else{
				loantype="iQM";
			}
			System.out.println(loantype);
		}
		
		public void getloannumber(){
			loannumber = driver.findElementByXPath("//span[contains(@class,'col-blue')]").getText();
			System.out.println("Loan created successfully: "+ loannumber);
		}
		

	}
