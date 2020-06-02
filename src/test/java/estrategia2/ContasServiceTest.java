package estrategia2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javafaker.Faker;

import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

public class ContasServiceTest {

	static Faker faker = new Faker();
	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();
	private static Usuario usuarioGlobal;
	private Conta contaTests;

	@BeforeClass
	public static void setup() {

		usuarioGlobal = new Usuario(faker.name().fullName(), faker.internet().emailAddress(),
				faker.internet().password());

	}

	@Before
	public void inserirConta() throws Exception {

		Usuario usuarioSalvo = userService.salvar(usuarioGlobal);
		Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
		contaTests = service.salvar(conta);
	}

	@Test
	public void testeInserir() throws Exception {
		Usuario usuario = new Usuario(faker.name().fullName(), faker.internet().emailAddress(),
				faker.internet().password());
		Usuario usuarioSalvo = userService.salvar(usuario);
		Conta conta = new Conta(faker.superhero().name(), usuarioSalvo);
		Conta contaSalva = service.salvar(conta);
		assertNotNull(contaSalva.getId());
		userService.printAll();
		service.printAll();

	}

	@Test
	public void testeConsultar() throws Exception {

		Conta contaBusca = service.findById(contaTests.getId());
		assertEquals(contaTests.getNome(), contaBusca.getNome());

	}

	@Test
	public void testeExcluir() throws Exception {

		service.delete(contaTests);
		Conta contaBusca = service.findById(contaTests.getId());
		assertNull(contaBusca);

	}

	@Test
	public void testAlterar() throws Exception {

		String novoNome = faker.ancient().god() + faker.gameOfThrones().dragon();
		contaTests.setNome(novoNome);
		Conta contaAlterada = service.salvar(contaTests);
		assertEquals(novoNome, contaAlterada.getNome());
		service.printAll();
	}

}