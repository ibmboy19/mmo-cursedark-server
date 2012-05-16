package cursed.server.server.utils;

public class Vector3 {
	public float x,y,z;
	//Constructor
	public Vector3(){
		x = 0;
		y = 0;
		z = 0;
	}
	public Vector3(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
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
}
