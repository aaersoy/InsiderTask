package pages;

import org.openqa.selenium.By;
import utils.Action;

public class HomePage extends Action{

  public static By navBarMoreButton = By.xpath("//a[@id = 'mega-menu-1']//span[text()= 'More']");
  public static By careersButtonInNavBarMenu = By.xpath("//div[@class='col-12 col-lg-4']//h5[text()='Careers']");
  public static By acceptAllCookiesButton = By.id("wt-cli-accept-all-btn");

  public void clickNavBarMoreButton(){
    clickElement(navBarMoreButton);
  }

  public void clickCareersButtonInNavBarMenu(){
    clickElement(careersButtonInNavBarMenu);
  }

  public void clickAcceptAllCookiesButton(){
    clickElement(acceptAllCookiesButton);
  }

}
