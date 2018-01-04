package pl.inder00.drop.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pl.inder00.drop.objects.User;

public class PlayerJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(User.get(e.getPlayer().getUniqueId().toString()) == null) {
			new User(e.getPlayer().getName(),e.getPlayer().getUniqueId().toString(),0,0);
		}
	}
	

}
