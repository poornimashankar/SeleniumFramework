package AutomationTutorial;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
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

public class SubmitOrderJson extends BaseTest {
	String productName = "ZARA COAT 3";
	LandingPage landingPage;

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		String countryName = "Canada";
		landingPage = launchApplication();
		ProductCatalog catalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = catalog.getProductList();
		catalog.addProductToCart(input.get("product"));
		CartPage cartPage = catalog.goToCartPage();
		Boolean isTrue = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(isTrue);
		CheckOutPage checkOut = cartPage.goToCheckOut();
		checkOut.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOut.submitOrder();
		String confirmMsg = confirmationPage.getConfirmationMsg();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//	    closeDriver();
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryVerification() throws IOException {
		System.out.println("Helloooo");
		landingPage = launchApplication();
		ProductCatalog catalog = landingPage.loginApplication("ps30@gmail.com", "Hello@1234");
		OrderPage orderPage = catalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrder(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\Users\\poorn\\Workspace Spring\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationTutorial\\data\\purchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
//	public void getScreenShot() {
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		
//	}

}
