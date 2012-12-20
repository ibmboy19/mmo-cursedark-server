package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Login;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PacketSymbol;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import cursed.server.LoginController;
import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.model.CursedWorld;

/**
 * 處理C_Login封包
 */
public class C_Login {
	public C_Login(ClientProcess _client,String packet) throws IOException, NoSuchAlgorithmException{
		String accountName = packet.split(C_PacketSymbol)[1];//id
		String password = packet.split(C_PacketSymbol)[2];//pw
		String ip = _client.get_ip();
		Account account = Account.load(accountName);
		
		if (account == null) {
			account = Account.create(accountName, password, ip);
		} else {
			// 帳號存在
		}
		if (!account.validatePassword(password)) {
			_client.getWr().println(C_Login+C_PacketSymbol+"false");
			return;
		}
		try {
			
			LoginController.getInstance().login(_client, account);
			//Account.updateLastActive(account, ip); // 更新最後一次登入的時間與IP
			_client.setAccount(account);

			
			_client.getWr().println(C_Login+C_PacketSymbol+"true"+C_PacketSymbol+"5");
			System.out.format("帳號: %s 已經登入\n", accountName);
			
		} catch (Exception e) {
			e.getStackTrace();
			return;
		}
	}
}
