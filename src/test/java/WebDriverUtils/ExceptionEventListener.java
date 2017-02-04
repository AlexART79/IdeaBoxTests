package WebDriverUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import CucumberTest.CucumberTest.annotation;

public class ExceptionEventListener extends AbstractWebDriverEventListener {
	private static final Logger log = LogManager.getLogger(annotation.class.getName());
	private String test_class_name;
	
	public ExceptionEventListener(String name){
		test_class_name = name;
	}
	
	public void takeScreenshot(String screenshotName, WebDriver driver) {
	    if (driver instanceof TakesScreenshot) {
	        File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        try {
	        	String screenshotPath = "screenshots/" + screenshotName + ".png";
	            FileUtils.copyFile(tempFile, new File(screenshotPath));
	            log.info("Screenshot saved: " + screenshotPath);
	        } catch (IOException e) {
	            // TODO handle exception
	        }
	    }
	}
	
	@Override
	public void onException(Throwable throwable, WebDriver driver){
		log.error(throwable.getMessage());
		takeScreenshot(throwable.getClass().getSimpleName() + "_" + test_class_name, driver);
	}
	
}
