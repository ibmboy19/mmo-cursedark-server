package cursed.server.server.model;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;

public class CursedScene {
	private final Map<String, PcInstance> _allPlayers;
	private final Map<String, Object> _allObjects;
	
	public CursedScene(){
		_allPlayers = Maps.newConcurrentMap();
		_allObjects = Maps.newConcurrentMap();
		
	}
	
	public void storeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
		_allObjects.put(object.getId(), object);
		if (object instanceof PcInstance) {
			_allPlayers.put(((PcInstance) object).getCharID(), (PcInstance) object);			
		}
	}
	public void StorePlayer(PcInstance pc){
		_allPlayers.put(pc.getCharID(),  pc);		
	}

	public void removeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}

		_allObjects.remove(object.getId());
		if (object instanceof PcInstance) {
			_allPlayers.remove(((PcInstance) object).getCharID());
		}
	}

	public Object findObject(String oID) {
		return _allObjects.get(oID);
	}
	private Collection<Object> _allValues;

	public Collection<Object> getObject() {
		Collection<Object> vs = _allValues;
		return (vs != null) ? vs : (_allValues = Collections.unmodifiableCollection(_allObjects.values()));
	}
	
	private Collection<PcInstance> _allPlayerValues;
	
	public Collection<PcInstance> getAllPlayers() {
		Collection<PcInstance> vs = _allPlayerValues;
		return (vs != null) ? vs : (_allPlayerValues = Collections.unmodifiableCollection(_allPlayers.values()));
	}
	
	
	/**
	 * 廣播給某個地圖
	 * @param op
	 * @param scene
	 * @param message
	 */
	public void broadcastPacketToScene(String msg){
		for (PcInstance pc : getAllPlayers()) {
				pc.sendpackets(msg);
		}
	}
}
