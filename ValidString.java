// to compile do from folder above c:\>javac BJSS_TEST\PricingBasket.java
// to compile do from folder above c:\>java BJSS_TEST.PricingBasket SOUP

//Sherlock considers a string to be valid if all characters of the string appear the same number of times. 
//It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters will 
//occur the same number of times. Given a string s, determine if it is valid. If so, return YES, otherwise return NO.
//For example, if s - abc, it is a valid string because frequencies are {a:1, b:1, c:1}. So is s = abcc because we can remove one 
//c and have 1 of each character in the remaining string. If s = abccc however, the string is not valid as we can only remove 1
//occurrence of c. That would leave character frequencies of {a:1, b:1, c:2}.
 
package hackerrank;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;

public class ValidString
{

    //===================================================================================
    public static void debug(String msg){
	System.out.println(msg);
    }

    //====================================================
    static int freqCnt(int cnt, String str) {

	int i = 0;
	String cntx = Integer.toString(cnt);
	int num = 0;
	while (i < str.length()){
		if (cntx.equals(str.substring(i,i+1))) num++;
		i++;
	}
	return num;
    }

    //====================================================
    static String findCharFrequency(String str) {

	System.out.println("String - "+str);

	int i = 0;
	int j = 0;
	int cnt  = 0;
	int num = 0;
	String tmpStr = "";
	String tmpStri = "|";
	String oneChar = "";
	List<Integer> lInt = new ArrayList<Integer>();
	List<Integer> l1Int = new ArrayList<Integer>();
	List<Integer> lfreq = new ArrayList<Integer>();

	while (i < str.length()){
		oneChar = str.substring(i,i+1);

		if (tmpStr.indexOf(oneChar)==-1){
			j = i;
			cnt = 0;
			while (j < str.length()){
				if (oneChar.equals(str.substring(j,j+1))) cnt++;
				j++;
			}
			tmpStr += oneChar;
			lInt.add(cnt); 
			if (tmpStri.indexOf(Integer.toString(cnt))==-1) l1Int.add(cnt);     //add different cnts if > 2 not valid
			tmpStri += cnt + "|";
		}
		i++;
	}

	debug("tmps = "+tmpStr+" - "+tmpStri+" l1i="+l1Int.size());

	if (l1Int.size() == 1) return "YES";			//if only 1 count of frequenct then valid
	if (l1Int.size() > 2) return "NO";			//if more than 2 different freqs not valid and cannot be made so
	if (Math.abs(l1Int.get(0) - l1Int.get(1)) > 1) return "NO"; 	//if diff > 1 cannot be made valid

	//to get here only 2 different freqs, both are 1 apart now check to see if removal of 1 can fix this
	if (freqCnt(l1Int.get(0),tmpStri) > 1 && freqCnt(l1Int.get(1),tmpStri) > 1) return "NO";

	//to get here only 2 different freqs, both are 1 apart and at least 1 has a freq of 1 so can be removed and made valid
	return "YES";
    }

    //===================================================================================
    static public void main(String[] args)
    {
	String xx = findCharFrequency("aabbcd");			//no
	System.out.println("Valid String - " + xx);
	System.out.println("==================================");

	xx = findCharFrequency("aabbccddeefghi");		//no
	System.out.println("Valid String - " + xx);
	System.out.println("==================================");

	xx = findCharFrequency("abcdefghhgfedecba");		//yes
	System.out.println("Valid String - " + xx);
	System.out.println("==================================");
    }
}
