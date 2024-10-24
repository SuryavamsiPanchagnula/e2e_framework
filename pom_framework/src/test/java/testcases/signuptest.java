package testcases;

import pages.signup;
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
import java.util.Random;

public class signuptest {
    
    private WebDriver driver;
    private signup signup;
    private WebDriverWait wait;
    
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        
        // Extending the wait to 100 seconds for all operations
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        signup = new signup(driver);
        handleAlertIfPresent();  // Handle any initial alerts
    }
    
    public void handleAlertIfPresent() {
        try {
            // Wait for an alert and switch to it
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert Text: " + alert.getText());  // For debugging
        } catch (Exception e) {
            System.out.println("No alert present or unexpected error: " + e.getMessage());
        }
    }
    
    // Verify visibility of popup after handling alerts
    public void VerifyAfterAlert() {
        handleAlertIfPresent();
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h5[@id='signInModalLabel']")));
        Assert.assertTrue(popup.isDisplayed(), "Popup is not displayed after handling the alert.");
    }

    // Assert that the alert message matches the expected text
    public void assertAlertMessage(String expectedMessage) {
        try {
            // Wait for the alert
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);  // For debugging
            // Assert the alert contains the expected message
            Assert.assertTrue(alertText.contains(expectedMessage), 
                "Alert message doesn't match. Expected: " + expectedMessage + ", but got: " + alertText);
        } catch (Exception e) {
            System.out.println("Alert handling failed: " + e.getMessage());
            Assert.fail("Failed to find or handle the alert.");
        }
    }

    // Method to generate a random username
    public String generateRandomUsername() {
        Random random = new Random();
        int length = 10;
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char)(random.nextInt(26) + 'a');
            username.append(c);
        }
        username.append(random.nextInt(1000));  // Add some digits for randomness
        return username.toString() + "@example.com";
    }
    
    public String generateRandomNumber() {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        return String.valueOf(random.nextInt((max - min) + 1) + min);
    }

    // Method to generate a random password
    public String generateRandomPassword() {
        Random random = new Random();
        int length = 12;
        StringBuilder password = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
    
    public String generatecharactorset(int length) {
String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>?/`~";
        
        StringBuilder result = new StringBuilder();
        Random random = new Random();

        // Generate random special characters
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(specialChars.length());
            result.append(specialChars.charAt(index));
        }
        
        return result.toString();
    }
    @Test
    public void tc1_usernamelabel() {
        signup.redirect_to_signup_popup();
        signup.username_label();
    }

    @Test
    public void tc2_passwordlabel() {
        signup.redirect_to_signup_popup();
        signup.password_label();
    }

    @Test
    public void tc3_usernametextfield_display() {
        signup.redirect_to_signup_popup();
        signup.usernametextfield_display();
    }

    @Test
    public void tc4_passwordtextfield_display() {
        signup.redirect_to_signup_popup();
        signup.passwordtextfield_display();
    }
    @Test
    public void tc5_signup() {
        signup.redirect_to_signup_popup();
        
        String randomUsername = generateRandomUsername();
        String randomPassword = generateRandomPassword();
        
        signup.create_username(randomUsername);
        signup.create_password(randomPassword);
        signup.click_on_signup();
        
        handleAlertIfPresent();  // Handle the alert after signup
        
        String expectedAlert = "Sign up successful.";
        assertAlertMessage(expectedAlert);  // Verify the alert message
    }
    @Test
    public void tc6_existingsignup() {
    	
    	signup.redirect_to_signup_popup();
    	signup.create_username("psuryavamsi88@gmail.com");
    	signup.create_password("Vamsi@2000");
    	signup.click_on_signup();
    	String expectedAlert = "This user already exist.";
        assertAlertMessage(expectedAlert);
    }
    @Test
    public void tc7_onlyusername() {
    	
    	String randomusername = generateRandomUsername();
    	signup.redirect_to_signup_popup();
    	signup.create_username(randomusername);
    	signup.click_on_signup();
    	String expectedAlert = "Please fill out Username and Password.";
        assertAlertMessage(expectedAlert);
    }
    @Test
    public void tc8_onlypassword() {
    	String randompassword = generateRandomPassword();
    	signup.create_password(randompassword);
    	signup.redirect_to_signup_popup();
    	signup.click_on_signup();
    	String expectedAlert = "Please fill out Username and Password.";
        assertAlertMessage(expectedAlert);
    }
    @Test
    public void tc9_empty_submit() {
    	signup.redirect_to_signup_popup();
    	signup.click_on_signup();
    	String expectedAlert = "Please fill out Username and Password.";
        assertAlertMessage(expectedAlert);
    }
    @Test
    public void tc10_closepopup() {
    	signup.redirect_to_signup_popup();
    	signup.click_on_signup();
    	String expectedAlert = "Please fill out Username and Password.";
        assertAlertMessage(expectedAlert);
    }
    
    @Test
    public void tc11_numbervalidation() {
    	
    	String randomnumber = generateRandomNumber();
    	String randompassword = generateRandomPassword();
    	signup.redirect_to_signup_popup();
    	signup.create_username(randomnumber);
    	signup.create_password(randompassword);
    	signup.click_on_signup();
    	String expectedAlert = "Sign up successful.";
    	assertAlertMessage(expectedAlert);
    	
    }
    
    @Test
    public void tc12_specialcharector_validation() {
    	
    	String charactor = generatecharactorset(7);
    	String randompassword = generateRandomPassword();
    	signup.redirect_to_signup_popup();
    	signup.create_username(charactor);
    	signup.create_password(randompassword);
    	signup.click_on_signup();
    	String expectedAlert = "Sign up successful.";
    	assertAlertMessage(expectedAlert);	
    }
    
    @AfterMethod
    public void close() {
        driver.quit();
    }
}
