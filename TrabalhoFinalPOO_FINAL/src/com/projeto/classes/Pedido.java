package com.projeto.classes;

import java.text.SimpleDateFormat;
import java.util.*;

public class Pedido {

	private int idCliente;
	private double vlrtotal;
	private String data_emissao;
	
	public Pedido(int idCliente, double vlrtotal, String data_emissao) {		
		this.vlrtotal = vlrtotal;
		this.idCliente = idCliente;
		this.data_emissao = data_emissao;
	}
	
	public Pedido() {
		
	}
	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public double getVlrtotal() {
		return vlrtotal;
	}

	public void setVlrtotal(double vlrtotal) {
		this.vlrtotal = vlrtotal;
	}

	public String getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		this.data_emissao = df.format(data_emissao);
	}

	
}
