package com.AutomationPractice.TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SummerDresses extends baseClass {

	@Test
	public void automationpracticepage() throws InterruptedException {

		// Instantiate Action Class
		Actions actions = new Actions(driver);

		// Mouse Hover to Women
		WebElement menuOption = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"));

		// to check if the sub navigation option appear, when mouse hover to woman
		boolean isSubNavigatePresent = driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a"))
				.isDisplayed();
		if (isSubNavigatePresent == true) {
			Assert.assertTrue(true);
			logger.info("on mouse hover button- sub navigation appear");
		} else {
			Assert.assertTrue(false);
			logger.info("no sub navigation");
		}

		// select Summer dresses
		actions.moveToElement(menuOption)
				.moveToElement(driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[2]/ul/li[3]/a")))
				.click().build().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// True if only Summer Dresses are displayed
		boolean isSummerDressesDisplayed = driver.findElement(By.xpath("//span[contains(text(),'Summer Dresses')]"))
				.isEnabled();
		if (isSummerDressesDisplayed == true) {
			Assert.assertTrue(true);
			logger.info("test passed--Summer Dress Displayed ");
		} else {
			Assert.assertTrue(false);
			logger.info("test failed");
		}
	}

}
