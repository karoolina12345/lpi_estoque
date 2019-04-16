package com.fundatec.lpi.estoque.domain;

public class Brinde {
	private int id;
	private String nome;
	private int item_id;

	public Brinde(int id, String nome, int item_id) {
		this.id = id;
		this.nome = nome;
		this.item_id = item_id;
	}

	public Brinde(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

}
