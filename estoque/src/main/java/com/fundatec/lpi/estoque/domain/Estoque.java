package com.fundatec.lpi.estoque.domain;

import java.util.List;

public class Estoque {
	public float custoTotal(List<Item> itens) {
		float somaPrecos = 0;
		for (Item item : itens) {

			somaPrecos += item.getPreco();

		}

		return somaPrecos;// calcula custo total
	}
}
