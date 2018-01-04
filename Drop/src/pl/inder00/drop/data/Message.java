package pl.inder00.drop.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class Message {
	
	private HashMap<String, String> single = new HashMap<>();
    private HashMap<String, List<String>> multiple = new HashMap<>();
    private static YamlConfiguration msg;
    private static Message inst;

	//Instance
	public static Message getInst(){
		if(inst == null) return new Message();
		return inst;
	}
	public Message(){
		inst = this;
	}

    public void load(){
    	msg = YamlConfiguration.loadConfiguration(DataManager.getMessagesFile());
    	for(String s : msg.getKeys(true)){
    		if(s.toLowerCase().contains("list")){
    			List<String> list = msg.getStringList(s);
    			for (int i = 0; i < list.size(); i++) {
                    list.set(i, list.get(i).replace("&", "§"));
                }
    			multiple.put(s, list);
    		} else {
    			String msgs = msg.getString(s);
    			single.put(s, msgs.replace("&", "§"));
    		}
    	}
    }
    public String getMessage(String key) {
        String s = single.get(key);
        if (s == null) {
            return null;
        }
        else {
            return s;
        }
    }

    public List<String> getList(String key) {
        List<String> list = multiple.get(key);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

}
