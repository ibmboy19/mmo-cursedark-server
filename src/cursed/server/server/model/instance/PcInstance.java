package cursed.server.server.model.instance;

import java.util.logging.Level;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.Character;

public class PcInstance extends Character{
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
	
	public void sendpackets(String s){
		_netConnection.getWr().println(s);
	}
	

}
