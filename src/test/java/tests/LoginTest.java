// ============================================
// LoginTest.java
// ============================================
package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ExtentReportManager;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info("Starting successful login test");
        }
        logger.info("Starting successful login test");

        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword()
        );

        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info("Verifying successful login");
        }
        logger.info("Verifying successful login");

        Assert.assertTrue(dashboardPage.isPageTitleDisplayed(), "Page title is not displayed");
        Assert.assertTrue(dashboardPage.isLogoutButtonDisplayed(), "Logout button is not displayed");
        Assert.assertTrue(dashboardPage.isSuccessMessageDisplayed(), "Success message is not displayed");

        String expectedMessage = "Congratulations";
        String actualMessage = dashboardPage.getSuccessMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage),
                "Success message doesn't contain expected text. Expected: " + expectedMessage + ", Actual: " + actualMessage);

        logger.info("Login test passed successfully");
    }

    @Test(priority = 2, description = "Verify login failure with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info("Starting invalid login test");
        }
        logger.info("Starting invalid login test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidUser", "invalidPassword");

        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info("Verifying error message");
        }
        logger.info("Verifying error message");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Your username is invalid"),
                "Error message doesn't match. Actual: " + errorMessage);

        logger.info("Invalid login test passed successfully");
    }

    @Test(priority = 3, description = "Verify login with empty credentials")
    public void testLoginWithEmptyCredentials() {
        if (ExtentReportManager.getTest() != null) {
            ExtentReportManager.getTest().info("Starting empty credentials test");
        }
        logger.info("Starting empty credentials test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSubmit();

        // Validation happens at browser level, so just verify we're still on login page
        Assert.assertTrue(driver.getCurrentUrl().contains("practice-test-login"),
                "Should remain on login page");

        logger.info("Empty credentials test passed successfully");
    }
}