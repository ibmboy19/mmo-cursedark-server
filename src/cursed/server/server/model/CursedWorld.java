package cursed.server.server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import cursed.server.server.database.ObjectTable;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.collections.Maps;


public class CursedWorld {
        private static CursedWorld _instance;
        private final Map<String, PcInstance> _allPlayers;//玩家
        private final Map<Integer ,Portal> _allPortals;//傳送門
        private final Map<Integer ,NPCObject> _allNPCs;//NPC
        private final Map<Integer ,NPCMonsterObject> _allMonsters;//怪物
        private boolean _worldChatEnabled = true;
        
        
        private CursedWorld(){          
                _allPlayers = Maps.newConcurrentMap();
                _allPortals = Maps.newConcurrentMap();//載入所有傳送門
                _allNPCs = Maps.newConcurrentMap();
                _allMonsters = Maps.newConcurrentMap();      
        }
        /**
         * 
         * */
        public static CursedWorld getInstance() {
                if (_instance == null) {
                        _instance = new CursedWorld();
                        ObjectTable.LoadPortal();
                        ObjectTable.LoadMonster();                        
                        System.out.println("initial world");
                }
                return _instance;
        }
        /**
         * 玩家相關
         * */
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

        public PcInstance getPlayer(String id){
                return _allPlayers.get(id);
        }
        
        /**
         * 傳送門
         * */
        public void StorePortal(Portal pt){
            if(pt == null){
                     throw new NullPointerException();
            }
            if(_allPortals.containsKey(pt.getID())){
            	return;
            }
            _allPortals.put(pt.getID(),  pt);   
        }
        public void RemovePortal(Portal pt) {
        	if (pt == null) {
            	throw new NullPointerException();
        		}               
        	_allPortals.remove(pt);
        }
    
        private Collection<Portal> _allPortalValues;
    
        public Collection<Portal> getAllPortals() {
        	Collection<Portal> vs = _allPortalValues;
        	return (vs != null) ? vs : (_allPortalValues = Collections.unmodifiableCollection(_allPortals.values()));
        }

        public Portal getPortal(int id){
        	return _allPortals.get(id);
        }
        /**
         * NPC
         * */
        
        
        /**
         * 怪物
         * */
        
        public void StoreMonster(NPCMonsterObject mon){
            if(mon == null){
                     throw new NullPointerException();
            }
            if(_allMonsters.containsKey(mon.getID())){
            	return;
            }
            _allMonsters.put(mon.getID(),  mon);
        }
        public void RemoveMonster(NPCMonsterObject mon) {
        	if (mon == null) {
            	throw new NullPointerException();
        		}               
        	_allMonsters.remove(mon);
        }
    
        private Collection<NPCMonsterObject> _allMonsterValues;
    
        public Collection<NPCMonsterObject> getAllMonsters() {
        	Collection<NPCMonsterObject> vs = _allMonsterValues;
        	return (vs != null) ? vs : (_allMonsterValues = Collections.unmodifiableCollection(_allMonsters.values()));
        }

        public NPCMonsterObject getMonster(int id){
        	return _allMonsters.get(id);
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