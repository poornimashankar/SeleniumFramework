package AutomationTutorial;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTutorial.pageobjects.LandingPage;
import AutomationTutorial.pageobjects.OrderPage;
import AutomationTutorial.pageobjects.ProductCatalog;
import AutomationTutorial.TestComponents.BaseTest;
import AutomationTutorial.pageobjects.CartPage;
import AutomationTutorial.pageobjects.CheckOutPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import AutomationTutorial.pageobjects.ConfirmationPage;

public class StandaloneTest extends BaseTest {
	String productName = "ZARA COAT 3";
	LandingPage landingPage;

	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(String email,String password,String productName) throws IOException {

		String countryName = "Canada";
		 landingPage = launchApplication();
		ProductCatalog catalog = landingPage.loginApplication(email, password);
		List<WebElement> products = catalog.getProductList();
		catalog.addProductToCart(productName);
		CartPage cartPage = catalog.goToCartPage();
		Boolean isTrue = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(isTrue);
		CheckOutPage checkOut = cartPage.goToCheckOut();
		checkOut.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOut.submitOrder();
		String confirmMsg = confirmationPage.getConfirmationMsg();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//	    closeDriver();
	}

	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryVerification() throws IOException {
		System.out.println("Helloooo");
     landingPage = launchApplication();
	ProductCatalog catalog = landingPage.loginApplication("abc@gmail.com", "Hello@1234");
		OrderPage orderPage = catalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrder(productName));

	}
	@DataProvider
	public Object[][] getData() {
		Object[][] testData =  new Object[2][3];
		testData[0][0] = "ps30@gmail.com";
		testData[0][1] = "Hello@1234";
		testData[0][2] = "ADIDAS ORIGINAL";

		testData[1][0] = "abc@gmail.com";
		testData[1][1] = "Hello@2017";
		testData[1][2] = "ZARA COAT 3";
		return testData;
	}

}
