package com.AutomationPractice.TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ShoppingBasket extends baseClass {

	@Test
	public void automationpracticepage() throws InterruptedException {

		logger.info("opening the web page");
		driver.get("http://automationpractice.com/index.php");
		driver.manage().window().maximize();

		// Instantiate Action Class
		Actions actions = new Actions(driver);

		// Mouse Hover to First Item
		WebElement menuOption = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]"));

		// Add to cart
		actions.moveToElement(menuOption)
				.moveToElement(
						driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span")))
				.click().build().perform();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Proceed to Checkout
		driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a/span"))
				.click();

		// Check Bin icon is present in Shopping Cart
		boolean isDeletePresent = driver
				.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[7]/div/a/i"))
				.isDisplayed();
		if (isDeletePresent == true) {
			Assert.assertTrue(true);
			logger.info("shopping cart has delete button");
		} else {
			// captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("no delete button present");
		}

		// removing item in Shopping Basket using delete
		driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[7]/div/a/i"))
				.click();

		// check to see if item is deleted using delete button
		boolean isItemFound;
		try {
			isItemFound = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/ul[1]/li[1]"))
					.isDisplayed();
		} catch (NoSuchElementException e) {
			isItemFound = false;
		}
		if (isItemFound) {
			Assert.assertTrue(false);
			logger.info("item is not removed");

		} else {
			Assert.assertTrue(true);
			logger.info("item is removed from the shopping/cart");
		}

		// Test Passed-- if Banner Displayed " Your Shopping Cart is Empty"
		boolean isBannerDisplayed = driver.findElement(By.xpath("//p[contains(text(),'Your shopping cart is empty.')]"))
				.isEnabled();
		if (isBannerDisplayed == true) {
			Assert.assertTrue(true);
			logger.info("test passed--Banner Displayed -- Your Shopping Cart is Empty  ");
		} else {
			Assert.assertTrue(false);
			logger.info("test failed");
		}
	}
}
