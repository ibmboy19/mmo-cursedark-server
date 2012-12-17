package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterLogin;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_RequestCharacterLogin {
	public C_RequestCharacterLogin(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		// 載入角色
		PcInstance pc = PcInstance.load(packet.split(C_PacketSymbol)[1]);
		pc.setNetConnection(_client);
		_client.setActiveChar(pc);
		CursedWorld.getInstance().StorePlayer(pc);
		pc.CalcAllAttribite();
						
		// write pc data to client
		/**
		 * op 
		 * id 
		 * character_class
		 * level exp max exp 
		 * hp maxhp 
		 * mp maxmp 
		 * r g b
		 * str con dex luck wis ws remain 
		 * scene_id
		 * location
		 * */
		_client.getWr().println(
				C_RequestCharacterLogin+C_PacketSymbol+
				pc.getCharID()+C_PacketSymbol+			
				pc.getCharacterClass().getID()+C_PacketSymbol+
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
				pc.getLocation().ToString());
	}
}
