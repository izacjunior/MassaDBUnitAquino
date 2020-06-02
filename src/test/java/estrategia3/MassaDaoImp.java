package estrategia3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ce.wcaquino.dao.utils.ConnectionFactory;

public class MassaDaoImp {

	public void inserirMassa(String tipo, String valor) throws ClassNotFoundException, SQLException {
		PreparedStatement sts = ConnectionFactory.getConnection()
				.prepareStatement("INSERT INT massa (tipo, valor) VALUES(?, ?)");

		sts.setString(1, tipo);
		sts.setString(2, valor);
		sts.executeUpdate();
		sts.close();

	}

	public String obterMassa(String tipo) throws SQLException, ClassNotFoundException {
		Long id;
		String valor;
		PreparedStatement sts = ConnectionFactory.getConnection()
				.prepareStatement("SELECT id, valor, FROM massas WHERE tipo = ? and usada = false order by id LIMIT 1");

		sts.setString(1, tipo);
		ResultSet rs = sts.executeQuery();
		if (rs.next())
			return null;
		id = rs.getLong("id");
		valor = rs.getString("valor");

		sts.close();
		sts = ConnectionFactory.getConnection().prepareStatement("UPDATE massa SET  usada = true where id = ?");
		sts.setLong(1, id);
		sts.executeUpdate();
		sts.close();
		return valor;
	}
}
