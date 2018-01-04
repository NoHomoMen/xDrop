package pl.inder00.drop.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pl.inder00.drop.enums.TurbodropType;

public class User {
	
	public static List<User> players = new ArrayList<User>();
	
	private String name;
	private String uuid;
	private int xp;
	private int level;
	private boolean messages;
	private boolean cobblestone;
	private boolean statusDrops;
	private Turbodrop turbodrop;
	private HashMap<Drop, Boolean> drops = new HashMap<Drop, Boolean>();
	
	public User(String name, String uuid, int xp, int level) {
		this.name = name;
		this.uuid = uuid;
		this.xp = xp;
		this.level = level;
		this.messages = false;
		this.cobblestone = true;
		this.statusDrops = true;
		for(Drop d : Drop.drops) {
			drops.put(d, true);
		}
		players.add(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void rankUp() {
		this.level = this.level+1;
	}

	public boolean isMessages() {
		return messages;
	}

	public void setMessages(boolean messages) {
		this.messages = messages;
	}

	public HashMap<Drop, Boolean> getDrops() {
		return drops;
	}

	public void setDrops(HashMap<Drop, Boolean> drops) {
		this.drops = drops;
	}
	public boolean isEnabled(Drop drop) {
		if(this.drops.get(drop)) return true;
		return false;	
	}
	public boolean replaceDropStatus(Drop drop) {
		if(this.drops.get(drop) == true) {
			this.drops.replace(drop, false);
			this.replaceStatusDrop();
			return false;
		} else {
			this.drops.replace(drop, true);
			this.replaceStatusDrop();
			return true;
		}
	}
	private void replaceStatusDrop() {
		boolean allEnabled = false;
		boolean allDisabled = false;
		for(Drop d : this.drops.keySet()) {
			if(this.drops.get(d) == false) {
				allDisabled = true;
			}
			if(this.drops.get(d) == true) {
				allEnabled = true;
			}
		}
		if(allDisabled) {
			this.setStatusDrops(false);
		}
		if(allEnabled) {
			this.setStatusDrops(true);
		}
	}
	public static User get(String uuid) {
		for(User u : players) {
			if(u.getUuid().equalsIgnoreCase(uuid)) {
				return u;
			}
		}
		return null;
	}

	public boolean isCobblestone() {
		return cobblestone;
	}

	public void setCobblestone(boolean cobblestone) {
		this.cobblestone = cobblestone;
	}

	public boolean isStatusDrops() {
		return statusDrops;
	}

	public void setStatusDrops(boolean statusDrops) {
		this.statusDrops = statusDrops;
	}
	public boolean chanceStatusAll() {
		if(!this.isStatusDrops()) {
			for(Drop d : drops.keySet()) {
				drops.replace(d, true);
			}
			this.setStatusDrops(true);
			return true;
		} else {
			for(Drop d : drops.keySet()) {
				drops.replace(d, false);
			}
			this.setStatusDrops(false);
			return false;
		}
	}

	public boolean haveTurbodrop() {
		if(this.turbodrop != null && this.turbodrop.getTimeLeft() > 0) {
			return true;
		}
		if(Turbodrop.turbodropAll != null && Turbodrop.turbodropAll.getTimeLeft() > 0) {
			return true;
		}
		return false;
	}
	public TurbodropType getTurbodropType() {
		if(this.turbodrop != null && this.turbodrop.getTimeLeft() > 0) {
			return TurbodropType.TURBODROP_PLAYER;
		}
		if(Turbodrop.turbodropAll != null && Turbodrop.turbodropAll.getTimeLeft() > 0) {
			return TurbodropType.TURBODROP_ALL;
		}
		return null;
	}

	public void setTurbodrop(Turbodrop turbodrop) {
		this.turbodrop = turbodrop;
	}
	public Turbodrop getTurbodrop() {
		return this.turbodrop;
	}
	public static void chceckPlayersExist() {
		for(Player p :  Bukkit.getOnlinePlayers()) {
			if(get(p.getUniqueId().toString()) == null) {
				new User(p.getName(),p.getUniqueId().toString(),0,0);
			}
		}
	}

}
