package pl.inder00.drop.listeners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import pl.inder00.drop.data.Config;
import pl.inder00.drop.data.Message;
import pl.inder00.drop.inventory.GUI;
import pl.inder00.drop.objects.Drop;
import pl.inder00.drop.objects.User;
import pl.inder00.drop.utils.Util;

public class InventoryClickListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onClick(InventoryClickEvent e) {
		if(e.isCancelled()) return;
		if(e.getInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType().equals(Material.AIR)) return;
		
		Config cfg = Config.getInst();
		Message msg = Message.getInst();
		Player p = (Player) e.getWhoClicked();
		ItemStack select = e.getCurrentItem();
		ItemMeta itemmeta = select.getItemMeta();
		
		User u = User.get(p.getUniqueId().toString());
		
		List<String> statusON = new ArrayList<String>();
		for(String s : cfg.gui$status$on) {
			statusON.add(s.replace("&", "§"));
		}
		List<String> statusOFF = new ArrayList<String>();
		for(String s : cfg.gui$status$off) {
			statusOFF.add(s.replace("&", "§"));
		}
		
		
		
		
		
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(cfg.gui$name.replace("&", "§"))){
			e.setCancelled(true);
			if(select.getType() == Material.getMaterial(cfg.gui$drop$item) 
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$drop$name.replace("&", "§"))) {
				p.openInventory(GUI.getDropGUI(p));
				return;
			}
			if(select.getType() == Material.getMaterial(cfg.gui$stats$item)
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$stats$name.replace("&", "§"))) {
				p.openInventory(GUI.getStatsGUI(p));
				return;
			}
			if(select.getType() == Material.getMaterial(cfg.gui$msg$item)
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$msg$name.replace("&", "§"))) {
				if(u.isMessages()) {
					u.setMessages(false);
					itemmeta.setLore(statusOFF);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("disableMessages"));
					return;
				} else {
					u.setMessages(true);
					itemmeta.setLore(statusON);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("enableMessages"));
					return;
				}
			}
			if(select.getType() == Material.getMaterial(cfg.gui$cobble$item)
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$cobble$name.replace("&", "§"))) {
				if(u.isCobblestone()) {
					u.setCobblestone(false);
					itemmeta.setLore(statusOFF);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("disableCobblestone"));
					return;
				} else {
					u.setCobblestone(true);
					itemmeta.setLore(statusON);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("enableCobblestone"));
					return;
				}
			}
			if(select.getType() == Material.getMaterial(cfg.gui$dropdisable$item)
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$dropdisable$name.replace("&", "§"))) {
				if(!u.chanceStatusAll()) {
					itemmeta.setLore(statusOFF);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("disableDrops"));
					return;
				} else {
					itemmeta.setLore(statusON);
					select.setItemMeta(itemmeta);
					p.sendMessage(msg.getMessage("enableDrops"));
					return;
				}
			}
			if(select.getType() == Material.getMaterial(cfg.gui$top$item)
					&& itemmeta.getDisplayName().equalsIgnoreCase(cfg.gui$top$name.replace("&", "§"))) {
				p.openInventory(GUI.getTopGUI(p));
				return;
			}
		}
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(cfg.gui$stats$name.replace("&", "§"))){
			e.setCancelled(true);
			return;
		}
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(cfg.gui$top$name.replace("&", "§"))){
			e.setCancelled(true);
			return;
		}
		if(e.getInventory().getTitle() !=null && e.getInventory().getTitle().equals(cfg.gui$drop$name.replace("&", "§"))){
			e.setCancelled(true);
			Drop d = Drop.get(select.getType());
			if(d == null) {
				p.closeInventory();
				p.sendMessage("§4Niespodziewany b³¹d!");
				return;
			}
			u.replaceDropStatus(d);
			List<String> lore = new ArrayList<String>();
			for (String s : cfg.gui$drop$show$lore) {
				lore.add(s
						.replace("&", "§")
						.replace("{MINY}", ""+d.getMinY())
						.replace("{MAXY}", ""+d.getMaxY())
						.replace("{CHANCE}", ""+d.getChance()+"%")
						.replace("{FORTUNE}", (d.isFortuneEnabled() ? cfg.active.replace("&", "§") : cfg.disable.replace("&", "§")))
						.replace("{BIOMES}", Util.getBiomes(d))
						.replace("{TOOLS}", Util.getTools(d))
						.replace("{STATUS}", (u.isEnabled(d)) ?  cfg.active.replace("&", "§") : cfg.disable.replace("&", "§"))
				);
			}
			itemmeta.setLore(lore);
			select.setItemMeta(itemmeta);
			return;
		}
	}

}



