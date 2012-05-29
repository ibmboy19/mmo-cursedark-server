package cursed.server.server.clientpacket;

import java.io.IOException;
import java.net.SocketException;

import cursed.server.LoginController;
import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.database.CharacterTable;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;
import static cursed.server.server.clientpacket.ClientOpcodes.C_chat;
import static cursed.server.server.clientpacket.ClientOpcodes.C_login;
import static cursed.server.server.clientpacket.ClientOpcodes.C_logout;
import static cursed.server.server.clientpacket.ClientOpcodes.C_position;

public class ClientPacketHandler {
	private final ClientProcess _client;

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
						PcInstance pc = new PcInstance();
						pc.setAccountName(accountName);
						Ct.storeNewCharacter(pc);
				}
				if (!account.validatePassword(password)) {
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
					
					PcInstance pc = PcInstance.load(accountName);
					pc.setName(account.getName());
					CursedWorld.getInstance().storeObject(pc);

					pc.setNetConnection(_client);
					_client.setActiveChar(pc);
				}catch (Exception e) {
					e.getStackTrace();
					return;
				}
				break;
			case C_chat:
				String id = null;
				id  = _client.getBr().readLine();
				String d = _client.getBr().readLine();
				System.out.println(id+ ": " + d);
				
				CursedWorld.getInstance().broadcastPacketToAllClient(id, d);
				break;
			case C_position:
				String scine_id = _client.getBr().readLine();
				String char_id =  _client.getBr().readLine();
				String Location = _client.getBr().readLine();
				String State = _client.getBr().readLine();
				// TODO 儲存角色狀態到DB
				break;
			case C_logout:
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
