package cursed.server.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import cursed.server.DatabaseFactory;
import cursed.server.server.database.MySQL.MySqlCharacterStorage;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.SQLUtil;
import cursed.server.server.utils.collections.Maps;

public class CharacterTable {
	private static Logger _log = Logger.getLogger(CharacterTable.class
			.getName());
	private final Map<String, String> _charNameList = Maps.newConcurrentMap();

	private static CharacterTable _instance;
	private MySqlCharacterStorage _charStorage;

	public static CharacterTable getInstance() {
		if (_instance == null) {
			_instance = new CharacterTable();
		}
		return _instance;
	}

	private CharacterTable() {
		_charStorage = new MySqlCharacterStorage();
	}

	public void storeNewCharacter(PcInstance pc) throws Exception {
		synchronized (pc) {
			_charStorage.createCharacter(pc);
		}
	}

	public void storeCharacter(PcInstance pc) throws Exception {
		synchronized (pc) {
			_charStorage.storeCharacter(pc);
		}
	}

	public PcInstance restoreCharacter(String charName) throws Exception {
		PcInstance pc = _charStorage.loadCharacter(charName);
		return pc;
	}

	public PcInstance loadCharacter(String charName) throws Exception {
		PcInstance pc = null;
		try {
			pc = restoreCharacter(charName);
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return pc;
	}
	
	public static void saveCharStatus(PcInstance pc) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = DatabaseFactory.getInstance().getConnection();
			//pstm = con.prepareStatement("UPDATE characters SET OriginalStr= ?" + ", OriginalCon= ?, OriginalDex= ?, OriginalCha= ?"
			//		+ ", OriginalInt= ?, OriginalWis= ?" + " WHERE objid=?");
			//pstm.setInt(1, pc.getBaseStr());
			//pstm.setInt(2, pc.getBaseCon());
			//pstm.setInt(3, pc.getBaseDex());
			//pstm.setInt(7, pc.getId());
			pstm.execute();
		}
		catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
	}
	
	public static boolean doesCharNameExist(String name) {
		boolean result = true;
		java.sql.Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT account_name FROM characters WHERE char_name=?");
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			result = rs.next();
		}
		catch (SQLException e) {
			_log.warning("could not check existing charname:" + e.getMessage());
		}
		finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return result;
	}


}
