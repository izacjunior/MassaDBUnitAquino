package cursoMassa.massa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class IniciarSelenium {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Tools\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://seubarriga.wcaquino.me/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("email")).sendKeys("a@a");
		
		driver.findElement(By.id("senha")).sendKeys("a");
		
		driver.findElement(By.tagName("button")).click();
		
		driver.quit();
		
	}
	
	
}
