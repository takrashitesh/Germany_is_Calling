package GermanyIsCalling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Login {

	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;
	static WebDriver driver;

	static {
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Reports" + "\\report_" + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest("Verify the login Functionality");
	}

	@BeforeMethod
	public void setUp() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://app.germanyiscalling.com/common/login/");
		test.log(Status.INFO, "Browser opened : Chrome");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		extent.flush();
	}

	@Test(description = "verifies a successful login using valid credentials")
	public void test1() {

		driver.findElement(By.id("username")).sendKeys("hiteshtakras123@gmail.com");
		driver.findElement(By.id("password")).sendKeys("hitesh123@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		test.log(Status.INFO, "valid username and password is entered");

		String currenturl = driver.getCurrentUrl();
		String expectedurl = "https://app.germanyiscalling.com/cv/upload/";

		Assert.assertEquals(currenturl, expectedurl, "Actual URL is NOT matching with Expected URL");
		test.log(Status.PASS, "successfully login");
	}

	@Test(description = "verifies an unsuccessful login using incorrect credentials")
	public void test2() {

		driver.findElement(By.id("username")).sendKeys("hiteshtakras@gmail.com");
		driver.findElement(By.id("password")).sendKeys("hitesh123@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		test.log(Status.INFO, "Invalid username and password is entered");

		String actualerromesseage = driver.findElement(By.xpath("//div//li")).getText();
		String expectederrormesseage = "Please enter a correct username and password. Note that both fields may be case-sensitive.";

		Assert.assertEquals(actualerromesseage, expectederrormesseage,
				"Actual error message is NOT matching with Expected error message");
		test.log(Status.PASS, "Appropriate Error message Is Displayed");
	}

}
