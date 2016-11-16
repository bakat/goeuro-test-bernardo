package driver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import cucumber.api.DataTable;

public class ComponentFinder {
	
	private static final int TIMEOUT = 30000;
	
	private String browserChrome;
	private String browserIE;
	private String browserFireFox;
	private String environmentProduction;
	private String environmentQA;
	private String defaultEnvironment;
	private WebDriver webDriver;
	private static final String PROPERTIES_FILE = 
			"config/testAutomation.properties";
	
	public ComponentFinder() {
		loadProperties();
	}
	
	public ComponentFinder(WebDriver driver){
		loadProperties();
		webDriver = driver;
	}

	private void loadProperties() {
		Properties propertiesFile = new Properties();
		 
    	try {
    		propertiesFile.load(getClass().getClassLoader().getResourceAsStream(
					PROPERTIES_FILE));
 
    		browserFireFox = propertiesFile.getProperty("BROWSER_FIREFOX");
    		browserChrome = propertiesFile.getProperty("BROWSER_CHROME");
    		browserIE = propertiesFile.getProperty("BROWSER_IE");
    		environmentProduction = propertiesFile.getProperty("ENVIRONMENT_PROD");
    		environmentQA = propertiesFile.getProperty("ENVIRONMENT_QA");
    		defaultEnvironment = propertiesFile.getProperty("DEFAULT_ENVIRONMENT");
    		
    	} catch (IOException ex) {
    		System.out.println("Could not load properties file. \r\n");
    		ex.printStackTrace();
        }
	}
	
	public WebDriver getWebDriver(){
		return webDriver;
	}
	

	public void navigateToUrl(String url){
		webDriver.navigate().to(url);
	}
	
	public void LoadPage(String url){
		webDriver.get(url);
		webDriver.manage().window().maximize();
	}
	
	public void getDriver(EnumBrowser browser, DesiredCapabilities capabilities){
		
		File file;
		
		if(browser != null){
			switch (browser) {
				case FIREFOX:
					try {
						webDriver = new FirefoxDriver();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				
				case CHROME:
					file = new File(browserChrome);
					System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

					try {
						//TODO
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
					
				case IE:
					file = new File(browserIE);
					System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
					
					try {
						//TODO
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
					
				default:
					System.out.println("Not a valid browser.");
					break;
			}
		} else if(browser == null){
			try {
				file = new File(browserFireFox);
				System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
				webDriver = new FirefoxDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getEnvironment(EnumEnvironment environment){
		
		String webEnvironment = "";
		
		if(environment != null){
			switch (environment) {
				case PRODUCTION:
					webEnvironment = environmentProduction;
					break;
				
				case QA:
					webEnvironment = environmentQA;	
					break;
					
				default:
					System.out.println("Not a valid environment");
					break;
			}
		} else {
			if(defaultEnvironment.equals("QA")){
				webEnvironment = environmentQA;
			}else if(defaultEnvironment.equals("PRODUCTION")){
				webEnvironment = environmentProduction;
			} else{
				webEnvironment = environmentQA;
			}
		}
		
		return webEnvironment;
	}
	
	public void closeBrowser(){
		webDriver.quit();
	}
	
	public String getPageTitle(){
		return webDriver.getTitle();
	}
	
	public Boolean verifyPageTitle(String pageTitle){
		return webDriver.getTitle().equals(pageTitle);
	}
	
	public void waitForElementPresent(final By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

	   wait.until(new Function<WebDriver, WebElement>() {
	     public WebElement apply(WebDriver driver) {
	       return driver.findElement(by);
	     }
	   });
	}
	
	public void waitForElementToBeClickable(WebElement element){
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementPresent(WebElement element) {
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForTextPresent(final String text){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
			       .withTimeout(30, TimeUnit.SECONDS)
			       .pollingEvery(5, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

	   Boolean elemento = wait.until(new Function<WebDriver, Boolean>() {
	     public Boolean apply(WebDriver driver) {
	       return driver.getPageSource().contains(text);
	     }
	   });
	   
	   if(elemento.equals(false)){
		   System.out.println("Text " + text + " not found.");
	   }
	}
	
	public Boolean verifyTextPresent(String text){
		return webDriver.getPageSource().contains(text);
	}
	
	public void fillInElement(WebElement element, String text){
		waitForElementPresent(element);
		element.click();
		element.sendKeys(text);
	}
	
	public void clearElementText(WebElement element){
		element.clear();
	}
	
	public void clickElement(WebElement element){
		waitForElementPresent(element);
		waitForElementToBeClickable(element);
		element.click();
	}
	
	public String getElementsText(WebElement element){
		waitForElementPresent(element);
		return element.getText();
	}
	
	public void submitForm(WebElement element){
		element.submit();
	}
	
	public Boolean verifyElementPresent(WebElement element){
		waitForElementPresent(element);
		return element.isEnabled();
	}

	public List<List<String>> getListFromDataTable(DataTable cucumberTable){
		List<List<String>> listOfRecords = new ArrayList<List<String>>(cucumberTable.raw());
		listOfRecords.remove(0);
		return listOfRecords;
	}
	
	public void switchToFrameByXpath(String valorAtributoId){
		webDriver.switchTo().frame(webDriver.findElement(By.xpath(valorAtributoId)));
	}
	
	public void switchToFramByClassName(String nomeClasse){
		webDriver.switchTo().frame(webDriver.findElement(By.className(nomeClasse)));
	}
	
	public void switchToMainPage(){
		webDriver.switchTo().defaultContent();
	}
}