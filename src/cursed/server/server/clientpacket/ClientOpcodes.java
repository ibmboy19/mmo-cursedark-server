package cursed.server.server.clientpacket;

public class ClientOpcodes {

	public static final int C_login = 0; 						// "ID,PW" , //回傳true or false

	public static final int C_char_create = 1;					// 角色創造

	public static final int C_scene_switch = 2;
	public static final int C_char_spawn = 3;
	public static final int C_char_state = 4;
	public static final int C_char_ablitity = 5;
	public static final int C_position = 6; 					// "(x,y,z)"
	public static final int C_char_useskill = 7;
	public static final int C_npc_spawn = 8;
	public static final int C_npc_monster_spawn = 9;
	public static final int C_npc_monster_action = 10;
	
	public static final int C_char_loadInventory = 30;//讀取角色背包(from server)
	public static final int C_char_saveInventory = 31;//寫入角色背包(write back in server)

	public static final int C_chat = 128;
	public static final int C_chat_channel_global = 0;			// 伺服器頻道
	public static final int C_chat_channel_local = 1;			// 區域頻道
	public static final int C_chat_channel_area = 2;			// 一般頻道(角色周圍)
	public static final int C_chat_channel_guild = 3;			// 公會頻道
	public static final int C_chat_channel_team = 4;			// 隊伍頻道
	public static final int C_chat_channel_msg = 5;				// 密語頻道

	public static final int C_char_team_request = 200;
	public static final int C_char_trade_request = 300;
	public static final int C_char_create_name_check = 900;		// 比對ID是否可使用
	public static final int C_regist_account = 1000; 			// 回傳true or false
	public static final int C_logout = 1023;

}
