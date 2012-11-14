package cursed.server.server.clientpacket;

public class ClientOpcodes {

	public static final int C_Login = 1; 										//client	傳 op id pw 驗證帳號密碼
	public static final int C_CreateCharacter = 2;					//client  傳 op, id , str,con,dex,luck,wis,ws,clr_r,clr_g,clr_b 新增一筆角色資料
	public static final int C_Chat = 4;										//client  傳op msg
	public static final int C_Walk = 5;										//client   傳op, pos
	public static final int C_KeyBoardWalk = 6;						//client   傳op,direction,look-direction,position
	public static final int C_Party = 7;										//client   傳 op,id,request 
	public static final int C_PartyApply = 8;							//client   傳 op, id , aggrement?
	public static final int C_NewItem = 9;			          			 //client   傳 op , itemData
	public static final int C_RequestInventory = 10;				// client  傳 op ;and server will return all character Inventory that player has
	public static final int C_ChangeTexture = 11;					// client  傳 op, armor type, armor index;	change cloth texture
	public static final int C_ChangeModel = 12;					//client   傳 op, weapon type, weapon index ;change weapon model
	public static final int C_RequestCharacterList = 15;		//client  傳 op
	public static final int C_Logout = 999;								// client  傳 op

}
