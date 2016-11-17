package pageObjects.resultsPage;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.PageObjectInterface;
import driver.ComponentFinder;

public class ResultsPage implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.CLASS_NAME, using="searchbar-wrapper")
	private WebElement searchBar;
	
	@FindBy(how = How.CLASS_NAME, using="resultTabs")
	private WebElement resultTabs;
	
	public ResultsPage(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(searchBar);
		finder.waitForTheFirstTravelResult(resultTabs);
	}
	
	public void sortResultsByTheCheapestPrice(){
		SortingBar sortingBar = PageFactory.initElements(finder.getWebDriver(), SortingBar.class);
		sortingBar.verifyElementsPresent();
		sortingBar.sortByCheapest();
	}

	public List<BigDecimal> getListOfTravelResultPrices(){
		ResultTabs resultTabs = PageFactory.initElements(finder.getWebDriver(), ResultTabs.class);
		resultTabs.verifyElementsPresent();
		return resultTabs.getListOfTravelResultPrices();
	}
}