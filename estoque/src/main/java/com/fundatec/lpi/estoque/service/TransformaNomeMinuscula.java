package com.fundatec.lpi.estoque.service;

public class TransformaNomeMinuscula implements TransformaNome {

	public String valida(String nome) {
		nome = nome.toLowerCase();
		return nome;
	}
}