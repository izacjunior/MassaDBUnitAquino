package cursoMassa.massa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.UsuarioService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioServicoTest {

	private UsuarioService servico = new UsuarioService();
	private static Usuario usuarioGlobal;

	@Test
	public void Test1_inserir() throws Exception {
		Usuario usuario = new Usuario("Usuario1", "teste@teste.com", "password");
		usuarioGlobal = servico.salvar(usuario);

		assertNotNull(usuarioGlobal.getId());
	}

	@Test
	public void Test2_Consultar() throws Exception {

		Usuario usuario = servico.findById(usuarioGlobal.getId());
		assertEquals("Usuario1", usuario.getNome());
	}

	@Test
	public void Test3_Alterar() throws Exception {
		Usuario usuario = servico.findById(usuarioGlobal.getId());
		usuario.setNome("Usuario alterado");
		Usuario usuarioAlterado = servico.salvar(usuario);
		assertEquals("Usuario alterado", usuarioAlterado.getNome());
	}

	@Test
	public void Test4_Excluir() throws Exception {
		servico.delete(usuarioGlobal);
		Usuario usuarioRemovido = servico.findById(usuarioGlobal.getId());
		assertNull(usuarioRemovido);

	}

}
