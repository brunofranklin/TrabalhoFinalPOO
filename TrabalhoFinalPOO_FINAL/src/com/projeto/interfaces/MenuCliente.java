package com.projeto.interfaces;

import java.sql.SQLException;

import com.projeto.classes.Cliente;
import com.projeto.conexao.Conexao;
import com.projeto.dao.*;
import com.projeto.functions.pinput;
import com.projeto.functions.print;

public class MenuCliente extends ClienteDAO {

	Conexao conn;
	public MenuCliente(Conexao conn){
		super(conn);
		this.conn = conn;
	}
	
	public void menuInsertCliente() {
		
		Cliente cl = new Cliente();
		conn.connect();
		
		cl.setNome(pinput.readString("Informe o NOME do cliente: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setCpf(pinput.readString("Informe o CPF: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setTelefone(pinput.readString("Informe o TELEFONE: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setEndereco(pinput.readString("Informe o ENDEREÇO: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setNmrendereco(pinput.readString("Informe o NÚMERO DO ENDEREÇO: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setComplemento(pinput.readString("Informe o COMPLEMENTO: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setBairro(pinput.readString("Informe o BAIRRO: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setCidade(pinput.readString("Informe a CIDADE: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		cl.setCep(pinput.readString("Informe o CEP: ", true).toUpperCase());
		System.out.println("");
		print.smallDivision();
		
		if(existeCpf(cl.getCpf()) == true) {
			System.out.println("ERRO NO CADASTRO!");
		} else {		
			Cliente cliente = new Cliente(cl.getNome(), cl.getCpf(), cl.getEndereco(), cl.getNmrendereco(), 
					cl.getComplemento(), cl.getBairro(), cl.getCidade(), cl.getCep(), cl.getTelefone());		
			insertCliente(cliente);
		}
	}
	
	public void menuSelectTodosCliente() {
		selectTodosCliente();	
	}
	
	public void menuSelectClienteCpf() {
		String cpf = pinput.readString("Informe o CPF do cliente que deseja visualizar: ").toUpperCase();
		
		selectClienteCpf(cpf);
	}
	
	public void menuDeleteCliente() {
		String cpf = pinput.readString("Informe o CPF do cliente que gostaria de excluir: ").toUpperCase();
		
		deleteCliente(cpf);
	}
	
	public void menuSelectPedidoCpf(){
		String cpf = pinput.readString("Informe o CPF do cliente: ").toUpperCase();
		PedidoDAO pedDAO = new PedidoDAO(conn);
		pedDAO.selectPedidoCpf(cpf);
	}
	
	public void menuSelectRelatorioFinalCliente() {
		PedidoItemDAO pedItemDAO = new PedidoItemDAO(conn);
		String cpf = pinput.readString("Informe o CPF do cliente: ").toUpperCase();
		pedItemDAO.selectRelatorioFinalCpf(cpf);
	}
	
	public void menuUpdateCliente() throws SQLException {
		
		conn.connect();
		
		String cpf = pinput.readString("Informe o CPF do cliente que deseja alterar: ");			
		String resposta;
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o NOME do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("nome", cpf, resposta);
		}

		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o CPF do cliente: ").toUpperCase();		
		if(!resposta.isEmpty() && existeCpf(resposta) == false) {
			updateCliente("cpf", cpf, resposta);	
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o TELEFONE do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("telefone", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o ENDEREÇO do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("endereco", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o NÚMERO DO ENDEREÇO do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("nmrendereco", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o COMPLEMENTO do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("complemento", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o BAIRRO do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("bairro", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o CIDADE do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("cidade", cpf, resposta);
		}
		
		print.smallDivision();
		resposta = pinput.readString(""
				+ "Aperte {ENTER} sem informar nennhum valor para pular."
				+ "\nInforme o CEP do cliente: ").toUpperCase();		
		if(!resposta.isEmpty()) {
			updateCliente("cep", cpf, resposta);
		}				
	}
	
	public void menuPesquisaIdCLiente() {
		String nome = pinput.readString("Informe o CPF do cliente que deseja verificar o ID: ").toUpperCase();
		System.out.println("ID: " + retornarIdCliente(nome));		
	}
	
	public void menuCliente() throws SQLException {
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ CLIENTES ]"
					+ "\n{1} - Consultar cliente"
					+ "\n{2} - Consultar Nº do cliente"
					+ "\n{3} - Lista de clientes"
					+ "\n{4} - Pedidos do cliente"
					+ "\n{5} - Cadastrar novo cliente"
					+ "\n{6} - Editar cadastro"
					+ "\n{7} - Deletar cadastro"
					+ "\n{0} - Voltar");

			switch (option) {
				case 1:
					print.division();
					menuSelectClienteCpf();
				break;

				case 2:
					print.division();
					menuPesquisaIdCLiente();
				break;

				case 3:
					print.division();
					menuSelectTodosCliente();
				break;

				case 4:
					menuClientePedido();
				break;
				
				case 5:
					print.division();
					menuInsertCliente();
				break;

				case 6:
					print.division();
					menuUpdateCliente();
				break;

				case 7:
					print.division();
					menuDeleteCliente();
				break;
				
				case 0:
					stage = false;
				break;

				default:
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				break;
			}
			
			if (stage && option != 4) {  
				pinput.readString("\nAperte {ENTER} para continuar.");
			}
			
		} while(stage);
	}
	
	public void menuClientePedido() {
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ PEDIDOS DO CLIENTE ]"
					+ "\n{1} - Relatório rápido"
					+ "\n{2} - Relatório completo"
					+ "\n{0} - Voltar");

			switch (option) {
				case 1:
					print.division();
					menuSelectPedidoCpf();
				break;

				case 2:
					print.division();
					menuSelectRelatorioFinalCliente();
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

