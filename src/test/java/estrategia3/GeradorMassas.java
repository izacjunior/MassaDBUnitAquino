package estrategia3;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

public class GeradorMassas {

	public static final String Chave_Conta = "CONTA_SB";

	public void gerarContaSeuBarriga() throws ClassNotFoundException, SQLException {

		ChromeDriver driver = new ChromeDriver();

		driver.get("http://seubarriga.wcaquino.me/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("email")).sendKeys("a@a");

		driver.findElement(By.id("senha")).sendKeys("a");

		driver.findElement(By.tagName("button")).click();

		Faker faker = new Faker();

		String registro = faker.backToTheFuture().character() + faker.gameOfThrones().dragon();

		driver.findElement(By.linkText("Contas")).click();

		driver.findElement(By.linkText("Adicionar")).click();

		driver.findElement(By.id("nome")).sendKeys(registro);

		driver.findElement(By.tagName("button")).click();

		driver.quit();

		new MassaDaoImp().inserirMassa(Chave_Conta, registro);

	}

	public static void main(String[] args) throws Exception {
		GeradorMassas massa = new GeradorMassas();
		for (int i = 0; i < 5; i++) {
			massa.gerarConta();
		}

//		new MassaDaoImp().obterMassa(Chave_Conta);
//		System.out.println();
	}
	
	public void gerarConta() throws Exception {
		Faker faker = new Faker();
		ContaService service = new ContaService();
		UsuarioService userService = new UsuarioService();
		Usuario usuarioGlobal = new Usuario(faker.name().fullName(), faker.internet().emailAddress(),
				faker.internet().password());
				
		Usuario usuarioSalvo = userService.salvar(usuarioGlobal);
		Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
		service.salvar(conta);
		new MassaDaoImp().inserirMassa(Chave_Conta, conta.getNome());
	}

}
