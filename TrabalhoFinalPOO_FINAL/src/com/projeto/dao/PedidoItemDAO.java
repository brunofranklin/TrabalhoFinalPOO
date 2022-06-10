package com.projeto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.projeto.classes.PedidoItem;
import com.projeto.conexao.Conexao;
import com.projeto.functions.print;

public class PedidoItemDAO {

	private Conexao conn;	
	
	public PedidoItemDAO(Conexao conn) {
		this.conn = conn;
	}
	
	public int insertPedidoItem(PedidoItem pedItem) {
		conn.connect();		
		String sql = "INSERT INTO loja.pedido_item (vlvendaun, qtdproduto, vldesconto, "
				+ "percdesconto, idproduto, idpedido)";
		sql += "values(";
		sql += "'" + pedItem.getVlvendaun() + "',";
		sql += "'" + pedItem.getQtdproduto() + "',";
		sql += "'" + pedItem.getVldesconto() + "',";
		sql += "'" + pedItem.getPercdesconto() + "'," ;
		sql += "'" + pedItem.getIdProduto() + "'," ;
		sql += "'" + pedItem.getIdPedido() + "'" ;
		sql += ")";
						
		try {			
			Statement stm = conn.getConn().createStatement();
			int execSql = stm.executeUpdate(sql);
			System.out.println("PEDIDO ITEM CADASTRADO COM SUCESSO");
			return execSql;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NO CADASTRO");
			return 0;	
		}
	}	
	
	@SuppressWarnings("exports")
	public PreparedStatement deletePedido(int idPedido) {
		conn.connect();
		
		String sql = "DELETE FROM loja.pedido_item WHERE idpedido = ?";	
		
		try {
			PreparedStatement ps = conn.getConn().prepareStatement(sql);
			ps.setInt(1, idPedido);
			ps.execute();
			System.out.println("Exclusão feita com sucesso!");
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NA EXCLUSÃO");
			return null;
		}				
	}
	
