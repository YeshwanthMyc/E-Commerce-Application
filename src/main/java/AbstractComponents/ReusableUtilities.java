package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import PageObjectModel.OrderPage;
import PageObjectModel.cartPage;

public class ReusableUtilities {

	WebDriver driver;
	WebDriverWait wait;
	public ReusableUtilities(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*=cart]")
	WebElement cartIconLocator;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement OrderIconLocator;
	
	By OrderIconBy = By.cssSelector("[routerlink*='myorders']");
	
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public cartPage goToCartPage() {
		cartIconLocator.click();
		return new cartPage(driver);
	}
	
	public OrderPage goToOrdersPage() {
		waitForElementToAppear(OrderIconBy);
		OrderIconLocator.click();
		return new OrderPage(driver);
	}
	

}
