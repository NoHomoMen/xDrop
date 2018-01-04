package pl.inder00.drop.listeners;

import java.util.Collection;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import pl.inder00.drop.data.Config;
import pl.inder00.drop.data.Message;
import pl.inder00.drop.inventory.GUI;
import pl.inder00.drop.objects.Drop;
import pl.inder00.drop.objects.User;
import pl.inder00.drop.utils.Util;

public class BlockBreakListener implements Listener {
	
	@EventHandler(priority=EventPriority.MONITOR)
	public void onBreak(BlockBreakEvent e) {
		if(e.isCancelled()) return;
		Player p = e.getPlayer();
		if(p.getGameMode() != GameMode.SURVIVAL) return;
		Block b = e.getBlock();
		
		Biome biome = e.getBlock().getBiome();
		ItemStack tool = p.getItemInHand();
		Config cfg = Config.getInst();
		User u = User.get(p.getUniqueId().toString());
		if(u == null) new User(p.getName(),p.getUniqueId().toString(),0,0);
		
		HashMap<Integer, ItemStack> rest = new HashMap<Integer, ItemStack>();
		
		for(String s : Config.getInst().cancelore) {
			if(Material.getMaterial(s) == b.getType()) {
				b.breakNaturally(new ItemStack(Material.AIR));
				Util.recalculateDurability(p, tool);
				return;
			}
		}
		
		boolean existFromThisMaterial = false;
		boolean checkRankUp = false;
		for(Drop d : Drop.drops) {
			if(d.getFrom().equals(b.getType())) {
				existFromThisMaterial = true;
				if(b.getY() >= d.getMinY() && b.getY() <= d.getMaxY()) {
					if(Util.chance((u.haveTurbodrop() ? d.getChance()+cfg.activeTurbodropLoot : d.getChance()))) {;
						if(Util.equalsBiome(biome, d)) {
							if(Util.equalsTool(tool.getType(), d)) {
								int amount = (d.isFortuneEnabled()) ? Util.getLootBonus(tool) : 1;
								int xp = d.getRewardXP()*amount;
								u.setXp(u.getXp()+xp);
								checkRankUp = true;
								p.giveExp(d.getExp());
								if(u.isEnabled(d)) {
									
									if(u.isMessages()) p.sendMessage(d.getMessage()
											.replace("&", "§")
											.replace("{AMOUNT}", ""+amount)
											.replace("{XP}", ""+xp)
									);
									
									ItemStack item = new ItemStack(d.getMaterial(), amount);
									rest.putAll(p.getInventory().addItem(item));
								}								
							}
						}
					}
				}	
			}
		}
		if(existFromThisMaterial) {
			if(u.isCobblestone()) {
				if(b.getDrops().iterator().next() != null) {
					rest.putAll(p.getInventory().addItem(b.getDrops().iterator().next()));	
				}
			}
			b.breakNaturally(new ItemStack(Material.AIR));
			Util.recalculateDurability(p, tool);
		}
		Message msg = Message.getInst();
		if(checkRankUp) {
			if(u.getXp() >= (u.getLevel()+1)*Config.getInst().xpToLevel) {
				u.rankUp();
				p.sendMessage(msg.getMessage("levelup").replace("{LEVEL}", (u.getLevel())+""));
				Util.firework(b.getLocation());
			}	
		}
		for (ItemStack l : rest.values())
		{
		    b.getWorld().dropItemNaturally(b.getLocation(), l);
		}
	}

}
