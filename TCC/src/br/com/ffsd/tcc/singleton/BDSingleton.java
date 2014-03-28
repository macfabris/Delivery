package br.com.ffsd.tcc.singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDSingleton {
	private static BDSingleton instancia;
	private Connection conexao = null;

	public static synchronized BDSingleton getInstancia()
			throws ClassNotFoundException {
		if (instancia == null)
			instancia = new BDSingleton();
		return instancia;
	}

	private BDSingleton() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(
					"jdbc:mysql://localhost/tcc1", "root", "");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com o banco. Erro: "
					+ e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Erro na classe " + e.getMessage());
		}
	}

	public Connection getConexao() {
		return conexao;
	}
}

