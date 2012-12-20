package cursed.server.server.model;

import cursed.server.server.utils.Vector3;

public class NPCMonsterObject extends Object{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	public int getID(){
		return id;
	}
	
	private int monsterid;
	public int getMonsterID(){
		return monsterid;
	}
	
	
	public NPCMonsterObject(int id,int monsterid,Vector3 loc){
		this.id = id;
		this.monsterid = id;
		this._location = loc;
		//System.out.println("load monster id"+monsterid+loc.ToString()+"scene "+loc.getScene_id());
	}
}
