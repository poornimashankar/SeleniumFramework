package AutomationTutorial.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTutorial.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProductsTitles;
	@FindBy(css = ".totalRow button")
	WebElement checkOutElelement;

	public Boolean verifyProductDisplay(String productName) {
		return cartProductsTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

	}

	public CheckOutPage goToCheckOut() {
		checkOutElelement.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}
}
