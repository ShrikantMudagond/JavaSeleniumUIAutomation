package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static ExtentReports extent = ExtentReportManager.createInstance();

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: " + result.getMethod().getMethodName());
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: " + result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: " + result.getMethod().getMethodName());
        String screenshotPath = ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());

        ExtentReportManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentReportManager.getTest().fail(result.getThrowable());

        if (screenshotPath != null) {
            try {
                ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                logger.error("Failed to attach screenshot to report", e);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: " + result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Finished: " + context.getName());
        extent.flush();
    }
}