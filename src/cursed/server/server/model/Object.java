package cursed.server.server.model;

import java.io.Serializable;

import cursed.server.server.utils.Vector3;

public class Object implements Serializable{
	private static final long serialVersionUID = 1L;
	//Object Location - Vector3
	
	public Vector3 _location = new Vector3();
	
	private String _id = null;
	
	private int state = 0;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getId() {
		return _id;
	}

	/**
	 * 設定對象在世界中唯一的ID
	 * 
	 * @param id
	 *            唯一的ID
	 */
	public void setId(String id) {
		_id = id;
	}
	
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
		return _location.scene_id;
	}
	public void setScene_id(int scene_id) {
		_location.scene_id = scene_id;
	}
	
	/**
	 * 對象存在在地圖上的L1Location
	 * 
	 * @return L1Location的座標對應
	 */
	public Vector3 getLocation() {
		return _location;
	}

	public void setLocation(Vector3 loc) {
		_location.setX(loc.getX());
		_location.setY(loc.getY());
	}

	public void setLocation(int x, int y) {
		_location.setX(x);
		_location.setY(y);
	}
}
