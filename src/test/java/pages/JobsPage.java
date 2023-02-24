package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Action;

public class JobsPage extends Action {
  public static By jobTitleTexts = By.xpath("//p[@class = 'position-title font-weight-bold']");
  public static By jobLocationTexts = By.xpath("//div[@class = 'position-location text-large']");
  public static By jobDepartmentTexts = By.xpath("//span[@class = 'position-department text-large font-weight-600 text-primary']");
  public static By jobCards = By.xpath("//div[@id = 'jobs-list']/div");
  public static By jobApplyButtons = By.xpath("//*[@class = 'btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']");
  public static By jobNextPageButton = By.xpath("//*[@class = 'btn btn-yellow rounded has-icon page-button next pr-4']");
  public static By footer = By.id("footer");
  public static By locationFilterList = By.id("select2-filter-by-location-container");
  public static By locationFilterOption = By.xpath("//*[@class = 'select2-results']//li");
  public static By departmantFilterList =  By.id("select2-filter-by-department-container");
  public static By departmantFilterOption =  By.xpath("//*[@class = 'select2-results']//li");

  public void checkAllTextsAndApplyButtonInJobCardContains(String title, String departmant, String location){
    wait(2);
    List<String> jobTitleTextList = getTexts(jobTitleTexts);
    List<String> jobLocationTextList = getTexts(jobLocationTexts);
    List<String> jobDepartmentTextList = getTexts(jobDepartmentTexts);
    List<WebElement> jobs = findElements(jobCards);
    List<WebElement> applyButtons = findElements(jobApplyButtons);

    for (int i = 0; i < jobs.size(); i++) {
      hover(jobs.get(i));
      wait(1);
      Assert.assertTrue(applyButtons.get(i).isDisplayed(), "Job Apply Now Button was not shown");
      Assert.assertTrue(jobTitleTextList.get(i).contains(title),"Job title was not shown as expected");
      Assert.assertTrue(jobLocationTextList.get(i).contains(location) ,"Job location was not shown as expected");
      Assert.assertTrue(jobDepartmentTextList.get(i).contains(departmant),"Job departmant was not shown as expected");
    }
  }

  public void selectLocationFilter(String location){
    wait(2);
    clickElement(locationFilterList);
    findElementWithLocatorAndTextContains(locationFilterOption,location).click();
  }

  public void selectDepartmantFilter(String departmant){
    clickElement(departmantFilterList);
    findElementWithLocatorAndTextContains(departmantFilterOption,departmant).click();
  }

  public boolean clickNextJobPageIfItIsExisted(){
    WebElement nextPageButton = findElement(jobNextPageButton);
    WebElement footerArea = findElement(footer);
    hover(footerArea);
    if (nextPageButton.isEnabled()){
      scrollAndClick(nextPageButton);
      return true;
    }
    return false;
  }
}
