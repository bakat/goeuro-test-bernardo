package pageObjects.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import pageObjects.PageObjectInterface;
import driver.ComponentFinder;

public class MainPageSearch implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.ID, using="from_filter")
	private WebElement inputFrom;
	
	@FindBy(how = How.ID, using="to_filter")
	private WebElement inputTo;
	
	@FindBy(how = How.ID, using="departure_date")
	private WebElement departureDate;
	
	@FindBy(how = How.ID, using="search-form__submit-btn")
	private WebElement searchButton;
	
	public MainPageSearch(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(inputFrom);
		finder.verifyElementPresent(inputTo);
		finder.verifyElementPresent(searchButton);
	}
	
	public void searchForDestination(String from, String to){
		finder.fillInElement(inputFrom, from);
		finder.fillInElement(inputTo, to);
		finder.submitForm(searchButton);
	}
}