package com.projeto.dao;

import java.sql.*;
import java.text.SimpleDateFormat;

import com.projeto.classes.Produto;
import com.projeto.conexao.Conexao;
import com.projeto.functions.print;

public class ProdutoDAO{

	Conexao conn;
	
	public ProdutoDAO(Conexao conn) {
		this.conn = conn;
	}
	
	public int insertProduto(Produto produto) {
		conn.connect();		
		String sql = "INSERT INTO loja.produto (dtfabricacao, nomeproduto, custo, vlrunitario)";
		sql += "values(";
		sql += "'" + produto.getDtfabricacao() + "',";
		sql += "'" + produto.getNomeproduto().toUpperCase() + "',";
		sql += "'" + produto.getCusto() + "',";
		sql += "'" + produto.getVlrunitario() + "'" ;
		sql += ")";
						
		try {			
			Statement stm = conn.getConn().createStatement();
			int execSql = stm.executeUpdate(sql);
			System.out.println("PRODUTO CADASTRADO COM SUCESSO!");
			return execSql;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NO CADASTRO!");
			return 0;	
		}
	}	

	@SuppressWarnings("exports")
	public ResultSet selectProdutoNome(String Nome) {
		conn.connect();
		
		String sql = "SELECT * FROM loja.produto WHERE nomeproduto = " + "'" + Nome.toUpperCase() + "' ";
		sql += "ORDER BY idproduto";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			while(rs.next()) {			
				System.out.println("ID: " + rs.getInt("idproduto"));
				System.out.println("Nome prod.: " + rs.getString("nomeproduto"));
				System.out.println("Data Fabric: " + sdf.format(rs.getDate("dtfabricacao")));
				System.out.println("custo: R$ " + rs.getString("custo"));
				System.out.println("Valor unit.: R$ " + rs.getString("vlrunitario"));
				System.out.println("");
				print.smallDivision();
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	@SuppressWarnings("exports")
	public ResultSet selectProdutoId(int id) {
		conn.connect();
		
		String sql = "SELECT * FROM loja.produto WHERE idproduto = " + id;
		sql += " ORDER BY idproduto";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			while(rs.next()) {			
				System.out.println("ID: " + rs.getInt("idproduto"));
				System.out.println("Nome prod.: " + rs.getString("nomeproduto"));
				System.out.println("Data Fabric: " + sdf.format(rs.getDate("dtfabricacao")));
				System.out.println("custo: R$ " + rs.getString("custo"));
				System.out.println("Valor unit.: R$ " + rs.getString("vlrunitario"));	
				System.out.println("");
				print.smallDivision();
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	@SuppressWarnings("exports")
	public ResultSet selectTodosProdutos() {
		conn.connect();
		
		String sql = "SELECT * FROM loja.produto ORDER BY idproduto";
				
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
			while(rs.next()) {		
				System.out.println("ID: " + rs.getInt("idproduto"));
				System.out.println("Nome prod.: " + rs.getString("nomeproduto"));
				System.out.println("Data Fabric: " + sdf.format(rs.getDate("dtfabricacao")));
				System.out.println("Custo: R$ " + rs.getString("custo"));
				System.out.println("Valor unit.: R$ " + rs.getString("vlrunitario"));	
				System.out.println("");
				print.smallDivision();
			}
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
	
	@SuppressWarnings("exports")
	public PreparedStatement deleteProduto(int id) {
		conn.connect();
		
		String sql = "DELETE FROM loja.produto WHERE idproduto = ?";	
		
		try {
			PreparedStatement ps = conn.getConn().prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("EXCLUSÃO FEITA COM SUCESSO!");
			return ps;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NA EXCLUSÃO!");
			return null;
		}				
	}

	@SuppressWarnings("exports")
	public PreparedStatement updateProduto(String sql) {
		conn.connect();
		try {
			PreparedStatement ps = conn.getConn().prepareStatement(sql);
			return ps;		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}						
	}
	
	public int retornarIdProduto(String nomeProd) {
		conn.connect();
		int idProduto = 0;
		
		String sql = "SELECT idproduto FROM loja.produto WHERE nomeproduto = " +
		"'" + nomeProd + "'";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				idProduto = rs.getInt("idproduto");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idProduto;
	}
	
	public double retornarValorProduto(String nomeProd) {
		conn.connect();
		double valorProd = 0;
		
		String sql = "SELECT vlrunitario FROM loja.produto WHERE nomeproduto = " +
		"'" + nomeProd + "'";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				valorProd = rs.getDouble("vlrunitario");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return valorProd;
	}
		
}
