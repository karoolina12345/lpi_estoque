package com.fundatec.lpi.estoque.domain;



public class Item {

	private String nome;
	private float preco;
	private int id;
	
	
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Item(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public Item(int id) {
		this.id = id;
	}

	public Item(String nome) {
		this.nome = nome;
	}

}