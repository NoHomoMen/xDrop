package pl.inder00.drop.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {
	
	public static ItemStack createIM(Material material, String name, int amount){
		ItemStack is = new ItemStack(material);
		is.setAmount(amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack createIM(Material material, String name, int amount, short a){
		ItemStack is = new ItemStack(material, amount, a);
		is.setAmount(amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack createIM(Material material, String name, int amount, short a, List<String> lore){
		ItemStack is = new ItemStack(material, amount, a);
		is.setAmount(amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack createIM(Material material, String name, int amount, List<String> lore){
		ItemStack is = new ItemStack(material);
		is.setAmount(amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	public static ItemStack createIM(ItemStack item, String name, List<String> lore){
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}	
	public static ItemStack createIM(Material material, int amount, List<String> lore){
		ItemStack is = new ItemStack(material);
		is.setAmount(amount);
		ItemMeta im = is.getItemMeta();
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}	
	public static ItemStack glassPane(){
		ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
		is.setAmount(1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}

}
