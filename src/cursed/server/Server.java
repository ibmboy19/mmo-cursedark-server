package cursed.server;

import java.io.File;
import java.util.logging.Logger;

import cursed.server.server.GameServer;


/**
 * 伺服器啟動
 */
public class Server {
	/** 紀錄用 */
	private static Logger _log = Logger.getLogger(Server.class.getName());

	/* 變數 */
	/** 資料庫驅動*/
	private static String DB_DRIVER = "com.mysql.jdbc.Driver"; 
	/** 資料庫位址*/
	private static String DB_URL = "jdbc:mysql://127.0.0.1/cursedark?useUnicode=true&characterEncoding=utf8";
	/** 資料庫帳號*/
	private static String DB_LOGIN = "root";
	/** 資料庫密碼*/
	private static String DB_PASSWORD = "root";

	public static void main(String[] args) throws Exception {
		File logFolder = new File("log");
		//logFolder.mkdir();

		// DatabaseFactory初期設定
		DatabaseFactory.setDatabaseSettings(DB_DRIVER, DB_URL, DB_LOGIN,DB_PASSWORD);
		DatabaseFactory.getInstance();

		/** 初始化並啟動Game Server */
		GameServer.getInstance().initialize();

	}
}