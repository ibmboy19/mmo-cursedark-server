package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_PartyApply {
	public C_PartyApply(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_PartyApply),
				_client.getActiveChar().getCharID(), _client.getBr().readLine(), _client.getBr().readLine());
	}
}
