package pl.inder00.drop.comamnds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.inder00.drop.rhDrop;
import pl.inder00.drop.data.Config;
import pl.inder00.drop.data.Message;
import pl.inder00.drop.enums.TurbodropType;
import pl.inder00.drop.objects.Turbodrop;
import pl.inder00.drop.objects.User;
import pl.inder00.drop.utils.Util;

public class TurbodropCommand implements CommandExecutor {
	
	public TurbodropCommand(rhDrop api) {
		api.getCommand("turbodrop").setExecutor(this);
	}
	/*
	 * (non-Javadoc)
	 * @see org.bukkit.command.CommandExecutor#onCommand(org.bukkit.command.CommandSender, org.bukkit.command.Command, java.lang.String, java.lang.String[])
	 */

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		Config cfg = Config.getInst();
		Message msg = Message.getInst();
		
		if(sender.hasPermission("rhDrop.admin.turbodrop")) {
			
			
			if(args.length <= 0) {
				sender.sendMessage(msg.getMessage("correctTurbodrop"));
				return false;
			} else {
				if(args[0].equalsIgnoreCase("all")) {
					if(args.length <= 1){
						sender.sendMessage(msg.getMessage("giveTime"));
						return false;
					}
					if(!args[1].matches("[0-9]+")){
						sender.sendMessage(msg.getMessage("correctTime"));
						return false;
					}
					if(!Util.isInteger(args[1])) {
						sender.sendMessage(msg.getMessage("correctTime"));
						return false;
					}
					int time = Integer.parseInt(args[1]);
					if(time > cfg.limitTurbodrop) {
						sender.sendMessage(msg.getMessage("limitTimeTurbodrop"));
						return false;
					}
					if(Turbodrop.turbodropAll != null && Turbodrop.turbodropAll.getTimeLeft() > 0) {
						Turbodrop tb = Turbodrop.turbodropAll;
						tb.setTime(time);
						tb.setTimeLeft(time);
						tb.setUuid(null);
					} else {
						new Turbodrop(null,time,TurbodropType.TURBODROP_ALL);
					}
					Bukkit.broadcastMessage(msg.getMessage("broadcastTurbodropall").replace("{ADMIN}", sender.getName()).replace("{TIME}", Util.convertTime(time)));
					return false;
				} else if(args[0].equalsIgnoreCase("give")) {
					if(args.length <= 1){
						sender.sendMessage(msg.getMessage("giveTime"));
						return false;
					}
					if(!args[1].matches("[0-9]+")){
						sender.sendMessage(msg.getMessage("correctTime"));
						return false;
					}
					if(args.length <= 2){
						sender.sendMessage(msg.getMessage("givePlayerNickname"));
						return false;
					}
					if(Bukkit.getServer().getPlayerExact(args[2].toString()) == null){
						sender.sendMessage(msg.getMessage("playerOffline"));
						return false;
					}
					if(!Util.isInteger(args[1])) {
						sender.sendMessage(msg.getMessage("correctTime"));
						return false;
					}
					int time = Integer.parseInt(args[1]);
					if(time > cfg.limitTurbodrop) {
						sender.sendMessage(msg.getMessage("limitTimeTurbodrop"));
						return false;
					}
					Player player = Bukkit.getPlayer(args[2].toString());
					User u = User.get(player.getUniqueId().toString());
					Turbodrop tb = u.getTurbodrop();
					if(tb != null && tb.getTimeLeft() > 0) {
						tb.setTime(time);
						tb.setTimeLeft(time);
						u.setTurbodrop(tb);
					} else {
						u.setTurbodrop(new Turbodrop(player.getUniqueId().toString(), time, TurbodropType.TURBODROP_PLAYER));
					}
					Bukkit.broadcastMessage(msg.getMessage("broadcastTurbodrop").replace("{PLAYER}", player.getName()).replace("{ADMIN}", sender.getName()).replace("{TIME}", Util.convertTime(time)));
					return false;
				} else {
					sender.sendMessage(msg.getMessage("availableArguments").replace("{ARGUMENTS}", "<all/give>"));
					return false;
				}
			}
			
			
			
			
		} else {
			sender.sendMessage(msg.getMessage("noPermission"));
			return false;
		}

	}

}
