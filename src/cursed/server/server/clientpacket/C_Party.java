package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Party;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_Party {
	public C_Party(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		/****/
		CursedWorld.getInstance().broadcastPacketToClient(
				packet.split(C_PacketSymbol)[1],//要求的對象ID
				Integer.toString(C_Party)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.split(C_PacketSymbol)[2]);
	}
}
