package com.projeto.interfaces;

import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.projeto.classes.Produto;
import com.projeto.conexao.Conexao;
import com.projeto.dao.PedidoItemDAO;
import com.projeto.dao.ProdutoDAO;
import com.projeto.functions.pinput;
import com.projeto.functions.print;

public class MenuProduto extends ProdutoDAO {
	
	private Conexao conn;
	
	public MenuProduto(Conexao conn){
		super(conn);
		this.conn = conn;
	}
		
	public void menuInsertProduto() throws ParseException {		
		Produto produto = new Produto();
		
		conn.connect();
		
		String data = pinput.readString("Informe a data de fabricação do produto: ", true);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = df.parse(data);
		produto.setDtfabricacao(dt);		
		
		produto.setNomeproduto(pinput.readString("Informe o nome do produto: ", true).toUpperCase());
		
		produto.setCusto(pinput.readDouble("Informe o custo: "));
		
		produto.setVlrunitario(pinput.readDouble("Informe o valor unitário: "));

		Produto prod = new Produto(produto.getDtfabricacao(),produto.getNomeproduto(),
		produto.getCusto(),produto.getVlrunitario());			
		
		insertProduto(prod);	
		
	}
	
	public void menuSelectTodosProduto() {
		selectTodosProdutos();	
	}
	
	public void menuSelectProduto() {
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ Selecionar Produto ]"
					+ "\n{1} - localizar pelo ID"
					+ "\n{2} - localizar pelo Nome"
					+ "\n{0} - Voltar");

			switch (option) {
				case 1:
					print.division();
					int id = pinput.readInt("Informe o ID do produto: ");
					selectProdutoId(id);
				break;

				case 2:
					print.division();
					String NomeProd = pinput.readString("Informe o NOME do produto: ", true).toUpperCase();
					selectProdutoNome(NomeProd);
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
		
	public void menuDeleteProduto() {
		int id = pinput.readInt("Informe o ID do produto que gostaria de excluir: ");
		deleteProduto(id);
	}
	
	public void menuUpdateProduto() throws ParseException {
		print.division();
		int id = pinput.readInt("Informe o ID do produto que deseja alterar: ");
		
		boolean stage = true;
		do {
			print.division();
			int option = pinput.readInt(""
					+ "[ Editar Produto (Id: " + id +") ]"
					+ "\n{1} - Alterar nome"
					+ "\n{2} - Alterar data de fabric."
					+ "\n{3} - Alterar custo"
					+ "\n{4} - Alterar valor unitário"
					+ "\n{0} - Voltar");

			switch(option) {
				case 1 -> {
					String nomeProdUp = pinput.readString("Informe o novo NOME do produto: ");
				
					String updtSql = "UPDATE loja.produto SET nomeproduto = ? WHERE idproduto = ? ";
					PreparedStatement ps = updateProduto(updtSql);
					try {
						ps.setString(1,nomeProdUp.toUpperCase());
						ps.setInt(2, id);
						ps.executeUpdate();
						System.out.println("ATUALIZADO COM SUCESSO!");
						
					} catch (Exception e) {
						e.printStackTrace();
					}			 
				}
				 
				case 2 -> {
					String data = pinput.readString("Informe a nova DATA DE FABRICAÇÃO do produto: ");
					String updtSql = "UPDATE loja.produto SET dtfabricacao = '" + data + "' WHERE idproduto = ? ";
					PreparedStatement ps = updateProduto(updtSql);
					try {
						ps.setInt(1, id);
						ps.executeUpdate();
						System.out.println("ATUALIZADO COM SUCESSO!");
							
					} catch (Exception e) {
						e.printStackTrace();
					}			 
				}
				 
				case 3 -> {
					double custo = pinput.readDouble("Informe o novo CUSTO do produto: ");
					String updtSql = "UPDATE loja.produto SET custo = ? WHERE idproduto = ? ";
					PreparedStatement ps = updateProduto(updtSql);
					try {
						ps.setDouble(1,custo);
						ps.setInt(2, id);
						ps.executeUpdate();
						System.out.println("ATUALIZADO COM SUCESSO!");
							
					} catch (Exception e) {
						e.printStackTrace();
					}			 
				}
				
				case 4 -> {
					double vlrUnit = pinput.readDouble("Informe o novo VALOR UNITÁRIO do produto: ");
					String updtSql = "UPDATE loja.produto SET vlrunitario = ? WHERE idproduto = ? ";
					PreparedStatement ps = updateProduto(updtSql);
					try {
						ps.setDouble(1,vlrUnit);
						ps.setInt(2, id);
						ps.executeUpdate();
						System.out.println("ATUALIZADO COM SUCESSO!");
							
					} catch (Exception e) {
						e.printStackTrace();
					}			 
				}
				 
				case 0 -> {
					stage = false;
				}
				
				default -> {
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				}
			}
			
			if (stage) {  
				pinput.readString("\nAperte {ENTER} para continuar.");
			}
			
		} while(stage);
	}	

	public void menuPesquisaIdProduto() {
		ProdutoDAO clDAO = new ProdutoDAO(conn);
		String nome = pinput.readString("Informe o NOME do produto que deseja saber o ID: ").toUpperCase();
		System.out.println("ID: " + clDAO.retornarIdProduto(nome));		
	}	
	
	public void menuSelectRelatorioFinalProduto() {
		PedidoItemDAO pedItemDAO = new PedidoItemDAO(conn);
			
		String nomeProd = pinput.readString("Informe o NOME do produto para visualizar todas as ocorrências nos PEDIDOS: ").toUpperCase();
		pedItemDAO.selectRelatorioFinalProduto(nomeProd);
	}
	
	public void menuProduto() throws ParseException {
		boolean stage = true;
		do {
			print.division();
			
			int option = pinput.readInt(""
					+ "[ PRODUTOS ]"
					+ "\n{1} - Consultar produto"
					+ "\n{2} - Consultar Nº do produto"
					+ "\n{3} - Lista de produtos"
					+ "\n{4} - Pedidos do produto"
					+ "\n{5} - Cadastrar novo produto"
					+ "\n{6} - Editar produto"
					+ "\n{7} - Deletar produto"
					+ "\n{0} - Voltar");

			switch (option) {
				case 1:
					menuSelectProduto();
				break;
				
				case 2:
					print.division();
					menuPesquisaIdProduto();
				break;
				
				case 3:
					print.division();
					menuSelectTodosProduto();
				break;

				case 4:
					print.division();
					menuSelectRelatorioFinalProduto();
				break;
				
				case 5:
					print.division();
					menuInsertProduto();
				break;

				case 6:
					menuUpdateProduto();
				break;
				
				case 7:
					print.division();
					menuDeleteProduto();
				break;
				
				case 0:
					stage = false;
				break;

				default:
					System.out.println("DIGITE UMA OPÇÃO VÁLIDA.");
				break;
			}
			
			if (stage && option != 1 && option != 6) {  
				pinput.readString("\nAperte {ENTER} para continuar.");
			}
			
		} while(stage);
	}
}

