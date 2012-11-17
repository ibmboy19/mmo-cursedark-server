package cursed.server.server.model;

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
	//class
	private CharacterClass _characterClass;
	public CharacterClass getCharacterClass(){
		return _characterClass;
	}
	
	//character color
	private float _colorR,_colorG,_colorB;
	public float getColorR(){return _colorR;}
	public float getColorG(){return _colorG;}
	public float getColorB(){return _colorB;}
	//level exp...
	private int _level;
	public synchronized int getLevel() {
		return _level;
	}
	public void LevelUp(){
		_level++;
	}
	public synchronized void setLevel(int level) {
		_level =  level;
	}
	public boolean CalcIsLevelUp(int exp){
		if(exp>getMaxExp()){//若經驗值大於最大值
			LevelUp();			//升級			
			return true;
		}
		return false;
	}
	private int _currentExp,_maxExp;
	public int get_currentExp() {
		return _currentExp;
	}
	public void setCurrentExp(int _currentExp) {
		this._currentExp = _currentExp;
	}
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
	
		int buffExp = getCurrentExp()+exp;	//暫存的exp
		//升級的迴圈
		while(CalcIsLevelUp(buffExp)){//計算是否升級
			buffExp-=getMaxExp();		//扣除經驗
			CalcMaxExp();//計算升級後的所需最大經驗值
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
		}
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
	public int geMtDef(){
		return _mdef;
	}
	public void setMDef(int value){
		_mdef = value;
	}
	public void SetAllData(String _id,String _str,String _con,String _dex,String _luck,String _wis,
			String _ws,String _colorR,String _colorG,String _colorB){
		this._charID = _id;
		this._str = Integer.valueOf(_str);
		this._con = Integer.valueOf(_con);
		this._dex = Integer.valueOf(_dex);
		this._luck = Integer.valueOf(_luck);
		this._wis = Integer.valueOf(_wis);
		this._ws = Integer.valueOf(_ws);
		this._colorR = Float.valueOf(_colorR);
		this._colorG = Float.valueOf(_colorG);
		this._colorB = Float.valueOf(_colorB);
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
		setMaxHp(Calculator.CalcCharHP());
	}
	public void CalcMaxMp(){
		setMaxMp(Calculator.CalcCharMP());
	}
	public void CalcAtk(){
		setAtk(Calculator.CalcCharAtkValue());
	}
	public void CalcDef(){
		setDef(Calculator.CalcCharDefValue());
	}
	public void CalcMAtk(){
		setMAtk(Calculator.CalcCharMAtkValue());
	}
	public void CalcMDef(){
		setMDef(Calculator.CalcCharMDefValue());
	}
	
}
