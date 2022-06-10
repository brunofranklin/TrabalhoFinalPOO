package com.projeto.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.projeto.classes.*;
import com.projeto.conexao.Conexao;
import com.projeto.dao.*;
import com.projeto.functions.pinput;
import com.projeto.functions.print;

public class MenuPedido extends PedidoDAO {

	Conexao conn;
	
	public MenuPedido(Conexao conn) {
		super(conn);
		this.conn = conn;
	}
	
	public void menuCadastrarPedido() throws ParseException {
			
		ClienteDAO clDAO = new ClienteDAO(conn);
		ProdutoDAO prodDAO = new ProdutoDAO(conn);
		
		PedidoItemDAO pedItemDAO = new PedidoItemDAO(conn);
		
		String data = pinput.readString("Informe a DATA do pedido: ", true);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dt = df.parse(data);				
		String cpf = pinput.readString("Informe o CPF do cliente: ", true).toUpperCase();
		String nomeProd = pinput.readString("Informe o NOME do produto: ", true).toUpperCase();
		int qtdProd = pinput.readInt("Informe a QUANTIDADE comprada: ");	
		double percDesconto = pinput.readDouble("Informe o PERCENTUAL DE DESCONTO: ");
		
		double vlrTotal = calculaValorTotal(calculaDesconto(percDesconto,
				prodDAO, nomeProd, qtdProd),prodDAO,qtdProd,nomeProd);
		
		Pedido ped = new Pedido(clDAO.retornarIdCliente(cpf),vlrTotal,df.format(dt));
		insertPedido(ped);
		
		PedidoItem pedItem = new PedidoItem(prodDAO.retornarIdProduto(nomeProd), 
				prodDAO.retornarValorProduto(nomeProd),
				qtdProd,calculaDesconto(percDesconto,prodDAO, nomeProd, qtdProd),
				percDesconto,
				retornarIdPedido(clDAO.retornarIdCliente(cpf)));
		
		pedItemDAO.insertPedidoItem(pedItem);
				
	}
	
	public void menuSelectPedidoData() throws ParseException {
		String data = pinput.readString("Informe a DATA em que foi feito o pedido: ");
		selectPedidoData(data);
	}
	
	public void menuSelectTodosPedido() {
		selectTodosPedido();
	}
		
	public void menuSelectRelatorioFinal() {
		PedidoItemDAO pedItemDAO = new PedidoItemDAO(conn);
		pedItemDAO.selectRelatorioFinal();	
	}
	
	public void menuUpdatePedido() throws SQLException, ParseException {
		int idPedido = pinput.readInt("Informe o ID do pedido que deseja alterar: ");			
		String data = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme a DATA: ").toUpperCase();	
				
		if(!data.isEmpty()) {
			updatePedidoData("data_emissao", idPedido, data);
		}	
	}
	
	public void menuDeletePedido() {
		
		PedidoItemDAO pedItemDAO = new PedidoItemDAO(conn);
		int idPedido = pinput.readInt("Informe o ID do pedido que deseja excluir: ");
		pedItemDAO.deletePedido(idPedido);
		deletePedido(idPedido);	
	}
	
	public double calculaValorTotal(double vlrDesconto, ProdutoDAO prodDAO, int qtdProd, String nomeProd) {
				
		double vlTotal = (prodDAO.retornarValorProduto(nomeProd)*qtdProd) - vlrDesconto;
		
		return vlTotal;
	}
	
	public double calculaDesconto(double percDesc, ProdutoDAO prodDAO,String nomeProd, int qtdProd) {
		
		double vlrDesconto = (percDesc/100)*(prodDAO.retornarValorProduto(nomeProd)*qtdProd);

		return vlrDesconto;
	}
	
	public void menuPedido() throws ParseException, SQLException {
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ PEDIDOS ]"
					+ "\n{1} - Consultar pedido"
					+ "\n{2} - Lista de pedidos"
					+ "\n{3} - Realizar pedido"
					+ "\n{4} - Editar pedido"
					+ "\n{5} - Deletar pedido"
					+ "\n{0} - Voltar");
			
			switch (option) {
				case 1:
					print.division();
					menuSelectPedidoData();
				break;
				
				case 2:
					menuRelatorioPedido();
				break;
				
				case 3:
					print.division();
					menuCadastrarPedido();
				break;

				case 4:
					print.division();
					menuUpdatePedido();
				break;
				
				case 5:
					print.division();
					menuDeletePedido();
				break;
				
				case 0:
					stage = false;
				break;

				default:
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				break;
			}
			
			if (stage && option != 2) {  
				pinput.readString("\nAperte {ENTER} para continuar.");
			}
			
		} while(stage);
	}
	
	public void menuRelatorioPedido() {
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ PEDIDOS ]"
					+ "\n{1} - Relatório rápido"
					+ "\n{2} - Relatório completo"
					+ "\n{0} - Voltar");

			switch (option) {
				case 1:
					print.division();
					menuSelectTodosPedido();
				break;

				case 2:
					print.division();
					menuSelectRelatorioFinal();
				break;
				
				case 0:
					stage = false;
				break;

				default:
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				break;
			}
			
			if (stage) {  
				pinput.readString("\nAperte {ENTER} para continuar.");
			}

		} while(stage);
	}
}

