package AutomationTutorial.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import AutomationTutorial.TestComponents.BaseTest;
import AutomationTutorial.pageobjects.CartPage;
import AutomationTutorial.pageobjects.CheckOutPage;
import AutomationTutorial.pageobjects.ConfirmationPage;
import AutomationTutorial.pageobjects.LandingPage;
import AutomationTutorial.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalog catalog;
	public ConfirmationPage confirmationPage;
	public String countryName = "Canada";

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with the username (.+) and password (.+)$")
	public void logged_in_with_the_username_and_password(String userName, String password) {
		catalog = landingPage.loginApplication(userName, password);

	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_the_product_to_cart(String productName) {
		List<WebElement> products = catalog.getProductList();
		catalog.addProductToCart(productName);
	}

	@And("^checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName) {
		CartPage cartPage = catalog.goToCartPage();
		Boolean isTrue = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(isTrue);
		CheckOutPage checkOut = cartPage.goToCheckOut();
		checkOut.selectCountry(countryName);
		confirmationPage = checkOut.submitOrder();

	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationPage(String message) {
		String confirmMsg = confirmationPage.getConfirmationMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(message));
	}
	@Then("{string} meessage is displayed")
	public void meessage_is_displayed(String errorMessage) {
		Assert.assertEquals(errorMessage, landingPage.getErrorMessage());

	}

}