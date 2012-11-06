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
import cursed.server.server.utils.ItemEncoder;
import cursed.server.server.utils.SQLUtil;
import cursed.server.server.utils.collections.Maps;

public class ItemTable {
	//從資料庫抓物品 編碼
	//測試用函式
	public static String CreateItem(int id) {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM item WHERE id="+String.valueOf(id));
			
			
			rs = pstm.executeQuery();			
			if (!rs.next()) {
				return null;
			}
			
			return ItemEncoder.EncodingResourceItem(rs.getInt("id"), rs.getInt("type"), rs.getInt("icon"),
					 rs.getInt("values"),  rs.getInt("weight"),  rs.getInt("durability"), 
					 rs.getInt("max_durability"),  rs.getInt("request_lv"), rs.getInt("request_class"), 
					 rs.getInt("request_str"), rs.getInt("request_con"), rs.getInt("request_dex"), 
					 rs.getInt("request_movp"), rs.getInt("request_wis"), rs.getInt("request_ws"));
			
		}
		catch (Exception e) {
			
		}
		finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
			
		}
		return null;
		
	}
	//測試用函式
	public static String CreateItemAll() {
		String itemData = "";
		for(int cnt = 1;cnt<25;cnt++){
			itemData += CreateItem(cnt);
		}
		return itemData;
	}
	


}
