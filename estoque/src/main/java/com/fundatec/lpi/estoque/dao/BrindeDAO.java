package com.fundatec.lpi.estoque.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fundatec.lpi.estoque.domain.Brinde;

public class BrindeDAO implements BaseDAO<Brinde> {

	public void save(Brinde brinde) throws IOException {
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// Statements permitem executar queries (SQL) no banco
			Statement statement = conn.createStatement();

			// Exemplo de INSERT usando prepared statements
			String query = "INSERT INTO brindes (nome,item_id) values (\"" + brinde.getNome() + "\", "
					+ brinde.getItem_id() + ")";
			statement.execute(query);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public int testarId(int id) throws IOException {
		int idTeste = 0;
		String nomeTeste = null;
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			String query = "SELECT * FROM brindes WHERE id = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet resultSet = preparedStmt.executeQuery();

			// Itera no resultado
			while (resultSet.next()) {
				idTeste = resultSet.getInt("id");
				nomeTeste = resultSet.getString("nome");

			}

			if (idTeste > 0) {
				System.out.format("id %s encontrado, pertence ao brinde %s \n", idTeste, nomeTeste);
			} else {
				System.out.println("id não encontrado");
				idTeste = 0;
			}
			conn.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return idTeste;
	}

	public int testarItem_Id(int item_id) throws IOException {
		int item_idTeste = 0;
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			String query = "SELECT * FROM brindes WHERE item_id = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, item_id);
			ResultSet resultSet = preparedStmt.executeQuery();

			// Itera no resultado
			while (resultSet.next()) {
				item_idTeste = resultSet.getInt("item_id");

			}

			if (item_idTeste > 0) {
				System.out.format("id %s encontrado!\n", item_idTeste);
			} else {
				System.out.println("id não encontrado");
				item_idTeste = 0;
			}
			conn.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return item_idTeste;
	}

	public void update(Brinde brinde) throws IOException {
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// query de editar
			String query = "UPDATE brindes SET nome = ? , item_id = ? where id=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, brinde.getNome());
			preparedStmt.setFloat(2, brinde.getItem_id());
			preparedStmt.setInt(3, brinde.getId());
			preparedStmt.execute();

			System.out.println("brinde " + brinde.getNome() + " editado com sucesso!");

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) throws IOException {
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// Query para deletar
			String query = "DELETE FROM brindes WHERE id = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, id);
			preparedStmt.execute();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<Brinde> listAll() throws IOException {
		List<Brinde> brindes = new ArrayList<Brinde>();
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// Statements permitem executar queries (SQL) no banco
			Statement statement = conn.createStatement();

			// Nossa query
			String query = "SELECT brindes.*, brindes.id FROM brindes INNER JOIN itens ON (brindes.item_id=itens.id);";

			// Roda a query e pega o retorno
			ResultSet resultSet = statement.executeQuery(query);

			// Itera no resultado
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				int item_id = resultSet.getInt("item_id");
				Brinde brinde = new Brinde(null);
				brinde.setId(id);
				brinde.setNome(nome);
				brinde.setItem_id(item_id);
				brindes.add(brinde);

			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return brindes;
	}

}
