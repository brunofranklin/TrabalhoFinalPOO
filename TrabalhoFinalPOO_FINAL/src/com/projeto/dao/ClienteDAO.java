package com.projeto.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projeto.classes.Cliente;
import com.projeto.conexao.Conexao;
import com.projeto.functions.print;

public class ClienteDAO {
	
	Conexao conn;
	
	public ClienteDAO(Conexao conn) {
		this.conn = conn;
	}
	
	public int insertCliente(Cliente cliente) {
		conn.connect();		
		String sql = "INSERT INTO loja.cliente (nome, cpf, endereco, nmrendereco, complemento, bairro, "
				+ "cidade, cep, telefone)";
		sql += "values(";
		sql += "'" + cliente.getNome().toUpperCase() + "',";
		sql += "'" + cliente.getCpf() + "',";
		sql += "'" + cliente.getEndereco() + "',";
		sql += "'" + cliente.getNmrendereco()+ "'," ;
		sql += "'" + cliente.getComplemento()+ "'," ;
		sql += "'" + cliente.getBairro()+ "'," ;
		sql += "'" + cliente.getCidade()+ "'," ;
		sql += "'" + cliente.getCep()+ "'," ;
		sql += "'" + cliente.getTelefone()+ "'" ;
		sql += ")";
				
		
		try {			
			Statement stm = conn.getConn().createStatement();
			int execSql = stm.executeUpdate(sql);
			System.out.println("CLIENTE CADASTRADO COM SUCESSO!");
			return execSql;
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO NO CADASTRO!");
			return 0;	
		}
	}	

	@SuppressWarnings("exports")
	public ResultSet selectClienteCpf(String cpf) {
		conn.connect();
		
		String sql = "SELECT * FROM loja.cliente WHERE cpf = " + "'" + cpf + "' ORDER BY idcliente";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()){		
				System.out.println("ID: " + rs.getInt("idcliente"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
				System.out.println("Telefone: " + rs.getString("telefone"));
				System.out.println("Endereco: " + rs.getString("endereco"));		
				System.out.println("Nmr.Ende: " + rs.getString("nmrendereco"));	
				System.out.println("Complemento: " + rs.getString("complemento"));	
				System.out.println("Bairro: " + rs.getString("bairro"));	
				System.out.println("Cidade: " + rs.getString("cidade"));		
				System.out.println("CEP: " + rs.getString("cep"));	
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
	public ResultSet selectTodosCliente() {
		conn.connect();
		
		String sql = "SELECT * FROM loja.cliente ORDER BY idcliente";
				
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {		
				System.out.println("ID: " + rs.getInt("idcliente"));
				System.out.println("Nome: " + rs.getString("nome"));
				System.out.println("CPF: " + rs.getString("cpf"));
				System.out.println("Telefone: " + rs.getString("telefone"));
				System.out.println("Endereco: " + rs.getString("endereco"));		
				System.out.println("Nmr.Ende: " + rs.getString("nmrendereco"));	
				System.out.println("Complemento: " + rs.getString("complemento"));	
				System.out.println("Bairro: " + rs.getString("bairro"));	
				System.out.println("Cidade: " + rs.getString("cidade"));		
				System.out.println("CEP: " + rs.getString("cep"));
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
	public PreparedStatement deleteCliente(String cpf) {
		conn.connect();
		
		String sql = "DELETE FROM loja.cliente WHERE cpf = ?";	
		
		try {
			PreparedStatement ps = conn.getConn().prepareStatement(sql);
			ps.setString(1, cpf);
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
	public PreparedStatement updateCliente(String coluna, String cpf, String dado) throws SQLException {
		conn.connect();
		String sql = "UPDATE loja.cliente SET " + coluna + " = ? WHERE cpf = ? ";
		PreparedStatement ps = conn.getConn().prepareStatement(sql);
		try {
			ps.setString(1, dado);
			ps.setString(2, cpf);
			ps.executeUpdate();
			System.out.println("ATUALIZAÇÃO FEITA COM SUCESSO!\n");
			return ps;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			 				
	}

	public int retornarIdCliente(String cpf) {
		conn.connect();
		int idCliente = 0;
		
		String sql = "SELECT idcliente FROM loja.cliente WHERE cpf = " +
		"'" + cpf + "'";
		
		try {
			Statement stm = conn.getConn().createStatement();
			ResultSet rs = stm.executeQuery(sql);
			if (rs.next()) {
				idCliente = rs.getInt("idcliente");
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}		
		
		return idCliente;
	}
	
	public boolean existeCpf(String cpf) {
		boolean retorno;
		conn.getConn();
		String sql = "SELECT cpf FROM loja.cliente WHERE cpf = '" + cpf +"'";
				
		try {
			Statement stmmmmm = conn.getConn().createStatement();
			ResultSet rs = stmmmmm.executeQuery(sql);	
			
			if (rs.next()) {
					System.out.printf("O CPF " + cpf + " já foi cadastrado.\n");
					rs.close();
					retorno = true;
			} else
				retorno = false;
			
			rs.close();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
			return true;
		}
		
		return retorno;
	}
	
	
	
}

	
	


