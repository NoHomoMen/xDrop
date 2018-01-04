package pl.inder00.drop.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pl.inder00.drop.objects.User;

public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent e) {
		if(e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();
		User u = User.get(p.getUniqueId().toString());
		String format = e.getFormat();
		format.replace("{LVL}", new StringBuilder(u.getLevel()));
		format.replace("{XP}", new StringBuilder(u.getXp()));
		e.setFormat(format);
		return;
	}

}
