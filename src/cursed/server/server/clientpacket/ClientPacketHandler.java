package cursed.server.server.clientpacket;

import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeModel;
import static cursed.server.server.clientpacket.ClientOpcodes.C_ChangeTexture;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Chat;
import static cursed.server.server.clientpacket.ClientOpcodes.C_CreateCharacter;
import static cursed.server.server.clientpacket.ClientOpcodes.C_KeyBoardWalk;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterInfo;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Login;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Logout;
import static cursed.server.server.clientpacket.ClientOpcodes.C_Party;
import static cursed.server.server.clientpacket.ClientOpcodes.C_PartyApply;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterList;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCharacterLogin;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestCreateCharacter;
import static cursed.server.server.clientpacket.ClientOpcodes.C_RequestInventory;

import java.io.IOException;
import java.net.SocketException;

import cursed.server.LoginController;
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
				new C_Login(_client);
				break;
			case C_CreateCharacter:
				new C_CreateCharacter(_client);
				break;
			case C_RequestCreateCharacter:
				new C_RequestCreateCharacter(_client);
				break;
			case C_RequestCharacterList: // op count (id class lv guild)
				new C_RequestCharacterList(_client);
				break;
			case C_RequestCharacterInfo:
				/**client 要求角色資訊**/
				
				break;
			case C_RequestCharacterLogin:				
				new C_RequestCharacterLogin(_client);
				break;
			case C_Chat:				
				new C_Chat(_client);
				break;			
			case C_KeyBoardWalk:// op,id,direction,look-direction,position
				new C_KeyBoardWalk(_client);
				break;
			case C_Party: // id request
				new C_Party(_client);
				break;
			case C_PartyApply:	
				new C_PartyApply(_client);
				break;
			case C_RequestInventory:
				break;
			case C_ChangeTexture:
				new C_ChangeTexture(_client);
				break;
			case C_ChangeModel:
				new C_ChangeModel(_client);
				break;
			case C_Logout:
				new C_Logout(_client);
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
