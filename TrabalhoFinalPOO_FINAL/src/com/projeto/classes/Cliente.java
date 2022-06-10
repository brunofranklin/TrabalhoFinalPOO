package com.projeto.classes;

public class Cliente {
	
	private String nome;
	private String cpf;
	private String endereco;
	private String nmrendereco;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String telefone;
	
	public Cliente(String nome, String cpf, String endereco, String nmrendereco, String complemento, 
			String bairro, String cidade, String cep, String telefone) {		
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.nmrendereco = nmrendereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.telefone = telefone;
	}
	
	public Cliente() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNmrendereco() {
		return nmrendereco;
	}

	public void setNmrendereco(String nmrendereco) {
		this.nmrendereco = nmrendereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
