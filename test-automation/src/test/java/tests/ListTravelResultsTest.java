package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"html:target/test-report"}, 
features = {"src/test/resources/features/ListTravelResults.feature"}, 
glue = {"steps", "driver"},
tags = {})
public class ListTravelResultsTest {

}