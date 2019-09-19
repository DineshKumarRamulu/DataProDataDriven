package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserCreation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stubs

		Xls_Reader reader = new Xls_Reader("C:\\Automation\\DataProDataDrivern\\Testdata\\USERSSAMPLE.xlsx");
		int rowcount = reader.getRowCount("USERSSAMPLE");

		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Drivers\\Chrome\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir")+"\\src\\test\\java\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		String URL = reader.getCellData("USERSSAMPLE", "URL", 2);
		String LoginUserID = reader.getCellData("USERSSAMPLE", "LoginUserID", 2);
		String LoginPassword = reader.getCellData("USERSSAMPLE", "LoginPassword", 2);
		

		driver.get(URL);
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(LoginUserID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(LoginPassword);
		driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable']")).click();

		driver.findElement(By.xpath("//i[contains(text(),'person')]")).click();
		// Thread.sleep(2000);
		driver.findElement(By.xpath("(//i[@class='material-icons' and text()='person'])[2]")).click();
		// Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@class,'ui-g-12 ui-md-7 text-right')]//button[1]")).click();

		for (int rowNum = 2; rowNum <= rowcount; rowNum++) {

			String username = reader.getCellData("USERSSAMPLE", "USERNAME", rowNum);
			String firstname = reader.getCellData("USERSSAMPLE", "FIRSTNAME", rowNum);
			String lastname = reader.getCellData("USERSSAMPLE", "LASTNAME", rowNum);
			String email = reader.getCellData("USERSSAMPLE", "EMAIL", rowNum);
			String jobtitle = reader.getCellData("USERSSAMPLE", "JOBTITLE", rowNum);
			String usertype = reader.getCellData("USERSSAMPLE", "USERTYPE", rowNum);
			String role = reader.getCellData("USERSSAMPLE", "ROLE", rowNum);
			String password = reader.getCellData("USERSSAMPLE", "PASSWORD", rowNum);
			String confirmpwd = reader.getCellData("USERSSAMPLE", "CONFIRMPASSWORD", rowNum);
			String sitename = reader.getCellData("USERSSAMPLE", "SITENAME", rowNum);

			driver.findElement(By.xpath("(//span[@class='md-inputfield ui-md-12 padding-none']/input)[1]"))
					.sendKeys(username);
			driver.findElement(By.xpath("//div[4]//span[1]//input[1]")).sendKeys(firstname);
			driver.findElement(By.xpath("//div[3]//span[1]//input[1]")).sendKeys(lastname);
			driver.findElement(By.xpath("//div[5]//span[1]//input[1]")).sendKeys(email);
			driver.findElement(By.xpath("//div[6]//span[1]//input[1]")).sendKeys(jobtitle);
			driver.findElement(By.xpath(
					"(//ng-select[@class='ng-select ng-select-single ng-select-searchable ng-untouched ng-pristine ng-valid']//input)[1]"))
					.sendKeys(usertype + Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[text()='Roles']/following::div[2]/input")).sendKeys(role + Keys.ENTER);
			driver.findElement(By.xpath("//label[text()=' Authentication']/following::div[1]/span/input"))
					.sendKeys(password);
			driver.findElement(By.xpath("//label[text()=' Authentication']/following::div[2]/span/input"))
					.sendKeys(confirmpwd);
			// driver.findElement(By.xpath("//label[text()=' Sites
			// Local']/following::div[1]/ng-select/div/div/div[2]")).click();
			driver.findElement(By.xpath(
					"//label[text()=' Sites']/following::div[1]/ng-select/div/div/div[2]/following::div[1]/input"))
					.sendKeys(sitename);
			driver.findElement(By.xpath(
					"//label[text()=' Sites']/following::div[1]/ng-select/div/div/div[2]/following::div[1]/input/following::ng-dropdown-panel/div[2]/div/div/div/input"))
					.click();
			driver.findElement(By.xpath("//span[@class='ui-button-text ui-clickable' and text()='Save and Add New']"))
					.click();

			String alertmsg = driver.findElement(By.xpath("//div[@class='ui-toast-detail']")).getText();

			System.out.println(username + " " + alertmsg);
			reader.setCellData("USERSSAMPLE", "RESULT", rowNum, alertmsg);

			WebElement userlink = driver.findElement(By.xpath("//a[text()='Users']"));

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", userlink);
			js.executeScript("arguments[0].click();", userlink);
			// userlink.click();

			driver.findElement(By.xpath("//div[contains(@class,'ui-g-12 ui-md-7 text-right')]//button[1]")).click();
			

		}
		
		driver.close();
	}

}
