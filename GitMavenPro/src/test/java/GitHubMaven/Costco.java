package GitHubMaven;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Costco {
	public WebDriver driver;

	@BeforeMethod
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Chrome browser started ...");
		driver.manage().window().maximize(); // Maximize the browser window
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterTest() throws Exception {
		Thread.sleep(5 * 1000);
		driver.quit();
	}

	@Test(enabled = true)
	public void costcoPracitce1() throws Exception {

		// Step1: go to Costco.com
		driver.get("http://www.costco.com");
		String actualPageTitle = driver.getTitle();
		System.out.println("page title is: " + actualPageTitle);

		// Step2: verify page title
		assertEquals(actualPageTitle, "Welcome to Costco Wholesale");

		// Step3: find and locate Search field enter "Xbox"
		System.out.println("locating 'Search' field.");
		WebElement searchField = driver.findElement(By.id("search-field"));

		System.out.println("entering 'Xbox'");
		searchField.sendKeys("Xbox");
		Thread.sleep(1 * 1000);
		System.out.println("Waiting for 1 second");

		// Step4: select 2nd option from the drop-down
		System.out.println("locating dropdown element");
		WebElement dropdownParent = driver.findElement(By.cssSelector(".tt-dataset.tt-dataset-search"));
		List<WebElement> dropdownOptions = dropdownParent.findElements(By.tagName("h6"));
		System.out.println("clicking on 2nd option from dropdown");
		dropdownOptions.get(1).click();

		// Step5: locate the "Item number" and print on the console
		System.out.println("locating product item number element using explicite wait");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement productItemNum = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.id("product-body-item-number")));

		// WebElement productItemNum =
		// driver.findElement(By.id("product-body-item-number"));
		String itemNumber = productItemNum.getText();
		System.out.println("Product Item Number: " + itemNumber);

	}

}
