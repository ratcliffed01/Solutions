// to compile C:\JobTests\yospace\locator>javac -cp ../ LocatorSolution.java
// to run C:\JobTests\yospace\locator>java -cp ../ locator.LocatorSolution

//Please write a concrete Java implementation of the Locator interface detailed below.
//Your class, LocatorSolution, must find the index of an item efficiently from within a sorted array of candidates. 
//The implementation must be written from first principles, and must not make use of classes not explicitly mentioned in 
//the interface definition. The solution should adhere to the specification below when supplied with reasonable inputs and 
//must provide a no-throw guarantee.

// written by Dave Ratcliffe version 1.0 08/01/2019

package locator;

import java.io.*;					// only used to read a txt file for testing

public class LocatorSolution implements Locator
{

	//=============================================================
	// this section finds the array item from the name passed.
	// initially it uses the binary chop to reduce the sequential search to less than 10,
	// where up to sequential search of 10 is done
	public int getIndex(String itemSought, String[] candidates)
	{

		if (candidates == null) return -1;

		int startPos = 0;
		int midPos = 0;
		int endPos = candidates.length;
		int minDiff = 10;
		boolean found = false;
		int result = 0;
		int i = 0;

		// midpoint is found then checked to see is > or < or =
		while ((endPos - startPos) > minDiff && !found){
			midPos = (startPos + endPos)/2;

			result = candidates[midPos].compareTo(itemSought);
			if (result == 0) found = true;				//midpos = itemsought
			if (result > 0) endPos = midPos;			//midpos < itemsought
			if (result < 0) startPos = midPos;			//midpos > itemsought

			//System.out.println("sp="+startPos+" mp="+midPos+" ep="+endPos);
		}

		// if required a sequential search of the array is made for up to 10 items
		if (found) i = midPos; else i = startPos;
		while (i < endPos && !found){
			if (candidates[i].equals(itemSought)) found = true;
			if (!found) i++;
		}
		if (!found) i = -1;

		return i;
	}

    	//===================================================================================
	// read txt files from local folder and load into 1darray, 1st line is number of names
    	public String[] readFile(String path)
    	{

		int i = 0;
		int j = 0;

		try
		{
			RandomAccessFile cp = new RandomAccessFile(path, "r");

			String allLines = "";
			String xx = "";

			xx=cp.readLine();
			int len = Integer.parseInt(xx);
			String[] linex = new String[len]; 

			while ((xx=cp.readLine())!=null){
				linex[j] = xx.trim();
				j++;
			}
			cp.close();

			//System.out.println("rf - siz="+linex.length+" j="+j);

			return linex;
		}
		catch (IOException ioe)
		{
        	    	System.out.println("reading file IOException - "+ioe.getMessage());
	    		return null;
		}
		catch (Exception e)
		{
            		System.out.println("reading files Exception - i="+i+" j="+j+" "+e.getMessage());
	    		return null;
		}
    	}

	//=================================================================
	public static void main(String[] args){

		LocatorSolution ls = new LocatorSolution();

		String[] xx = new String[12];
		xx[0] = "BakerA";
		xx[1] = "ColinsI";
		xx[2] = "DaviesA";
		xx[3] = "DwyerN";
		xx[4] = "GreggsJ";
		xx[5] = "HowardT";
		xx[6] = "MorecomeE";
		xx[7] = "NewlanC";
		xx[8] = "RoddyP";
		xx[9] = "SmithJ";
		xx[10] = "SmithT";
		xx[11] = "TomlinsonK";

		System.out.println("Name to find - SmithT");
		int item = ls.getIndex("SmithT",xx);
		System.out.println("test 1 ind = "+item+" name="+xx[item]);

		System.out.println("Name to find - MorecomeE");
		item = ls.getIndex("MorecomeE",xx);
		System.out.println("test 2 ind = "+item+" name="+xx[item]);

		System.out.println("Name to find - BakerA");
		item = ls.getIndex("BakerA",xx);
		System.out.println("test 3 ind = "+item+" name="+xx[item]);

		System.out.println("Name to find - TomlinsonK");
		item = ls.getIndex("TomlinsonK",xx);
		System.out.println("test 4 ind = "+item+" name="+xx[item]);

		System.out.println("Name to find - NewlanD");
		item = ls.getIndex("NewlanD",xx);
		System.out.println("test 4 ind = "+item+" not found");

		String[] x1 = ls.readFile("names.txt");
		System.out.println("Name to find - R SHOKUNB");
		item = ls.getIndex("R SHOKUNBI",x1);
		if (item == -1) System.out.println("test 5 ind = "+item+" not found");
		else System.out.println("test 5 ind = "+item+" name="+x1[item]);

		System.out.println("Name to find - T.BRAYFORD");
		item = ls.getIndex("T.BRAYFORD",x1);
		if (item == -1) System.out.println("test 6 ind = "+item+" not found");
		else System.out.println("test 6 ind = "+item+" name="+x1[item]);

		System.out.println("Name to find - xx");
		item = ls.getIndex("xx",x1);
		if (item == -1) System.out.println("test 7 ind = "+item+" not found");
		else System.out.println("test 7 ind = "+item+" name="+x1[item]);

		System.out.println("Name to find - A            .VINCENT");
		item = ls.getIndex("A            .VINCENT",x1);
		if (item == -1) System.out.println("test 8 ind = "+item+" not found");
		else System.out.println("test 8 ind = "+item+" name="+x1[item]);

		System.out.println("Name to find - ");
		item = ls.getIndex("",x1);
		if (item == -1) System.out.println("test 9 ind = "+item+" not found");
		else System.out.println("test 9 ind = "+item+" name="+x1[item]);

	}

}