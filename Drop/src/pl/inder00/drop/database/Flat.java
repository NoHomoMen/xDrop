package pl.inder00.drop.database;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import pl.inder00.drop.data.DataManager;
import pl.inder00.drop.objects.User;


public class Flat {
	
	public static void load(){
		for(File f : DataManager.getUsersFolder().listFiles()){
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			String name = yml.getString("name");
			String uuid = yml.getString("uuid");
			int xp = yml.getInt("xp");
			int level = yml.getInt("level");
			new User(name,uuid,xp,level);
		}
	}
	
	public static void save(){
		for(User u : User.players){
			File f = new File(DataManager.getUsersFolder(), u.getName() + ".yml");
			if(!f.exists()){
				try {
					f.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
			yml.set("name", u.getName());
			yml.set("uuid", u.getUuid());
			yml.set("xp", u.getXp());
			yml.set("level", u.getLevel());
			try {
				yml.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
