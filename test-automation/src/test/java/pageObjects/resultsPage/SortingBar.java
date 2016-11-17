package pageObjects.resultsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import pageObjects.PageObjectInterface;
import driver.ComponentFinder;

public class SortingBar implements PageObjectInterface{

	private ComponentFinder finder;
	
	@FindBy(how = How.CLASS_NAME, using="result-wrapper")
	private WebElement resultWrapper;

	public SortingBar(WebDriver webDriver) {
		finder = new ComponentFinder(webDriver);
	}

	public void verifyElementsPresent() {
		finder.verifyElementPresent(resultWrapper);
	}
	
	public void sortByCheapest(){
		finder.clickElementThatContainsNameInsideASpan(resultWrapper, "Cheapest");
	}
}