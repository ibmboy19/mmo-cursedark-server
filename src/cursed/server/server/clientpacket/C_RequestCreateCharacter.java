package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCreateCharacter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.instance.PcInstance;

public class C_RequestCreateCharacter {
	public C_RequestCreateCharacter(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
	
		/** 創角名稱*/
		String NewName = packet.split(C_PacketSymbol)[1];			
		PcInstance pc = new PcInstance();
		
		if(CharacterTable.doesCharNameExist(NewName)){
			// 名稱已存在
			// Insert to 資料庫 若success 回傳true 否則傳false
			_client.getWr().println(C_RequestCreateCharacter+C_PacketSymbol+"false");
		} else{
			// 角色名稱可用
			pc.setCharID(NewName);
			pc.setAccountName(_client.getAccountName());
			_client.setActiveChar(pc);
			try {
				CharacterTable.getInstance().storeNewCharacter(pc);
				_client.getWr().println(C_RequestCreateCharacter+C_PacketSymbol+"true");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 若client 收到true，將會送一次OP_RequestCharacterList；false則不做事
	
	
	}
	
}
