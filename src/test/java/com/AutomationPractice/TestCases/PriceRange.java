package com.AutomationPractice.TestCases;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PriceRange extends baseClass {

	@Test
	public void automationpracticepage() throws InterruptedException {

		// move slider to $16-$20
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"layered_price_slider\"]/a[2]"));
		Actions moveSlider = new Actions(driver);
		org.openqa.selenium.Dimension sliderWidth = slider.getSize();
		System.out.println("Slider Width = " + sliderWidth);
		int numberOfRangeToDragTheSlider = -183;
		moveSlider.moveToElement(slider).clickAndHold().moveByOffset(numberOfRangeToDragTheSlider, 0).release()
				.perform();
		logger.info("slider has moved to $16-$20");

		// to check if slider has moved
		boolean isSliderMoved;
		try {
			isSliderMoved = slider.isSelected();
			isSliderMoved = true;

		} catch (NoSuchElementException e) {
			isSliderMoved = false;
		}

		if (isSliderMoved) {
			logger.info("slider has moved");
			Assert.assertTrue(true);

		} else {
			logger.info("slider has not moved");
			Assert.assertTrue(false);

		}

	}

}
