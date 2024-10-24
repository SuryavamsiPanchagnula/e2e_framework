package testcases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.login;

public class logintest{

	private WebDriver driver;
	private login login;
	private WebDriverWait wait;
	
	@BeforeMethod
    public void setup() {
        // Initialize a new ChromeDriver for each test case
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait= new WebDriverWait(driver,Duration.ofSeconds(40));

        login = new login(driver);
        handleAlertIfPresent();
    }
	
	 public void handleAlertIfPresent() {
			try {
	            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(40));
	            Alert alert = alertWait.until(ExpectedConditions.alertIsPresent());
	            System.out.println("Alert Text: " + alert.getText());  
	            alert.accept();  
	        } catch (Exception e) {
	            System.out.println("No alert present");
	        }
		}
	public void VerifyAfterAlert() {
		
		handleAlertIfPresent();
		WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h5[@id='logInModalLabel']")));
		Assert.assertTrue(popup.isDisplayed(),"not displayed");
		
	}
	@Test
	public void tc1_login_username_label() {
		login.redirect_to_login_popup();
		login.username_label();
	}
	
	@Test
	public void tc2_login_password_label() {
		login.redirect_to_login_popup();
		login.password_label();
		
	}
	@Test
	public void tc3_login_is_usernamefield_present() {
		login.redirect_to_login_popup();
		login.username_field();
		
	}
	
	@Test
	public void tc4_login_is_passwordfield_present() {
		login.redirect_to_login_popup();
		login.password_field();
		
	}

	@Test
	public void tc5_login() throws InterruptedException {
		
		login.redirect_to_login_popup();
		login.enterusername("psuryavamsi88@gmail.com");
		Thread.sleep(5000);
		login.enterpassword("Vamsi@2000");
		Thread.sleep(5000);
		login.loginsubmit();
		Thread.sleep(5000);
		WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nameofuser']")));
		//driver.findElement(By.xpath("//a[@id='nameofuser']"));
		Assert.assertTrue(text.isDisplayed(),"not displayed");
	}
	
	@Test
	public void tc6_invalidusername() throws InterruptedException {
		
		
		login.redirect_to_login_popup();
		login.enterusername("psurya88@gmail.com");
		Thread.sleep(5000);
		login.enterpassword("Vamsi@2000");
		Thread.sleep(5000);
		login.loginsubmit();
		VerifyAfterAlert();
	}
	
	@Test
	public void tc7_invalidpassword() throws InterruptedException {
		
		login.redirect_to_login_popup();
		login.enterusername("psuryavamsi88@gmail.com");
		Thread.sleep(5000);
		login.enterpassword("vamsi200");
		Thread.sleep(5000);
		login.loginsubmit();
		VerifyAfterAlert();
		
	}
	
	@Test
	public void tc8_onlyusernamesubmit() throws InterruptedException {
		
		
		login.redirect_to_login_popup();
		login.enterusername("psuryavamsi88@gmail.com");
		Thread.sleep(5000);
		login.loginsubmit();
		VerifyAfterAlert();
		
	}
	
	@Test
	public void tc_9_onlypasswordsubmit() throws InterruptedException {
		
		login.redirect_to_login_popup();
		login.enterpassword("Vamsi@2000");
		Thread.sleep(5000);
		login.loginsubmit();
		VerifyAfterAlert();
	}
	@Test
	public void tc_10_cancellogin() throws InterruptedException {
		
		
		login.redirect_to_login_popup();
		login.login_closepopup();
		Thread.sleep(5000);
		WebElement popup = driver.findElement(By.xpath("//div//h5[@id='logInModalLabel']"));
		WebElement popupvisible = wait.until(ExpectedConditions.visibilityOf(popup));
		Assert.assertFalse(popupvisible.isDisplayed(),"not displayed");
	}
	@Test
	public void tc_11_passwordencryption() {
		
		
		login.redirect_to_login_popup();
		login.enterpassword("Vamsi@2000");
		Assert.assertTrue(login.islogin_PasswordEncrypted(),"Password not encrypted");
	}
	public void tc_12_popupname() {
		login.redirect_to_login_popup();
		WebElement popupname = driver.findElement(By.id("logInModalLabel"));
		Assert.assertTrue(popupname.isDisplayed(),"not displayed");
	}
	@AfterMethod
	public void close() {
		driver.quit();
	}
	
}