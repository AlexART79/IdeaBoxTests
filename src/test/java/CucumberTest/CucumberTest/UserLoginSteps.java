package CucumberTest.CucumberTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.*;

import WebDriverUtils.*;

public class UserLoginSteps {
	// logger
	private static final Logger log = LogManager.getLogger(UserLoginSteps.class.getName());
	//WebDriver driver = null;
	

	@Given("^I have open the browser and navigate to the main page$")
	public void i_have_open_the_browser_and_navigate_to_the_main_page() throws Throwable {
		log.info("Entering 'I have open the browser and navigate to the main page' step");

		String env = System.getProperty("env", "http://mb-win7.vlab.lohika.com/ideabox");
		
		BrowserDriver.getCurrentDriver().get(env);
		log.info("Page loaded: " + env);
	}

	@When("^I have entered my login and password$")
	public void i_have_entered_my_login_and_password() throws Throwable {
		log.info("Entering 'I have entered my login and password' step");

		log.info("Type login into 'username' field");
		BrowserDriver.getCurrentDriver().findElement(By.cssSelector("input[name='username']")).sendKeys("ideabox-ldap");
		log.info("Type password into 'password' field");
		BrowserDriver.getCurrentDriver().findElement(By.cssSelector("input[name='password']")).sendKeys("xwPgV42Px9Af67LjJtr");

		log.info("Press 'LOGIN' button");
		BrowserDriver.getCurrentDriver().findElement(By.cssSelector("button[type='submit']")).click();
	}

	@Then("^I will successfully logged in$")
	public void i_will_successfully_logged_in() throws Throwable {
		log.info("Entering 'I will successfully logged in' step");

		WebDriverWait w1 = new WebDriverWait(BrowserDriver.getCurrentDriver(), 20);
		WebElement we = w1
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.logo-label-wrapper")));

		log.info("Check that we are logged in");
		Assert.assertNotNull(we); // element is not null
		Assert.assertTrue(we.isDisplayed()); // and it's displayed
		log.info("Assertion passed: " + we.isDisplayed());		
	}
}