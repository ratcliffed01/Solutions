package FDGSUK;

import java.util.*;

public class Word {

	private HashMap<String,Integer> val = new HashMap<String,Integer>();

    	public boolean contains(String value) {
		return val.containsKey(value);
    	}

    	public void addWord(String value) {
		val.put(value,1);
    	}

    	public void updateCount(String value) {
		val.put(value, val.get(value) + 1);
    	}

	public String removePuncts(String str){
		String allPuncts = ",.;:?";
	    	if (allPuncts.indexOf(str.substring(str.length()-1)) >  -1) str = str.substring(0,str.length()-1);
		return str;
	}

    	public String[] displayList() {
		String[] x = new String[val.size()];
		int i = 0;
        	for (String key : val.keySet()){ 
			x[i] = key + " occurred " + val.get(key) + " times";
			i++;
		}
		return x;
    	}
}
