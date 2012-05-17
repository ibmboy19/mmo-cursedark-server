package cursed.server.server;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cursed.server.DatabaseFactory;
import cursed.server.server.utils.SQLUtil;


public class Account {
	/** 使用者帳號名稱 */
	private String _name;

	/** 來源IP位址 */
	private String _ip;

	/** 密碼 */
	private String _password;

	/** 紀錄用 */
	private static Logger _log = Logger.getLogger(Account.class.getName());

	/** 帳戶是否有效 (True 代表有效). */
	private boolean _isValid = false;

	/**
	 * 建構式
	 */
	private Account() {
	}

	/**
	 * 建立新帳號
	 * 
	 * @param name
	 * @param rawPassword
	 * @param ip
	 * @param host
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static Account create(final String name, final String rawPassword, final String ip)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			Account account = new Account();
			account._name = name;
			account._password = rawPassword;
			account._ip = ip;
			con = DatabaseFactory.getInstance().getConnection();
			String sqlstr = "INSERT INTO account SET account_id=?,password=?,host=?";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, account._name);
			pstm.setString(2, account._password);
			pstm.setString(3, account._ip);
			pstm.execute();
			_log.info("created new account for " + name);
			return account;
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}
		return null;
	}
	
	/**
	 * 從資料庫中取得指定帳號的資料
	 * 
	 * @param name
	 *            帳號名稱
	 * @return Account
	 */
	public static Account load(final String name) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		Account account = null;
		try {
			con = DatabaseFactory.getInstance().getConnection();
			String sqlstr = "SELECT * FROM account WHERE account_id=? LIMIT 1";
			pstm = con.prepareStatement(sqlstr);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if (!rs.next()) {
				return null;
			}
			account = new Account();
			account._name = rs.getString("account_id");
			account._password = rs.getString("password");
			//account._lastActive = rs.getTimestamp("lastactive");
			//account._accessLevel = rs.getInt("access_level");
			account._ip = rs.getString("host");

			_log.fine("account exists");
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SQLUtil.close(rs);
			SQLUtil.close(pstm);
			SQLUtil.close(con);
		}

		return account;
	}


	/**
	 * 驗證密碼
	 * 
	 * @param rawPassword
	 * @return
	 */
	public boolean validatePassword(final String rawPassword) {
		// 認證成功後如果再度認證就判斷為失敗
		if (_isValid) {
			return false;
		}
		try {
			_isValid = _password.equals(rawPassword);
			if (_isValid) {
				_password = null; // 認證成功後就將記憶體中的密碼清除
			}
			return _isValid;
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return false;
	}

	/**
	 * 取得帳號名稱
	 * 
	 * @return String
	 */
	public String getName() {
		return _name;
	}

	/**
	 * 取得連線時的 IP
	 * 
	 * @return String
	 */
	public String getIp() {
		return _ip;
	}

	/**
	 * 取得帳號使否有效 (True 為有效).
	 * 
	 * @return boolean
	 */
	public boolean isValid() {
		return _isValid;
	}

}
