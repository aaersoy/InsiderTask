package tests;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Action;
import utils.WebDriverManager;
import utils.helpers.PropertyManager;

public class BaseTest implements ITestListener {

  @Override
  public void onTestFailure(ITestResult result) {
    Action action = new Action();
    action.takeScreenShot(new PropertyManager().getProperty("env.properties","screenshotFolder"));
  }

}
