package me.eubrunodev.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class VincularMenuBuilder {
	private ItemStack item;
	
	public VincularMenuBuilder(Material m) {
		this(m, 1);
	}

	public VincularMenuBuilder(ItemStack is) {
		this.item = is;
	}

	public VincularMenuBuilder(Material m, int quantia) {
		this.item = new ItemStack(m, quantia);

	}

	public VincularMenuBuilder(Material m, int quantia, byte durabilidade) {
		this.item = new ItemStack(m, quantia, durabilidade);
	}

	public VincularMenuBuilder(Material m, int quantia, int durabilidade) {
		this.item = new ItemStack(m, quantia, (short) durabilidade);
	}

	public VincularMenuBuilder setAmount(int amount) {
		this.item.setAmount(amount);
		ItemMeta im = this.item.getItemMeta();
		im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder setName(String name) {
		ItemMeta im = this.item.getItemMeta();
		im.setDisplayName(name);
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder setLore(String... lore) {
		ItemMeta im = this.item.getItemMeta();
		im.setLore(Arrays.asList(lore));
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder setLore(List<String> lore) {
		ItemMeta im = this.item.getItemMeta();
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder addLores(List<String> linha) {
		ItemMeta im = this.item.getItemMeta();
		List<String> lore = new ArrayList<>();
		if (im.hasLore())
			lore = new ArrayList<>(im.getLore());
		for (String s : linha)
			lore.add(s);
		im.setLore(lore);
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder lore(String... lore) {
		ItemMeta im = this.item.getItemMeta();
		List<String> out = (im.getLore() == null) ? new ArrayList<>() : im.getLore();
		byte b;
		int i;
		String[] arrayOfString;
		for (i = (arrayOfString = lore).length, b = 0; b < i;) {
			String string = arrayOfString[b];
			out.add(ChatColor.translateAlternateColorCodes('&', string));
			b = (byte) (b + 1);
		}
		im.setLore(out);
		this.item.setItemMeta(im);

		return this;
	}

	public VincularMenuBuilder builder(Consumer<ItemStack> consumer) {
		consumer.accept(item);

		return this;

	}

	public VincularMenuBuilder builderMeta(Consumer<ItemMeta> consumer) {
		ItemMeta meta = item.getItemMeta();
		consumer.accept(meta);
		item.setItemMeta(meta);

		return this;

	}

	public ItemStack toItemStack() {
		return this.item;
	}

	public ItemStack build() {
		return this.item;

	}
}

