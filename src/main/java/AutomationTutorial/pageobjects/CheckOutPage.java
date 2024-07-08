package AutomationTutorial.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import AutomationTutorial.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	@FindBy(css = ".action__submit")
	WebElement submit;
//	@FindBy(xpath = "//button[contains(@class,'ta-item')])[1]")
//	WebElement selectCountry;
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[1]")
	 WebElement selectCountry;

	By result = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, "Canada").build().perform();
		waitForElementToAppear(result);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}

	public void verifyConfirmationMsg() {

	}
}
