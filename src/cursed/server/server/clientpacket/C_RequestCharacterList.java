package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterList;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;

public class C_RequestCharacterList {
	public C_RequestCharacterList(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		_client.getWr().println(C_RequestCharacterList);
		String msg = CharacterTable.loadCharacterList(_client.getActiveChar().getAccountName());
		
		for(String s : msg.split("\n")){
			_client.getWr().println(s);
		}
		
		// 回傳給client角色的清單 op, character_id ,character_class, character_lv,guild_name
	}
}
