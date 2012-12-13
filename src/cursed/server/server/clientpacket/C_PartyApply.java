package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_PartyApply {
	public C_PartyApply(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		CursedWorld.getInstance().broadcastPacketToClient(
				packet.split(C_PacketSymbol)[1],//回覆的對象ID
				Integer.toString(C_PartyApply)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.split(C_PacketSymbol)[2]);
	}
}
