package cursed.server.server.model;

import java.util.Map;

import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.Vector3;
import cursed.server.server.utils.collections.Maps;

public class CursedScene {
	private final Map<String, Object> _allObjects;//NPC或機關等所有遊戲物件
	
	private final Vector3 _spawnPosition;
	
	private CursedScene(Vector3 _spawnPosition){
		this._spawnPosition = _spawnPosition; 
		_allObjects = Maps.newConcurrentMap();
	}
	public void StoreObject(Object object) {
        if (object == null) {
                throw new NullPointerException();
        }
        _allObjects.put(object.getId(), object);        
	}
	public void RemoveObject(Object object) {
		if (object == null) {
			throw new NullPointerException();
		}		
		_allObjects.remove(object.getId());
	}
	
}
