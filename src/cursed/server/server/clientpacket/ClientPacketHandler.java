package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeModel;
import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeTexture;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Chat;
import static cursed.server.server.clientpacket.ClientOpcodes.C_CreateCharacter;
import static cursed.server.server.clientpacket.ClientOpcodes.C_KeyBoardWalk;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Login;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Logout;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Party;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterList;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterLogin;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCreateCharacter;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestInventory;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Walk;

import java.io.IOException;
import java.net.SocketException;

import cursed.server.LoginController;
import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;

public class ClientPacketHandler {
	private final ClientProcess _client;
	private PcInstance pc = null;

	public ClientPacketHandler(ClientProcess client) {
		_client = client;
	}

	public void handlePacket(final int op) {

		try {
			switch (op) {
			case C_Login:
				String accountName = _client.getBr().readLine();
				String password = _client.getBr().readLine();
				String ip = _client.get_ip();
				Account account = Account.load(accountName);
				

				if (account == null) {
					account = Account.create(accountName, password, ip);
				} else {
					// 帳號存在
					
				}
				if (!account.validatePassword(password)) {
					_client.getWr().println(C_Login);
					_client.getWr().println("false");
					return;
				}
				try {
					CursedWorld.getInstance().addClient(_client);
					LoginController.getInstance().login(_client, account);
					//Account.updateLastActive(account, ip); // 更新最後一次登入的時間與IP
					_client.setAccount(account);
					
					pc = new PcInstance();//new pc
					pc.setAccountName(accountName);//設定pc的帳號
					pc.setNetConnection(_client);//設定pc 的connection
					_client.setActiveChar(pc);
					
					_client.getWr().println(C_Login);
					_client.getWr().println("true");
					System.out.format("帳號: %s 已經登入\n", accountName);
					
					
				} catch (Exception e) {
					e.getStackTrace();
					return;
				}
				break;
			case C_CreateCharacter:
				//收到  str, con,dex,luck,wis,ws,color_r,color_g,color_b
				//創造角色
				pc.setStr(Integer.valueOf(_client.getBr().readLine()));//str
				pc.setCon(Integer.valueOf(_client.getBr().readLine()));//con
				pc.setDex(Integer.valueOf(_client.getBr().readLine()));//dex
				pc.setLuck(Integer.valueOf(_client.getBr().readLine()));//luck
				pc.setWis(Integer.valueOf(_client.getBr().readLine()));//wis
				pc.setWs(Integer.valueOf(_client.getBr().readLine()));//ws
				pc.setColorR(Float.valueOf(_client.getBr().readLine()));//color_r
				pc.setColorG(Float.valueOf(_client.getBr().readLine()));//color_g
				pc.setColorB(Float.valueOf(_client.getBr().readLine()));//color_b
			
				CharacterTable.getInstance().storeCharacter(pc);
				
				_client.getWr().println(C_CreateCharacter);
				_client.getWr().println("true");
				break;
			case C_RequestCreateCharacter:
				// 收到 id
				String NewName = _client.getBr().readLine();			
				
				if(CharacterTable.doesCharNameExist(NewName)){
					// TODO 名稱已存在
					// TODO Insert to 資料庫 若success 回傳true 否則傳false
					_client.getWr().println(C_RequestCreateCharacter);
					_client.getWr().println("false");					
					
				} else{
					// 角色名稱可用
					pc.setCharID(NewName);
					CharacterTable.getInstance().storeNewCharacter(pc);
					_client.getWr().println(C_RequestCreateCharacter);
					_client.getWr().println("true");
				}
				// 若client 收到true，將會送一次OP_RequestCharacterList；false則不做事
				break;
			case C_RequestCharacterList: // op count (id class lv guild)
				_client.getWr().println(C_RequestCharacterList);//write op
				String msg = CharacterTable.loadCharacterList(pc.getAccountName());//get msg
				for(int cnt = 0;cnt<msg.split("\n").length;cnt++){
					_client.getWr().println(msg.split("\n")[cnt]);//write msg
				}
				// TODO client要求該帳號的角色清單
				// 回傳給client角色的清單 op, character_id ,character_class, character_lv,guild_name
				break;
			case C_RequestCharacterLogin:
				//載入角色
				pc = PcInstance.load(_client.getBr().readLine());
				pc.setNetConnection(_client);
				
				_client.setActiveChar(pc);
				//將角色置入世界裡
				CursedWorld.getInstance().StorePlayer(pc);
								
				//write pc data to client
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
				
				break;
			case C_Chat:
				//String msg = null;				
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Chat),_client.getActiveChar().getCharID(), msg);
				System.out.println(_client.getActiveChar().getCharID() + ": " + msg);
				break;
			case C_Walk:
				String Positoon = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Walk), _client.getActiveChar().getCharID(),Positoon);

				// TODO 儲存角色狀態到DB
				CharacterTable.saveCharStatus(pc);
				break;
			case C_KeyBoardWalk:// op,id,direction,look-direction,position
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_KeyBoardWalk),_client.getActiveChar().getCharID(), _client.getBr().readLine(),_client.getBr().readLine(), _client.getBr().readLine());
				break;
			case C_Party: // id request
				// broadcast error
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_Party), _client.getActiveChar().getCharID(),_client.getBr().readLine(), _client.getBr().readLine());
				break;
			case C_PartyApply:
				// broadcast error
				String toId = _client.getBr().readLine();
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_PartyApply),_client.getActiveChar().getCharID(), toId, msg);
				break;
			case C_RequestInventory:
				break;
			case C_ChangeTexture:
				String type,
				index;
				type = _client.getBr().readLine();
				index = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeTexture),_client.getActiveChar().getCharID(), type, index);
				break;
			case C_ChangeModel:
				type = _client.getBr().readLine();
				index = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeModel),_client.getActiveChar().getCharID(), type, index);
				break;
			case C_Logout:
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Logout), _client.getActiveChar().getCharID());
				System.out.println(_client.getActiveChar().getCharID() + " 離線");
				LoginController.getInstance().logout(_client);
				_client.close();
				break;

			}
		} catch (NumberFormatException nf) {
			System.out.println("接收到一個null.");
		} catch (SocketException se) {
			try {
				_client.get_csocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("登出...");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
