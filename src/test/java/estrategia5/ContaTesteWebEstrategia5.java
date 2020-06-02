package estrategia5;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTesteWebEstrategia5 {

	private static ChromeDriver driver;
	private Faker faker = new Faker();

	@BeforeClass
	public static void reset() {

		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Tools\\chromedriver.exe");

		driver.manage().window().maximize();
		
		driver.get("http://seubarriga.wcaquino.me/");

		driver.findElement(By.id("email")).sendKeys("a@a");

		driver.findElement(By.id("senha")).sendKeys("a");

		driver.findElement(By.tagName("button")).click();

		driver.findElement(By.linkText("reset")).click();

		
	}

	@Test
	public void Teste01_inserir() {

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Adicionar")).click();

		driver.findElement(By.id("nome")).sendKeys("Conta Estrategia 5");

		driver.findElement(By.tagName("button")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta adicionada com sucesso!");
	}

	@Test
	public void Teste02_consultar() throws ClassNotFoundException, SQLException {

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), 'Conta para saldo')]/..//a")).click();

		assertEquals(driver.findElement(By.id("nome")).getAttribute("value"), "Conta para saldo");
	}

	@Test
	public void Teste03_alterar() throws ClassNotFoundException, SQLException {

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), 'Conta para alterar')]/..//a")).click();

		driver.findElement(By.id("nome")).sendKeys("Alterado");

		driver.findElement(By.tagName("button")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta alterada com sucesso!");

	}

	@Test
	public void Teste04_excluir() throws ClassNotFoundException, SQLException {

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), 'Conta mesmo nome')]/..//a[2]")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta removida com sucesso!");
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

}
