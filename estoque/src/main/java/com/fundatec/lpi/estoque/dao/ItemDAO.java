package com.fundatec.lpi.estoque.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fundatec.lpi.estoque.domain.Item;

public class ItemDAO implements BaseDAO<Item> {

	public void save(Item item) throws IOException {
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
			String query = "INSERT INTO itens (nome, preco) values (\"" + item.getNome() + "\", " + item.getPreco()
					+ ")";
			statement.execute(query);

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public int testarId(int id) throws IOException {
		int idTeste = 0;
		try {
			String nomeTeste = null;
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			String query = "SELECT * FROM itens WHERE id = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, id);
			ResultSet resultSet = preparedStmt.executeQuery();

			// Itera no resultado, imprimindo os valores
			while (resultSet.next()) {
				idTeste = resultSet.getInt("id");
				nomeTeste = resultSet.getString("nome");
			}

			if (idTeste > 0) {
				System.out.format("id %s encontrado, pertence ao %s \n", idTeste, nomeTeste);
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

	public void update(Item item) throws IOException {
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// query de editar
			String query = "UPDATE itens SET nome = ? , preco = ? where id=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, item.getNome());
			preparedStmt.setFloat(2, item.getPreco());
			preparedStmt.setInt(3, item.getId());
			preparedStmt.execute();

			System.out.println("item " + item.getNome() + " editado com sucesso!");

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void delete(int Id) throws IOException {
		try {
			// Carrega driver do MySQL (cada banco tem o seu driver)
			String mysqlDriver = "com.mysql.cj.jdbc.Driver";
			Class.forName(mysqlDriver);

			// Cria uma conexão com o banco de dados, passando o nome
			// do banco, usuário e senha
			String connString = "jdbc:mysql://localhost/fundatec?user=root&password=";
			Connection conn = DriverManager.getConnection(connString);

			// Query para deletar
			String query = "DELETE FROM itens WHERE id = ?";

			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Id);
			preparedStmt.execute();

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<Item> listAll() throws IOException {
		List<Item> itens = new ArrayList<Item>();
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
			String query = "SELECT * FROM itens";

			// Roda a query e pega o retorno
			ResultSet resultSet = statement.executeQuery(query);

			// Itera no resultado, imprimindo os valores
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				float preco = resultSet.getFloat("preco");
				Item item = new Item();
				item.setId(id);
				item.setNome(nome);
				item.setPreco(preco);
				itens.add(item);

			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return itens;
	}
	
	
	

	public List<Item> PrecoTotal() throws IOException {
		List<Item> precos = new ArrayList<Item>();
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
			String query = "SELECT preco FROM itens";

			// Roda a query e pega o retorno
			ResultSet resultSet = statement.executeQuery(query);

			// Itera no resultado, imprimindo os valores
			while (resultSet.next()) {
				
				float preco = resultSet.getFloat("preco");
				Item item = new Item();
				item.setPreco(preco);
				precos.add(item);
				System.out.format("Preço: %s", item.getPreco());

			}

			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return precos;
	}


}
