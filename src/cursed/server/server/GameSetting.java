package cursed.server.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameSetting {

	private String levelSetting = null;
	private static GameSetting _instance;

	/** 緩衝讀取 */
	private static BufferedReader buf;

	/** announcementsCycle文件的位置 */
	private static File dir = new File("data/Level.txt");

	
	public static GameSetting getInstance() {
		if (_instance == null) {
			_instance = new GameSetting();
		}
		_instance.scanfile();
		return _instance;
	}
	public String getMaxExp(int lv){
		return levelSetting.split("\n")[lv].split("\t")[1];
	}
	/**
	 * 從announcementsCycle.txt將字串讀入
	 */
	private void scanfile() {
		try {
			fileEnsure(); // 先確保檔案存在			
				String buffStr;
				levelSetting = "";
				buf = new BufferedReader(new InputStreamReader(new FileInputStream(dir)));
				while ((buffStr = buf.readLine()) != null) {
					
					levelSetting += buffStr+"\n";
				
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buf.close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 確保announcementsCycle.txt存在
	 * 
	 * @throws IOException
	 *             產生檔案錯誤
	 */
	private void fileEnsure() throws IOException {
		if (!dir.exists())
			dir.createNewFile();
	}

	

	
}
