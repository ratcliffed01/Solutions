
//Insertion Sort is a simple sorting technique which was covered in previous challenges. 
//Sometimes, arrays may be too large for us to wait around for insertion sort to finish. 
//Is there some other way we can calculate the number of times Insertion Sort shifts each elements when sorting an array?
//If ki is the number of elements over which the ith element of the array has to shift, 
//then the total number of shifts will be k1 +k2 +…+kN.

package hackerrank;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;

public class InsertionSort
{

    //===================================================================================
    public static void debug(String msg){
	System.out.println(msg);
    }

    //====================================================
    static void display1darray(int[] onedarr,int diff) {

	int w = onedarr.length;
	int i = 0;
	String xx = "";

	while (i < w){
		xx += onedarr[i] + " ";
		i++;
	}
	if (diff == 0){
		System.out.println(xx);
	}else{
		System.out.println(xx+"   diff="+diff);

	}
    }

    //====================================================
    static int[] shiftArray(int[] onedarr,int i,int j) {

	int dum = onedarr[j];
	while (j > i){
		onedarr[j] = onedarr[j - 1];
		j--;
	}
	onedarr[i] = dum;
	return onedarr;
    }

    //====================================================
    static void numOfShifts(int[] onedarr) {

	int l = onedarr.length;
	int i = 0;
	int j = 0;
	int dum = 0;
	int diff = 0;
	int sumdiff = 0;

	display1darray(onedarr,0);
	System.out.println("=============");
	while (i < l && diff > -1){
		j = i;
		while (j < l){
			if (onedarr[j] < onedarr[i]){
				diff = (j - i);
				if (diff < 0) break;
				onedarr = shiftArray(onedarr,i,j);
				display1darray(onedarr,diff);
				sumdiff += diff;
			}
			j++;
		}
		i++;
	}
	System.out.println("Total shifts="+sumdiff);
    }

    //===================================================================================
    static public void main(String[] args)
    {

	System.out.println("==========================");

	int[] arr1 = new int[5];
	arr1 =  new int[] {4,3,2,1};			//total shifts = 6
	numOfShifts(arr1);

	System.out.println("==========================");

	int[] arr2 = new int[5];
	arr2 =  new int[] {2, 1, 3, 1, 2};			//total shifts = 4
	numOfShifts(arr2);

	System.out.println("==========================");

	int[] arr3 = new int[5];
	arr3 =  new int[] {1,2,3,4};			//total shifts = 0
	numOfShifts(arr3);

	System.out.println("==========================");

	int[] arr4 = new int[17];
	arr4 =  new int[] {8,3,6,9,1,4,3,8,7,2,9,3,0,7,2,9,5};			//total shifts = ?
	numOfShifts(arr4);

	System.out.println("==========================");
    }
}
