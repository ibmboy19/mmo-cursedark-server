package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Party;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_Party {
	public C_Party(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		/****/
		PcInstance reqPc = CursedWorld.getInstance().getPlayer(packet.split(C_PacketSymbol)[1]);
		if(reqPc.getCharID() == _client.getActiveChar().getCharID()){
			return ;
		}
		if(!reqPc.isInTeam()){
			//符合組隊條件
			CursedWorld.getInstance().broadcastPacketToClient(
					reqPc.getCharID(),//要求的對象ID
					Integer.toString(C_Party)+C_PacketSymbol+
					_client.getActiveChar().getCharID()+C_PacketSymbol+
					packet.split(C_PacketSymbol)[2]);
		}else {
			//組隊不符資格
			_client.getWr().println(Integer.toString(C_PartyApply)+C_PacketSymbol+
					reqPc.getCharID()+C_PacketSymbol+
					"false");
		}
	}
}
