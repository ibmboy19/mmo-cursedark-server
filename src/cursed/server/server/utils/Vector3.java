package cursed.server.server.utils;

public class Vector3 {
	private float x,y,z;
	private int scene_id;
	//Constructor
	public Vector3(){
		x = 0;
		y = 0;
		z = 0;
		scene_id =0;		
	}
	public Vector3(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}	
	public Vector3(float x,float y,float z,int scene_id){
		this.x = x;
		this.y = y;
		this.z = z;
		this.scene_id = scene_id;
	}	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	
	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}
	public int getScene_id() {
		return scene_id;
	}
	public String ToString(){
		return "("+String.valueOf(x)+","+String.valueOf(y)+","+String.valueOf(z)+")";
	}

}
