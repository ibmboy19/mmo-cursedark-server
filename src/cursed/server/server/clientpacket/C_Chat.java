package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Chat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_Chat {
	public C_Chat(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		String msg = null;				
		msg = _client.getBr().readLine();
		CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Chat),_client.getActiveChar().getCharID(), msg);
	}
}