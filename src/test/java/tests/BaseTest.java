package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setup() {
        logger.info("Setting up test");
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getUrl());
        logger.info("Navigated to URL: " + ConfigReader.getUrl());
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test");
        DriverManager.quitDriver();
    }
}