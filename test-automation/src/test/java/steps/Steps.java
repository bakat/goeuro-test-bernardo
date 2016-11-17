package steps;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import pageObjects.mainPage.MainPage;
import pageObjects.resultsPage.ResultsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.TestEnvironment;

public class Steps {

	private TestEnvironment testEnvironment;
	private MainPage mainPage;
	private ResultsPage resultsPage;
	
	private String fromDestination;
	private String toDestination;
	
	public Steps(TestEnvironment testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	@Given("^a travel search from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void a_travel_search_from_to(String from, String to) throws Throwable {
		testEnvironment.getFinder().LoadPage(testEnvironment.getUrl());
		
		mainPage = PageFactory.initElements(testEnvironment.getDriver(), MainPage.class);
		mainPage.verifyElementsPresent();
		
		fromDestination = from;
		toDestination = to;
	}

	@When("^the search is submitted$")
	public void the_search_is_submitted() throws Throwable {
		resultsPage = mainPage.performASearch(fromDestination, toDestination);
	}
	
	@And("^the results are sorted by the \"([^\"]*)\" option$")
	public void the_results_are_sorted_by_the(String option) throws Throwable {
		resultsPage.verifyElementsPresent();

		if(option.equalsIgnoreCase("cheapest")){
			resultsPage.sortResultsByTheCheapestPrice();
		}
	}
	
	@Then("^the travel results should be ordered by their prices in ascending order$")
	public void the_travel_results_should_be_ordered_by_their_prices_in_ascending_order() throws Throwable {
		List<BigDecimal> pricesList = resultsPage.getListOfTravelResultPrices();
		BigDecimal lastPrice = new BigDecimal("0.00");
		
		for (BigDecimal price : pricesList) {
			Assert.assertTrue(price.compareTo(lastPrice) == 1 || price.compareTo(lastPrice) == 0);
			System.out.println("The price " + lastPrice.toString() + " is less than or equal to " + price.toString());
			lastPrice = price;
		}
	}
}