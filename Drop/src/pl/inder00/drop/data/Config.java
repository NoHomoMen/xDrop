package pl.inder00.drop.data;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;

import pl.inder00.drop.rhDrop;
import pl.inder00.drop.objects.Drop;
import pl.inder00.drop.utils.Util;

public class Config {
	
	private static Config inst;
	public FileConfiguration cfg = rhDrop.getInst().getConfig();
	
	public String mysql_dbname;
	public String mysql_user;
	public String mysql_pass;
	public int mysql_port;
	public String mysql_ip;
	
	public String datatype;
	public boolean debug;
	
	public int fortune1;
	public int fortune2;
	public int fortune3;
	
	public List<String> cancelore;
	
	public int xpToLevel;
	
	public String gui$name;
	public String gui$drop$name;
	public String gui$stats$name;
	public String gui$msg$name;
	public String gui$turbodrop$name;
	public String gui$cobble$name;
	public String gui$dropdisable$name;
	public String gui$top$name;
	
	public String gui$drop$item;
	public String gui$stats$item;
	public String gui$msg$item;
	public String gui$turbodrop$item;
	public String gui$cobble$item;
	public String gui$dropdisable$item;
	public String gui$top$item;
	
	public List<String> gui$drop$lore;
	public List<String> gui$stats$lore;
	public List<String> gui$turbodrop$lore;
	public List<String> gui$top$lore;
	
	public List<String> gui$status$on;
	public List<String> gui$status$off;
	
	public String gui$top$show$name;
	public List<String> gui$top$show$lore;
	
	public String gui$stats$show$xp$item;
	public String gui$stats$show$xp$name;
	
	public String gui$stats$show$level$item;
	public String gui$stats$show$level$name;
	
	public List<String> gui$drop$show$lore;
	
	public String active;
	public String disable;
	
	public int limitTurbodrop;
	
	public double activeTurbodropLoot;
	

	public void reload(){
		rhDrop.getInst().reloadConfig();
		this.cfg = rhDrop.getInst().getConfig();
		load();
	}
	
	public void load(){
		this.mysql_dbname = cfg.getString("mysql.dbname");
		this.mysql_user = cfg.getString("mysql.username");
		this.mysql_pass = cfg.getString("mysql.password");
		this.mysql_port = cfg.getInt("mysql.port");
		this.mysql_ip = cfg.getString("mysql.ip");
		
		this.fortune1 = cfg.getInt("config.fortune.level1");
		this.fortune2 = cfg.getInt("config.fortune.level2");
		this.fortune3 = cfg.getInt("config.fortune.level3");
		
		this.cancelore = cfg.getStringList("config.cancel-ores");
		
		this.xpToLevel = cfg.getInt("config.xp");
		
		this.gui$name = cfg.getString("config.gui.name");
		this.gui$drop$name = cfg.getString("config.gui.selections.drop.name");
		this.gui$stats$name = cfg.getString("config.gui.selections.stats.name");
		this.gui$msg$name = cfg.getString("config.gui.selections.msg.name");
		this.gui$turbodrop$name = cfg.getString("config.gui.selections.turbodrop.name");
		this.gui$cobble$name = cfg.getString("config.gui.selections.cobble.name");
		this.gui$dropdisable$name = cfg.getString("config.gui.selections.dropdisable.name");
		this.gui$top$name = cfg.getString("config.gui.selections.top.name");
		
		this.gui$drop$item = cfg.getString("config.gui.selections.drop.item");
		this.gui$stats$item = cfg.getString("config.gui.selections.stats.item");
		this.gui$msg$item = cfg.getString("config.gui.selections.msg.item");
		this.gui$turbodrop$item = cfg.getString("config.gui.selections.turbodrop.item");
		this.gui$cobble$item = cfg.getString("config.gui.selections.cobble.item");
		this.gui$dropdisable$item = cfg.getString("config.gui.selections.dropdisable.item");
		this.gui$top$item = cfg.getString("config.gui.selections.top.item");
		
		this.gui$drop$lore = cfg.getStringList("config.gui.selections.drop.lore");
		this.gui$stats$lore = cfg.getStringList("config.gui.selections.stats.lore");
		this.gui$turbodrop$lore = cfg.getStringList("config.gui.selections.turbodrop.lore");
		this.gui$top$lore = cfg.getStringList("config.gui.selections.top.lore");
		
		this.gui$status$on = cfg.getStringList("config.gui.selections.statusON");
		this.gui$status$off = cfg.getStringList("config.gui.selections.statusOFF");
		
		this.gui$top$show$name = cfg.getString("config.gui.selections.top.show.name");
		this.gui$top$show$lore = cfg.getStringList("config.gui.selections.top.show.lore");
		
		this.gui$stats$show$xp$item = cfg.getString("config.gui.selections.stats.show.xp.item");
		this.gui$stats$show$xp$name = cfg.getString("config.gui.selections.stats.show.xp.name");
		
		this.gui$stats$show$level$item = cfg.getString("config.gui.selections.stats.show.level.item");
		this.gui$stats$show$level$name = cfg.getString("config.gui.selections.stats.show.level.name");
		
		this.gui$drop$show$lore = cfg.getStringList("config.gui.selections.drop.show.lore");
		
		this.active = cfg.getString("config.gui.selections.active");
		this.disable = cfg.getString("config.gui.selections.disable");
		
		this.limitTurbodrop = cfg.getInt("config.limitTurbodrop");
		this.activeTurbodropLoot = cfg.getDouble("config.activeTurbodropLoot");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		this.datatype = cfg.getString("config.datatype");
		if(!this.datatype.equalsIgnoreCase("Flat")) {
			if(!this.datatype.equalsIgnoreCase("MySQL")) {
				if(!this.datatype.equalsIgnoreCase("SQLite")) {
					rhDrop.error("Bledny zapis danych: "+Config.getInst().datatype.toUpperCase(), false);
					this.datatype = "flat";
				}
			}
		}
		rhDrop.success("Zapis danych: "+this.datatype.toUpperCase());
		this.debug = cfg.getBoolean("config.debug");
		if(this.debug) rhDrop.success("Debug: "+(this.debug ? "TAK" : "NIE"));
		
		for(String s : cfg.getConfigurationSection("drops").getKeys(false)) {
			
			
			new Drop(
					Material.getMaterial(cfg.getString("drops."+s+".drop.what"))
					, cfg.getDouble("drops."+s+".chance")
					, cfg.getStringList("drops."+s+".tools")
					, cfg.getInt("drops."+s+".xp")
					, cfg.getBoolean("drops."+s+".fortune")
					, cfg.getInt("drops."+s+".height.minY")
					, cfg.getInt("drops."+s+".height.maxY")
					, cfg.getString("drops."+s+".message")
					, cfg.getInt("drops."+s+".exp")
					, cfg.getStringList("drops."+s+".biome")
					, Material.getMaterial(cfg.getString("drops."+s+".drop.from"))
					
			);
		}
	}
	//=========================================================================
	
	//=========================================================================
	//Instance
	public static Config getInst(){
		if(inst == null) return new Config();
		return inst;
	}
	public Config(){
		inst = this;
	}
	//=========================================================================

}
