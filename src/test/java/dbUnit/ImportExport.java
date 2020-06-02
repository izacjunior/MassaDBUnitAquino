package dbUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import br.ce.wcaquino.dao.utils.ConnectionFactory;

public class ImportExport {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, DatabaseUnitException, IOException {
		importaBanco();
		exportarBanco();
		
	}

	private static void importaBanco() throws DatabaseUnitException, SQLException, ClassNotFoundException,
			DataSetException, FileNotFoundException {
		DatabaseConnection dbCon = new DatabaseConnection(ConnectionFactory.getConnection());
		FlatXmlDataSetBuilder build = new FlatXmlDataSetBuilder();
		IDataSet dataset = build.build(new FileInputStream("massas" + File.separator + "saida.xml"));
		DatabaseOperation.CLEAN_INSERT.execute(dbCon, dataset);
	}
	
	private static void exportarBanco()
			throws ClassNotFoundException, DatabaseUnitException, SQLException, IOException {
		DatabaseConnection dbCon = new DatabaseConnection(ConnectionFactory.getConnection());
		IDataSet dataSet = dbCon.createDataSet();
		DatabaseSequenceFilter databaseSequenceFilter = new DatabaseSequenceFilter(dbCon);
		FilteredDataSet filteredDataSet = new FilteredDataSet(databaseSequenceFilter, dataSet);
		FileOutputStream fos = new FileOutputStream("massas" + File.separator + "saidaFiltrada.xml");
		//FlatXmlDataSet.write(dataSet, fos);
		FlatXmlDataSet.write(filteredDataSet, fos);
	}
	
}
