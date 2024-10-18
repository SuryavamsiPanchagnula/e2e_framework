package pages;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base_page {
	
	 protected WebDriver driver;
	 protected WebDriverWait wait;

	    public base_page(WebDriver driver) {
	    	
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	        PageFactory.initElements(driver, this);
	    }

		
	    
	    
}