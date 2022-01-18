package assignments.day7;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("http://leaftaps.com/opentaps/control/login");
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.xpath("//a[normalize-space(text()='CRM/SFA')]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		
		driver.findElement(By.xpath("(//span[@class='requiredField'])[1]/parent::td/following-sibling::td/a")).click();
		
		Set<String> fromWind = driver.getWindowHandles();
		for (String windHand : fromWind) {
			System.out.println(windHand);
		}
		
		List<String> fromwindList = new ArrayList<String>(fromWind);
		driver.switchTo().window(fromwindList.get(1));
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		Thread.sleep(3000);
		driver.switchTo().window(fromwindList.get(0));
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("(//span[@class='requiredField'])[2]/parent::td/following-sibling::td/a")).click();
		
		Set<String> toWindowChild = driver.getWindowHandles();
		for (String toChild : toWindowChild) {
			System.out.println(toChild);
		}
		
		List<String> towindList = new ArrayList<String>(toWindowChild);
		driver.switchTo().window(towindList.get(1));
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(towindList.get(0));
		
		driver.findElement(By.linkText("Merge")).click();
		
		driver.switchTo().alert().accept();
		
		if(driver.getTitle().contains("View Contact")) {
			System.out.println("The contacts are merged.");
		}
		else {
			System.out.println("There is some issue.");
		}
		Thread.sleep(3000);
		driver.quit();
	}

}
