package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Logout;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.LoginController;
import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_Logout {
	public C_Logout(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		CursedWorld.getInstance().broadcastPacketToAllClient(
				Integer.toString(C_Logout)+C_PacketSymbol+
				_client.getActiveChar().getCharID());
		System.out.println(_client.getActiveChar().getCharID() + " 離線");
		LoginController.getInstance().logout(_client);
		_client.close();
	}
}
