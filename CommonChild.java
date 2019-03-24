
//A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string. 
//Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both? 
//For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD. They can be formed by eliminating either 
//the D or C from both strings. Note that we will not consider ABCD as a common child because we can't rearrange characters and 
//ABCD != ABDC. 

package hackerrank;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;

public class CommonChild
{

    //===================================================================================
    public static void debug(String msg){
	System.out.println(msg);
    }

    //===================================================================================
    public static void findCommon(String str1, String str2){

	System.out.println("str="+str1+" str2="+str2);
	int i = 0;
	int k = 0;
	String intStr = "";
	String commonStr1 = "";
	String commonStr2 = "";

	List<Integer> lList = new ArrayList<Integer>();
	List<String> lstr = new ArrayList<String>();

	while (i < str1.length()){
		//debug(str1.substring(i,i+1)+" "+str2.indexOf(str1.substring(i,i+1)));		
		if (str2.indexOf(str1.substring(i,i+1))>-1){
			lList.add(str2.indexOf(str1.substring(i,i+1)));
			commonStr1 += str1.substring(i,i+1);
			lstr.add(str1.substring(i,i+1));
		}
		i++;
	}
	Collections.sort(lList);
	i = 0;
	while (i < lList.size()){
		commonStr2 += str2.substring(lList.get(i),lList.get(i)+1);		
		i++;
	}
	String tmpstr = "";
	int maxlen = 0;
	System.out.println("Common string1 = "+commonStr1+" Common string2 = "+commonStr2);
	if (commonStr1.equals(commonStr2)){
		tmpstr = commonStr1;
		maxlen = tmpstr.length();
	}else{
		i = 0;
		int j = 0;
		int pos = 0;
		while (i < commonStr2.length()){
			pos = commonStr2.indexOf(lstr.get(i));
			j = i + 1;
			tmpstr = lstr.get(i);
			while (j < commonStr2.length() && pos < commonStr2.indexOf(lstr.get(j))){
				pos = commonStr2.indexOf(lstr.get(j));
				tmpstr += lstr.get(j);
				j++;
			}
			//System.out.println("Common string = "+tmpstr+" len="+tmpstr.length());
			if (maxlen < tmpstr.length()) maxlen = tmpstr.length();
			i++;
		}
	}
	System.out.println("longest common string length = "+maxlen);
    }

    //===================================================================================
    static public void main(String[] args)
    {

	findCommon("HARRY","SALLY");
	System.out.println("===============================");

	findCommon("SHINCHAN","NOHARAAA");
	System.out.println("===============================");

	findCommon("ABCDEF","FBDAMN");
	System.out.println("===============================");
 
	findCommon("AA","BB");
	System.out.println("===============================");

   }
}
