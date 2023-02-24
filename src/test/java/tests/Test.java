package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import pages.CareersPage;
import pages.HomePage;
import pages.JobsPage;
import utils.WebDriverManager;
import utils.helpers.PropertyManager;

@Listeners(BaseTest.class)
public class Test {

  HomePage homePage = new HomePage();
  CareersPage careersPage = new CareersPage();
  JobsPage jobsPage = new JobsPage();

  PropertyManager propertyManager = new PropertyManager();

 @org.testng.annotations.Test
  public void test1(){
   homePage.clickAcceptAllCookiesButton();
   homePage.clickNavBarMoreButton();
   homePage.clickCareersButtonInNavBarMenu();
   careersPage.openPositions(propertyManager.getProperty("test_data.properties","positionCategory"));
   careersPage.clickSeeAllJobs(propertyManager.getProperty("test_data.properties","seeAllPositions"));
   jobsPage.selectLocationFilter(propertyManager.getProperty("test_data.properties","jobLocation"));
   jobsPage.selectDepartmantFilter(propertyManager.getProperty("test_data.properties","jobDepartmant"));

   do{
    jobsPage.checkAllTextsAndApplyButtonInJobCardContains(
        propertyManager.getProperty("test_data.properties","jobTitleContains"),
        propertyManager.getProperty("test_data.properties","jobDepartmantContains"),
        propertyManager.getProperty("test_data.properties","jobLocationContains")
        );
   }while (jobsPage.clickNextJobPageIfItIsExisted());
  }

  @AfterTest
  public void tearDown(){
    new WebDriverManager().quitWebdriver();
  }

}
