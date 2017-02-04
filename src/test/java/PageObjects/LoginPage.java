package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import WebDriverUtils.*;


public class LoginPage {	
	@FindBy(how=How.CSS, using="input[name='username']")
	protected WebElement txtLogin;
	
	@FindBy(how = How.CSS, using="input[name='password']")
	protected WebElement txtPassword;
	
	@FindBy(how = How.CSS, using="button[type='submit']")
	protected WebElement btnLogin;
	
	public void Login(String username, String password) {
		InputUserName(username);
		InputPassword(password);
		ClickLoginButton();
	}

	private void ClickLoginButton() {
		btnLogin.click();		
	}

	private void InputPassword(String password) {
		txtPassword.sendKeys(password);
	}

	private void InputUserName(String username) {
		txtLogin.sendKeys(username);
	}
	
	public LoginPage() {
		PageFactory.initElements(BrowserDriver.getCurrentDriver(), this);
	}
	
}
