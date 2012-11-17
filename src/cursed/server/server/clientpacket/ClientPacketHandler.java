package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.*;

import java.io.IOException;
import java.net.SocketException;

import cursed.server.LoginController;
import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CharacterObject;
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
				CharacterTable Ct = CharacterTable.getInstance();

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
				// 收到 id , str, con,dex,luck,wis,ws,color_r,color_g,color_b
				//暫用client省麻煩寫法
				String NewName = _client.getBr().readLine();				
				pc.SetAllData(NewName, _client.getBr().readLine(), 
					      _client.getBr().readLine(), _client.getBr().readLine(), 
					      _client.getBr().readLine(), _client.getBr().readLine(), 
					      _client.getBr().readLine(), _client.getBr().readLine(), 
					      _client.getBr().readLine(), _client.getBr().readLine());
				
				if(CharacterTable.doesCharNameExist(NewName)){
					// TODO 名稱已存在
					// TODO Insert to 資料庫 若success 回傳true 否則傳false
					_client.getWr().println(C_CreateCharacter);
					_client.getWr().println("false");
					pc.setId(null);
					return;
				} else{
					// 角色名稱可用
					CharacterTable.getInstance().storeNewCharacter(pc);
					_client.getWr().println(C_CreateCharacter);
					_client.getWr().println("true");
				}
				// 若client 收到true，將會送一次OP_RequestCharacterList；false則不做事
				break;
			case C_RequestCharacterList: // op count (id class lv guild)
				_client.getWr().println(C_RequestCharacterList);//op
				String msg = CharacterTable.loadCharacterList(pc.getAccountName());
				for(int cnt = 0;cnt<msg.split("\n").length;cnt++){
					_client.getWr().println(msg.split("\n")[cnt]);
				}
				// TODO client要求該帳號的角色清單
				// 回傳給client角色的清單 op, character_id ,character_class, character_lv,guild_name
				break;
			case C_RequestCharacterLogin:
				
				break;
			case C_Chat:
				//String msg = null;				
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Chat),_client.getAccountName(), msg);
				System.out.println(_client.getAccountName() + ": " + msg);
				break;
			case C_Walk:
				String Positoon = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Walk), _client.getAccountName(),Positoon);

				// TODO 儲存角色狀態到DB
				CharacterTable.saveCharStatus(pc);
				break;
			case C_KeyBoardWalk:// op,id,direction,look-direction,position
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_KeyBoardWalk),_client.getAccountName(), _client.getBr().readLine(),_client.getBr().readLine(), _client.getBr().readLine());
				break;
			case C_Party: // id request
				// broadcast error
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_Party), _client.getAccountName(),_client.getBr().readLine(), _client.getBr().readLine());
				break;
			case C_PartyApply:
				// broadcast error
				String toId = _client.getBr().readLine();
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_PartyApply),_client.getAccountName(), toId, msg);
				break;
			case C_RequestInventory:
				break;
			case C_ChangeTexture:
				String type,
				index;
				type = _client.getBr().readLine();
				index = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeTexture),_client.getAccountName(), type, index);
				break;
			case C_ChangeModel:
				type = _client.getBr().readLine();
				index = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeModel),_client.getAccountName(), type, index);
				break;
			case C_Logout:
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Logout), _client.getAccountName());
				System.out.println(_client.getAccountName() + " 離線");
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
