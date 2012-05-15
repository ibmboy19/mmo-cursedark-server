package cursed.server;

import java.util.Map;

import cursed.server.server.Account;
import cursed.server.server.ClientProcess;
import cursed.server.server.utils.collections.Maps;

public class LoginController {
	private static LoginController _instance;

	private Map<String, ClientProcess> _accounts = Maps.newConcurrentMap();

	//private int _maxAllowedOnlinePlayers = 200;

	private LoginController() {
	}

	public static LoginController getInstance() {
		if (_instance == null) {
			_instance = new LoginController();
		}
		return _instance;
	}
	public ClientProcess[] getAllAccounts() {
		return _accounts.values().toArray(new ClientProcess[_accounts.size()]);
	}

	public int getOnlinePlayerCount() {
		return _accounts.size();
	}
	
	private void kickClient(final ClientProcess client) {
		if (client == null) {
			return;
		}
	}
	
	public synchronized void login(ClientProcess client, Account account){
		if (!account.isValid()) {
			// 密碼驗證未指定或不驗證帳戶。
			throw new IllegalArgumentException("帳戶沒有通過認證");
		}
		if (_accounts.containsKey(account.getName())) {
			kickClient(_accounts.remove(account.getName()));
		}

		_accounts.put(account.getName(), client);
	}
	
	public synchronized boolean logout(ClientProcess client) {
		if (client.getAccountName() == null) {
			return false;
		}
		return _accounts.remove(client.getAccountName()) != null;
	}
}
