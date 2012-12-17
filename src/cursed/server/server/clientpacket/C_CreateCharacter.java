package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_CreateCharacter;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.instance.PcInstance;

public class C_CreateCharacter {
	public C_CreateCharacter(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		
		//收到  class str, con,dex,luck,wis,ws,color_r,color_g,color_b
		//創造角色
		PcInstance pc = _client.getActiveChar();
		pc.setAccountName(_client.getAccountName());
		pc.setCharacterClass(Integer.valueOf(packet.split(C_PacketSymbol)[1]));//class id
		pc.setStr(Integer.valueOf(packet.split(C_PacketSymbol)[2]));//str
		pc.setCon(Integer.valueOf(packet.split(C_PacketSymbol)[3]));//con
		pc.setDex(Integer.valueOf(packet.split(C_PacketSymbol)[4]));//dex
		pc.setLuck(Integer.valueOf(packet.split(C_PacketSymbol)[5]));//luck
		pc.setWis(Integer.valueOf(packet.split(C_PacketSymbol)[6]));//wis
		pc.setWs(Integer.valueOf(packet.split(C_PacketSymbol)[7]));//ws
		pc.setColorR(Float.valueOf(packet.split(C_PacketSymbol)[8]));//color_r
		pc.setColorG(Float.valueOf(packet.split(C_PacketSymbol)[9]));//color_g
		pc.setColorB(Float.valueOf(packet.split(C_PacketSymbol)[10]));//color_b
		
		pc.CalcAllAttribite();
		pc.setCurrentHp(pc.getMaxHp());
		pc.setCurrentMp(pc.getMaxMp());
		
		
		try {
			CharacterTable.getInstance().storeCharacter(pc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		_client.getWr().println(C_CreateCharacter+C_PacketSymbol+"true");
		
		
	}	
}
