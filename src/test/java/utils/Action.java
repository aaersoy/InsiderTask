package utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Action {

  private static WebDriver webDriver = null;
  private static WebDriverWait wait = null;
  private static boolean isActionCreated = false;
  private static Actions actions = null;
  static int attempt = 0;

  public Action(){
    if (!isActionCreated) {
      utils.WebDriverManager webDrivers = new utils.WebDriverManager();
      webDriver = webDrivers.createAndGetDriver();
      webDrivers.manage();
      webDrivers.setDefaultPageTimeout();
      wait = webDrivers.getWebDriverWait();
      actions = new Actions(webDriver);
      isActionCreated = true;
    }
  }

  protected void clickElement(By element){
    WebElement webElement = findElement(element);
    wait.until(ExpectedConditions.visibilityOf(webElement));
    wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
  }

  protected WebElement findElementWithLocatorAndTextContains(By element, String text){
    wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    List<WebElement> elementList = findElements(element).stream().filter( e -> e.getText().contains(text)).collect(Collectors.toList());
    return elementList.size() != 0 ? elementList.get(0) : null;
  }
  protected WebElement findElement(By element){
    return wait.until(ExpectedConditions.presenceOfElementLocated(element));
  }

  protected List<WebElement> findElements(By element){
    wait.until(ExpectedConditions.presenceOfElementLocated(element));
    return webDriver.findElements(element);
  }

  protected List<String> getTexts(By element){
    List<String> texts = new ArrayList<>();
    findElements(element).stream().forEach(e->texts.add(e.getText()));
    return texts;
  }

  protected void scrollAndClick(WebElement element) {
    scroll(element);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
  }

  protected void scroll(WebElement element){
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
  }

  protected void hover(WebElement element){

    while(attempt < 12){
      try{
        attempt++;
        actions.moveToElement(element).perform();
        attempt = 12;
        break;
      }catch (MoveTargetOutOfBoundsException e){
        actions.scrollByAmount(0,element.getSize().getHeight()).perform();
        hover(element);
      }catch (ElementNotInteractableException e){
        actions.scrollByAmount(0,100).perform();
        hover(element);
      }catch (Exception e){
        break;
      }
    }
    attempt = 0;
  }

  public void wait(int seconds){
    try {
      Thread.sleep(seconds*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
  public void takeScreenShot(String filePath){
    TakesScreenshot takesScreenshot =((TakesScreenshot)webDriver);
    File ss = takesScreenshot.getScreenshotAs(OutputType.FILE);
    File file = new File(filePath);
    try {
      FileUtils.moveFileToDirectory(ss,file,true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
