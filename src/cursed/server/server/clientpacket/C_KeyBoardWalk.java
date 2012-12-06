package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_KeyBoardWalk;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;

public class C_KeyBoardWalk {
	public C_KeyBoardWalk(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		if(!_client.getBr().ready()){
			return;
		}
		CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_KeyBoardWalk),_client.getActiveChar().getCharID(), _client.getBr().readLine(),_client.getBr().readLine(), _client.getBr().readLine());
		CharacterTable.saveCharStatus(_client.getActiveChar()); 
	}
}
