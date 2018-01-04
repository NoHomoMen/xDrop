package pl.inder00.drop.data;

import java.io.File;
import java.sql.SQLException;

import pl.inder00.drop.rhDrop;
import pl.inder00.drop.database.Flat;
import pl.inder00.drop.database.MySQL;
import pl.inder00.drop.database.SQLite;

public class DataManager {
	
	private static File mainDir = rhDrop.getInst().getDataFolder();
	private static File cfgFile = new File(mainDir, "config.yml");
	private static File users = new File(mainDir, "userdata");
	private static File messages = new File(rhDrop.getInst().getDataFolder(), "messages.yml");
	
	public static void check(){
		if(!mainDir.exists()) mainDir.mkdir();
		if(!users.exists()) users.mkdir();
		if(!messages.exists()) rhDrop.getInst().saveResource("messages.yml", true);
		if(!cfgFile.exists()) rhDrop.getInst().saveDefaultConfig();
	}
	public static File getUsersFolder(){
		return users;
	}
	public static File getMessagesFile(){
		return messages;
	}
	public static File getConfigFile(){
		return cfgFile;
	}
	
	
	public static void load() {
		Config cfg = Config.getInst();
		if(cfg.datatype.equalsIgnoreCase("mysql")) {
			try {
				MySQL.setConnection("jdbc:mysql://"+cfg.mysql_ip+":"+cfg.mysql_port+"/"+cfg.mysql_dbname, cfg.mysql_user, cfg.mysql_pass);
				MySQL.load();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else 	if(cfg.datatype.equalsIgnoreCase("sqlite")) {
			try {
				SQLite.setConnection("jdbc:sqlite:plugins/" + rhDrop.desc.getName() + "/database.db");
				SQLite.load();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else {
			Flat.load(); 
		}
	}
	public static void save() {
		Config cfg = Config.getInst();
		if(cfg.datatype.equalsIgnoreCase("mysql")) {
			try {
				MySQL.save();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else if(cfg.datatype.equalsIgnoreCase("sqlite")) {
			try {
				SQLite.save();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} else {
			Flat.save();
		}
	}

}
