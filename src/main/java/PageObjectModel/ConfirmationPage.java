package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableUtilities;

public class ConfirmationPage extends ReusableUtilities{
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By ConfirmationMessageBy = By.cssSelector(".hero-primary");
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessageLocator;
	
	public String confirmMessage() {
		waitForElementToAppear(ConfirmationMessageBy);
		String confirmationMessage = confirmationMessageLocator.getText();
		return confirmationMessage;
	}

}
