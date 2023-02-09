import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MainFlowTestScript {
	
	public static void main(String args[]) throws InterruptedException {
		
		System.setProperty("windows.chrome.driver", "C://WebDrivers//chromedriver.exe");

		// Chrome driver
		WebDriver driver = new ChromeDriver();

		String URL = "https://dev.superneetindia.com/";

		// Launch URL
		driver.get(URL);
		// maximize the window
		driver.manage().window().maximize();
		
		//PageLoad time
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		
		//implicit time 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		// Title of the page
		System.out.println("The  title of the page is  " + driver.getTitle());
		
		driver.findElement(By.id("details-button")).click();
		//Thread.sleep(2000);
		
		driver.findElement(By.id("proceed-link")).click();
		
		//Sign in
		driver.findElement(By.linkText("Sign In")).click();
		
		//Log in to Admin Module
		Select select = new Select(driver.findElement(By.id("LoginAs")));
	
		select.selectByVisibleText("Institute");
		
		try {
			// create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream("C:\\Eclipse\\SNIProject\\SNI-DATA.xlsx");
			// creating workbook instance that refers to .xls file
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// creating a Sheet object
			XSSFSheet sheet = workbook.getSheet("LogIn");
			// get all rows in the sheet
			Iterator<Row> rows = sheet.rowIterator();
			XSSFRow row;
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				XSSFCell cell = row.getCell(0);
				//System.out.println("The cell is " + cell);
					String email = row.getCell(0).getStringCellValue();
					String pwd = row.getCell(1).getStringCellValue();
					driver.findElement(By.id("emailid1")).sendKeys(email);
					driver.findElement(By.id("txrPassword")).sendKeys(pwd);
			}
			
			driver.findElement(By.xpath("//*[contains(text(),'Log In Now')]")).click();
			
			//******************Click on 'Institutes' after successful log in***********
			//driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/a[2]/p[1]")).click();
			//p[contains(text(),'Student')]
			driver.findElement(By.xpath("//p[contains(text(),'Student')]")).click();
			
			driver.findElement(By.xpath("//button[@id='UploadNewStudent']")).click();
			
			XSSFSheet sheet1 = workbook.getSheet("Create-popup");
			// get all rows in the sheet
			Iterator<Row> rows1 = sheet1.rowIterator();
			XSSFRow row2;
			while (rows1.hasNext()) {
				row2 = (XSSFRow) rows1.next();
				XSSFCell cell = row2.getCell(0);
					String username = row2.getCell(0).getStringCellValue();
					
					String email = row2.getCell(1).getStringCellValue();
					String address = row2.getCell(2).getStringCellValue();
					
					String marks = row2.getCell(3).getStringCellValue();
					
					int phoneNumber = (int) row2.getCell(4).getNumericCellValue();
					
					String pwd = row2.getCell(5).getStringCellValue();
					String confPwd = row2.getCell(6).getStringCellValue();
					
					//Entering the data for the institute create pop-up
					driver.findElement(By.id("UserName")).sendKeys(username);
					driver.findElement(By.id("Email")).sendKeys(email);					
					driver.findElement(By.id("studentaddress")).sendKeys(address);
					driver.findElement(By.id("studentmarks")).sendKeys(marks);
					driver.findElement(By.id("studentPhoneNumber")).sendKeys(Integer.toString(phoneNumber));
					uploadImage(driver);
					driver.findElement(By.id("Password")).sendKeys(pwd);
					driver.findElement(By.id("ConfirmPassword")).sendKeys(confPwd);
					
				//Click create new institute button
					driver.findElement(By.id("CreateNewStudent")).click();	
					
					//Click 'OK' button after the successful creation					
					driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
					
					//Click on 'Close' button
					driver.findElement(By.xpath("//body/div[2]/section[1]/div[5]/div[1]/div[1]/div[1]/div[2]/form[1]/div[8]/div[1]/button[2]")).click();
					
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		

		driver.close();
		driver.quit();
		
		
	}
	
	
	public static void  uploadImage(WebDriver driver) throws AWTException, InterruptedException {
		// Upload the file using Robot class using Robot
		Robot robo = new Robot();
		// File path passed as a parameter using StringSelection //
		StringSelection str = new StringSelection("C:\\Users\\shirv\\OneDrive\\Pictures\\strawberry-1330459_960_720.jpg");
		// Set the delay
		robo.setAutoDelay(1000);
		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		WebElement m=driver.findElement(By.xpath("//input[@id='studentFile']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", m);
		
		
		//driver.findElement(By.xpath("//input[@id='studentFile']")).click();
		// Pressing ctl+v
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);

		// release ctr+v
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);

		// pressing Enter
		robo.keyPress(KeyEvent.VK_ENTER);
		// Release Enter //
		robo.keyRelease(KeyEvent.VK_ENTER);

	}

}
