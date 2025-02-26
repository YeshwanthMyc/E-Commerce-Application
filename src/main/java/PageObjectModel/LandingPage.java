package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReusableUtilities;

public class LandingPage extends ReusableUtilities{
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement UserMailLocator;
	
	@FindBy(id="userPassword")
	WebElement userPasswordLocator;
	
	@FindBy(id="login")
	WebElement loginButtonLocator;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement incorrectUserOrPassLocator;
	
	By incorrectUserOrPassBy = By.cssSelector("div[aria-label='Incorrect email or password.']");
	
	public void landingPage() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public productPage loginCredentials(String mail, String password) {
		UserMailLocator.sendKeys(mail);
		userPasswordLocator.sendKeys(password);
		loginButtonLocator.click();
		return new productPage(driver);
	}
	
	public String incorrectUserNameOrPassword() {
		waitForElementToAppear(incorrectUserOrPassBy);
		return incorrectUserOrPassLocator.getText();
	}
	
	
	

}
