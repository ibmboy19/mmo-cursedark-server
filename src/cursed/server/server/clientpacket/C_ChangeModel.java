package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeModel;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_ChangeModel {
	public C_ChangeModel(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		//op id type index
		CursedWorld.getInstance().broadcastPacketToAllClient(
				Integer.toString(C_ChangeModel)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.split(C_PacketSymbol)[1]+C_PacketSymbol+
				packet.split(C_PacketSymbol)[2]);
	}
}
