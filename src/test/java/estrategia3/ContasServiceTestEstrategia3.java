package estrategia3;

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

public class ContasServiceTestEstrategia3 {

	static Faker faker = new Faker();
	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();
	private static Usuario usuarioGlobal;

	@Test
	public void testeInserir() throws Exception {

		Usuario usuario = new Usuario(faker.name().fullName(), faker.internet().emailAddress(),
				faker.internet().password());
		usuario = userService.salvar(usuario);
		Conta conta = new Conta(faker.superhero().name(), usuario);
		Conta contaSalva = service.salvar(conta);
		assertNotNull(contaSalva.getId());
		userService.printAll();
		service.printAll();

	}

	@Test
	public void testeConsultar() throws Exception {
		String nomeConta = new MassaDaoImp().obterMassa(GeradorMassas.Chave_Conta);
		Conta contaTeste = service.findByName(nomeConta);

		Conta contaBusca = service.findById(contaTeste.getId());
		assertEquals(contaTeste.getNome(), contaBusca.getNome());

	}

	@Test
	public void testeExcluir() throws Exception {
		Conta contaTeste = service.findByName(new MassaDaoImp().obterMassa(GeradorMassas.Chave_Conta));

		service.delete(contaTeste);
		Conta contaBusca = service.findById(contaTeste.getId());
		assertNull(contaBusca);

	}

	@Test
	public void testAlterar() throws Exception {
		Conta contaTeste = service.findByName(new MassaDaoImp().obterMassa(GeradorMassas.Chave_Conta));
		String novoNome = faker.ancient().god() + " " + faker.gameOfThrones().dragon();
		contaTeste.setNome(novoNome);
		Conta contaAlterada = service.salvar(contaTeste);
		assertEquals(novoNome, contaAlterada.getNome());
		service.printAll();
	}

}