Feature: Travel results listing
	As a travel search service
	I need to be able to display travel options by train, plane and bus
	So that the customer can find the best way to travel

@PositiveScenario
Scenario: Search results are sorted by price
	Given a travel search from "Berlin, Germany" to "Prague, Czech Republic"
	When the search is submitted
	And the results are sorted by the "Cheapest" option
	Then the travel results should be ordered by their prices in ascending order