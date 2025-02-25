package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.ReusableUtilities;

public class OrderPage extends ReusableUtilities{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tr//td[2]")
	List<WebElement> orderPageProductsLocator;
	
	By orderPageProductsby = By.xpath("//tr//td[2]");
	
	public boolean orderPageValidations(String product) {
		waitForElementToAppear(orderPageProductsby);
		return orderPageProductsLocator.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(product));
	}
}
