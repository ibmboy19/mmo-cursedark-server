package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterInfo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_RequestCharacterInfo {
	public C_RequestCharacterInfo(ClientProcess _client, String packet)
			throws IOException, NoSuchAlgorithmException {

		/**
		 * 所有要求資訊封包 op 
		 * type 0:
		 * 
		 * 
		 * type 1:
		 * 
		 * 
		 * type 2:
		 * 
		 * */
		int type = Integer.valueOf(packet.split(C_PacketSymbol)[1]);
		String retPacket = String.valueOf(C_RequestCharacterInfo);
		switch (type) {
		case 0:// ReqAll
			PcInstance reqPc = CursedWorld.getInstance().getPlayer(packet.split(C_PacketSymbol)[2]);
			
			retPacket += C_PacketSymbol 
					+type+C_PacketSymbol
					+ reqPc.getCharID()	+ C_PacketSymbol 
					+reqPc.getCurrentHp()	+ C_PacketSymbol 
					+reqPc.getCurrentMp()	+ C_PacketSymbol 
					+ reqPc.getColorR() + C_PacketSymbol
					+ reqPc.getColorG() + C_PacketSymbol
					+ reqPc.getColorB() + C_PacketSymbol 
					+ reqPc.getX()+ C_PacketSymbol 
					+ reqPc.getY() + C_PacketSymbol
					+ reqPc.getZ();
		
			break;
		case 1:// ReqSelfAll
			retPacket+= C_PacketSymbol 
					+type+C_PacketSymbol
					+_client.getActiveChar().getCurrentHp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentMp();
			break;
		case 2:// ReqSelfHpMp
			retPacket+= C_PacketSymbol 
					+type+C_PacketSymbol
					+_client.getActiveChar().getCurrentHp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentMp();
			break;
		case 3:// ReqOtherHpMp
			retPacket+= C_PacketSymbol 
					+type+C_PacketSymbol
					+packet.split(C_PacketSymbol)[2]+C_PacketSymbol
					+_client.getActiveChar().getCurrentHp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentMp();
			break;
		}
		_client.getWr().println(retPacket);

	}
}
