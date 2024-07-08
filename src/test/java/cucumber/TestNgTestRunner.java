package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "C:\\Users\\poorn\\Workspace Spring\\SeleniumFrameworkDesign\\src\\test\\java\\cucumber", glue = "AutomationTutorial.stepDefinitions", monochrome = true, tags = "@ErrorValidation", plugin = {
		"html:target/cucumber.html" })
public class TestNgTestRunner extends AbstractTestNGCucumberTests {

}
