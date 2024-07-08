package AutomationTutorial.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTutorial.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productsBy = By.cssSelector(".mb-3");
	By addCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	By animated = By.cssSelector(".ng-animating");
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisapper(spinner);
	}
}