package cursed.server.server.clientpacket;

public class ClientOpcodes {

	public static final int C_login = 1; 										// send op, id, pw
	public static final int C_CreateCharacter = 2;					//send op, id , str,con,dex,luck,wis,ws,clr_r,clr_g,clr_b
	public static final int C_Chat = 4;										// send op, msg
	public static final int C_Walk = 5;										// send op, state, pos
	public static final int C_KeyBoardWalk = 6;						// send op, direction
	public static final int C_Party = 7;										// send op, target, id
	public static final int C_PartyApply = 8;							// send op, id , aggrement?
	public static final int C_NewItem = 9;			          			 // recevie op,  used for server cmd
	public static final int C_RequestInventory = 10;				// send op ;and server will return all character Inventory that player has
	public static final int C_ChangeTexture = 11;					// send op, armor type, armor index;	change cloth texture
	public static final int C_ChangeModel = 12;				// send op, weapon type, weapon index ;change weapon model
	public static final int C_Logout = 999;								// send op

}
