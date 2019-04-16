package com.fundatec.lpi.estoque;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fundatec.lpi.estoque.dao.ItemDAO;
import com.fundatec.lpi.estoque.domain.Item;
import com.fundatec.lpi.estoque.service.TransformaNomeMaiuscula;
import com.fundatec.lpi.estoque.service.TransformaNomeMinuscula;

public class App {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		TransformaNomeMaiuscula transformaMai = new TransformaNomeMaiuscula();
		TransformaNomeMinuscula transformaMin = new TransformaNomeMinuscula();
		Item item = new Item(null);
		ItemDAO itemDAO = new ItemDAO();
		List<Item> itens = new ArrayList<Item>();

		int comando = 0;

		do {
			System.out.println("Escolha uma opção:");
			System.out.println("------------------");
			System.out.println("1 - Cadastro de item");
			System.out.println("2 - Editar item");
			System.out.println("3 - Deletar um item");
			System.out.println("4 - Listar todos itens");
			System.out.println("5 - Mostar custo total do estoque");
			System.out.println("0 - Sair");
			System.out.println("");
			System.out.print("Qual opção? ");

			comando = scanner.nextInt();

			switch (comando) {
			case 1: {
				System.out.println("");
				System.out.println("opção 1: Cadastrar item");
				System.out.println("insira o nome do item: ");
				String nome = scanner.next();
				String novoNome = transformaMin.valida(nome);
				nome = transformaMai.valida(novoNome);

				System.out.println("insira o valor do item");
				float preco = scanner.nextFloat();

				item.setNome(nome);
				item.setPreco(preco);
				itemDAO.save(item);
				System.out.format("%s criado com sucesso!\n\n", nome);
				break;
			}

			case 2: {
				System.out.println("");
				System.out.println("opção 2: Editar item por ID\n");
				System.out.println("insira o ID do item que deseja editar: ");
				int id = scanner.nextInt();
				id = itemDAO.testarId(id);
				if (id > 0) {
					System.out.println("Insira o novo nome do item: ");
					String nome = scanner.next();
					String novoNome = transformaMin.valida(nome);
					nome = transformaMai.valida(novoNome);

					System.out.println("Insira o novo preco do item ");
					float preco = scanner.nextFloat();
					item.setId(id);
					item.setNome(nome);
					item.setPreco(preco);
					itemDAO.update(item);
				} else {
					System.out.println("não foi possível editar pois id não foi encontrado\n \n");
				}
				break;
			}

			case 3: {
				System.out.println("");
				System.out.println("opção 3: Deletar item por ID\n");
				System.out.println("insira o ID do item que deseja deletar: ");
				int id = scanner.nextInt();
				id = itemDAO.testarId(id);
				if (id > 0) {
					itemDAO.delete(id);
					System.out.println("item deletado com sucesso!");
				} else {
					System.out.println("não foi possível deletar pois id não foi encontrado\n \n");
				}
				break;
			}

			case 4: {
				System.out.println("");
				System.out.println("opção 4: Listar itens e brindes");
				
				itens = itemDAO.listAll();
				System.out.println(itens);
				break;
			}
			case 5: {
				System.out.println("");
				System.out.println("opção 5: Mostrar custo total");
				break;
			}
			case 0: {
				System.out.println("");
				System.out.println("Saindo...");
				break;
			}
			default: {
				System.out.println("");
				System.out.println("Comando invalido ");
				break;
			}
			}

		} while (comando != 0);

		scanner.close();
	}
}
