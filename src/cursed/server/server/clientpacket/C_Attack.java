package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Attack;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_Attack {
	public C_Attack(ClientProcess _client, String packet) throws IOException,
			NoSuchAlgorithmException {

		
		/**
		 * 1.op targetID type 
		 * 2.op targetID type skillID
		 * 		
		 * 計算傷害....
		 * */
		int type =  Integer.valueOf(packet.split(C_PacketSymbol)[2]);
		String targetID = packet.split(C_PacketSymbol)[1];
		int damage = 0;
		PcInstance targetPC = CursedWorld.getInstance().getPlayer(targetID);
		
		switch(type){
		case 0:
			damage = (int) (_client.getActiveChar().getAtk()-.5f*targetPC.getDef());
			targetPC.adjustCurrentHp(damage);			
			break;
		case 1:
			damage = (int) (_client.getActiveChar().getMAtk()-.2f*targetPC.getMDef()+2);
			targetPC.adjustCurrentHp(damage);			
			break;
		case 2://revive
			damage = -targetPC.getMaxHp();
			targetPC.adjustCurrentHp(damage);
			break;
		}
		CursedWorld.getInstance().broadcastPacketToScene(
				_client.getActiveChar().getScene_id(),
				Integer.toString(C_Attack) + C_PacketSymbol
				+ _client.getActiveChar().getCharID() + C_PacketSymbol
				+ targetID + C_PacketSymbol
						+ damage);
	}
}
