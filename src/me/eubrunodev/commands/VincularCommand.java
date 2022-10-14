package me.eubrunodev.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.eubrunodev.VincularMain;
import me.eubrunodev.managers.Menu;
import me.eubrunodev.managers.VincularManager;
import me.eubrunodev.managers.MenuManager;
import me.eubrunodev.utils.HeadsAPI;
import me.eubrunodev.utils.VincularMenuBuilder;

public class VincularCommand extends VincularManager implements CommandExecutor{
	public static ConsoleCommandSender sc = Bukkit.getConsoleSender();
	public static VincularMain plugin = (VincularMain)Bukkit.getPluginManager().getPlugin("hs_VincularDiscord");
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String vinculado;
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("Comando apenas para Jogadores");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("vincular")) {
			// comando de staff - LEMBRAR DE POR A VERIFICAÇÃO SE TEM A PERM
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("set") && args[1] != null) {
					String target = String.valueOf(args[1]);
					if(target.contains(target)) {
						if(staffGetCodigo(target) != null) {
							sender.sendMessage(prefix+"O jogador "+target+" já tem um código!");
							return true;
						} else {
							staffSetCodigo(target, gerar_key());
							sender.sendMessage(prefix+"§aVocê setou o código do jogador §d"+target);
							return true;
						}
					} 
				} else {
					sender.sendMessage(prefix+"§cUse: /vincular set/remove <jogador>");
					return true;
				}
				// comando staff - LEMBRAR DE POR A VERIFICAÇÃO SE TEM A PERM
				if(args[0].equalsIgnoreCase("remove")) {
					String jogador = String.valueOf(args[1]);
					staffRemoveCodigo(jogador, staffGetCodigo(jogador));
					sender.sendMessage(prefix+"§aVocê removeu o código do jogador §d"+jogador);
					return true;
				}
			}
			
			// comando para players
			if(args.length == 0) {
				Inventory inv = Bukkit.createInventory(null, 1*9, plugin.getConfig().getString("Menus.Menu.Nome").replace("&", "§"));
				ItemStack menu_gerar = new MenuManager().addItemMenu("Gerar");
				ItemStack menu_discord = new MenuManager().addItemMenu("Discord");
				
				int value = VincularManager.getStatus(p);//VincularManager.getStatus(p)
				String status = null;
				
				if(value == 1) {
					Menu info = VincularMain.getInstance().getVincularMenuManager().getMenu().get("Info");
					String name = info.getName().replace("&", "§").replace("{status}", "&aVinculado").replace("&", "§");
					String skullURL= info.getSkullURL();
					ItemStack head_info = new VincularMenuBuilder(HeadsAPI.getSkull(skullURL)).setName(name).build();
					inv.setItem(4, head_info);
				} else {
					inv.setItem(2, menu_gerar);
					inv.setItem(4, menu_discord);
					Menu info = VincularMain.getInstance().getVincularMenuManager().getMenu().get("Info");
					String name = info.getName().replace("&", "§").replace("{status}", "&cNão Vinculado").replace("&", "§");
					String skullURL= info.getSkullURL();
					ItemStack head_info = new VincularMenuBuilder(HeadsAPI.getSkull(skullURL)).setName(name).build();
					inv.setItem(6, head_info);
				}
				
				p.openInventory(inv);
			}
	  }
		return false;
	}
}
