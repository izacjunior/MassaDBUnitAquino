package estrategia4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;
import estrategia3.GeradorMassas;
import estrategia3.MassaDaoImp;

public class ContasServiceTestEstrategia4 {

	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();

	@Before
	public void inserirConta() throws SQLException, ClassNotFoundException {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM transacoes");
		conn.createStatement().executeUpdate("DELETE FROM contas");
		conn.createStatement().executeUpdate("DELETE FROM usuario");
		conn.createStatement().executeUpdate(
				"INSERT INTO usuario(id, nome, email, senha) VALUES(1, 'usuario de controle, 'usuario@gmail.com', password)");
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
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				"INSERT INTO contas(id, nome, id_usuario) VALUES(1, 'usuario de teste', 1)");
		
		Conta contaBusca = service.findById(1L);
		assertEquals("usuario de teste", contaBusca.getNome());

	}

	@Test
	public void testeExcluir() throws Exception {
		
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				"INSERT INTO contas(id, nome, id_usuario) VALUES(1, 'usuario de teste', 1)");
		
		Conta contaTeste = service.findByName("usuario de teste");

		service.delete(contaTeste);
		Conta contaBusca = service.findById(contaTeste.getId());
		assertNull(contaBusca);

	}

	@Test
	public void testAlterar() throws Exception {
		
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				"INSERT INTO contas(id, nome, id_usuario) VALUES(1, 'usuario de teste', 1)");
		
		Conta contaTeste = service.findByName("usuario de teste");
		contaTeste.setNome("Conta Alterada");
		Conta contaAlterada = service.salvar(contaTeste);
		assertEquals("Conta Alterada", contaAlterada.getNome());
		service.printAll();
		
		
	}

}