package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class login extends base_page {
	
	
	public login(WebDriver driver) {
		super(driver);
	}
	@FindBy(id ="loginusername") WebElement usernametext;
	@FindBy(id ="loginpassword") WebElement passwordtext;
	@FindBy(xpath ="//button[@onclick='logIn()']") WebElement loginbutton;
	@FindBy(xpath="//div[@id='logInModal']//div[@class='modal-footer']//button[@class='btn btn-secondary']") WebElement closebutton;
	@FindBy(id="login2") WebElement loginproceed;
	@FindBy(xpath="//div[@id='logInModal']//label[@for='log-name']") WebElement usernamelabel;
	@FindBy(xpath="//div[@id='logInModal']//div[2]//div[2]//label") WebElement passwordlabel;
	
	
	public void redirect_to_login_popup() {
		
		wait.until(ExpectedConditions.elementToBeClickable(loginproceed)).click(); 
		
	}
	public void enterusername(String username) {
		
		wait.until(ExpectedConditions.visibilityOf(usernametext)).sendKeys(username);
	}
	
	public void enterpassword(String password) {
		
		wait.until(ExpectedConditions.visibilityOf(passwordtext)).sendKeys(password);
	}
	
	public void loginsubmit() {
		
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton)).click();
	}
	
	public void login_closepopup() {
		
		wait.until(ExpectedConditions.elementToBeClickable(closebutton)).click();
	}
	public boolean islogin_PasswordEncrypted() {
        return passwordtext.getAttribute("type").equals("password");
    }
	
	public void username_label() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(usernamelabel)).isDisplayed());
	}
	
	public void username_field() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername"))).isDisplayed());
	}
	
	public void password_label() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(passwordlabel)).isDisplayed());
	}
	
	public void password_field() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword"))).isDisplayed());
	}

}
