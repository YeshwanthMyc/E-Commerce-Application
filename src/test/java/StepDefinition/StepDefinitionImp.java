package StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import PageObjectModel.ConfirmationPage;
import PageObjectModel.LandingPage;
import PageObjectModel.PaymentPage;
import PageObjectModel.cartPage;
import PageObjectModel.productPage;
import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class StepDefinitionImp extends BaseTest{
	public LandingPage loginPage;
	public productPage productCatalogue;
	public cartPage cartPage;
	public PaymentPage paymentPage;
	public ConfirmationPage confirmationPage;
	
	@Given("i landed on ECommerce page")
	public void i_landed_on_ECommerce_page() throws IOException{
		loginPage = launchApplication();
	}
	
	@Given("^Logged in with (.+) and (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		 productCatalogue = loginPage.loginCredentials(username,password);
	}
	
	@When("^I add the (.+) in the cart$")
	public void I_add_the_product_in_the_cart(String productName){
		productCatalogue.addToCart(productName);
	}
	
	@And("^checkout (.+) and select (.+) and submit order$")
	public void checkout_product_and_submit_the_order(String productName,String country) {
		cartPage = productCatalogue.goToCartPage();
		boolean productPresentInCartPage = cartPage.productPresentInCartPage(productName);
		Assert.assertTrue(productPresentInCartPage);
		paymentPage = cartPage.checkOut();
		paymentPage.selectCountry(country);
		confirmationPage = paymentPage.placeOrder();
	}
	
	@Then("{string}  success message is displayed in the confirmation page")
	public void success_message_is_displayed_in_the_confirmation_page(String string) {
		String confirmationMessage = confirmationPage.confirmMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.quit();
	}
}
