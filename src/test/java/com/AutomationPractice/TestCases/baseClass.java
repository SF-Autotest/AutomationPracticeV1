package com.AutomationPractice.TestCases;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class baseClass {
	public static WebDriver driver = null;
	public static Logger logger;

	// @BeforeSuite
	@BeforeTest
	public void initialize() throws IOException {

		System.out.println("Inside Baseclass beforesuite");

		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver");
		driver = new ChromeDriver();

		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("logger has created");

	}

	@AfterTest
	// Test cleanup
	public void TeardownTest() {
		driver.quit();
	}
}
