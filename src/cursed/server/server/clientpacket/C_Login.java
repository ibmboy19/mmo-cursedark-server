package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_Login;

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
	public C_Login(ClientProcess _client) throws IOException, NoSuchAlgorithmException{
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

			
			_client.getWr().println(C_Login);
			_client.getWr().println("true");
			System.out.format("帳號: %s 已經登入\n", accountName);
			
		} catch (Exception e) {
			e.getStackTrace();
			return;
		}
	}
}
