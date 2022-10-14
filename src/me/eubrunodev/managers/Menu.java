package me.eubrunodev.managers;
import java.util.List;
public class Menu {

	private String name;
	private String itemName;
	private String skullURL;
	private List<String> lore;
	
	
	public Menu(String name, String skullURL, List<String> lore) {
		this.name = name;
		this.skullURL = skullURL;
		this.lore = lore;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSkullURL() {
		return skullURL;
	}

	public void setSkullURL(String skullURL) {
		this.skullURL = skullURL;
	}

	public List<String> getLore() {
		return lore;
	}

	public void setLore(List<String> lore) {
		this.lore = lore;
	}
}
