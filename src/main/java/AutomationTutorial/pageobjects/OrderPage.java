package AutomationTutorial.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTutorial.AbstractComponents.AbstractComponents;
import java.util.List;

public class OrderPage extends AbstractComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderListElement;
	public Boolean verifyOrder(String productName) {
		return orderListElement.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

	}
}
