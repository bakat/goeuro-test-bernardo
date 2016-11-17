package pageObjects.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.PageObjectInterface;
import pageObjects.resultsPage.ResultsPage;
import driver.ComponentFinder;

public class MainPageSearch implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.ID, using="from_filter")
	private WebElement inputFrom;
	
	@FindBy(how = How.ID, using="ui-id-1")
	private WebElement inputFromAutoComplete;
	
	@FindBy(how = How.ID, using="to_filter")
	private WebElement inputTo;
	
	@FindBy(how = How.ID, using="ui-id-2")
	private WebElement inputToAutoComplete;
	
	@FindBy(how = How.ID, using="departure_date")
	private WebElement departureDate;
	
	@FindBy(how = How.ID, using="search-form__submit-btn")
	private WebElement searchButton;
	
	@FindBy(how = How.CLASS_NAME, using="hotel-checkbox__airbnb")
	private WebElement searchForAccomodationCheck;
	
	public MainPageSearch(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(inputFrom);
		finder.verifyElementPresent(inputTo);
		finder.verifyElementPresent(searchButton);
		finder.verifyElementPresent(searchForAccomodationCheck);
	}
	
	public ResultsPage searchForDestination(String from, String to){
		finder.fillInElement(inputFrom, from);
		finder.clickElementByPartialLinkText(inputFromAutoComplete, from);
		finder.fillInElement(inputTo, to);
		finder.clickElementByPartialLinkText(inputToAutoComplete, to);
		finder.clickElement(searchForAccomodationCheck);
		finder.clickElement(searchButton);
		finder.clickElement(searchButton);
		finder.clickElement(searchButton);
		
		return PageFactory.initElements(finder.getWebDriver(), ResultsPage.class);
	}
}