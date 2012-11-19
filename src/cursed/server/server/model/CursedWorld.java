package cursed.server.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;

public class CursedWorld {
	private static CursedWorld _instance;
	private final Map<String, PcInstance> _allPlayers;
	private final Map<String, Object> _allObjects;
	private final Map<String, ClientProcess> _allClient;
	private boolean _worldChatEnabled = true;
	
	private CursedWorld(){
		_allPlayers = Maps.newConcurrentMap();
		_allObjects = Maps.newConcurrentMap();
		_allClient = Maps.newConcurrentMap();
	}
	
	public static CursedWorld getInstance() {
		if (_instance == null) {
			_instance = new CursedWorld();
		}
		return _instance;
	}
	public void addClient(ClientProcess cp){
		_allClient.put(cp.get_ip(), cp);
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
	
private Collection<ClientProcess> _allClientValues;
	
	public Collection<ClientProcess> getAllClient() {
		Collection<ClientProcess> vs = _allClientValues;
		return (vs != null) ? vs : (_allClientValues = Collections.unmodifiableCollection(_allClient.values()));
	}
	
	public void broadcastPacketToAll(String message) {
		for (PcInstance pc : getAllPlayers()) {
			
			pc.sendpackets(message);
		}
	}
	
	public void broadcastPacketToAllClient(String op, String id) {
		for (PcInstance pc : getAllPlayers()) {
			pc.sendpackets(op);
			pc.sendpackets(id);
		}
	}
	
	/**
	 * 世界廣播
	 * @param id
	 * @param message
	 */
	public void broadcastPacketToAllClient(String op, String id ,String message) {
		for (PcInstance pc : getAllPlayers()) {
			pc.sendpackets(op);
			pc.sendpackets(id);
			pc.sendpackets(message);
		}
	}
	
	public void broadcastPacketToAllClient(String op, String id ,String type, String index) {
		for (PcInstance pc : getAllPlayers()) {
			pc.sendpackets(op);
			pc.sendpackets(id);
			pc.sendpackets(type);
			pc.sendpackets(index);
		}
	}

	public void broadcastPacketToAllClient(String op, String id ,String type, String index, String pos) {
		for (PcInstance pc : getAllPlayers()) {
			pc.sendpackets(op);
			pc.sendpackets(id);
			pc.sendpackets(type);
			pc.sendpackets(index);
			pc.sendpackets(pos);
		}
	}
	public void broadcastPacketToClient(String op,String fromID,String toID,String message) {
		_allPlayers.get(toID).sendpackets(op);
		_allPlayers.get(toID).sendpackets(fromID);
		_allPlayers.get(toID).sendpackets(message);
	}
}