	@SuppressWarnings("exports")
	public ResultSet selectRelatorioFinal() {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pi.idpedido_item, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.idpedido, ";	
			sql += "pr.nomeproduto, ";	
			sql += "pi.qtdproduto, ";	
			sql += "pi.vlvendaun, ";	
			sql += "pi.percdesconto, ";	
			sql += "pi.vldesconto, ";	
			sql += "pe.vltotal ";	
			sql += "FROM loja.pedido_item pi ";
			sql += "LEFT JOIN loja.produto pr ON pi.idproduto = pr.idproduto ";
			sql += "LEFT JOIN loja.pedido pe ON pi.idpedido = pe.idpedido ";				
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";			
			sql += "ORDER BY pi.idpedido_item";
			
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Programação EletroSerra ltda.");
			System.out.println("CNPJ: 36.781.597/0001-04");
			System.out.println("Email: vendas@eletroserra.com");
			System.out.println("Tel.: 0800-0151-551");
			System.out.println("");
			print.smallDivision();					
			while(rs.next()) {			
				System.out.println("ID DA NOTA: " + rs.getInt("idpedido_item"));
				System.out.println("Data Emissão: " + spdf.format(rs.getDate("data_emissao")));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
				System.out.println("ID PEDIDO: " + rs.getString("idpedido"));
				System.out.println("Nome Prod.: " + rs.getString("nomeproduto"));
				System.out.println("Qtd.Prod.: " + rs.getString("qtdproduto"));
				System.out.println("Valor Unit.: R$ " + rs.getString("vlvendaun"));
				System.out.println("Perc. Desc.: " + rs.getString("percdesconto") + "%");
				System.out.println("Valor Desc.: R$ " + rs.getString("vldesconto"));
				System.out.println("Valor Total: R$ " + rs.getString("vltotal"));		
				System.out.println("");
				print.smallDivision();
			}
			return rs;
		} catch (Exception e) {
			System.out.println("ERRO");
			e.printStackTrace();
			return null;
		}
	
	}
	
	@SuppressWarnings("exports")
	public ResultSet selectRelatorioFinalCpf(String cpf) {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pi.idpedido_item, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.idpedido, ";	
			sql += "pr.nomeproduto, ";	
			sql += "pi.qtdproduto, ";	
			sql += "pi.vlvendaun, ";	
			sql += "pi.percdesconto, ";	
			sql += "pi.vldesconto, ";	
			sql += "pe.vltotal ";	
			sql += "FROM loja.pedido_item pi ";
			sql += "LEFT JOIN loja.produto pr ON pi.idproduto = pr.idproduto ";
			sql += "LEFT JOIN loja.pedido pe ON pi.idpedido = pe.idpedido ";				
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";	
			sql += "WHERE cl.cpf = '" + cpf + "' ";	
			sql += "ORDER BY pe.idpedido";
			
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			if(rs.next()) {
			SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
			System.out.println("Programação EletroSerra ltda.");
			System.out.println("CNPJ: 36.781.597/0001-04");
			System.out.println("Email: vendas@eletroserra.com");
			System.out.println("Tel.: 0800-0151-551");
			System.out.println("");
			print.smallDivision();				
				do{			
					System.out.println("ID DA NOTA: " + rs.getInt("idpedido_item"));
					System.out.println("Data Emissão: " + spdf.format(rs.getDate("data_emissao")));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("CPF: " + rs.getString("cpf"));
					System.out.println("ID PEDIDO: " + rs.getString("idpedido"));
					System.out.println("Nome Prod.: " + rs.getString("nomeproduto"));
					System.out.println("Qtd.Prod.: " + rs.getString("qtdproduto"));
					System.out.println("Valor Unit.: R$ " + rs.getString("vlvendaun"));
					System.out.println("Perc. Desc.: " + rs.getString("percdesconto") + "%");
					System.out.println("Valor Desc.: R$ " + rs.getString("vldesconto"));
					System.out.println("Valor Total: R$ " + rs.getString("vltotal"));	
					System.out.println("");
					print.smallDivision();
					
				}while(rs.next());
				return rs;
			}
			else {
				System.out.println("O cliente não possuí nenhum pedido");
			}

		} catch (Exception e) {
			System.out.println("ERRO");
			e.printStackTrace();
			return null;
		}
		return null;
	
	}
	
	@SuppressWarnings("exports")
	public ResultSet selectRelatorioFinalProduto(String nomeProd) {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pi.idpedido_item, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.idpedido, ";	
			sql += "pr.nomeproduto, ";	
			sql += "pi.qtdproduto, ";	
			sql += "pi.vlvendaun, ";	
			sql += "pi.percdesconto, ";	
			sql += "pi.vldesconto, ";	
			sql += "pe.vltotal ";	
			sql += "FROM loja.pedido_item pi ";
			sql += "LEFT JOIN loja.produto pr ON pi.idproduto = pr.idproduto ";
			sql += "LEFT JOIN loja.pedido pe ON pi.idpedido = pe.idpedido ";				
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";	
			sql += "WHERE pr.nomeproduto = '" + nomeProd.toUpperCase() + "' ";	
			sql += "ORDER BY pe.idpedido";
			
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);					
			
			if(rs.next()) {
				SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
				System.out.println("Programação EletroSerra ltda.");
				System.out.println("CNPJ: 36.781.597/0001-04");
				System.out.println("Email: vendas@eletroserra.com");
				System.out.println("Tel.: 0800-0151-551");
				System.out.println("");
				print.smallDivision();
				do{			
					System.out.println("ID DA NOTA: " + rs.getInt("idpedido_item"));
					System.out.println("Data Emissão: " + spdf.format(rs.getDate("data_emissao")));
					System.out.println("Nome: " + rs.getString("nome"));
					System.out.println("CPF: " + rs.getString("cpf"));
					System.out.println("ID PEDIDO: " + rs.getString("idpedido"));
					System.out.println("Nome Prod.: " + rs.getString("nomeproduto"));
					System.out.println("Qtd.Prod.: " + rs.getString("qtdproduto"));
					System.out.println("Valor Unit.: R$ " + rs.getString("vlvendaun"));
					System.out.println("Perc. Desc.: " + rs.getString("percdesconto") + "%");
					System.out.println("Valor Desc.: R$ " + rs.getString("vldesconto"));
					System.out.println("Valor Total: R$ " + rs.getString("vltotal"));	
					System.out.println("");
					print.smallDivision();
				}while(rs.next()); 
			return rs;
			}
			else{
				System.out.println("O produto não existe dentro de nenhum pedido");
			}
		
		} catch (Exception e) {
			System.out.println("ERRO");
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
		
}
