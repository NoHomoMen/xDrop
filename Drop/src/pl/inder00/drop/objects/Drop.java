package pl.inder00.drop.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
public class Drop {

	public static List<Drop> drops = new ArrayList<Drop>();
	
	private Material material;
	private double chance;
	private List<String> tools;
	private int rewardXP;
	private boolean fortuneEnabled;
	private int minY;
	private int maxY;
	private String message;
	private int exp;
	private List<String> biome;
	private Material from;
	
	public Drop(Material material, double chance, List<String> tools, int rewardXP, boolean fortuneEnabled
			, int minY, int maxY, String message, int exp, List<String> biome, Material from) {
		this.material = material;
		this.chance = chance;
		this.tools = tools;
		this.rewardXP = rewardXP;
		this.fortuneEnabled = fortuneEnabled;
		this.minY = minY;
		this.maxY = maxY;
		this.message = message;
		this.exp = exp;
		this.biome = biome;
		this.from = from;
		drops.add(this);
	}
	public static Drop get(Material g) {
		for(Drop d : drops) {
			if(d.getMaterial() == g) {
				return d;
			}
		}
		return null;
	}
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public double getChance() {
		return chance;
	}
	public void setChance(double chance) {
		this.chance = chance;
	}
	public List<String> getTools() {
		return tools;
	}
	public void setTools(List<String> tools) {
		this.tools = tools;
	}
	public int getRewardXP() {
		return rewardXP;
	}
	public void setRewardXP(int rewardXP) {
		this.rewardXP = rewardXP;
	}
	public boolean isFortuneEnabled() {
		return fortuneEnabled;
	}
	public void setFortuneEnabled(boolean fortuneEnabled) {
		this.fortuneEnabled = fortuneEnabled;
	}
	public int getMinY() {
		return minY;
	}
	public void setMinY(int minY) {
		this.minY = minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public List<String> getBiome() {
		return biome;
	}
	public void setBiome(List<String> biome) {
		this.biome = biome;
	}
	public Material getFrom() {
		return from;
	}
	public void setFrom(Material from) {
		this.from = from;
	}
	public static boolean existFromMaterial(Material from) {
		for(Drop d : drops) {
			if(d.getFrom().equals(from)) {
				return true;
			}
		}
		return false;
	}
}
