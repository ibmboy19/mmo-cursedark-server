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
						pc = new PcInstance();
						pc.setAccountName(accountName);
						Ct.storeNewCharacter(pc);
				}else {
					pc = new PcInstance();
					pc.setAccountName(accountName);
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
					_client.getWr().println(C_Login);
					_client.getWr().println("true");
					pc.setStr(10);
					System.out.format("帳號: %s 已經登入\n", accountName);
					//進入世界用
					/*pc = PcInstance.load(accountName);
					pc.setName(account.getName());
					CursedWorld.getInstance().storeObject(pc);*/

					pc.setNetConnection(_client);
					_client.setActiveChar(pc);
				}catch (Exception e) {
					e.getStackTrace();
					return;
				}
				break;
			case C_CreateCharacter:
				//收到 id , str, con,dex,luck,wis,ws,color_r,color_g,color_b
				//TODO Insert to 資料庫 若success 回傳true 否則傳false
				pc.SetAllData(_client.getBr().readLine(),_client.getBr().readLine(),_client.getBr().readLine(),_client.getBr().readLine(),
						_client.getBr().readLine(),_client.getBr().readLine(),_client.getBr().readLine(),_client.getBr().readLine(),
						_client.getBr().readLine(),_client.getBr().readLine());
				
				
				
				Ct = CharacterTable.getInstance();
				Ct.storeCharacter(pc);
				
				//若client 收到true，將會送一次OP_RequestCharacterList；false則不做事
				break;
			case C_RequestCharacterList:
				//TODO client要求該帳號的角色清單
				//回傳給client角色的清單 op, character_id , character_lv,guild_name
			break;
			case C_Chat:
				String msg = null;
				msg  = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Chat), _client.getAccountName(), msg);
				System.out.println(_client.getAccountName()+ ": " + msg);
				break;
			case C_Walk:
				String Positoon = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Walk), _client.getAccountName(), Positoon);
				
				// TODO 處理座標 
				
				// TODO 儲存角色狀態到DB
				//CharacterTable.saveCharStatus(pc);
				break;
			case C_KeyBoardWalk://op,id,direction,look-direction,position
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_KeyBoardWalk), _client.getAccountName(), _client.getBr().readLine(),_client.getBr().readLine(),_client.getBr().readLine());
				break;
			case C_Party: //id request
				//broadcast error
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_Party),_client.getAccountName(),  _client.getBr().readLine(), _client.getBr().readLine());
                break;
			case C_PartyApply:
				//broadcast error
				String toId  = _client.getBr().readLine();
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToClient(Integer.toString(C_PartyApply),_client.getAccountName(),  toId, msg);
                break;
            case C_RequestInventory:
                break;
            case C_ChangeTexture:
                String type, index;
                type = _client.getBr().readLine();
                index = _client.getBr().readLine();	
                CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeTexture), _client.getAccountName(),type ,index);
                break;
            case C_ChangeModel:
            	type = _client.getBr().readLine();
                index = _client.getBr().readLine();
                CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeModel), _client.getAccountName(),type ,index);
                break;            
			case C_Logout:
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_Logout),_client.getAccountName());
				System.out.println(_client.getAccountName()+" 離線");
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
