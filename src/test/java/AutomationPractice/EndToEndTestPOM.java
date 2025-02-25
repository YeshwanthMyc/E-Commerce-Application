package AutomationPractice;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import PageObjectModel.ConfirmationPage;
import PageObjectModel.LandingPage;
import PageObjectModel.OrderPage;
import PageObjectModel.PaymentPage;
import PageObjectModel.cartPage;
import PageObjectModel.productPage;
import TestComponents.BaseTest;
import TestComponents.retry; 


public class EndToEndTestPOM extends BaseTest {
	@Test(dataProvider = "getData")
	public void endToEndTest(HashMap<String, String> input) throws IOException {

		productPage productCatalogue = loginPage.loginCredentials(input.get("mail"), input.get("password"));
		productCatalogue.addToCart(input.get("product"));

		cartPage cartPage = productCatalogue.goToCartPage();
		boolean productPresentInCartPage = cartPage.productPresentInCartPage(input.get("product"));

		Assert.assertTrue(productPresentInCartPage);

		PaymentPage paymentPage = cartPage.checkOut();

		paymentPage.selectCountry(input.get("country"));
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		String confirmationMessage = confirmationPage.confirmMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dataProvider = "getData",retryAnalyzer =retry.class)
	public void ordersPageValidation(HashMap<String, String> input) {
		loginPage.loginCredentials(input.get("mail"), input.get("password"));
		OrderPage ordersPage = loginPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.orderPageValidations("Adidas"));
	}

}
