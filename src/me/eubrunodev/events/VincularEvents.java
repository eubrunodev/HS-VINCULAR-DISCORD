package me.eubrunodev.events;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.eubrunodev.VincularMain;
import me.eubrunodev.managers.VincularManager;

public class VincularEvents extends VincularManager implements Listener{
	public static VincularMain plugin = (VincularMain)Bukkit.getPluginManager().getPlugin("hs_VincularDiscord");
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();

	
	@EventHandler
	public void aoClicar(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(e.getInventory().getTitle().equals(plugin.getConfig().getString("Menus.Menu.Nome").replace("&", "§"))) {
			
			e.setCancelled(true);
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("Menus.Discord.Nome").replace("&", "§"))) {
				p.closeInventory();
				
				p.sendMessage("§fLink do Servidor: §d"+plugin.getConfig().getString("Menus.Discord.Link"));
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(plugin.getConfig().getString("Menus.Gerar.Nome").replace("&", "§"))) {
				p.closeInventory();
				
				if(getCodigo(p) != null) {
					p.sendMessage("§cVocê já possuí um código, vá até um canal chamado §avincular §c e Use: §f/vincular §d"+getCodigo(p));
				} else {
				
					setCodigo(p, gerar_key());
					p.sendMessage("§eCódigo gerado, seu código é: §d"+getCodigo(p));	
				}
			}
		}
	}
}
