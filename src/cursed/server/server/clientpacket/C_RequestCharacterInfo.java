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
		 * 未知的其他玩家
		 * 
		 * type 1:
		 * 未知的自己
		 * 
		 * type 2:
		 * 自身血量魔力
		 * 
		 * type 3:
		 * 其他玩家血量魔力
		 * 
		 * type 5://剛登入
		 * 所有其他玩家資料
		 * */
		int type = Integer.valueOf(packet.split(C_PacketSymbol)[1]);
		String retPacket = String.valueOf(C_RequestCharacterInfo);
		switch (type) {
		case 0:// ReqAll
			PcInstance reqPc = CursedWorld.getInstance().getPlayer(packet.split(C_PacketSymbol)[2]);
			
			retPacket += C_PacketSymbol 
					+type+C_PacketSymbol
					+ reqPc.getCharID()	+ C_PacketSymbol 
					+ reqPc.getCharacterClass()	+ C_PacketSymbol 
					+ reqPc.getLevel()	+ C_PacketSymbol 
					+reqPc.getCurrentHp()	+ C_PacketSymbol 
					+reqPc.getMaxHp()	+ C_PacketSymbol 
					+reqPc.getCurrentMp()	+ C_PacketSymbol 
					+reqPc.getMaxMp()	+ C_PacketSymbol 
					+ reqPc.getColorR() + C_PacketSymbol
					+ reqPc.getColorG() + C_PacketSymbol
					+ reqPc.getColorB() + C_PacketSymbol 
					+reqPc.getStr() + C_PacketSymbol 
					+reqPc.getCon() + C_PacketSymbol 
					+reqPc.getDex() + C_PacketSymbol 
					+reqPc.getLuck() + C_PacketSymbol 
					+reqPc.getWis() + C_PacketSymbol 
					+reqPc.getWs() + C_PacketSymbol 
					+ reqPc.getLocation().ToString() + C_PacketSymbol
					+	reqPc.getEquipSlot();
		
			break;
		case 1:// ReqSelfAll
			retPacket+= C_PacketSymbol 
					+type+C_PacketSymbol
					+_client.getActiveChar().getLevel()+C_PacketSymbol 					
					+_client.getActiveChar().getMaxExp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentExp()+C_PacketSymbol 
					+_client.getActiveChar().getMaxHp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentHp()+C_PacketSymbol 
					+_client.getActiveChar().getMaxMp()+C_PacketSymbol 
					+_client.getActiveChar().getCurrentMp()  ;
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
		case 4://ReqStatus
			retPacket+= C_PacketSymbol 
			+type+C_PacketSymbol+
			_client.getActiveChar().getStr()+C_PacketSymbol 					
			+_client.getActiveChar().getCon()+C_PacketSymbol 
			+_client.getActiveChar().getDex()+C_PacketSymbol 
			+_client.getActiveChar().getLuck()+C_PacketSymbol 
			+_client.getActiveChar().getWis()+C_PacketSymbol 
			+_client.getActiveChar().getWs()+C_PacketSymbol 
			+_client.getActiveChar().getRemain() ;
			break;		
		case 5:
			for(PcInstance _pc : CursedWorld.getInstance().getAllPlayers() ){
				if(_pc.getCharID() != _client.getActiveChar().getCharID()
						&& _pc.getScene_id() == _client.getActiveChar().getScene_id()){
					
					 retPacket =  String.valueOf(C_RequestCharacterInfo)+C_PacketSymbol 
							+type+C_PacketSymbol
							+ _pc.getCharID()	+ C_PacketSymbol 
							+ _pc.getCharacterClass()	+ C_PacketSymbol 
							+ _pc.getLevel()	+ C_PacketSymbol 
							+_pc.getCurrentHp()	+ C_PacketSymbol 
							+_pc.getMaxHp()	+ C_PacketSymbol 
							+_pc.getCurrentMp()	+ C_PacketSymbol 
							+_pc.getMaxMp()	+ C_PacketSymbol 
							+ _pc.getColorR() + C_PacketSymbol
							+ _pc.getColorG() + C_PacketSymbol
							+ _pc.getColorB() + C_PacketSymbol 
							+_pc.getStr() + C_PacketSymbol 
							+_pc.getCon() + C_PacketSymbol 
							+_pc.getDex() + C_PacketSymbol 
							+_pc.getLuck() + C_PacketSymbol 
							+_pc.getWis() + C_PacketSymbol 
							+_pc.getWs() + C_PacketSymbol 
							+ _pc.getLocation().ToString() + C_PacketSymbol
							+	_pc.getEquipSlot();
					
					_client.getWr().println(retPacket);
				}
			}
			return;
		}
		_client.getWr().println(retPacket);

	}
}
