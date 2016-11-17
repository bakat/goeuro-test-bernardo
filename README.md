# GOEURO - Technical Exercise - Software Engineer in Test

Author: Bernardo Vieira Carneiro

### Description

The project ***test-automation*** contains a test automation for validating the behavior of the travel search from [GOEURO] (http://www.goeuro.com). It was built in Java, using the Selenium Webdriver for UI interaction, Cucumber for test scenario description and Junit for test execution.

Internally you can find the project organized as follows:
- `src/main/java`: consists of a simple framework for automating tests in a more reusable and maintainable way;
- `src/main/resources`: contains a properties file within which some variables are set;
- `src/test/java`: contains the implementation of the tests, with page objects, step definitions and test classes;
- `src/test/resources`: contains a feature file with a scenario description;
- `browserDrivers`: contains the firefox driver.

### How to run the test

- From an IDE of your choice:
Import the project in your preferred IDE, open the class ***ListTravelResultsTest.java*** and click the RUN button. You can also right click the mentioned class and choose the option "Run As > Junit Test".

- From maven:
From a command line tool, access the path "goeuro-test-bernardo/test-automation" and then execute the following command: ***mvn surefire:test -Dtest=ListTravelResultsTest.java***

Although the project supports the test execution on multiple browsers, for this technical exercise it was only tested with the Firefox browser. Bear in mind that the version of the Firefox browser matters for test execution. Below follows a list containing the versions of the browser and libraries used.

### Versions of the libraries used

- Junit: 4.12
- Selenium: 3.0.1
- Cucumber: 1.2.3
- Firefox browser: 50.0
- Java version: 1.8