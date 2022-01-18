package assignments.day7;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLeaf_Frames {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get("http://leafground.com/pages/frame.html");
		
		driver.switchTo().frame(0);
		WebElement clickMe = driver.findElement(By.id("Click"));
		
		Thread.sleep(2000);
		File clickSource = clickMe.getScreenshotAs(OutputType.FILE);
		File clickDestination = new File("./image/OKbutton.png");
		FileUtils.copyFileToDirectory(clickSource, clickDestination);
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		int numberOfFrames = frames.size();
		System.out.println("The number of Frames on the given WebPage is "+numberOfFrames);
		Thread.sleep(2000);
		
		driver.close();
		

	}

}
