package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_KeyBoardWalk;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;

public class C_KeyBoardWalk {
	public C_KeyBoardWalk(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		
		//set location
				_client.getActiveChar().setLocation(packet.split(C_PacketSymbol)[3]);
				//save db
				CharacterTable.saveCharLocation(_client.getActiveChar()); 
		
		// broadcast
		CursedWorld.getInstance().broadcastPacketToScene(_client.getActiveChar().getScene_id(),
				Integer.toString(C_KeyBoardWalk)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.split(C_PacketSymbol)[1]+C_PacketSymbol+
				packet.split(C_PacketSymbol)[2]+C_PacketSymbol+
				packet.split(C_PacketSymbol)[3]);
		
	}
}
