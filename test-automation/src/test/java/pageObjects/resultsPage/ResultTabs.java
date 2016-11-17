package pageObjects.resultsPage;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import driver.ComponentFinder;
import pageObjects.PageObjectInterface;

public class ResultTabs implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.CLASS_NAME, using="resultTabs")
	private WebElement resultTabs;
	
	public ResultTabs(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(resultTabs);
	}

	public List<BigDecimal> getListOfTravelResultPrices(){
		return finder.getPricesFromTravelResults(resultTabs);
	}
}