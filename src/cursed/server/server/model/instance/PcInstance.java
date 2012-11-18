package cursed.server.server.model.instance;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CharacterObject;
import cursed.server.server.model.CursedWorld;

public class PcInstance extends CharacterObject{
	private static final long serialVersionUID = 1L;
	private ClientProcess _netConnection;
	private String _accountName;
	// 職業
	public static final int CLASSID_ELECTRIC_GUITARIST = 200;
	
	public static PcInstance load(String charName) {
		PcInstance result = null;
		try {
			result = CharacterTable.getInstance().loadCharacter(charName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void logout() {
		CursedWorld world = CursedWorld.getInstance();
		world.removeObject(this);
		setNetConnection(null);
	}
	
	public String getAccountName() {
		return _accountName;
	}

	public void setAccountName(String s) {
		_accountName = s;
	}
	
	public ClientProcess getNetConnection() {
		return _netConnection;
	}

	public void setNetConnection(ClientProcess Clientprocess) {
		_netConnection = Clientprocess;
	}
	
	public void sendpackets(int s){
		_netConnection.getWr().println(s);
	}
	
	public void sendpackets(String s){		
		_netConnection.getWr().println(s);
	}
	

}
