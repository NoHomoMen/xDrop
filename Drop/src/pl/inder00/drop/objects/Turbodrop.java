package pl.inder00.drop.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import pl.inder00.drop.enums.TurbodropType;

public class Turbodrop {
	
	public static ArrayList<Turbodrop> turbodrops = new ArrayList<Turbodrop>();
	public static Turbodrop turbodropAll;
	
	private String uuid;
	private int time;
	private int timeLeft;
	private TurbodropType type;
	
	public Turbodrop(String uuid, int time, TurbodropType type) {
		this.uuid = uuid;
		this.time = time;
		this.timeLeft = time;
		this.type = type;
		turbodrops.add(this);
		if(this.getType() == TurbodropType.TURBODROP_ALL) {
			turbodropAll = this;
		}
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public TurbodropType getType() {
		return type;
	}
	public void setType(TurbodropType type) {
		this.type = type;
	}
	public void remove() {
		turbodrops.remove(this);
	}
	public static Turbodrop get(String uuid) {
		for(Turbodrop s : turbodrops) {
			if(s.getUuid().equalsIgnoreCase(uuid)) {
				return s;
			}
		}
		return null;
	}
	public static boolean hasTurbodrop(String uuid) {
		for(Turbodrop s : turbodrops) {
			if(s.getType() == TurbodropType.TURBODROP_ALL) return true;
			if(s.getUuid().equalsIgnoreCase(uuid)) {
				return true;
			}
		}
		return false;
	}

}
