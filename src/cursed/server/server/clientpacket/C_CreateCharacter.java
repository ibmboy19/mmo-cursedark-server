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
		
		//收到  str, con,dex,luck,wis,ws,color_r,color_g,color_b
		//創造角色
		PcInstance pc = _client.getActiveChar();
		pc.setAccountName(_client.getAccountName());
		pc.setStr(Integer.valueOf(packet.split(C_PacketSymbol)[1]));//str
		pc.setCon(Integer.valueOf(packet.split(C_PacketSymbol)[2]));//con
		pc.setDex(Integer.valueOf(packet.split(C_PacketSymbol)[3]));//dex
		pc.setLuck(Integer.valueOf(packet.split(C_PacketSymbol)[4]));//luck
		pc.setWis(Integer.valueOf(packet.split(C_PacketSymbol)[5]));//wis
		pc.setWs(Integer.valueOf(packet.split(C_PacketSymbol)[6]));//ws
		pc.setColorR(Float.valueOf(packet.split(C_PacketSymbol)[7]));//color_r
		pc.setColorG(Float.valueOf(packet.split(C_PacketSymbol)[8]));//color_g
		pc.setColorB(Float.valueOf(packet.split(C_PacketSymbol)[9]));//color_b
		
		try {
			CharacterTable.getInstance().storeCharacter(pc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		_client.getWr().println(C_CreateCharacter+C_PacketSymbol+"true");
		
		
	}	
}
