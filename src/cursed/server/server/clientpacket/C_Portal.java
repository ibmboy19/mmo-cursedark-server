package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Portal;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.Portal;
import cursed.server.server.model.instance.PcInstance;

public class C_Portal {
	public C_Portal(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		/**
		 * portal id
		 * */	
		Portal pt = CursedWorld.getInstance().getPortal(Integer.valueOf(packet.split(C_PacketSymbol)[1]));
		
		String retPacket = String.valueOf(C_Portal)+C_PacketSymbol;
		
		PcInstance pc = _client.getActiveChar();

		System.out.println(pt.getTarget().ToString());
		
		if(pt.getTarget().getScene_id() == pc.getScene_id()){
			
			pc.setLocation(pt.getTarget());
			CharacterTable.saveCharLocation(pc);
			
			retPacket+="0"+C_PacketSymbol+pc.getLocation().ToString();
			
		}else {
			
			pc.setLocation(pt.getTarget());
			CharacterTable.saveCharLocation(pc);
			
			retPacket+="1"+C_PacketSymbol+
					pc.getCharID()+C_PacketSymbol+			
					pc.getCharacterClass()+C_PacketSymbol+
					pc.getLevel()+C_PacketSymbol+
					pc.getCurrentExp()+C_PacketSymbol+
					pc.getMaxExp()+C_PacketSymbol+
					pc.getCurrentHp()+C_PacketSymbol+
					pc.getMaxHp()+C_PacketSymbol+
					pc.getCurrentMp()+C_PacketSymbol+
					pc.getMaxMp()+C_PacketSymbol+
					pc.getColorR()+	C_PacketSymbol+
					pc.getColorG()+C_PacketSymbol+
					pc.getColorB()+C_PacketSymbol+
					pc.getStr()+C_PacketSymbol+
					pc.getCon()+C_PacketSymbol+
					pc.getDex()+C_PacketSymbol+
					pc.getLuck()+C_PacketSymbol+
					pc.getWis()+C_PacketSymbol+
					pc.getWs()+C_PacketSymbol+
					pc.getRemain()+C_PacketSymbol+
					pc.getScene_id()+C_PacketSymbol+
					pc.getLocation().ToString()+C_PacketSymbol+
					pc.getInventory()+C_PacketSymbol+
					pc.getEquipSlot()+C_PacketSymbol+
					pc.getInvenShort() ;
			
			
		}
		
		_client.getWr().println(retPacket);
		
		
	}
}
