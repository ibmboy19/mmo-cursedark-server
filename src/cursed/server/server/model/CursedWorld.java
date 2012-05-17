package cursed.server.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;

public class CursedWorld {
	private static CursedWorld _instance;
	private final Map<String, PcInstance> _allPlayers;
	private final Map<String, Object> _allObjects;
	private boolean _worldChatEnabled = true;
	
	private CursedWorld(){
		_allPlayers = Maps.newConcurrentMap();
		_allObjects = Maps.newConcurrentMap(); 
	}
	
	public static CursedWorld getInstance() {
		if (_instance == null) {
			_instance = new CursedWorld();
		}
		return _instance;
	}
	public void storeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
		_allObjects.put(object.getId(), object);
		if (object instanceof PcInstance) {
			_allPlayers.put(((PcInstance) object).getName(), (PcInstance) object);
		}
	}

	public void removeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}

		_allObjects.remove(object.getId());
		if (object instanceof PcInstance) {
			_allPlayers.remove(((PcInstance) object).getName());
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
	
	public void broadcastPacketToAll(String message) {
		for (PcInstance pc : getAllPlayers()) {
			
			pc.sendpackets(message);
		}
	}

}
