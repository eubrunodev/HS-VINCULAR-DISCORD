package me.eubrunodev.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VincularManager {
	
	public static String prefix = "§9[ocean-VincularDiscord] ";
	
	public static Connection abrirConexao() {
		try {
			String password = "";
			String user = "root";
			String host = "localhost";
			String port = "3306";
			String database = "vincular_discord";
			String type = "jdbc:mysql://";
			String url = type+host+":"+port+"/"+database;
			
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(VincularManager.prefix+"§cError ao conectar com o Banco de Dados");
		}
		return null;
	}
	
	public static void criarTabela() {
		try {
			Connection con = abrirConexao();
			
			PreparedStatement st = con.prepareStatement("CREATE TABLE IF NOT EXISTS CODIGOS(jogador VARCHAR(24), codigo VARCHAR(10), status INT(1));");
			st.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	public static void setCodigo(Player player, String codigo) {
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("INSERT INTO codigos(id_discord, jogador, codigo, status) VALUES (?, ?, ?, ?);");
			st.setString(1, "NULL");
			st.setString(2, player.getName());
			st.setString(3, codigo);
			st.setInt(4, 0);
			st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static String getCodigo(Player player) {
		String value = null;
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("SELECT codigo FROM codigos WHERE jogador = ?;");
			st.setString(1, player.getName());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				value = rs.getString("codigo");
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	
	public static int getStatus(Player player) {
		int value = 0;
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("SELECT status FROM vinculados WHERE nome = ?");
			st.setString(1, player.getName());
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				value = rs.getInt("status");
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}

	public static void staffSetCodigo(String player, String codigo) {
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("UPDATE codigos SET codigo = ? WHERE jogador = ?;");
			st.setString(1, codigo);
			st.setString(2, player);
			
			st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String staffGetCodigo(String player) {
		String value = null;
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("SELECT codigo FROM codigos WHERE jogador = ?;");
			st.setString(1, player);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				value = rs.getString("CODE");
			}
			
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return value;
	}
	public static void staffRemoveCodigo(String player, String codigo) {
		try {
			Connection con = abrirConexao();
			PreparedStatement st = con.prepareStatement("UPDATE codigos SET codigo = ? WHERE jogador = ?;");
			st.setString(1, null);
			st.setString(2, player);
			
			st.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	// parte de gerara uum codigo
	public static String gerar_key() {	
		String letras = "1234567890";
		String jogador_key = "";
		int tamanho_key = 5;
		
		Random rand = new Random();
		
		char[] key = new char[tamanho_key];
		
		for(int i=0; i< tamanho_key; i++) {
			key[i] = letras.charAt(rand.nextInt(letras.length()));
		}
		
		for(int i=0; i< key.length; i++) {
			jogador_key += key[i];
		}
			
		return jogador_key;
	}
	
	// parte de add um item no menu
	public static ItemStack addItem(String nome, int material, int qnt, int data) {
		ItemStack item = new ItemStack(material, qnt, (short)data);
		ItemMeta item_meta = item.getItemMeta();
		item_meta.setDisplayName(nome.replace("&", "§"));
		item.setItemMeta(item_meta);
		
		return item;
	}
}
