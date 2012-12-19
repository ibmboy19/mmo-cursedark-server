package cursed.server.server.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import cursed.server.DatabaseFactory;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.Portal;
import cursed.server.server.utils.SQLUtil;
import cursed.server.server.utils.Vector3;
import cursed.server.server.utils.collections.Maps;

public class ObjectTable {
	private static Logger _log = Logger.getLogger(CharacterTable.class.getName());
	
	public static void LoadPortal(){
		
		//Map<Integer,Portal> buff = Maps.newConcurrentMap();
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstm = null;
		Portal pt = null;
		
		try {
			con = DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM portal");
			
			rs = pstm.executeQuery();			
			while(rs.next()){
				
				pt = new Portal(rs.getInt("id"),new Vector3(rs.getFloat("loc_curx"),rs.getFloat("loc_cury"),rs.getFloat("loc_curz"),rs.getInt("scene_current")),new Vector3(rs.getFloat("loc_tox"),rs.getFloat("loc_toy"),rs.getFloat("loc_toz"),rs.getInt("scene_to")));
				CursedWorld.getInstance().StorePortal(pt);
				
			}
			
		}catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		finally {			
			SQLUtil.close(pstm);
			SQLUtil.close(con);
			
		}
		
	}
}
