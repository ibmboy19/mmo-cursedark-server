package cursed.server.server.model;

import cursed.server.server.GameSetting;
import cursed.server.server.utils.Calculator;
//原Character，與java的Character衝突所以Rename
public class CharacterObject extends Object{
	private static final long serialVersionUID = 1L;
	
	public CharacterObject(){
		
	}

	
	private String _charID; 

	public String getCharID() {
		return _charID;
	}
	public void setCharID(String _charID) {
		this._charID = _charID;
	}
	//inventory
	private String _inventory;
	public String getInventory(){
		return _inventory;
	}
	public void setInventory(String _inventory){
		this._inventory = _inventory;
	}
	//equipment slot
	private String _equipSlot;
	public String getEquipSlot(){
		return _equipSlot;
	}
	public void setEquipSlot(String _equipSlot){
		this._equipSlot = _equipSlot;
	}
	//Inventory Short
		private String _invenShort;
		public String getInvenShort(){
			return _invenShort;
		}
	public void setInvenShort(String _invenShort){
		this._invenShort = _invenShort;
	}
	
	//class
	private int _characterClass;
	public int getCharacterClass(){
		return _characterClass;
	}
	public void setCharacterClass(int index){
		_characterClass = index;
	}
	
	//alive?
	private boolean isAlive;
	public boolean IsAlive(){return isAlive;}
	
	//character color
	private float _colorR,_colorG,_colorB;
	public float getColorR(){return _colorR;}
	public void setColorR(float _colorR){this._colorR = _colorR;}
	public float getColorG(){return _colorG;}
	public void setColorG(float _colorG){this._colorG = _colorG;}
	public float getColorB(){return _colorB;}
	public void setColorB(float _colorB){this._colorB = _colorB;}
	//level exp...
	private int _level;
	public synchronized int getLevel() {
		return _level;
	}
	public void LevelUp(){
		_level++;
		_remain += _level/5+5;
		setMaxExp(Integer.valueOf(GameSetting.getInstance().getMaxExp(_level)));
		CalcAllAttribite();
	}
	public synchronized void setLevel(int level) {
		_level =  level;
		setMaxExp(Integer.valueOf(GameSetting.getInstance().getMaxExp(_level)));
	}
	public boolean CalcIsLevelUp(int exp){
		if(exp>getMaxExp()){//若經驗值大於最大值
					
			return true;
		}
		return false;
	}
	private int _currentExp,_maxExp;
	public int getCurrentExp(){
		return _currentExp;
	}
	public void setCurrentExp(int _currentExp) {
		this._currentExp = _currentExp;
	}
	
	public int getMaxExp(){
		return _maxExp;
	}
	public void setMaxExp(int _maxExp){
		this._maxExp = _maxExp;
	}
	
	public void AddExp(int exp){
	
		int buffExp = getCurrentExp()+exp;	//暫存的exp
		//升級的迴圈
		while(CalcIsLevelUp(buffExp)){//計算是否升級
			buffExp-=getMaxExp();		//扣除經驗
			LevelUp();			//升級	
		}
		_currentExp = buffExp;	
	}
	/////////---------HP  MP  Region----------------------////////
	//HP
	private int _currentHp;
	public int getCurrentHp() {
		return _currentHp;
	}
	private int _maxHp = 0; 
	public int getMaxHp() {
		return _maxHp;
	}
	
	
	
	public void setCurrentHp(int i) {
		_currentHp = i;
		if (_currentHp >= getMaxHp()) {
			_currentHp = getMaxHp();
			isAlive = true;
		}else if(_currentHp<0){
			_currentHp = 0;
			isAlive = false; 
		}
	}
	public void adjustCurrentHp(int i) {
		_currentHp = _currentHp-i;
		if (_currentHp >= getMaxHp()) {
			_currentHp = getMaxHp();
			isAlive = true;
		}else if(_currentHp<0){
			_currentHp = 0;
			isAlive = false;
		}
		System.out.println(_currentHp);
	}
	
