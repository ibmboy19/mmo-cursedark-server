package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_CreateCharacter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.instance.PcInstance;

public class C_CreateCharacter {
	public C_CreateCharacter(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		
		//收到  str, con,dex,luck,wis,ws,color_r,color_g,color_b
		//創造角色
		PcInstance pc = new PcInstance();
		pc.setStr(Integer.valueOf(_client.getBr().readLine()));//str
		pc.setCon(Integer.valueOf(_client.getBr().readLine()));//con
		pc.setDex(Integer.valueOf(_client.getBr().readLine()));//dex
		pc.setLuck(Integer.valueOf(_client.getBr().readLine()));//luck
		pc.setWis(Integer.valueOf(_client.getBr().readLine()));//wis
		pc.setWs(Integer.valueOf(_client.getBr().readLine()));//ws
		pc.setColorR(Float.valueOf(_client.getBr().readLine()));//color_r
		pc.setColorG(Float.valueOf(_client.getBr().readLine()));//color_g
		pc.setColorB(Float.valueOf(_client.getBr().readLine()));//color_b
	
		try {
			CharacterTable.getInstance().storeCharacter(pc);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		_client.getWr().println(C_CreateCharacter);
		_client.getWr().println("true");
		
		
	}	
}
