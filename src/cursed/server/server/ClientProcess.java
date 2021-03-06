package cursed.server.server;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import cursed.server.server.clientpacket.ClientPacketHandler;
import cursed.server.server.model.CursedWorld;
import cursed.server.server.model.instance.PcInstance;
import cursed.server.server.utils.StreamUtil;

public class ClientProcess implements Runnable {
	private static Logger _log = Logger
			.getLogger(ClientProcess.class.getName());

	private InputStream _in;

	private OutputStream _out;

	private Account _account;

	private PcInstance _activeChar;

	private ClientPacketHandler _handler;

	private BufferedReader br;

	private PrintWriter wr;

	private String _ip;

	private String _hostname;

	private Socket _csocket;

	// private int _loginStatus = 0;

	protected ClientProcess() {
	}

	public ClientProcess(Socket socket) {
		_csocket = socket;
		_ip = socket.getInetAddress().getHostAddress();
		_hostname = _ip;
		try {
			_in = _csocket.getInputStream();
			_out = _csocket.getOutputStream();
			// 輸出入串流
			br = new BufferedReader(new InputStreamReader(_in, "Unicode"));
			wr = new PrintWriter(new OutputStreamWriter(_out, "Unicode"), true);
			
		} catch (Exception e) {
			System.out.println(e);
		}

		// PacketHandler 初始化
		_handler = new ClientPacketHandler(this);
	}

	@Override
	public void run() {
		_log.info("(" + _hostname + ") 連結到伺服器。");
		System.out.println("等待客戶端連接...");

		// 背景封包作業
		while (!_csocket.isClosed()) {
			int op = 0;
			String packet = "";
			try {
				packet = br.readLine();
				op = Integer.valueOf(packet.split("\t")[0]);
				//System.out.println("accept packet "+packet);
			} catch (NumberFormatException ne) {
				continue;
			} catch (IOException e) {
				quite();
				e.printStackTrace();
			}
			_handler.handlePacket(op,packet);

		}
		quite();

	}

	/**
	 * 處理玩家斷線
	 */
	public void quite() {
		//CursedWorld.getInstance().RemovePlayer(_activeChar);
		StreamUtil.close(_in, _out);
		try {
			_csocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			_csocket = null;
		}
	}

	public void setAccount(Account account) {
		_account = account;
	}

	public Account getAccount() {
		return _account;
	}

	public String getAccountName() {
		if (_account == null) {
			return null;
		}
		return _account.getName();
	}

	public void close() throws IOException {
		_csocket.close();
	}

	public Socket get_csocket() {
		return _csocket;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public PrintWriter getWr() {
		return wr;
	}

	public void setWr(PrintWriter wr) {
		this.wr = wr;
	}

	public String get_ip() {
		return _ip;
	}

	public void set_ip(String _ip) {
		this._ip = _ip;
	}

	public void setActiveChar(PcInstance pc) {
		_activeChar = pc;
	}

	public PcInstance getActiveChar() {
		return _activeChar;
	}

}
