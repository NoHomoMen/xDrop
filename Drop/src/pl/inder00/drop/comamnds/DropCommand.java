package pl.inder00.drop.comamnds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.drop.rhDrop;
import pl.inder00.drop.inventory.GUI;

public class DropCommand implements CommandExecutor {
	
	public DropCommand(rhDrop api) {
		api.getCommand("drop").setExecutor(this);
	}

	/*
	 * 
	 * »
	 * «
	 * 
	 * (non-Javadoc)
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("§4Komenda /drop jest dostepna tylko dla graczy!"); 
			return false;
		}
		Player p = (Player) sender;
		p.openInventory(GUI.getMainGUI(p));
		return false;
	}

}
