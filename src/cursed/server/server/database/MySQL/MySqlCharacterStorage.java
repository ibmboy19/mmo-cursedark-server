package cursed.server.server.database.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cursed.server.DatabaseFactory;
import cursed.server.server.model.CharacterObject;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.SQLUtil;

public class MySqlCharacterStorage {
	private static Logger _log = Logger.getLogger(MySqlCharacterStorage.class.getName());

	public PcInstance loadCharacter(String charName) {
		PcInstance pc = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM char_info WHERE id=?");
			pstm.setString(1, charName);

			rs = pstm.executeQuery();
			if (!rs.next()) {
				return null;
			}
			// TODO 完成角色儲存
			//location_x=? location_y=? location_z=? cur_lv=1 cur_exp=0 cur_hp=1 max_hp=1 cur_mp=1 max_mp=1 inventory=? inventory_shortcut=? bank=? sp_str=0 sp_dex=0 sp_wis=0 sp_con=0 sp_remain=0 account_id=? scene_id=? guild=0"
			pc = new PcInstance();
			pc.setAccountName(rs.getString("account_id"));
			pc.setX(Float.valueOf(rs.getString("location_x")));
			pc.setY(Float.valueOf(rs.getString("location_y")));
			pc.setZ(Float.valueOf(rs.getString("location_z")));
			pc.setLevel(Integer.valueOf(rs.getString("cur_lv")));
			pc.setCurrentHp(Integer.valueOf(rs.getString("cur_hp")));
			pc.setAccountName(rs.getString("account_id"));
			
			_log.finest("restored char data: ");
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			
			return null;
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return pc;
	}
	/**
	 * 建立新腳色資料庫資料
	 * @param pc
	 */
	public void createCharacter(PcInstance pc) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			// 角色創建
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("INSERT INTO char_info SET id=?,account_id=?,scene_id=?,location_x=?,location_y=?,location_z=?,cur_lv=?,cur_exp=?,cur_hp=?,cur_mp=?,color_r=?,color_g=?,color_b=?,inventory=?,inventory_shortcut=?,bank=?,fame=?,class_id=?,str=?,con=?,dex=?,luck=?,wis=?,ws=?,remain=?,guild=?");
			pstm.setString(1, pc.getID());//id
			pstm.setString(2, pc.getAccountName());//account id
			pstm.setInt(3, 1); // scene id
			pstm.setFloat(4, 0.0f);//loc x
			pstm.setFloat(5, 0.0f);//loc y
			pstm.setFloat(6, 0.0f);//loc z
			pstm.setInt(7,1);//lv
			pstm.setInt(8,0);//exp
			pstm.setInt(9,1);//hp
			pstm.setInt(10,1);//mp
			pstm.setFloat(11, pc.getColorR());//color r
			pstm.setFloat(12, pc.getColorG());//color g
			pstm.setFloat(13, pc.getColorB());//color b
			pstm.setString(14, null);//inventory
			pstm.setString(15, null);//shortcut
			pstm.setString(16, null);//bank
			pstm.setInt(17,0);//fame
			pstm.setInt(18,1);//class
			pstm.setInt(19, pc.getStr());//str
			pstm.setInt(20, pc.getCon());//con
			pstm.setInt(21, pc.getDex());//dex
			pstm.setInt(22, pc.getLuck());//luck
			pstm.setInt(23, pc.getWis());//wis
			pstm.setInt(24, pc.getWs());//ws
			pstm.setInt(25, 20);//remain
			pstm.setString(26, null);//guild
			pstm.execute();
			_log.finest("stored char data: " + pc.getAccountName());
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}
	

	public void deleteCharacter(String accountName, String charName)
			throws Exception {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM char_info WHERE account_id=? AND id=?");
			pstm.setString(1, accountName);
			pstm.setString(2, charName);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				_log.warning("invalid delete char request: account="
						+ accountName + " char=" + charName);
				throw new RuntimeException("could not delete character");
			}

			pstm = con.prepareStatement("DELETE FROM char_info WHERE id=?");
			pstm.setString(1, charName);
			pstm.execute();

		} catch (SQLException e) {
			throw e;
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);

		}
	}

	public void storeCharacter(PcInstance pc) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			int i = 0;
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("UPDATE char_info SET location_x=?,location_y=?,location_z=?,cur_lv=?,cur_exp=?,cur_hp=?," +
					"cur_mp=?,color_r=?,color_g=?,color_b=?,inventory=?,inventory_shortcut=?,back=?,fame=?,str =?,con=?,dex=?,luck=?," +
					"wis=?,ws=?,remain=?,scene_id=?, WHERE id=?");
			pstm.setInt(++i, pc.getLevel());
			pstm.execute();
			_log.finest("stored char data:" + pc.getAccountName());
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}

}
