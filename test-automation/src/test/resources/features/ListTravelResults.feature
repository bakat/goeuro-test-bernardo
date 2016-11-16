Feature: Travel results listing
	As a travel search service
	I need to be able to display travel options by train, plane and bus
	So that the customer can find the best way to travel

@PositiveScenario
Scenario: Search results are sorted by price
	Given a travel search from "Berlin" to "Prague"
	When the search is submitted