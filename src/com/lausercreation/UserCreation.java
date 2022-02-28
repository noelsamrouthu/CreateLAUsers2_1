package com.lausercreation;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UserCreation extends FunctionsAll{

	
	
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception{
				
		int i;
		int r=1;
		String username;
		String iternum;
		int iteratenum;
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Repository\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.get("https://sit.my.la.gov/en-us/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.id("details-button")).click();
		driver.findElement(By.id("proceed-link")).click();
		driver.findElement(By.id("cookie-closer")).click();
		username = JOptionPane.showInputDialog("Enter the username you want to use:\n (Remember that this username will be created along with the iteration. "
				+ "								\n Remember to use a unique username each time and more than 8 characters.)");
		iternum = JOptionPane.showInputDialog("Enter the number of IDs you want to create");
		iteratenum = Integer.parseInt(iternum);
		
			for(i=1; i<=iteratenum; i++)
			{
				
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Name_FirstName"))));
			driver.findElement(By.id("Name_FirstName")).sendKeys("Testing");
			
			driver.findElement(By.id("Name_LastName")).sendKeys("User");
			
			driver.findElement(By.id("Credentials_UserIdentifier_UserId")).sendKeys(username+i);
			System.out.println(username+i);
			driver.findElement(By.id("Credentials_UserPassword_Password")).sendKeys("password_p1");
			
			driver.findElement(By.id("Credentials_ConfirmPassword")).sendKeys("password_p1");
			
			driver.findElement(By.id("PersonalIdentifier_Pin")).sendKeys("123456");
			
			driver.findElement(By.id("PersonalIdentifier_ConfirmPin")).sendKeys("123456");
			
			driver.findElement(By.id("Contact_UserEmail_Email")).sendKeys(username+i+"@mailcatch.com");
			
			driver.findElement(By.id("Contact_UserEmail_Email")).sendKeys(Keys.ENTER);
			
			try {
				Thread.sleep(2500);
			driver.findElement(By.xpath("//a[@href='/en-us/modifyprofile/redirectuserbacktoreferralurl']")).click();
			System.out.println("Clicked on Back");
			
			 
			FunctionsAll.createUser(username+i,username+i+"@mailcatch.com", r);
			r=r+1;
			}
		 catch (Exception e) {

				System.out.println("Email not sent for"+username+i);	
				//WriteData.createUser("pallavir"+i,"pallavir"+i+"@mailinator.com", r);
				//r=r+1;
				driver.navigate().refresh();
				driver.findElement(By.id("Credentials_UserIdentifier_UserId")).clear();
				driver.findElement(By.id("Contact_UserEmail_Email")).clear();
		}
		
			
		}
		
		//if (driver.findElement(By.xpath("//*[@id=\"section1\"]/div/div[1]/div/ul/li")) != null) {
			
			//driver.findElement(By.id("Contact_UserEmail_Email")).sendKeys(Keys.ENTER);
			
			//driver.findElement(By.xpath("//a[@href='/en-us/modifyprofile/redirectuserbacktoreferralurl']")).click();
			 
			//WriteData.createUser("testauto"+i,"testauto"+i+"@mailinator.com", r);
		//	r=r+1;
		//} else {
		//	driver.findElement(By.xpath("//a[@href='/en-us/modifyprofile/redirectuserbacktoreferralurl']")).click();
			 
		//	WriteData.createUser("testauto"+i,"testauto"+i+"@mailinator.com", r);
		//	r=r+1;	
		//}
		
		//driver.findElement(By.xpath("//a[@href='/en-us/modifyprofile/redirectuserbacktoreferralurl']")).click();
		 
		//WriteData.createUser("goodtest"+i,"goodtest"+i+"@mailinator.com", r);
		//r=r+1;	
			driver.close();
			
		/////////////////////////////////////////////////////////////////
			///Opening a new browser for Mailcatch
			
			int z =1;
			String valueEmail;
			System.setProperty("webdriver.chrome.driver", "C:\\Repository\\chromedriver.exe");
			WebDriver driver1 = new ChromeDriver();
			WebDriverWait wait1=new WebDriverWait(driver1, 10);
			
			driver1.get("http://mailcatch.com/en/disposable-email");
			driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver1.manage().window().maximize();
		//	waitForElement(driver, "id", "addOverlay");
			driver1.findElement(By.xpath("//input[@type='text']")).sendKeys("GO");
			driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
			waitForElement(driver1, "xpath", "//input[@type='text']");
			
			for(int i1=1; i1<=iteratenum; i1++)
			{
				System.out.println(i1);
				valueEmail = readData("UserCred", z); 
				driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.CONTROL + "a");
				driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.DELETE);
				System.out.println("Cleared the search field");
				//waitForElement(driver, "xpath", "//input[@type='text']");
				driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(valueEmail);
				Thread.sleep(2500);
				driver1.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
				
				System.out.println("Entered into " +valueEmail);
				
				
				try {
					wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Confirm Your Email')]")));
					driver1.findElement(By.xpath("//a[contains(text(),'Confirm Your Email')]")).click();
					System.out.println("Found Confirmation Email.");
					Thread.sleep(3000);
					WebElement frame1= driver1.findElement(By.xpath("//*[@id=\"emailframe\"]"));
					driver1.switchTo().frame(frame1);
					wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Confirm Your Email')]")));
				
					
					System.out.println("Found the confirmation link");
					String lnk=null;
					WebElement l = driver1.findElement(By.xpath("//a[contains(text(),'Confirm Your Email')]"));
					lnk = l.getAttribute("href");
					System.out.println("Link is stored in 'lnk'");
					
					System.out.println("Link for :"+valueEmail+" is "+lnk);
					
					storeLinks(valueEmail,lnk);
					System.out.println("Link is sent into excel sheet");
					driver1.navigate().back();
					System.out.println("Navigated back to inbox");
				}
				
				catch (Exception e) {
					System.out.println("Email not found for "+valueEmail);
					
					
					
				}
							z++;
							System.out.println("Row num is increased");
							
				}
			
			driver1.close();
			/////////////////////////////////////////////////////////////////
			///Opening a new browser for email confirmation
			int r1 =1;
			int r2=1;
			String valueLink;
			
			System.setProperty("webdriver.chrome.driver", "C:\\Repository\\chromedriver.exe");
			WebDriver driver2 = new ChromeDriver();
			//WebDriverWait wait2=new WebDriverWait(driver, 10);
			driver2.get("https://sit.my.la.gov/en-us/");
			driver2.manage().window().maximize();
			driver2.findElement(By.id("details-button")).click();
			driver2.findElement(By.id("proceed-link")).click();
			//driver.findElement(By.id("cookie-closer")).click();
			for(int i2=1;i2<=iteratenum;i2++) {
				
				valueLink = retrieveLink("ConfirmationLinks", r1, r2); 
				driver2.get(valueLink);
				
				
				boolean confirmation = driver2.findElement(By.xpath("//h2[contains(text(),'Your My.La.Gov account is confirmed!')]")).isDisplayed();
				System.out.println("\n Account Activated for: " +r1+"--"+confirmation);
				r2++;
				r1++;
			}
			
			JFrame f=new JFrame();
			JOptionPane.showMessageDialog(f,"Users created successfully");
			
			driver2.quit();
			
			
			}
		
		
	}

