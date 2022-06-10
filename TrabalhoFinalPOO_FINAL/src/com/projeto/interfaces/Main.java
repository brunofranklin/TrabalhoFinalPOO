package com.projeto.interfaces;

import com.projeto.classes.PedidoItem;
import com.projeto.classes.Produto;
import com.projeto.conexao.*;
import com.projeto.dao.ProdutoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;

import com.projeto.functions.pinput;
import com.projeto.functions.print;

@SuppressWarnings("unused")
public class Main {
	public static final Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws SQLException, ParseException{				
		
		//Conexao conn = new Conexao("193.123.100.137","3306","loja","serratec","trabalhopoo","mysql");
		Conexao conn = new Conexao("localhost","5432","TRABALHOPOO","postgres","Senha022!","postgresql");

		MenuProduto menuProd = new MenuProduto(conn);
		MenuCliente menuCl = new MenuCliente(conn);	
		MenuPedido menuPed = new MenuPedido(conn);
		
		conn.connect();
		boolean stage = true;
		do {
			int option = pinput.readInt(""
					+ "[ ÁREA INICIAL ]"
					+ "\n{1} - Clientes"
					+ "\n{2} - Produtos"
					+ "\n{3} - Pedidos"
					+ "\n{0} - Fechar");
			
			switch (option) {
				case 1:
					menuCl.menuCliente();
				break;
				
				case 2:
					menuProd.menuProduto();
				break;
				
				case 3:
					menuPed.menuPedido();
				break;

				case 0:
					stage = false;
				break;
				
				default:
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				break;
			}
			print.division();
		} while(stage);
		
		conn.disconect();
		System.out.println("PROGRAMA FINALIZADO!");
	}
}
