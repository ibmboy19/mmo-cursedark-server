package cursed.server.server.model;

import cursed.server.server.utils.Vector3;

public class Portal extends Object{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int id;
	public int getID(){
		return id;
	}
	
	private final Vector3 target;
	public Vector3 getTarget(){
		return target;
	}
	

	public Portal(int id,Vector3 loc,Vector3 target){
		this.id = id;
		this._location = loc;
		this.target = target;
		System.out.println("initialize portal : "+"location : "+loc.ToString()+"at scene   "+loc.getScene_id()+"     "+target.ToString()+" at scene "+target.getScene_id());
	}
}
