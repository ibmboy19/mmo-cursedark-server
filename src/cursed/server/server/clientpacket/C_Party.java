package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Party;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_Party {
	public C_Party(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		/****/
		CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_Party), _client.getActiveChar().getCharID(),_client.getBr().readLine(), _client.getBr().readLine());
	}
}
