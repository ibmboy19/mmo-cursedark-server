package cursed.server.server.utils;

public class ItemEncoder {
	
	//Encoding Decimal to Hex
	public static String ToHex(int _dec){
		return Integer.toHexString(_dec);
	}
	//編碼物品
	public static String EncodingResourceItem(boolean flag,int id,String name,int type,int icon,int values,int weight,
			int dura,int maxdura,int req_lv,int req_class,
			int req_str,int req_con,int req_dex,int req_movp,int req_wis,int req_ws){
		String itemData = "";
		//name
		if(flag){
			itemData = name+";";
		}
	
		
		//encoding - 1
		
		itemData += MakesUpZero(ToHex(id),8);
		//System.out.println(itemData);
		//encoding - 2
		itemData +=MakesUpZero(ToHex(type),2);					// type len 2
		
		
		itemData +=MakesUpZero(ToHex(icon),3);					// icon len 3
		
		itemData +=MakesUpZero(ToHex(values),3);					// values len 3
		//System.out.println(itemData);
		//encoding - 2
		itemData +=MakesUpZero(ToHex(weight),2);				// weight len 2
		
		itemData +=MakesUpZero(ToHex(dura),3);					// duration len 3
		
		itemData +=MakesUpZero(ToHex(maxdura),3);			// max duration len 3
		//System.out.println(itemData);
		//encoding - 4
		itemData +=MakesUpZero(ToHex(req_lv),2);				// require character level  len 2
		
		itemData +=MakesUpZero(ToHex(req_class),2);		// require character class  len 2
		
		itemData +=MakesUpZero(ToHex(req_str),2);			// require character stregth  len 2
		
		itemData +=MakesUpZero(ToHex(req_con),2);			// require character con  len 2
		//System.out.println(itemData);
		//encoding - 5
		itemData +=MakesUpZero(ToHex(req_dex),2);				// require character level  len 2
		
		itemData +=MakesUpZero(ToHex(req_movp),2);		// require character class  len 2
		
		itemData +=MakesUpZero(ToHex(req_wis),2);			// require character stregth  len 2
		
		itemData +=MakesUpZero(ToHex(req_ws),2);			// require character con  len 2
		//System.out.println(itemData);
		//encoding - 6
		itemData += MakesUpZero(ToHex(0),8);//初始物品是白的
		//System.out.println(itemData);
		//encoding - 7		
		itemData += MakesUpZero(ToHex(0),8);//初始物品是白的
		//System.out.println(itemData);
		if(flag){
			itemData += "/";
		}
		return itemData;
	}
	//補零函式
	public static String MakesUpZero(String str, int lenSize) {
        String zero = "00000000";
        String returnValue = zero;
 
        returnValue = zero + str;
        
        return returnValue.substring(returnValue.length() - lenSize);
 
    }
	
}
