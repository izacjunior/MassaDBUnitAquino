package estrategia5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

public class ContasServiceTestEstrategia5 {

	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();

	@BeforeClass
	public static void inserirConta() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM transacoes");
		conn.createStatement().executeUpdate("DELETE FROM contas");
		conn.createStatement().executeUpdate("DELETE FROM usuario");
		conn.createStatement().executeUpdate(
				"INSERT INTO usuario(id, nome, email, senha) VALUES(1, 'usuario de controle, 'usuario@gmail.com', password)");
		conn.createStatement().executeUpdate(
				"INSERT INTO contas (id, nome, usuario_id) VALUES(1, 'conta de teste', 1)");
		conn.createStatement().executeUpdate(
				"INSERT INTO contas (id, nome, usuario_id) VALUES(2, 'conta de alteracao', 1)");
		conn.createStatement().executeUpdate(
				"INSERT INTO contas (id, nome, usuario_id) VALUES(1, 'conta de deletar', 1)");
	}

	@Test
	public void testeInserir() throws Exception {

		Usuario usuario = userService.findById(1L);
		Conta conta = new Conta("CONTA SALVA", usuario);
		Conta contaSalva = service.salvar(conta);
		assertNotNull(contaSalva.getId());
		userService.printAll();
		service.printAll();

	}

	@Test
	public void testeConsultar() throws Exception {
				
		Conta contaBusca = service.findById(1L);
		assertEquals("conta de teste", contaBusca.getNome());

	}

	@Test
	public void testeExcluir() throws Exception {
		
		Conta contaTeste = service.findByName("conta de deletar");

		service.delete(contaTeste);
		Conta contaBusca = service.findById(contaTeste.getId());
		assertNull(contaBusca);

	}

	@Test
	public void testAlterar() throws Exception {
		
		Conta contaTeste = service.findByName("usuario de alteracao");
		contaTeste.setNome("Conta Alterada");
		Conta contaAlterada = service.salvar(contaTeste);
		assertEquals("Conta Alterada", contaAlterada.getNome());
		service.printAll();
		
		
	}

}