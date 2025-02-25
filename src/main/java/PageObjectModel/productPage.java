package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.ReusableUtilities;

public class productPage extends ReusableUtilities{
	
	WebDriver driver;
	public productPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By waitForProductToAppearBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector("button[class='btn w-10 rounded']");
	By loadingIconBy = By.cssSelector(".ng-animating");
	By successMessageBy =By.xpath("//div[@aria-label='Product Added To Cart']");
	
	@FindBy(css=".mb-3")
	List<WebElement> listOfproductsLocator;

	
	
	public List<WebElement> listOfProducts() {
		waitForElementToAppear(waitForProductToAppearBy);
		return listOfproductsLocator;
	}
	
	public WebElement selectDesiredProduct(String product) {
		return listOfProducts().stream()
		.filter(prod -> prod.findElement(By.tagName("b")).getText().equalsIgnoreCase(product)).findFirst()
		.orElse(null);
	}
	
	public void addToCart(String product) {
		selectDesiredProduct(product).findElement(addToCartBy).click();
		waitForElementToDisAppear(loadingIconBy);
		waitForElementToAppear(successMessageBy);
		
	}
	
	
}
