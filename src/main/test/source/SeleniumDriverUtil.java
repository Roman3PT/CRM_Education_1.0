package source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriverUtil {
    private static String DRIVER_NAME = "webdriver.chrome.driver";
    private static String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    private static WebDriver driver;

    public SeleniumDriverUtil() {
    }

    public static void init() {
        System.setProperty(DRIVER_NAME, DRIVER_PATH);
        driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
