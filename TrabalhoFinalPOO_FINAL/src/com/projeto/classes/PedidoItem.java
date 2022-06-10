package com.projeto.classes;

public class PedidoItem {
	
	private int idPedido_item;
	private int idProduto;
	private int idPedido;
	private double vlvendaun;
	private int qtdproduto;
	private double vldesconto;
	private double percdesconto;
	
	
	public PedidoItem(int idProduto,double vlvendaun,
			int qtdproduto, double vldesconto,	double percdesconto, int idPedido) {
		
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.vlvendaun = vlvendaun;
		this.qtdproduto = qtdproduto;
		this.vldesconto = vldesconto;
		this.percdesconto = percdesconto;
	}
	
	public PedidoItem() {
		
	}
	
	
	public int getIdPedido_item() {
		return idPedido_item;
	}
	public void setIdPedido_item(int idPedido_item) {
		this.idPedido_item = idPedido_item;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getVlvendaun() {
		return vlvendaun;
	}
	public void setVlvendaun(double vlvendaun) {
		this.vlvendaun = vlvendaun;
	}
	public int getQtdproduto() {
		return qtdproduto;
	}
	public void setQtdproduto(int qtdproduto) {
		this.qtdproduto = qtdproduto;
	}
	public double getVldesconto() {
		return vldesconto;
	}
	public void setVldesconto(double vldesconto) {
		this.vldesconto = vldesconto;
	}
	public double getPercdesconto() {
		return percdesconto;
	}
	public void setPercdesconto(double percdesconto) {
		this.percdesconto = percdesconto;
	}
}
