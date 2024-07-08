package AutomationTutorial.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTutorial.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//@FindBy(css=".hero-primary")
//WebElement confirmMsg;

@FindBy(css = ".hero-primary")
WebElement confirmMsg;


public String getConfirmationMsg() {
	CheckOutPage cp = new CheckOutPage(driver);	
	return confirmMsg.getText();

}
}