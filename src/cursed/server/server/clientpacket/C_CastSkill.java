package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_CastSkill;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

public class C_CastSkill {
public C_CastSkill(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		
		CursedWorld.getInstance().broadcastPacketToScene(_client.getActiveChar().getScene_id(),
				Integer.toString(C_CastSkill)+C_PacketSymbol+
				_client.getActiveChar().getCharID()+C_PacketSymbol+
				packet.substring(packet.indexOf(C_PacketSymbol)+1));
		
		
		
	}
}
