package com.fundatec.lpi.estoque;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fundatec.lpi.estoque.dao.BrindeDAO;
import com.fundatec.lpi.estoque.dao.ItemDAO;
import com.fundatec.lpi.estoque.domain.Brinde;
import com.fundatec.lpi.estoque.domain.Estoque;
import com.fundatec.lpi.estoque.domain.Item;
import com.fundatec.lpi.estoque.service.TransformaNomeMaiuscula;
import com.fundatec.lpi.estoque.service.TransformaNomeMinuscula;

public class App {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		// domain
		Item item = new Item(null);
		Brinde brinde = new Brinde(null);
		Estoque estoque = new Estoque();
		// DAO
		ItemDAO itemDAO = new ItemDAO();
		BrindeDAO brindeDAO = new BrindeDAO();
		// service
		TransformaNomeMaiuscula transformaMai = new TransformaNomeMaiuscula();
		TransformaNomeMinuscula transformaMin = new TransformaNomeMinuscula();
		// arrays
		List<Item> itens = new ArrayList<Item>();
		List<Brinde> brindes = new ArrayList<Brinde>();

		int comando = 0;
		System.out.println("Estoque lp1");
		do {
			System.out.println("");
			System.out.println("Escolha uma opção:");
			System.out.println("------------------");
			System.out.println("ITEM");
			System.out.println("1 - Cadastro de item");
			System.out.println("2 - Editar item por id");
			System.out.println("3 - Deletar um item por id");
			System.out.println("4 - Listar todos itens e brindes");
			System.out.println("5 - Mostar custo total do estoque");
			System.out.println("BRINDE");
			System.out.println("6 - Cadastro de brinde");
			System.out.println("7 - Editar um brinde por id");
			System.out.println("8 - Deletar um brinde por id");
			System.out.println("9 - Listar todos os brindes com item");
			System.out.println("");
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
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 2: {
				System.out.println("");
				System.out.println("opção 2: Editar item por id\n");
				System.out.println("insira o id do item que deseja editar: ");
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
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 3: {
				System.out.println("");
				System.out.println("opção 3: Deletar item por id\n");
				System.out.println("insira o id do item que deseja deletar: ");
				int id = scanner.nextInt();
				id = itemDAO.testarId(id);

				if (id > 0) {
					brindes = brindeDAO.listAll();
					for (Brinde brinde2 : brindes) {
						if (id == brinde2.getItem_id()) {
							brindeDAO.delete(brinde2.getId());
						}
					}
					itemDAO.delete(id);
					System.out.println("item deletado com sucesso!");
				} else {
					System.out.println("não foi possível deletar pois id não foi encontrado\n \n");
				}
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 4: {
				System.out.println("");
				System.out.println("opção 4: Listar itens e brindes");

				itens = itemDAO.listAll();
				brindes = brindeDAO.listAll();

				for (Item item1 : itens) {
					int cont = 0;
					System.out.format("id: %s ", item1.getId());
					System.out.format("Nome: %s ", item1.getNome());
					System.out.format("Preço: R$ %s - Brinde: ", item1.getPreco());
					for (Brinde brinde1 : brindes) {
						if (brinde1.getItem_id() == item1.getId()) {
							System.out.format("Id: %s ", brinde1.getId());
							System.out.format("Nome: %s\n", brinde1.getNome());
							cont++;
							break;
						}
					}
					if (cont == 0) {
						System.out.println("Sem brinde");
					}
				}
				try {
					Thread.sleep(5000);// pausa de 5000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			case 5: {
				System.out.println("");
				System.out.println("opção 5: Mostrar custo total");
				float somaPrecos = 0;
				itens = itemDAO.listAll();
				somaPrecos = estoque.custoTotal(itens);
				System.out.format("Preço total: %s\n\n", somaPrecos);
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 6: {
				System.out.println("");
				System.out.println("opção 6: Cadastrar Brinde");
				System.out.println("insira o nome do brinde: ");
				String nome = scanner.next();
				String novoNome = transformaMin.valida(nome);
				nome = transformaMai.valida(novoNome);

				System.out.println("Insira o id do item cujo brinde fará parte: ");
				int item_id = scanner.nextInt();
				item_id = itemDAO.testarId(item_id);

				if (item_id > 0) {
					brindes = brindeDAO.listAll();
					itens = itemDAO.listAll();
					int cont = 0;
					for (Brinde brinde2 : brindes) {
						if (item_id == brinde2.getItem_id()) {
							cont++;
						}
					}
					if (cont == 0) {
						brinde.setItem_id(item_id);
						brinde.setNome(nome);
						brindeDAO.save(brinde);
						System.out.format("brinde %s cadastrado com sucesso!\n", brinde.getNome());
					} else {
						System.out.println("este item já possui um brinde, portanto não será criado");
					}
				} else {
					System.out.println("id do item não existe, brinde não criado.");
				}
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			case 7: {
				System.out.println("");
				System.out.println("opção 7: Editar brinde por id\n");
				System.out.println("insira o id do brinde que deseja editar: ");
				int id = scanner.nextInt();
				id = brindeDAO.testarId(id);
				if (id > 0) {
					System.out.println("Insira o novo nome do Brinde: ");
					String nome = scanner.next();
					String novoNome = transformaMin.valida(nome);
					nome = transformaMai.valida(novoNome);

					System.out.println("Insira o novo id do item do qual o brinde pertencerá: ");
					int item_id = scanner.nextInt();
					item_id = brindeDAO.testarItem_Id(item_id);
					if (item_id > 0) {

						brinde.setId(id);
						brinde.setNome(nome);
						brinde.setItem_id(item_id);

						brindeDAO.update(brinde); //bugando o editar id
					} else {
						System.out.println("Não foi encontrado um item com este id, brinde não editado");
					}

				} else {
					System.out.println("não foi possível editar pois id não foi encontrado\n \n");
				}
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 8: {
				System.out.println("");
				System.out.println("opção 8: Deletar brinde por id\n");
				System.out.println("insira o ID do brinde que deseja deletar: ");
				int id = scanner.nextInt();
				id = brindeDAO.testarId(id);
				if (id > 0) {
					brindeDAO.delete(id);
					System.out.println("brinde deletado com sucesso!");
				} else {
					System.out.println("não foi possível deletar pois id não foi encontrado\n \n");
				}
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			case 9: {
				System.out.println("");
				System.out.println("opção 9: Listar todos brindes com itens");

				brindes = brindeDAO.listAll();
				for (Brinde brinde1 : brindes) {
					System.out.format("id: %s ", brinde1.getId());
					System.out.format("Nome: %s ", brinde1.getNome());
					System.out.format("id do item: %s\n", brinde1.getItem_id());
				}
				try {
					Thread.sleep(5000);// pausa de 5000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
				try {
					Thread.sleep(3000);// pausa de 3000 milisegundos
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			}

		} while (comando != 0);

		scanner.close();
	}
}
