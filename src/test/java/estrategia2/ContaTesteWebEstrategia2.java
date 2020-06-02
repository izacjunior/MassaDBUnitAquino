package estrategia2;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTesteWebEstrategia2 {

	private static ChromeDriver driver;
	private Faker faker = new Faker();

	@Before
	public void login() {

		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Tools\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://seubarriga.wcaquino.me/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("email")).sendKeys("a@a");

		driver.findElement(By.id("senha")).sendKeys("a");

		driver.findElement(By.tagName("button")).click();

	}

	@Test
	public void Teste01_inserir() {

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Adicionar")).click();

		driver.findElement(By.id("nome")).sendKeys(faker.harryPotter().character());

		driver.findElement(By.tagName("button")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta adicionada com sucesso!");
	}

	@Test
	public void Teste02_consultar() {

		String conta = inserirConta();

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), '" + conta + "')]/..//a")).click();

		assertEquals(driver.findElement(By.id("nome")).getAttribute("value"), conta);
	}

	@Test
	public void Teste03_alterar() {

		String conta = inserirConta();

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), '" + conta + "')]/..//a")).click();

		driver.findElement(By.id("nome")).sendKeys("Alterado");

		driver.findElement(By.tagName("button")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta alterada com sucesso!");

	}

	@Test
	public void Teste04_excluir() {

		String conta = inserirConta();

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Listar")).click();

		driver.findElement(By.xpath("//td[contains(text(), '" + conta + "')]/..//a[2]")).click();

		assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText(),
				"Conta removida com sucesso!");
	}

	public String inserirConta() {

		String registro = faker.backToTheFuture().character();

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Adicionar")).click();

		driver.findElement(By.id("nome")).sendKeys(registro);

		driver.findElement(By.tagName("button")).click();

		return registro;
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