	public void loadCurrentHp(int i) {
		_currentHp = i;
		if(_currentHp>0){
			isAlive = true;
		}else {
			isAlive = false;
		}
	}
	
	public void setMaxHp(int hp) {
		_maxHp =  hp;
		_currentHp = Math.min(_currentHp, _maxHp);
	}
	
	
	//MP
	private int _currentMp;
	public int getCurrentMp() {
		return _currentMp;
	}
	private int _maxMp = 0;


	public int getMaxMp() {
		return _maxMp;
	}
	
	public void setCurrentMp(int i) {
		_currentMp = i;
		if (_currentMp >= getMaxMp()) {
			_currentMp = getMaxMp();
		}else if(_currentMp<0){
			_currentMp = 0;
		}
	}
	public void adjustCurrentMp(int i) {
		_currentMp = _currentHp-i;
		if (_currentMp >= getMaxHp()) {
			_currentMp = getMaxHp();
		}else if(_currentMp<0){
			_currentMp = 0;
		}
	}
	
	public void loadCurrentMp(int i) {
		_currentMp  = i;
	}

	public void setMaxMp(int mp) {
		_maxMp =  mp;
		_currentMp = Math.min(_currentMp, _maxMp);
	}

	
	/////////---------HP  MP  Region End------------------////////
	
	
	/////////---------Ability Main Region-----------------------////////
	/*
	 * Str力量 
	 * Con體質
	 * Dex敏捷
	 * Luck運氣
	 * Wis智慧
	 * Ws魔法能量
	 * Remain剩餘點數
	 */
	
	
	private int _str;
	public int getStr(){
		return _str;
	}
	public void setStr(int _str){
		this._str = _str;
	}
	
	
	private int _con;
	public int getCon(){
		return _con;
	}
	public void setCon(int _con){
		this._con = _con;
	}
	
	private int _dex;
	public int getDex(){
		return _dex;
	}
	public void setDex(int _dex){
		this._dex = _dex;
	}
	
	private int _luck;
	public int getLuck(){
		return _luck;
	}
	public void setLuck(int _luck){
		this._luck = _luck;
	}
	
	private int _wis;
	public int getWis(){
		return _wis;
	}
	public void setWis(int _wis){
		this._wis = _wis;
	}
	
	private int _ws;
	public int getWs(){
		return _ws;
	}
	public void setWs(int _ws){
		this._ws = _ws;
	}
	
	private int _remain;
	public int getRemain(){
		return _remain;
	}
	public void setRemain(int _remain){
		this._remain = _remain;
	}
	/////////---------Ability Extras  Region-----------------------////////
	private int _atk;
	public int getAtk(){
		return _atk;
	}
	public void setAtk(int value){
		_atk = value;
	}
	
	private int _def;
	public int getDef(){
		return _def;
	}
	public void setDef(int value){
		_def = value;
	}
	private int _matk;
	public int getMAtk(){
		return _matk;
	}
	public void setMAtk(int value){
		_matk = value;
	}
	
	private int _mdef;
	public int getMDef(){
		return _mdef;
	}
	public void setMDef(int value){
		_mdef = value;
	}


	/////////---------Calculate  Region-----------------------////////
	//使用於角色能力變化Assign後
	public void CalcAllAttribite(){
		CalcMaxHp();
		CalcMaxMp();
		CalcAtk();
		CalcDef();
		CalcMAtk();
		CalcMDef();
	}
	public void CalcMaxHp(){
		setMaxHp(Calculator.CalcCharHP(this));
	}
	public void CalcMaxMp(){
		setMaxMp(Calculator.CalcCharMP(this));
	}
	public void CalcAtk(){
		setAtk(Calculator.CalcCharAtkValue(this));
	}
	public void CalcDef(){
		setDef(Calculator.CalcCharDefValue(this));
	}
	public void CalcMAtk(){
		setMAtk(Calculator.CalcCharMAtkValue(this));
	}
	public void CalcMDef(){
		setMDef(Calculator.CalcCharMDefValue(this));
	}
	
}
