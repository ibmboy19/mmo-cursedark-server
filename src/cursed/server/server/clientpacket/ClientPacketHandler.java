package cursed.server.server.clientpacket;

import java.net.SocketException;

import cursed.server.server.ClientProcess;
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
				String d = _client.getBr().readLine();
				System.out.println("帳密:" + d);
				break;
			case C_chat:
				String id = null;
				id  = _client.getBr().readLine();
				d = _client.getBr().readLine();
				System.out.println(id+ ": " + d);
				// To Client
				_client.getWr().println("128");
				_client.getWr().println(id);
				_client.getWr().println(d);
				break;
			case C_position:
				d = _client.getBr().readLine();
				System.out.println("座標" + d);
				break;
			case C_logout:
				System.out.println(_client.getAccountName()+" 離線");
				_client.quite();
				break;

			}
		} catch (NumberFormatException nf) {
			System.out.println("接收到一個null.");
		} catch (SocketException se) {
			System.out.println("登出...");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
