package cursed.server.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;


public class CursedWorld {
	private static CursedWorld _instance;
	private final Map<String, PcInstance> _allPlayers;//玩家
	private final Map<String,CursedScene> _allScenes;//
	private boolean _worldChatEnabled = true;
	
	private CursedWorld(){		
		_allPlayers = Maps.newConcurrentMap();
		_allScenes = Maps.newConcurrentMap();
	}
	
	public static CursedWorld getInstance() {
		if (_instance == null) {
			_instance = new CursedWorld();
		}
		return _instance;
	}
	
	public void StorePlayer(PcInstance pc){
		if(pc == null){
			 throw new NullPointerException();
		}
		_allPlayers.put(pc.getCharID(),  pc);	
	}
	public void RemovePlayer(PcInstance pc) {
		if (pc == null) {
			throw new NullPointerException();
		}		
		_allPlayers.remove(pc);
	}
	
	private Collection<PcInstance> _allPlayerValues;
	
	public Collection<PcInstance> getAllPlayers() {
		Collection<PcInstance> vs = _allPlayerValues;
		return (vs != null) ? vs : (_allPlayerValues = Collections.unmodifiableCollection(_allPlayers.values()));
	}	

	
	/**
	 * 世界廣播
	 * @param id
	 * @param packet
	 */
	public void broadcastPacketToAllClient(String packet) {
		for (PcInstance pc : getAllPlayers()) {
			pc.sendpackets(packet);
		}
	}
	/**
	 * 廣播給某玩家
	 * @param id
	 * @param packet
	 */
	public void broadcastPacketToClient(String toID,String packet) {
		_allPlayers.get(toID).sendpackets(packet);
	}
	
	/**
	 * 廣播給某個地圖
	 * @param scene
	 * @param packet
	 */
	public void broadcastPacketToScene(int scene, String packet){
		for (PcInstance pc : getAllPlayers()) {
			if(pc.getScene_id() == scene)
				pc.sendpackets(packet);
		}
	}	
}
