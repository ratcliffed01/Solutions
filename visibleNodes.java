
package Codility_test;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class visibleNodes
{
	//======================================================================
    	public static String lpad(String strValue, String oneChar, int strLen)
    	{
		try
		{
	    		if (strLen==0) return null;
	    		if (oneChar.equals(null)) return null;
	    		if(oneChar.length() > 1) return null;
	    		if (strValue.equals(null)) strLen=0;
	    		if (strValue.length() >= strLen) return strValue;

	    		int cnt = strValue.length();
	    		String strLpad = "";
	    		while (cnt < strLen){
				strLpad = strLpad + oneChar;
				cnt++;
	    		}
	    		strValue = strLpad + strValue;
	    		return strValue;
		}
		catch (Exception e)
		{
            		System.out.println("Exception in lpad "+e.getMessage());
            		return null;
		}
    	}

	//=========================================================
	public String findNodes(Tree T, String lvl,String xx, String nodeStr){

		if (T != null){
			//System.out.println(T.x+" lvl="+lvl+" xx="+xx+" "+nodeStr);
			//nodeStr += lvl.length() + lvl + "<" + xx + "!";
			nodeStr = findNodes(T.l,lvl+"l",xx+T.x+" ",nodeStr);
			nodeStr = findNodes(T.r,lvl+"r",xx+T.x+" ",nodeStr);
		}else{
			nodeStr += xx + "!";
		}
		return nodeStr;
	}

	//========================================
	public String checkNodes(String xx, String x){

		int i = 1;
		int result = 0;
		String visStr = x;
		String[] noStr = xx.split(" ");	
		int[] j = new int[noStr.length];	
		j[0] = Integer.parseInt(noStr[0]);

		if (visStr.indexOf(noStr[0])==-1)
			visStr += noStr[0] + "!";

		while (i < noStr.length){
			j[i] = Integer.parseInt(noStr[i]);
			if (j[i - 1] < j[i]){
				if (visStr.indexOf(noStr[i]+"!")==-1)
					visStr += j[i] + "!";
			}
			i++;
		}
		//System.out.println("i="+i+" nl="+noStr.length+" res="+result+" "+visStr+" xx="+xx);
		return visStr;	
	}

	//========================================
	public int findVisibleNodes(Tree T){

		int i = 0;

		visibleNodes vn = new visibleNodes();

		String x = vn.findNodes(T, "t", "", "");
		//System.out.println(x);

		String[] xx = x.split("!");
		x = "";
		while (i < xx.length){
			x = checkNodes(xx[i],x);
			i++;
		}
		System.out.println("visible nodes - "+x);

		String[] str = x.split("!");

		return str.length;
	}

	//====================================================
	public String loadRootStr(String str){
		
		int i = str.indexOf(" (");
		String root = str.substring(1 ,i - 1);
		return root;
	}

	//====================================================
	public String[] loadLeftStr(String str){

		int i = str.indexOf(" (");
		int j = 0;
		int brcnt = 0;

		String left = "";
		while (i < str.length()){
			if (str.substring(i ,i+1).equals("(")) brcnt++;
			if (str.substring(i ,i+1).equals(")")) brcnt--;
			if (brcnt==0 && str.substring(i ,i+1).equals(")")) break;
			i++;
		}
		left = str.substring(str.indexOf(" ("),i);
		System.out.println("lls - i="+i+" l="+left);
		String[] ls = left.split(",");
		return ls;
	}

	//====================================================
	public String[] loadRightStr(String str){

		int i = str.indexOf(" (");
		int brcnt = 0;

		String root = str.substring(0 ,i);
		String left = "";
		while (i < str.length()){
			if (str.substring(i ,i+1).equals("(")) brcnt++;
			if (str.substring(i ,i+1).equals(")")) brcnt--;
			if (brcnt==0 && str.substring(i ,i+1).equals(")")) break;
			i++;
		}
		String right = str.substring(i+2);
		System.out.println("lrs - i="+i+" r="+right);
		String[] lr = right.split(",");

		return lr;
	}

	//====================================================
	public String[] loadLeftDir(String[] ls){

		String[] lsdir = new String[ls.length];

		int i = 0;
		String xx = "";
		String dir = "l";			
		while (i < ls.length - 1){
			ls[i] = ls[i].substring(2);
			if (ls[i].matches("^[0-9]+$")){ 
				xx += dir;
			}else{
				xx = "l";
				dir = "r"; 
			}
			lsdir[i] = xx;
			//System.out.println("lld - i="+i+" ls="+ls[i]+" "+lsdir[i]);
			i++;
		} 
		return lsdir;
	}
	
	//====================================================
	public String[] loadRightDir(String[] rs){

		String[] rsdir = new String[rs.length];

		int i = 0;
		int j = 0;
		String xx = "";
		String dir = "r";
		while (i < rs.length - 1){
			rs[i] = rs[i].substring(2);
			if (rs[i].matches("^[0-9]+$")){ 
				j++;
				xx += dir;
				dir = "l";
			}else{
				xx = "r";
				dir = "r"; 
			}
			rsdir[i] = xx;
			//System.out.println("lrd - i="+i+" rs="+rs[i]+" "+rsdir[i]);
			i++;
		} 	
		return rsdir;
	}

	//=========================================================
	public Tree newLeftNode(LoadTreeVO lt, Tree T, int i, String dir){

		Tree nT = new Tree();
 		nT.x = Integer.parseInt(lt.ls[i]);
				
		//System.out.println("ntn - i="+i+" "+lt.lsdir[i]+" "+lt.ls[i]+" dir="+dir);

		if (dir == "l"){ 
			T.l = nT;
			T = T.l;
		}
		if (dir == "r"){ 
			T.r = nT;
			T = T.r;
		}
		return T;
	}

	//=========================================================
	public Tree newRightNode(LoadTreeVO lt, Tree T, int i, String dir){

		Tree nT = new Tree();
 		nT.x = Integer.parseInt(lt.rs[i]);
				
		System.out.println("ntn - i="+i+" "+lt.rsdir[i]+" "+lt.rs[i]+" dir="+dir);

		if (dir == "l"){ 
			T.l = nT;
			T = T.l;
		}
		if (dir == "r"){ 
			T.r = nT;
			T = T.r;
		}
		return T;
	}
	//=========================================================
	public Tree loadTreeNodes(LoadTreeVO lt, Tree T){

		T.x = Integer.parseInt(lt.root);
		Tree top = T;

		int i = 0;
		int j = 0;
		while (i < lt.ls.length){
			//lt.ls[i] = lt.ls[i].substring(2);
			T = top;
			if (lt.ls[i].matches("^[0-9]+$")){
				j = 0;
				while (j < lt.lsdir[i].length()){
					if (lt.lsdir[i].charAt(j)=='l'){
					 	if (T.l==null) T = newLeftNode(lt, T, i, "l");
						T = T.l;
					}
					if (lt.lsdir[i].charAt(j)=='r'){
					 	if (T.r==null) T = newLeftNode(lt, T, i, "r");
						T = T.r;
					}
					j++;
				}
				//System.out.println("ltn - i="+i+" j="+j+" "+lt.lsdir[i]+" "+lt.ls[i]);
			}
			i++;
		}

		System.out.println("========");
		i = 0;
		j = 0;
		while (i < lt.rs.length){
			//lt.ls[i] = lt.ls[i].substring(2);
			T = top;
			if (lt.rs[i].matches("^[0-9]+$")){
				j = 0;
				while (j < lt.rsdir[i].length()){
					if (lt.rsdir[i].charAt(j)=='l'){
					 	if (T.l==null) T = newRightNode(lt, T, i, "l");
						T = T.l;
					}
					if (lt.rsdir[i].charAt(j)=='r'){
					 	if (T.r==null) T = newRightNode(lt, T, i, "r");
						T = T.r;
					}
					j++;
				}
				System.out.println("ltn rs - i="+i+" j="+j+" "+lt.rsdir[i]+" "+lt.rs[i]);
			}
			i++;
		}

		return T;

	}

	//=========================================================
	public Tree loadTree(Tree T, String str){

		visibleNodes vn = new visibleNodes();

		String lr = loadRootStr(str);
		String[] ls  = loadLeftStr(str);
		String[] rs  = loadRightStr(str);
		String[] lsdir = loadLeftDir(ls);
		String[] rsdir = loadRightDir(rs);

		LoadTreeVO lt = new LoadTreeVO(lr, ls, rs, lsdir, rsdir);

		T = loadTreeNodes(lt, T);

		return T;
	}

	//=========================================================
	public String printNodes(Tree T, String lvl,String nodeStr){

		if (T != null){
			//System.out.println(T.x+" lvl="+lvl+" "+nodeStr);
			nodeStr += lvl.length() + lvl + "<" + T.x + "!";
			nodeStr = printNodes(T.l,lvl+"l",nodeStr);
			nodeStr = printNodes(T.r,lvl+"r",nodeStr);
		}
		return nodeStr;
	}

	//=========================================================
	public void formatTree(String x, String lvl, int w){

		//System.out.println("ft - val="+x+" lvl="+lvl);
		String slvl = lvl;
		lvl += ".0";
		double pow = Double.parseDouble(lvl);
		int div = (int) Math.pow(2.0,pow);
		int pad = w / div;
		int oldpad = 0;

		if (Integer.parseInt(slvl)>1){
			pow = Double.parseDouble(lvl) - 1.0;
			div = (int) Math.pow(2.0,pow);
			oldpad = w / div;
		}

		String[] xx = x.split(" ");
		x = "";
		int i = 0;
		int j = 2;
		while (i < xx.length){
			if (i == 0) x += lpad(xx[i]," ",pad);
			if (i > 0) x += lpad(xx[i]," ",pad+pad);
			i++;
		}
		if (Integer.parseInt(slvl)>1){
			i = 1;
			String y = "";
			oldpad--;
			while (oldpad > pad){
				y = lpad("/"," ",oldpad)+lpad("\\"," ",i*2);
				if (xx.length > 2){
					y += lpad("/"," ",((j * oldpad)));
					if (y.length() < x.length()) y += lpad("\\"," ",((i*2)));
				}
				System.out.println(y);
				i++;
				oldpad--;
			}
		}
		System.out.println(x);


	}

	//=========================================================
	public void displayTreeNodes(Tree T){

		String nodeStr = printNodes(T,"t","");

		String[] xx = nodeStr.split("!");
		String x = "";
		String y = "";
		System.out.println("dtn "+nodeStr+" xxl="+xx.length+" xx0="+xx[0]);
		Arrays.sort(xx);
		int i = 0;
		int j = 0;
		int w = 40;
		int maxlvl = Integer.parseInt(xx[xx.length - 1].substring(0,1));
		if (maxlvl > 3) w = 80; else w = 40;
		while (i < xx.length){
			x = "";
			y = xx[i].substring(0,xx[i].indexOf("t"));
			j = i;
			while (j < xx.length && y.equals(xx[j].substring(0,xx[j].indexOf("t")))){
				x += xx[j].substring(xx[j].indexOf("<")+1) + " ";
				j++;
			}
			formatTree(x,y,w);
			i = j;
		}

	}

	//=======================================================
	public static void main(String[] args) {

		visibleNodes vn = new visibleNodes();
		Tree T = new Tree();

		String loadStr = "(5, (3, (20, None, None), (21, None, None)), (10, (1, None, None), None))";
		T = vn.loadTree(T,loadStr);

		vn.displayTreeNodes(T);

		int cnt = vn.findVisibleNodes(T);

		System.out.println("Number of visible nodes = "+cnt);

	}

	//====================================
	static public class Tree 
	{
		int x;
		Tree l;
		Tree r;
	}

	//======================================
	public class LoadTreeVO
	{

		int x;
		int y;
		String root = "";
		String[] ls = new String[x];
		String[] lsdir = new String[x];
		String[] rs = new String[y];
		String[] rsdir = new String[y];

		public LoadTreeVO(String lr, String[] ls, String[] rs, String[] lsdir, String[] rsdir){
			this.x = ls.length;
			this.y = rs.length;
			this.ls = ls;
			this.lsdir = lsdir;
			this.rs = rs;
			this.rsdir = rsdir;
			this.root = lr;
		}

	}
}






