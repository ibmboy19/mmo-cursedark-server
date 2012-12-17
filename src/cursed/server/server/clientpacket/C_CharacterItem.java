package cursed.server.server.clientpacket;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;

public class C_CharacterItem {
	public C_CharacterItem(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		//op type data
	int type = Integer.valueOf(packet.split(C_PacketSymbol)[1] );
		switch(type){
		case 0:
			_client.getActiveChar().setInventory(packet.split(C_PacketSymbol)[2]);
			break;
		case 1:
			_client.getActiveChar().setEquipSlot(packet.split(C_PacketSymbol)[2]);
			break;
		case 2:
			_client.getActiveChar().setInvenShort(packet.split(C_PacketSymbol)[2]);
			break;
		}
		CharacterTable.saveCharInventory(_client.getActiveChar());
	}
}
