package com.projeto.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto {
	
	private String dtfabricacao;
	private String nomeproduto;
	private double custo;
	private double vlrunitario;
	
	public Produto(String dtfabricacao, String nomeproduto, double custo, double vlrunitario) {
		this.dtfabricacao = dtfabricacao;
		this.nomeproduto = nomeproduto;
		this.custo = custo;
		this.vlrunitario = vlrunitario;
	}
	
	public Produto() {
		
	}
	
	public String getDtfabricacao() {
		return dtfabricacao;
	}

	public void setDtfabricacao(Date dtfabricacao) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		this.dtfabricacao = df.format(dtfabricacao);
	}

	public String getNomeproduto() {
		return nomeproduto;
	}

	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public double getVlrunitario() {
		return vlrunitario;
	}

	public void setVlrunitario(double vlrunitario) {
		this.vlrunitario = vlrunitario;
	}	

}
