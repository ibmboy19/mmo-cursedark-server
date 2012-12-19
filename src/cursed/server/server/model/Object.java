package cursed.server.server.model;

import java.io.Serializable;

import cursed.server.server.utils.Vector3;

public class Object implements Serializable{
	private static final long serialVersionUID = 1L;
	//Object Location - Vector3
	
	public Vector3 _location = new Vector3();
	

	
	/**
	 * 取得對象在地圖上的X軸值
	 * 
	 * @return 座標X軸值
	 */
	public float getX() {
		return _location.getX();
	}

	/**
	 * 設定對象在地圖上的X軸值
	 * 
	 * @param x
	 *            座標X軸值
	 */
	public void setX(float x) {
		_location.setX(x);
	}

	/**
	 * 取得對象在地圖上的Y軸值
	 * 
	 * @return 座標Y軸值
	 */
	public float getY() {
		return _location.getY();
	}

	/**
	 * 設定對象在地圖上的Y軸值
	 * 
	 * @param y
	 *            座標Y軸值
	 */
	public void setY(float y) {
		_location.setY(y);
	}

	public float getZ() {
		return _location.getZ();
	}

	public void setZ(float z) {
		_location.setZ(z);
	}
	
	public int getScene_id() {
		return _location.getScene_id();
	}
	public void setScene_id(int scene_id) {		
		_location.setScene_id(scene_id);
	}
	
	/**
	 * 對象存在在地圖上的L1Location
	 * 
	 * @return L1Location的座標對應
	 */
	public Vector3 getLocation() {
		return _location;
	}


	public void setLocation(float x, float y,float z) {
		_location.setX(x);
		_location.setY(y);
		_location.setZ(z);
	}
	public void setLocation(String loc) {
		loc = loc.substring(1, loc.length()-1);
		_location.setX(Float.valueOf(loc.split(",")[0]));
		_location.setY(Float.valueOf(loc.split(",")[1]));
		_location.setZ(Float.valueOf(loc.split(",")[2]));		
	}
}
