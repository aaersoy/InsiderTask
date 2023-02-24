package utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.helpers.PropertyManager;

public class WebDriverManager {

  PropertyManager propertyManager = new PropertyManager();
  private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

  public WebDriver createAndGetDriver() {
    if (driver.get() != null) {
      driver.get().quit();
    }
    String browserName = propertyManager.getProperty("env.properties", "browser");
    if (browserName.equalsIgnoreCase("Chrome")) {
      driver.set(initializeAndGetChromeDriver());
    }else if(browserName.equalsIgnoreCase("Firefox"))
      driver.set(initializeAndGetFirefoxDriver());
    else{
      driver.set(initializeAndGetChromeDriver());
    }
    return driver.get();
  }


  public WebDriver initializeAndGetChromeDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--test-type");
    options.addArguments("--disable-extensions");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--allow-running-insecure-content");
    options.addArguments("--disable-translate");
    options.addArguments("--always-authorize-plugins");
    options.addArguments("--disable-infobars");
    options.addArguments("--enable-automation");
    options.setExperimentalOption("useAutomationExtension", false);
    ChromeDriver chromeDriver = new ChromeDriver();
    chromeDriver.get(propertyManager.getProperty("env.properties","url"));
    return chromeDriver;
  }

  public WebDriver initializeAndGetFirefoxDriver(){
    FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--test-type");
    options.addArguments("--disable-extensions");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--allow-running-insecure-content");
    options.addArguments("--disable-translate");
    options.addArguments("--always-authorize-plugins");
    options.addArguments("--disable-infobars");
    options.addArguments("--enable-automation");
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    firefoxDriver.get(propertyManager.getProperty("env.properties","url"));
    return firefoxDriver;
  }

  public void manage(){
    if (driver.get() != null) {
      driver.get().manage().window().maximize();
      driver.get().manage().deleteAllCookies();
    }
  }

  public void setDefaultPageTimeout(){
    if (driver.get() != null) {
      driver.get().manage().timeouts().pageLoadTimeout(
          Integer.parseInt(propertyManager.getProperty("env.properties", "pageLoadTimeout")),
          TimeUnit.SECONDS);
    }
  }

  public WebDriverWait getWebDriverWait(){
    if (driver.get() != null) {
      return new WebDriverWait(driver.get(), Duration.ofSeconds(
          Integer.parseInt(propertyManager.getProperty("env.properties", "wait"))));
    }
    return null;
  }

  public void quitWebdriver() {
      if (driver.get() != null) {
        driver.get().quit();
      }
    }
}
