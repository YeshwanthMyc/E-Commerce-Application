package AutomationPractice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import TestComponents.retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer =retry.class)
	public void loginIncorrectCredentialsTest() throws IOException {
		String mail = "vijay6190@gmail.com";
		String password = "Vijay@619.";

		loginPage.loginCredentials(mail, password);
		String actualMessage = loginPage.incorrectUserNameOrPassword();
		Assert.assertEquals("Incorrect1 email or password.", actualMessage);
	}
}