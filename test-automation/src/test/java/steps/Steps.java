package steps;

import org.openqa.selenium.support.PageFactory;

import pageObjects.mainPage.MainPage;
import pageObjects.mainPage.MainPageSearch;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.TestEnvironment;

public class Steps {

	private TestEnvironment testEnvironment;
	private MainPageSearch search;
	
	private String fromDestination;
	private String toDestination;
	
	public Steps(TestEnvironment testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	@Given("^a travel search from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void a_travel_search_from_to(String from, String to) throws Throwable {
		testEnvironment.getFinder().LoadPage(testEnvironment.getUrl());
		
		MainPage mainPage = PageFactory.initElements(testEnvironment.getDriver(), MainPage.class);
		mainPage.verifyElementsPresent();
		
		MainPageSearch search = PageFactory.initElements(testEnvironment.getDriver(), MainPageSearch.class);
		search.verifyElementsPresent();
		this.search = search;
		
		fromDestination = from;
		toDestination = to;
	}

	@When("^the search is submitted$")
	public void the_search_is_submitted() throws Throwable {
		search.searchForDestination(fromDestination, toDestination);
	}
}