package me.eubrunodev;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.eubrunodev.commands.StaffCommands;
import me.eubrunodev.commands.VincularCommand;
import me.eubrunodev.events.VincularEvents;
import me.eubrunodev.managers.Menu;
import me.eubrunodev.managers.VincularManager;
import me.eubrunodev.managers.MenuManager;

public class VincularMain extends JavaPlugin{
	
	
	private MenuManager menuManager;
	private static VincularMain instance;
	
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		instance = this;
		VincularManager.criarTabela();
		setup();
		loading();
		register();
		
	}
	
	
	public static VincularMain getInstance() {
		return instance;
	}
	
	public void register() {
		getCommand("vincular").setExecutor(new VincularCommand());
		getCommand("vinculador").setExecutor(new StaffCommands());
		Bukkit.getPluginManager().registerEvents(new VincularEvents(), this);
	}
	
	private void loading() {
		menuManager = new MenuManager();
	}
	
	public void setup() {
		ConfigurationSection section = getConfig().getConfigurationSection("Menus");
		for (String path : section.getKeys(false)) {
			ConfigurationSection key = getConfig().getConfigurationSection("Menus." + path);
			//String itemName = key.getString("ItemNome").replace("&", "§");
			String name = key.getString("Nome").replace("&", "§");
			String skullURL = key.getString("Skull-URL");
			List<String> lore = key.getStringList("Lore");
			lore = lore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());
			//int data = key.getInt("Item.Data");

			Menu newMenu = new Menu(name, skullURL, lore);

			MenuManager.addMenu(path, newMenu);
			
		}
	}
	
	
	public MenuManager getVincularMenuManager() {
		return menuManager;
	}
}




















