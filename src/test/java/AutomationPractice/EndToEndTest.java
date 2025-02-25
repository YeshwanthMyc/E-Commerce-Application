package AutomationPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EndToEndTest {

	public static void main(String[] args) {
		String product = "ADIDAS ORIGINAL";
		String country = "India";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// landing page
		driver.get("https://rahulshettyacademy.com/client/");

		// Login Page
		driver.findElement(By.id("userEmail")).sendKeys("vijay619@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Vijay@619.");
		driver.findElement(By.id("login")).click();

		// Find the List of products
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> listOfProducts = driver.findElements(By.cssSelector(".mb-3"));
		WebElement productToBeSelected = listOfProducts.stream()
				.filter(prod -> prod.findElement(By.tagName("b")).getText().equalsIgnoreCase(product)).findFirst()
				.orElse(null);

		// Add to cart for the productToBeSelected
		productToBeSelected.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

		// Add to cart Success Message
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label='Product Added To Cart']")));

		// Click on cart icon to go to cart page
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();

		// List of products present in the cart page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartPageProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean productPresentInCartPage = cartPageProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));

		// Validating the selected product is present in the cartpage
		Assert.assertTrue(productPresentInCartPage);

		// Checkout button
		driver.findElement(By.cssSelector("[class *=subtotal ] button")).click();

		// Payment Page
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		List<WebElement> dropdownCountries = driver.findElements(By.cssSelector(".ta-results button"));
		WebElement countryToBeSelected = dropdownCountries.stream()
				.filter(con -> con.getText().equalsIgnoreCase(country)).findAny().orElse(null);
		countryToBeSelected.click();

		// Place order
		driver.findElement(By.cssSelector(".action__submit")).click();

		// Final Page to confirm order
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		// closing the browser
		driver.quit();

	
		

	}

}
