package pageObjects.mainPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import pageObjects.PageObjectInterface;
import driver.ComponentFinder;

public class MainPage implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.ID, using="header-userlogin")
	private WebElement loginLink;
	
	private String pageTitle = "Search & Compare Cheap Buses, Trains & Flights | GoEuro";
	
	public MainPage(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
		Assert.assertThat(finder.getPageTitle(), is(equalTo(pageTitle)));
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(loginLink);
	}
}