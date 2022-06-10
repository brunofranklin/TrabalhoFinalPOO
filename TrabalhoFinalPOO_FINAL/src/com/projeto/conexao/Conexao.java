package com.projeto.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private String ip;
	private String url;
	private String user;
	private String pass;
	private Connection conn;
	private String drive;

	public Conexao(String ip, String porta, String banco, String user, String pass, String tipo) {
		if (tipo == "mysql") {
			this.url = "jdbc:mysql://" + ip + ":" + porta + "/" + banco;
			this.drive = "com.mysql.cj.jdbc.Driver";
		} else if (tipo == "postgresql") {
			this.url = "jdbc:postgresql://" + ip + ":" + porta + "/" + banco;
			this.drive = "org.postgresql.Driver";
		}
		
		this.ip = ip;
		this.user = user;
		this.pass = pass;	
	}
	
	public void connect() {
		try {			
			Class.forName(drive);
			this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
			//System.out.println("SERVIDOR CONETADO");
			
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("HOUVE UM ERRO AO CONECTAR O SERVIDOR");
		}		
	}
	
	public void disconect(){
		try {
			this.conn.close();
			//System.out.println("SERVIDOR DESCONETADO");
		}catch (SQLException sqlE) {
			sqlE.printStackTrace();
			//System.out.println("HOUVE UM ERRO AO DESCONECTAR O SERVIDOR");
		}
	}
	
	public String getIp() {
		return this.ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
		
	public String getUrl() {
		return this.url;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return this.pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@SuppressWarnings("exports")
	public Connection getConn() {
		return this.conn;
	}

	public void setConn(@SuppressWarnings("exports") Connection conn) {
		this.conn = conn;
	}	
	
}

