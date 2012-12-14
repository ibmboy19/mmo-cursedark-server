package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeTexture;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_ChangeTexture {
	public C_ChangeTexture(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
	//op id type index
		CursedWorld.getInstance().broadcastPacketToScene(_client.getActiveChar().getScene_id(),
				Integer.toString(C_ChangeTexture)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.split(C_PacketSymbol)[1]+C_PacketSymbol+
				packet.split(C_PacketSymbol)[2]);
	}
}
