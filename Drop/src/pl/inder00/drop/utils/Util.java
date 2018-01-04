package pl.inder00.drop.utils;

import java.util.Map;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.EntityEffect;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import pl.inder00.drop.data.Config;
import pl.inder00.drop.objects.Drop;

public class Util {
	
	public static boolean equalsTool(Material tool, Drop drop) {
		if(drop.getTools().size() == 0) return true;
		for(String s : drop.getTools()) {
			if(tool.equals(Material.getMaterial(s))) {
				return true;
			}
		}
		return false;
	}
	public static boolean equalsBiome(Biome biome, Drop drop) {
		if(drop.getBiome().size() == 0) return true;
		for(String s : drop.getBiome()) {
			if(biome.name().equalsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}
	public static int randomInt(int min, int max){
		Random rand = new Random();
		return (int) (rand.nextInt(max)) +1;
	}
	public static int getLootBonus(ItemStack tool) {
		Config cfg = Config.getInst();
		int returnAmount = 1;
		Map<Enchantment, Integer> enchant = tool.getEnchantments();
		if(enchant.containsKey(Enchantment.LOOT_BONUS_BLOCKS)){
			if(enchant.get(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
				returnAmount = randomInt(1, cfg.fortune1);
			} else if(enchant.get(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
				returnAmount = randomInt(1, cfg.fortune2);
			} else if(enchant.get(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
				returnAmount = randomInt(1, cfg.fortune3);
			}
		}
		return returnAmount;
		
	}
	public static boolean chance(double chance){
		Random rand = new Random();
	    double random1 = rand.nextDouble() * 100.0D;
	    if (random1 <= chance) {
	    	return true;
	    }
	    return false;
	}
    public static void recalculateDurability(Player player, ItemStack item) {
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > randomInt(0, 100)) {
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                } else {
                    item.setDurability((short) (d + 1));
                }
            }
        } else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
        } else {
            item.setDurability((short) (d + 1));
        }
    }
    
    public static String getTools(Drop d) {
		String tools = null;
		if(d.getTools().size() > 0) {
			for(String t : d.getTools()) {
				if(tools == null) {
					tools = Material.getMaterial(t).toString();
				} else {
					tools = tools+", "+Material.getMaterial(t).toString();
				}
			}	
		} else {
			tools = "Wszystkie";
		}
		return tools;
    }
    
    public static String getBiomes(Drop d) {
		String biomes = null;
		if(d.getBiome().size() > 0) {
			for(String b : d.getBiome()) {
				if(biomes == null) {
					biomes = b;
				} else {
					biomes = biomes+", "+b;
				}
			}	
		} else {
			biomes = "Wszystkie";
		}
		return biomes;
    }
    
    public static String convertTime(int input) {
    	int numberOfDays;
    	int numberOfHours;
    	int numberOfMinutes;
    	int numberOfSeconds;

    	numberOfDays = input / 86400;
    	numberOfHours = (input % 86400 ) / 3600;
    	numberOfMinutes = ((input % 86400 ) % 3600 ) / 60;
    	numberOfSeconds = ((input % 86400 ) % 3600 ) % 60;
    	
    	String output = "";
    	if(numberOfDays > 0) output = numberOfDays+"d. ";
    	if(numberOfHours > 0) output = output+numberOfHours+"h. ";
    	if(numberOfMinutes > 0) output = output+numberOfMinutes+"m. ";
    	output = output+numberOfSeconds+"s ";
    	
    	return output;
    }
    
    public static void firework(Location loc) {
    	FireworkEffect fe = FireworkEffect.builder().with(Type.BURST).withColor(Color.WHITE).withColor(Color.RED).build();
    	new InstantFirework(fe,loc);
	}
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}
