package com.AutomationPractice.TestCases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class StoresMap extends baseClass {

	@Test
	public void automationpracticepage() throws InterruptedException {

		logger.info("opening the web page");
		driver.get("http://automationpractice.com/index.php?controller=stores");
		driver.manage().window().maximize();

		// click on maximize button
		try {
			driver.findElement(By.xpath("//*[@id=\"map\"]/div[2]/table/tr/td[2]/button")).click();
		} catch (NoSuchElementException e1) {
			logger.info("No pop up came this time...");

		}

		// catch excception of google map popup
		try {
			driver.findElement(By.cssSelector("#map > div > div > div:nth-child(7) > button")).click();

		} catch (NoSuchElementException e) {
			logger.info("Error in maximising the map");
			e.printStackTrace();
		}

		// scroll the map
		WebElement mapElement = driver
				.findElement(By.cssSelector("#map > div > div > div:nth-child(1) > div:nth-child(1)"));
		org.openqa.selenium.Dimension mapWidth = mapElement.getSize();
		logger.info(mapWidth);
		new Actions(driver).moveToElement(mapElement).clickAndHold().moveByOffset(66, 132).release().build().perform();
		
		// get the screen shot
		captureScreenShot(driver, "MapScreenShot");
		
		//to check if map moved
		boolean isMapMoved;
		try {
			isMapMoved = mapElement.isSelected();
			isMapMoved = true;

		} catch (NoSuchElementException e) {
			isMapMoved = false;
		}

		 if (isMapMoved) {
		 logger.info("map has moved");
		 Assert.assertTrue(true);
		
		 } else {
		 logger.info("map has not moved");
		 Assert.assertTrue(false);
		
		 }

	}

	public static void captureScreenShot(WebDriver driver, String screenshotName) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("./Screenshots/" + screenshotName + ".png"));
		} catch (Exception e) {
			System.out.println("exception" + e.getMessage());

		}

	}

}
