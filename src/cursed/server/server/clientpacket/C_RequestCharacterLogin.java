package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterLogin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class C_RequestCharacterLogin {
	public C_RequestCharacterLogin(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
		// 載入角色
		PcInstance pc = PcInstance.load(_client.getBr().readLine());
		_client.setActiveChar(pc);
		pc.setNetConnection(_client);
		CursedWorld.getInstance().StorePlayer(pc);
						
		// write pc data to client
		_client.getWr().println(C_RequestCharacterLogin);//op
		_client.getWr().println(pc.getCharID());//id
		_client.getWr().println(pc.getLevel());//lv
		_client.getWr().println(pc.getCurrentExp());//cur exp
		_client.getWr().println(pc.getCurrentHp());//cur hp
		_client.getWr().println(pc.getCurrentMp());//cur mp
		_client.getWr().println(pc.getColorR());//color r
		_client.getWr().println(pc.getColorG());//color g
		_client.getWr().println(pc.getColorB());//color b
		_client.getWr().println(pc.getStr());//str
		_client.getWr().println(pc.getCon());//con
		_client.getWr().println(pc.getDex());//dex
		_client.getWr().println(pc.getLuck());//luck
		_client.getWr().println(pc.getWis());//wis
		_client.getWr().println(pc.getWs());//ws
		_client.getWr().println(pc.getRemain());//remain
		_client.getWr().println(pc.getScene_id());//scene id
		_client.getWr().println(pc.getLocation().ToString());//location
	}
}
