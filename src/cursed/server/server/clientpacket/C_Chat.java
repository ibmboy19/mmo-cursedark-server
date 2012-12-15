package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Chat;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_Chat {
	public C_Chat(ClientProcess _client, String packet) throws IOException,
			NoSuchAlgorithmException {
		CursedWorld.getInstance().broadcastPacketToScene(
				_client.getActiveChar().getScene_id(),Integer.toString(C_Chat) + C_PacketSymbol
						+ _client.getActiveChar().getCharID() + C_PacketSymbol + packet.split(C_PacketSymbol)[1]);
	}
}
