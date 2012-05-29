package cursed.server.server.utils;

public class Vector3 {
	public float x,y,z;
	public int scene_id;
	//Constructor
	public Vector3(){
		x = 0;
		y = 0;
		z = 0;
		scene_id =0;
	}
	public Vector3(float x,float y,float z, int scene_id){
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
	
	public int getScene_id() {
		return scene_id;
	}
	public void setScene_id(int scene_id) {
		this.scene_id = scene_id;
	}
}
