package GermanyIsCalling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://app.germanyiscalling.com/common/login/");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test	
	public void test1() {
		driver.findElement(By.id("username")).sendKeys("hiteshtakras123@gmail.com");
		driver.findElement(By.id("password")).sendKeys("hitesh123@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String currenturl = driver.getCurrentUrl();
		String expectedurl = "https://app.germanyiscalling.com/cv/upload/";

		Assert.assertEquals(currenturl, expectedurl, "Actual URL is NOT matching with Expected URL");

	}

	@Test
	public void test2() {
		driver.findElement(By.id("username")).sendKeys("hiteshtakras@gmail.com");
		driver.findElement(By.id("password")).sendKeys("hitesh123@");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String actualerromesseage = driver.findElement(By.xpath("//div//li")).getText();
		String expectederrormesseage = "Please enter a correct username and password. Note that both fields may be case-sensitive.";

		Assert.assertEquals(actualerromesseage, expectederrormesseage,
				"Actual error message is NOT matching with Expected error message");

	}

}
