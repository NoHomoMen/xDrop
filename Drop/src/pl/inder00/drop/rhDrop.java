package pl.inder00.drop;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import pl.inder00.drop.comamnds.DropCommand;
import pl.inder00.drop.comamnds.TurbodropCommand;
import pl.inder00.drop.data.Config;
import pl.inder00.drop.data.DataManager;
import pl.inder00.drop.data.Message;
import pl.inder00.drop.enums.TurbodropType;
import pl.inder00.drop.listeners.AsyncPlayerChatListener;
import pl.inder00.drop.listeners.BlockBreakListener;
import pl.inder00.drop.listeners.InventoryClickListener;
import pl.inder00.drop.listeners.PlayerJoinListener;
import pl.inder00.drop.objects.Turbodrop;
import pl.inder00.drop.objects.User;
import pl.inder00.drop.utils.Util;

public class rhDrop extends JavaPlugin {

	public static rhDrop inst;
	public static PluginDescriptionFile desc;
	
	public static rhDrop getInst(){
		return inst;
	}
	public static void error(String s, boolean disable) {
		Bukkit.getConsoleSender().sendMessage("§c["+desc.getName()+" v"+desc.getVersion()+"] "+s);
		if(disable) Bukkit.getPluginManager().disablePlugin(inst);
	}
	public static void success(String s) {
		Bukkit.getConsoleSender().sendMessage("§a["+desc.getName()+" v"+desc.getVersion()+"] "+s);
	}
	public void onEnable() {
		inst = this;
		desc = this.getDescription();
		DataManager.check();
		Config.getInst().load();
		Message.getInst().load();
		DataManager.load();
		
		
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
		
		new DropCommand(this);
		new TurbodropCommand(this);
		
		User.chceckPlayersExist();
		
		new BukkitRunnable(){
			
			@Override
			public void run(){
				for(int i=0; i < Turbodrop.turbodrops.size(); i++) {
					Turbodrop tb = Turbodrop.turbodrops.get(i);
					if(tb != null) {
						if(tb.getTimeLeft() <= 0) {
							tb.remove();
						} else {
							tb.setTimeLeft(tb.getTimeLeft()-1);
						}	
					}
				}
			}
		}.runTaskTimer(this, 20, 20);
		
	}
	
	public void onDisable() {
		DataManager.save();
	}
	
}
