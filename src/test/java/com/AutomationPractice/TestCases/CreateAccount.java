package com.AutomationPractice.TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateAccount extends baseClass {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;

	String Email;
	String Title;
	String FirstName;
	String LastName;
	String Password;
	int Day;
	String DayS;
	String Month;
	int Year;
	String YearS;
	String Company;
	String Address;
	String Address2;
	String City;
	String State;
	int Zip_PostalCode;
	String Zip_PostalCodeS;
	String AdditionalInfo;
	int HomePhone;
	String HomePhoneS;
	int MobilePhone;
	String MobilePhoneS;
	String Alias;

	@Test
	public void automationpracticepage() throws InterruptedException, IOException {

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();

		logger.info("opening excel file");
		String path = System.getProperty("user.dir") + "/src/test/java/com/AutomationPractice/TestData/LoginData1.xlsx";
		XSSFSheet sheet = this.openSheetFromExcel(path);
		int noOfRows = sheet.getLastRowNum();

		for (int row = 1; row <= noOfRows; row++) {

			// Reading excel row by row
			XSSFRow current_row = sheet.getRow(row);

			// getting values from excel and assigning into variables
			this.getExcelValues(current_row);

			// enter email from the file
			driver.findElement(By.id("email_create")).sendKeys(Email);
			driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
			logger.info("entered email from the excel file");

			// if email existing then banner appear
			boolean isBannerFound;
			try {
				isBannerFound = driver.findElement(By.xpath(
						"//*[contains(text(),'An account using this email address has already been registered.')]"))
						.isDisplayed();
			} catch (NoSuchElementException e) {
				isBannerFound = false;
			}

			// An account using this email address has already been registered.
			if (isBannerFound) {
				// found the banner and clearing the email
				logger.info("found the banner and clearing the email");
				driver.findElement(By.id("email_create")).clear();
				Assert.assertTrue(true);

			} else {
				// banner not found fill the form
				logger.info("banner not found fill the form");
				this.formFill();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Assert.assertTrue(true);

			}

		}

	}

	public XSSFSheet openSheetFromExcel(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		// creating workbook instance that refers to .xls file
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		// or get data with sheet name
		XSSFSheet sheet = wb.getSheet("sheet1");
		return sheet;
	}

	public void getExcelValues(XSSFRow current_row) {

		logger.info("getting the data from excel, storing into variables");
		Email = current_row.getCell(0).getStringCellValue();
		Title = current_row.getCell(1).getStringCellValue();
		FirstName = current_row.getCell(2).getStringCellValue();
		LastName = current_row.getCell(3).getStringCellValue();
		Password = current_row.getCell(4).getStringCellValue();
		Day = (int) current_row.getCell(5).getNumericCellValue();
		DayS = String.valueOf(Day);
		Month = current_row.getCell(6).getStringCellValue();
		Year = (int) current_row.getCell(7).getNumericCellValue();
		YearS = String.valueOf(Year);
		Company = current_row.getCell(8).getStringCellValue();
		Address = current_row.getCell(9).getStringCellValue();
		Address2 = current_row.getCell(10).getStringCellValue();
		City = current_row.getCell(11).getStringCellValue();
		State = current_row.getCell(12).getStringCellValue();
		Zip_PostalCode = (int) current_row.getCell(13).getNumericCellValue();
		Zip_PostalCodeS = String.valueOf(Zip_PostalCode);
		AdditionalInfo = current_row.getCell(14).getStringCellValue();
		HomePhone = (int) current_row.getCell(15).getNumericCellValue();
		HomePhoneS = String.valueOf(HomePhone);
		MobilePhone = (int) current_row.getCell(16).getNumericCellValue();
		MobilePhoneS = String.valueOf(MobilePhone);
		Alias = current_row.getCell(17).getStringCellValue();
	}

	public void formFill() throws InterruptedException {

		// select title
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// to check the valid data
		boolean isValid = true;
		if (Title.toString().contentEquals("Mr")) {
			WebElement radioBtnMr = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[1]/label"));
			radioBtnMr.click();
		} else {
			WebElement radioBtnMrs = driver
					.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/form/div[1]/div[1]/div[2]/label"));
			radioBtnMrs.click();
		}
		// enter First Name
		driver.findElement(By.id("customer_firstname")).sendKeys(FirstName);
		if ((FirstName.length() < 1) || (FirstName.length() > 32)) {
			isValid = false;
			logger.info("invalid first name");

		}
		// enter Last Name
		driver.findElement(By.id("customer_lastname")).sendKeys(LastName);
		if ((LastName.length() < 1) || (LastName.length() > 32)) {
			isValid = false;
			logger.info("invalid last name");

		}
		// Password
		driver.findElement(By.id("passwd")).sendKeys(Password);
		if ((Password.length() < 1) || (Password.length() > 32)) {
			isValid = false;
			logger.info("invalid password");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// enter DOB Day
		Select dropdownDay = new Select(driver.findElement(By.id("days")));
		dropdownDay.selectByValue(DayS);
		Select dropdownMonth = new Select(driver.findElement(By.id("months")));
		dropdownMonth.selectByVisibleText(Month + " ");
		// enter DOB Year
		Select dropdownYear = new Select(driver.findElement(By.id("years")));
		dropdownYear.selectByValue(YearS);
		// enter Company
		driver.findElement(By.id("company")).sendKeys(Company);
		// enter Address
		driver.findElement(By.id("address1")).sendKeys(Address);
		if ((Address.length() < 1) || (Address.length() > 128)) {
			isValid = false;
			logger.info("invalid address");
		}
		// enter Address 2
		driver.findElement(By.id("address2")).sendKeys(Address2);
		// enter City
		driver.findElement(By.id("city")).sendKeys(City);
		if ((City.length() < 1) || (City.length() > 64)) {
			isValid = false;
			logger.info("city");
		}
		// select State DropDown
		Select dropdownState = new Select(driver.findElement(By.id("id_state")));
		dropdownState.selectByVisibleText(State);
		// enter Zip PostalCode
		driver.findElement(By.id("postcode")).sendKeys(Zip_PostalCodeS);
		if ((Zip_PostalCodeS.length() < 1) || (Zip_PostalCodeS.length() > 5)) {
			isValid = false;
			logger.info("invalid Zip/Postal code");
		}
		// enter Additional Info
		driver.findElement(By.id("other")).sendKeys(AdditionalInfo);
		Thread.sleep(2000);
		// enter Home Phone
		driver.findElement(By.id("phone")).sendKeys(HomePhoneS);
		if ((HomePhoneS.length() < 1) || (HomePhoneS.length() > 7)) {
			isValid = false;
			logger.info("invalid home phone");
		}
		// enter Mobile Phone
		driver.findElement(By.id("phone_mobile")).sendKeys(MobilePhoneS);
		// enter Alias
		driver.findElement(By.id("alias")).sendKeys(Alias);
		// click Submit
		driver.findElement(By.id("submitAccount")).click();
		logger.info("filled the form using excel file data ");

		// All form is entered and checked
		if (isValid == false) {
			logger.info("banner should display ");
			// banner
			try {
				driver.findElement(By.xpath("//*[@id=\"center_column\"]/div"));
			} catch (NoSuchElementException e) {
				Assert.assertTrue(false);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// try - catch (banner), in this case, fail the test
		} else {
			// True if "My Account" displayed
			// boolean is still true
			// you should go and check the next page with information
			boolean isMyAccountDisplayed = driver.findElement(By.xpath("//span[contains(text(),'My account')]"))
					.isEnabled();
			if (isMyAccountDisplayed == true) {
				Assert.assertTrue(true);
				logger.info("Completing registration- takes user to My Account page ");
			} else {
				Assert.assertTrue(false);
				logger.info("my account not displayed");
			}

			// to check if account name displays on top right corner
			String result = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span"))
					.getText();
			if (result.equalsIgnoreCase(FirstName + " " + LastName)) {
				Assert.assertTrue(true);
				logger.info("user name displayed at top right corner");
			} else {
				Assert.assertTrue(false);
				logger.info("user name is not present");
			}

			// press Sign out
			driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();
			logger.info("sign out");
		}

	}
}
