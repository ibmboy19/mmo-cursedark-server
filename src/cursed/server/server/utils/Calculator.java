package cursed.server.server.utils;

import cursed.server.server.model.CharacterObject;

public class Calculator {

	public static int CalcCharHP(CharacterObject co){
		return co.getLevel()*2+co.getStr()*3+co.getCon()*8+53;
	}
	public static int CalcCharMP(CharacterObject co){
		return (int) (co.getLevel()*.5f+co.getWis()*6+co.getWs()*1.5f+53);
	}
	public static int CalcCharAtkValue(CharacterObject co){
		return (int) (co.getLevel()*1.6f+co.getStr()*1.5f+co.getDex()*1.2f+6);
	}
	public static int CalcCharDefValue(CharacterObject co){
		return (int) (co.getLevel()*.2f+co.getCon()*.4f+co.getDex()*.4f+co.getLuck()*.1f+1);
	}
	public static int CalcCharMAtkValue(CharacterObject co){
		return (int) (co.getLevel()*1.8f+co.getWis()*2.6f+co.getWs()*2f+5);
	}
	public static int CalcCharMDefValue(CharacterObject co){
		return (int) (co.getLevel()*.6f+co.getWs()*1.6f+co.getLuck()*.8f+2);
	}
	
}
