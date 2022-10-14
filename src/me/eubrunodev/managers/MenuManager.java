package me.eubrunodev.managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import me.eubrunodev.VincularMain;
import me.eubrunodev.utils.HeadsAPI;
import me.eubrunodev.utils.VincularMenuBuilder;

public class MenuManager {
	private static Map<String, Menu> newMenu = new HashMap<>();
	
	public static void addMenu(String name, Menu menu) {
		newMenu.put(name, menu);

	}

	public Map<String, Menu> getMenu() {
		return newMenu;
	}
	
	public ItemStack addItemMenu(String menu) {
		Menu newMenu = VincularMain.getInstance().getVincularMenuManager().getMenu().get(menu);
		String name = newMenu.getName();
		String skullURL= newMenu.getSkullURL();
		List<String> lore = newMenu.getLore();
		
		ItemStack item = new VincularMenuBuilder(HeadsAPI.getSkull(skullURL)).setName(name).setLore(lore).build();
		
		return item;
	}
}