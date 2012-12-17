package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_PartyApply {
	public C_PartyApply(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		
		
		if(Integer.valueOf(packet.split(C_PacketSymbol)[2]) == 1){
			
			CursedWorld.getInstance().getPlayer(packet.split(C_PacketSymbol)[1]).addTeamMembers(_client.getActiveChar());
			
			_client.getActiveChar().setTeam(CursedWorld.getInstance().getPlayer(packet.split(C_PacketSymbol)[1]).getTeam());
		
		}
		
		
	}
}
