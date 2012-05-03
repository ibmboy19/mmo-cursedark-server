package cursed.server.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 遊戲伺服器
 */
public class GameServer extends Thread {
	private static GameServer _instance;
	private ServerSocket _serverSocket;
	private int _port = 1200; // 端口

	public static GameServer getInstance() {
		if (_instance == null) {
			_instance = new GameServer();
		}
		return _instance;
	}
	
	public GameServer(){
		super("GameServer");
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("等待客戶端連接...");
			try {
				Socket socket = _serverSocket.accept();
				String host = socket.getInetAddress().getHostAddress();
				if (host == "禁止的IP") { // 不合法的連線

				} else {
					// 合法連線
					// 載入
					ClientProcess client = new ClientProcess(socket);
					GeneralThreadPool.getInstance().execute(client);
				}
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}

	}

	/**
	 * 初始化
	 */
	public void initialize() throws Exception {
		/** 建立Server連線 */
		_serverSocket = new ServerSocket(_port);
		System.out.println("Server已被建立在端口: " + _port);
		System.out.println("┌────────────────┐");
		System.out.println("│     " + "Cursed Ark Server" + "\t" + "│");
		System.out.println("└────────────────┘" + "\n");
		System.out.println();
		System.out.println("Server初始化完成。");
		this.start();
	}

}