package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeTexture;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_ChangeTexture {
	public C_ChangeTexture(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
	//op id type index
		CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeTexture),
				_client.getActiveChar().getCharID(),
				 _client.getBr().readLine(), 
				 _client.getBr().readLine());
	}
}
