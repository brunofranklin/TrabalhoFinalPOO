package com.projeto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import com.projeto.classes.Pedido;
import com.projeto.conexao.Conexao;
import com.projeto.functions.print;

public class PedidoDAO {
	
	Conexao conn;
	
	public PedidoDAO(Conexao conn) {
		this.conn = conn;
	}
	
	public int insertPedido(Pedido pedido) {
		conn.connect();		
		String sql = "INSERT INTO loja.pedido (data_emissao, vltotal, idcliente)";
		sql += "values(";
		sql += "'" + pedido.getData_emissao()+ "',";
		sql += "'" + pedido.getVlrtotal()+ "',";
		sql += "'" + pedido.getIdCliente()+ "'";
		sql += ")";
						
		try {			
			Statement stm = conn.getConn().createStatement();
			int execSql = stm.executeUpdate(sql);
			System.out.println("PEDIDO CADASTRADO COM SUCESSO!");
			return execSql;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NO CADASTRO!");
			return 0;	
		}
	}
	
	public int retornarIdPedido(int idcliente) {
		conn.connect();
		int idPedido = 0;
		
		String sql = "SELECT idpedido FROM loja.pedido WHERE idcliente = " +
		"'" + idcliente + "'";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				idPedido = rs.getInt("idpedido");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idPedido;
	}
	
	@SuppressWarnings("exports")
	public PreparedStatement deletePedido(int idPedido) {
		conn.connect();
		
		String sql = "DELETE FROM loja.pedido WHERE idpedido = ?";	
		
		try {
			PreparedStatement ps = conn.getConn().prepareStatement(sql);
			ps.setInt(1, idPedido);
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
	public ResultSet selectPedidoData(String dataEmissao) {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pe.idpedido, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.vltotal "		;	
			sql += "FROM loja.pedido pe ";
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";
			sql += "WHERE pe.data_emissao = " + "'" + dataEmissao + "' ";
			sql += "ORDER BY pe.idpedido";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {			
				System.out.println("ID PEDIDO: " + rs.getInt("idpedido"));
				System.out.println("Data Emissão: " + rs.getString("data_emissao"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
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
	public ResultSet selectPedidoCpf(String cpf) {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pe.idpedido, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.vltotal "		;	
			sql += "FROM loja.pedido pe ";
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";
			sql += "WHERE cl.cpf = " + "'" + cpf + "' ";
			sql += "ORDER BY pe.idpedido";
			
			
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {			
				System.out.println("ID PEDIDO: " + rs.getInt("idpedido"));
				System.out.println("Data Emissão: " + rs.getString("data_emissao"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
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
	public ResultSet selectTodosPedido() {
		conn.connect();
		
		String sql = "SELECT ";
			sql	+= "pe.idpedido, ";
			sql += "pe.data_emissao, ";
			sql += "cl.nome, ";
			sql += "cl.cpf, ";
			sql += "pe.vltotal "		;	
			sql += "FROM loja.pedido pe ";
			sql += "LEFT JOIN loja.cliente cl ON pe.idcliente = cl.idcliente ";
			sql += "ORDER BY pe.idpedido";
			
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			SimpleDateFormat spdf = new SimpleDateFormat("dd/MM/yyyy");
			while(rs.next()) {			
				System.out.println("ID PEDIDO: " + rs.getInt("idpedido"));
				System.out.println("Data Emissão: " + spdf.format(rs.getDate("data_emissao")));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
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
	public PreparedStatement updatePedidoData(String coluna, int idPedido, String data) throws SQLException {
		conn.connect();
		String sql = "UPDATE loja.pedido SET " + coluna + " = '" + data + "' WHERE idpedido = ? ";
		PreparedStatement ps = conn.getConn().prepareStatement(sql);
		try {
			ps.setInt(1, idPedido);
			ps.executeUpdate();
			System.out.println("ATUALIZADO COM SUCESSO!\n");
			return ps;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			 				
	}
	
	@SuppressWarnings("exports")
	public PreparedStatement updatePedidoIdCliente(String coluna, String idPedido, int idCliente) throws SQLException {
		conn.connect();
		String sql = "UPDATE loja.pedido SET " + coluna + " = ? WHERE idpedido = ? ";
		PreparedStatement ps = conn.getConn().prepareStatement(sql);
		try {
			ps.setInt(1, idCliente);
			ps.setString(2, idPedido);
			ps.executeUpdate();
			System.out.println("ATUALIZADO COM SUCESSO!\n");
			return ps;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			 				
	}
	
	
	
	
	
}
