package cursed.server.server.model;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;


public class CursedWorld {
	private static CursedWorld _instance;
	//private final Map<String, PcInstance> _allPlayers;
	//private final Map<String, Object> _allObjects;
	private final Map<String, ClientProcess> _allClient;
	private final Map<String, CursedScene> _allScene;
	private boolean _worldChatEnabled = true;
	
	private CursedWorld(){
		//_allPlayers = Maps.newConcurrentMap();
		//_allObjects = Maps.newConcurrentMap();
		_allScene = Maps.newConcurrentMap();
		_allScene.put("1", new CursedScene());
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
	public void removeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
		if (object instanceof PcInstance) {
			_allScene.get(((PcInstance) object).getScene_id()).removeObject(object);
		}
	}
	/*public void storeObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}
		_allObjects.put(object.getId(), object);
		if (object instanceof PcInstance) {
			_allPlayers.put(((PcInstance) object).getCharID(), (PcInstance) object);			
		}
	}*/
	/*public void StorePlayer(PcInstance pc){
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
*/
	/*private Collection<Object> _allValues;

	public Collection<Object> getObject() {
		Collection<Object> vs = _allValues;
		return (vs != null) ? vs : (_allValues = Collections.unmodifiableCollection(_allObjects.values()));
	}
	
	private Collection<PcInstance> _allPlayerValues;
	
	public Collection<PcInstance> getAllPlayers() {
		Collection<PcInstance> vs = _allPlayerValues;
		return (vs != null) ? vs : (_allPlayerValues = Collections.unmodifiableCollection(_allPlayers.values()));
	}*/
	
private Collection<ClientProcess> _allClientValues;
	
	public Collection<ClientProcess> getAllClient() {
		Collection<ClientProcess> vs = _allClientValues;
		return (vs != null) ? vs : (_allClientValues = Collections.unmodifiableCollection(_allClient.values()));
	}
private Collection<CursedScene> _allSceneValues;
	
	public Collection<CursedScene> getAllScenes() {
		Collection<CursedScene> vs = _allSceneValues;
		return (vs != null) ? vs : (_allSceneValues = Collections.unmodifiableCollection(_allScene.values()));
	}
	public void StorePlayer(PcInstance pc){
		_allScene.get(String.valueOf(pc.getScene_id())).StorePlayer(pc);
	}
	
	/**
	 * 世界廣播
	 * @param id
	 * @param message
	 */
	public void broadcastPacketToAllClient(String packet) {
		for (CursedScene cs : getAllScenes()) {
			cs.broadcastPacketToScene(packet);
		}
	}
	
	public void broadcastPacketToClient(String toID,String packet) {
		//_allPlayers.get(toID).sendpackets(packet);		

	}
	
	/**
	 * 廣播給某個地圖
	 * @param scene
	 * @param message
	 */
	public void broadcastPacketToScene(int scene, String msg){
		_allScene.get(scene).broadcastPacketToScene(msg);
	}
}
