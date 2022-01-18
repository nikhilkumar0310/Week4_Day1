package assignments.day7;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		
		driver.get("https://dev67933.service-now.com/");
		driver.switchTo().frame("gsft_main");//Since it is  frame
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@6117");
		driver.findElement(By.id("sysverb_login")).click();
		
		driver.findElement(By.id("filter")).sendKeys("incident");
		Thread.sleep(8000);
		driver.findElement(By.linkText("All")).click();
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[text()='New']")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys("Sam Sorokin");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("incident.short_description")).sendKeys("This is just a Testing Assignment created for TestLeaf");
		String incNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println(incNumber);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys(incNumber);
		driver.findElement(By.xpath("(//input[@placeholder='Search'])[1]")).sendKeys(Keys.ENTER);
		
		WebElement incRegion = driver.findElement(By.xpath("(//div[@role='region']/following-sibling::div)[5]"));
		File source = incRegion.getScreenshotAs(OutputType.FILE);
		File destination = new File("./image/Created_INC.png");
		FileUtils.copyFileToDirectory(source, destination);
		
		Thread.sleep(2000);
		driver.close();

	}

}
