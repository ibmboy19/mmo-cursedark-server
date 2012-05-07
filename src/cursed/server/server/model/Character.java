package cursed.server.server.model;

import cursed.server.server.utils.IntRange;

public class Character extends Object{
	private static final long serialVersionUID = 1L;
/////////---------HP  MP  Region-----------------------////////
	private int _level;
	public int getLevel(){
		return _level;
	}
	public void LevelUp(){
		_level++;
	}
	public boolean CalcIsLevelUp(int exp){
		if(exp>getMaxExp()){//若經驗值大於最大值
			LevelUp();			//升級
			return true;
		}
		return false;
	}
	private int _currentExp,_maxExp;
	public int getCurrentExp(){
		return _currentExp;
	}
	public int getMaxExp(){
		return _maxExp;
	}
	public void CalcMaxExp(){
		//Level Up & Calculate New MaxExp
	}
	public void AddExp(int exp){
		
		int levelUpCount = 0;//Assign exp所升級的次數
	
		int buffExp = getCurrentExp()+exp;	//暫存的exp
		//升級的迴圈
		while(CalcIsLevelUp(buffExp)){//計算是否升級
			buffExp-=getMaxExp();		//扣除經驗
			CalcMaxExp();//計算升級後的所需經驗
		}
		_currentExp = buffExp;	
	}
/////////---------HP  MP  Region-----------------------////////
	private int _currentHp;
	public int getCurrentHp() {
		return _currentHp;
	}
	public void setCurrentHp(int i) {
		_currentHp = i;
		if (_currentHp >= getMaxHp()) {
			_currentHp = getMaxHp();
		}
	}
	private int _currentMp;

	public int getCurrentMp() {
		return _currentMp;
	}

	public void setCurrentMp(int i) {
		_currentMp = i;
		if (_currentMp >= getMaxMp()) {
			_currentMp = getMaxMp();
		}
	}
	
	private short _maxHp = 0; 

	private int _trueMaxHp = 0; 

	public short getMaxHp() {
		return _maxHp;
	}

	public void setMaxHp(int hp) {
		_trueMaxHp = hp;
		_maxHp = (short) IntRange.ensure(_trueMaxHp, 1, 32767);
		_currentHp = Math.min(_currentHp, _maxHp);
	}

	public void addMaxHp(int i) {
		setMaxHp(_trueMaxHp + i);
	}

	private short _maxMp = 0;

	private int _trueMaxMp = 0;

	public short getMaxMp() {
		return _maxMp;
	}

	public void setMaxMp(int mp) {
		_trueMaxMp = mp;
		_maxMp = (short) IntRange.ensure(_trueMaxMp, 0, 32767);
		_currentMp = Math.min(_currentMp, _maxMp);
	}

	public void addMaxMp(int i) {
		setMaxMp(_trueMaxMp + i);
	}
	/////////---------Ability Main Region-----------------------////////
	/*
	 * Con體質
	 * Str力量 
	 * Dex敏捷
	 * Wis智慧
	 */
	private int _con;
	public int getCon(){
		return _con;
	}
	public void setCon(int str){
		_con = str;
	}
	
	private int _str;
	public int getStr(){
		return _str;
	}
	public void setStr(int str){
		_str = str;
	}
	
	private int _dex;
	public int getDex(){
		return _dex;
	}
	public void setDex(int str){
		_dex = str;
	}
	
	private int _wis;
	public int getWis(){
		return _wis;
	}
	public void setWis(int str){
		_wis = str;
	}
	/////////---------Ability Extras  Region-----------------------////////
	private int _atk;
	public int getAtk(){
		return _atk;
	}
	public void setAtk(){
		CalcAtk();
	}
	/////////---------Calculate  Region-----------------------////////
	//使用於角色能力變化Assign後
	public void CalcAllAttribite(){
		
	}
	public void CalcMaxHp(){
		
	}
	public void CalcMaxMp(){
		
	}
	public void CalcAtk(){
	}
	public void CalcMoveSpd(){
		
	}
}
