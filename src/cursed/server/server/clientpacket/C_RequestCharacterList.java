package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterList;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;

public class C_RequestCharacterList {
	public C_RequestCharacterList(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		
		String msg = CharacterTable.loadCharacterList(_client.getAccountName());
		String reMsg = String.valueOf(C_RequestCharacterList);
		for(String s : msg.split("\n")){
			reMsg += C_PacketSymbol+s;
		}
		_client.getWr().println(reMsg);
		
		// 回傳給client角色的清單 op, character_id ,character_class, character_lv,guild_name
	}
}
