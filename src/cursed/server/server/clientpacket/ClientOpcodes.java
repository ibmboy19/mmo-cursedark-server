package cursed.server.server.clientpacket;

public class ClientOpcodes {

	public static final String C_PacketSymbol = "\t";
	public static final int C_Login = 1; // 角色登入 回傳true/false
	public static final int C_CreateCharacter = 2; // 創造角色
	public static final int C_RequestCreateCharacter = 3; // 創造角色(檢查ID)
	public static final int C_Chat = 4; // 玩家聊天
	public static final int C_RequestCharacterInfo = 5; // 要求其他玩家資訊
	public static final int C_KeyBoardWalk = 6; // 玩家動作
	public static final int C_Party = 7; // 組隊要求
	public static final int C_PartyApply = 8; // 回應組隊
	public static final int C_NewItem = 9; // 載入物品
	public static final int C_RequestInventory = 10; // 要求角色物品清單
	public static final int C_ChangeTexture = 11; // 角色換裝(貼圖)
	public static final int C_ChangeModel = 12; // 角色換裝(模型)
	public static final int C_CastSkill = 13; // 使用技能
	public static final int C_RequestCharacterList = 15; // 載入角色列表(依帳號)
	public static final int C_RequestCharacterLogin = 16; // 載入角色(進遊戲場景)
	public static final int C_Attack = 20;// 攻擊
	public static final int C_CharacterItem = 21;//腳色物品或裝備
	public static final int C_Logout = 999; // 角色登出

}
