package pages;

import org.openqa.selenium.By;
import utils.Action;

public class CareersPage extends Action {

  public static By seeAllTeamsButton = By.xpath("//section[@id='career-find-our-calling']//a[text()='See all teams']");
  public static By categoryText = By.xpath("//div[@class='job-item col-12 col-lg-4 mt-5']//h3");
  public static By seeAllJobsButton = By.xpath("//section[@id = 'page-head']//a");


  public void openPositions(String category){
    scrollAndClick(findElement(seeAllTeamsButton));
    wait(1);
    scrollAndClick(findElementWithLocatorAndTextContains(categoryText, category));
  }

  public void clickSeeAllJobs(String category){
    wait(2);
    clickElement(seeAllJobsButton);
  }

}
