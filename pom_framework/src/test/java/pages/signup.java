package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class signup extends base_page {
	
	public signup(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="sign-username") WebElement createusername;
	@FindBy(id="sign-password") WebElement createpassword;
	@FindBy(xpath = "//div[@id='signInModal']//div[@class='modal-footer']//button[@class='btn btn-secondary']") WebElement close;
	@FindBy(xpath ="//button[@onclick='register()']") WebElement signupclick;
	@FindBy(id="signin2") WebElement signupproceed;
	@FindBy(xpath="//div[@id='signInModal']//label[@for='sign-username']") WebElement usernamelabel;
	@FindBy(id="sign-username") WebElement usernametextfield;
	@FindBy(xpath="//div[@id='signInModal']//label[@for='sign-password']") WebElement passwordlabel;
	@FindBy(id="sign-password") WebElement passwordtextfield;
	public void redirect_to_signup_popup() {
		
		signupproceed.click();
	}
	
	public void create_username(String username) {
		
		wait.until(ExpectedConditions.visibilityOf(createusername)).sendKeys(username);
		
	}
	
	public void create_password(String password) {
		
		wait.until(ExpectedConditions.visibilityOf(createpassword)).sendKeys(password);
	}
	
	public void click_on_signup() {
		
		wait.until(ExpectedConditions.elementToBeClickable(signupclick)).click();
	}
	
	public void close_signup_popup() {
		
		wait.until(ExpectedConditions.elementToBeClickable(close)).click();
	}
	
	public boolean issignup_password_Encrypted() {
		return createpassword.getAttribute("type").equals("password");
	}
	
	public void username_label() {
		wait.until(ExpectedConditions.visibilityOf(usernamelabel)).isDisplayed();
	}
	
	public void password_label() {
		wait.until(ExpectedConditions.visibilityOf(passwordlabel)).isDisplayed();
	}
	public void usernametextfield_display() {
		wait.until(ExpectedConditions.visibilityOf(usernametextfield)).isDisplayed();
	}
	
	public void passwordtextfield_display(){
		wait.until(ExpectedConditions.visibilityOf(passwordtextfield)).isDisplayed();
	}
	
	public boolean password_encryption() {
		return createpassword.getAttribute("type").equals("password");
	}
	
}
