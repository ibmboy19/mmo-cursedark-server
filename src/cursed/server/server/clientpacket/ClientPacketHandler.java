package cursed.server.server.clientpacket;

import java.io.IOException;
import java.net.SocketException;

import cursed.server.LoginController;
import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;
import static cursed.server.server.clientpacket.ClientOpcodes.*;

public class ClientPacketHandler {
	private final ClientProcess _client;
	private PcInstance pc = null;

	public ClientPacketHandler(ClientProcess client) {
		_client = client;
	}

	public void handlePacket(final int op) {
		try {
			switch (op) {
			case C_login:
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
				}
				if (!account.validatePassword(password)) {
					_client.getWr().println(C_login);
					_client.getWr().println("false");
					return;
				}
				try {
					CursedWorld.getInstance().addClient(_client);
					LoginController.getInstance().login(_client, account);
					//Account.updateLastActive(account, ip); // 更新最後一次登入的時間與IP
					_client.setAccount(account);
					_client.getWr().println("true");
					
					System.out.format("帳號: %s 已經登入\n", accountName);
					
					pc = PcInstance.load(accountName);
					pc.setName(account.getName());
					CursedWorld.getInstance().storeObject(pc);

					pc.setNetConnection(_client);
					_client.setActiveChar(pc);
				}catch (Exception e) {
					e.getStackTrace();
					return;
				}
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
				CharacterTable.saveCharStatus(pc);
				break;
			case C_KeyBoardWalk:
				CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_KeyBoardWalk), _client.getAccountName(), _client.getBr().readLine(),_client.getBr().readLine());
				break;
			case C_Party: //id request
				CursedWorld.getInstance().broadcastPacketToClient(_client.getAccountName(), Integer.toString(C_Party), _client.getBr().readLine(), _client.getBr().readLine());
                break;
			case C_PartyApply:
				String toId  = _client.getBr().readLine();
				msg = _client.getBr().readLine();
				CursedWorld.getInstance().broadcastPacketToClient(_client.getAccountName(), Integer.toString(C_PartyApply), toId, msg);
                break;
            case C_RequestInventory:
                break;
            case C_ChangeTexture:
                String type, index;
                type = _client.getBr().readLine();
                index = _client.getBr().readLine();	
                CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeTexture), _client.getAccountName(),type ,index);
                break;
            case C_ChangeWeapon:
            	type = _client.getBr().readLine();
                index = _client.getBr().readLine();
                CursedWorld.getInstance().broadcastPacketToAllClient(Integer.toString(C_ChangeWeapon), _client.getAccountName(),type ,index);
                break;
			case C_Logout:
				CursedWorld.getInstance().broadcastPacketToAllClient(_client.getAccountName(), Integer.toString(C_DeleteChar));
				System.out.println(_client.getAccountName()+" 離線");
				LoginController.getInstance().logout(_client);
				_client.quite();
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
