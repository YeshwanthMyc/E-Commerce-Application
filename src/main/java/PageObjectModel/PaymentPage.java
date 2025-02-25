package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableUtilities;

public class PaymentPage extends ReusableUtilities{

	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryLocator;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countryListLocator;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderLocator;
	
	By selectCountryBy = By.cssSelector(".ta-results button");
	
	
	public List<WebElement> countryList(String country) {
		countryLocator.sendKeys(country);
		waitForElementToAppear(selectCountryBy);
		return countryListLocator;
	}
	
	public void selectCountry(String country) {
		WebElement countryToBeSelected = countryList(country).stream()
				.filter(con -> con.getText().equalsIgnoreCase(country)).findAny().orElse(null);
		countryToBeSelected.click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderLocator.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	

}
