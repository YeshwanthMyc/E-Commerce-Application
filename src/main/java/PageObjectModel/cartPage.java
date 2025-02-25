package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableUtilities;

public class cartPage extends ReusableUtilities{
	
	WebDriver driver;
	public cartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By cartPageBy = By.cssSelector(".cartSection h3");
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productListLocator;
	
	@FindBy(css="[class *=subtotal ] button")
	WebElement checkOutButtonLocator;
	
	public List<WebElement> listOfProductsInCartPage() {
		waitForElementToAppear(cartPageBy);
		return productListLocator;
	}
	
	public boolean productPresentInCartPage(String product) {
		return listOfProductsInCartPage().stream()
		.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));
	}
	
	public PaymentPage checkOut() {
		checkOutButtonLocator.click();
		return new PaymentPage(driver);
	}

}
