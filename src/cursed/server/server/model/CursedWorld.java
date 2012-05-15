package cursed.server.server.model;

import java.util.Map;

import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;

public class CursedWorld {
	private static CursedWorld _instance;
	private final Map<String, PcInstance> _allPlayers;
	private boolean _worldChatEnabled = true;
	
	private CursedWorld(){
		_allPlayers = Maps.newConcurrentMap();
	}
	
	public static CursedWorld getInstance() {
		if (_instance == null) {
			_instance = new CursedWorld();
		}
		return _instance;
	}

}
