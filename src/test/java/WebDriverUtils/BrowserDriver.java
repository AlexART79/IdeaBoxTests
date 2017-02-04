package WebDriverUtils;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BrowserDriver {
	private static WebDriver mDriver;
	private static final Logger log = LogManager.getLogger(BrowserDriver.class.getName());
	
	// factory method to get proper driver (based on cmd-line argument)
	// supported: firefox, edge, chrome
	// default driver - ChromeDriver()
	private static WebDriver GetDriver() {
		String drv = System.getProperty("browser", "chrome").toUpperCase();
		EventFiringWebDriver ed;

		if (drv.equals("FIREFOX")) {
			ed = new EventFiringWebDriver(new FirefoxDriver(new FirefoxProfile()));
		} else if (drv.equals("EDGE")) {
			ed = new EventFiringWebDriver(new EdgeDriver());
		} else {
			ed = new EventFiringWebDriver(new ChromeDriver());
		}

		ed.register(new ExceptionEventListener("Login test"));
		ed.getWrappedDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		return ed;
	}
	
	public synchronized static WebDriver getCurrentDriver() {
		if (mDriver==null) {
			try {
	                	mDriver = GetDriver();
		        } finally{
		        	Runtime.getRuntime().addShutdownHook(
						new Thread(new BrowserCleanup()));
		        }
		}
		return mDriver;
	}

	private static class BrowserCleanup implements Runnable {
		public void run() {
			log.info("Closing the browser");
			close();
		}
	}

	public static void close() {
		try {
			getCurrentDriver().quit();
			mDriver = null;
			log.info("closing the browser");
		} catch (UnreachableBrowserException e) {
			log.info("cannot close browser: unreachable browser");
		}
	}

}
