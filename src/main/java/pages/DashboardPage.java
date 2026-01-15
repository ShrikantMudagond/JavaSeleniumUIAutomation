// ============================================
// DashboardPage.java
// ============================================
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "h1.post-title")
    private WebElement pageTitle;

    @FindBy(linkText = "Log out")
    private WebElement logoutButton;

    @FindBy(css = "p.has-text-align-center strong")
    private WebElement successMessage;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageTitleDisplayed() {
        return isDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public void clickLogout() {
        click(logoutButton);
        logger.info("Clicked logout button");
    }
}