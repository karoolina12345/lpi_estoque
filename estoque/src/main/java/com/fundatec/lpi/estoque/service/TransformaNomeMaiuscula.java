package com.fundatec.lpi.estoque.service;


public class TransformaNomeMaiuscula implements TransformaNome{

	public String valida(String nome) {
		nome = nome.substring(0,1).toUpperCase().concat(nome.substring(1));
		return nome;
	}

}
