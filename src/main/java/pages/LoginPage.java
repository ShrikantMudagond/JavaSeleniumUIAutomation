package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
        logger.info("Entered username: " + username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
        logger.info("Entered password");
    }

    public void clickSubmit() {
        click(submitButton);
        logger.info("Clicked submit button");
    }

    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        logger.info("Login attempt with username: " + username);
        return new DashboardPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}