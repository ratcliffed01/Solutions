
//You are given a 2D matrix, a, of dimension MxN and a positive integer R. You have to rotate the matrix R times and 
//print the resultant matrix. Rotation should be in anti-clockwise direction.

package hackerrank;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;

public class MatrixRotation
{

    //===================================================================================
    public static void debug(String msg){
	System.out.println(msg);
    }

    //====================================================================================
    public static int countNumInLayer(int[][] twodarr, int w,int h,int startPos){

	int i = 0;
	int j = startPos;
	int k = startPos;
	String dir = "down";

	int num = 0;
	//debug("cnil - w="+w+" h="+h+" start="+startPos);

	//load 1darray
	while (true){
		//debug("rl - a i="+i+" j="+j+" k="+k+" dir="+dir);
		num++;
		if (dir.equals("left")){
			if (k > startPos) k--; else break;
		}
		if (dir.equals("up")){
			if (j > startPos){
				j--;
			}else{
				k--;
				dir = "left";
			}
		}
		if (dir.equals("right")){
			if (k < w-1){
				k++;
			}else{
				j--;
				dir = "up";
			}
		}
		if (dir.equals("down")){
			if (j < h-1){
				j++;
			}else{
				k++;
				dir = "right";
			}
		}
		if (j == startPos && k == startPos) break;
		i++;
	}
	return num;
    }

    //====================================================================================
    public static int[][] rotateLayer(int[][] twodarr,int numrot,int w,int h,int startPos){

	w = w - startPos;
	h = h - startPos;
	if (w < 2 || h < 2) return twodarr;

	int numInLayer = ((w - startPos)*2 + (h - startPos)*2) - 4;
	int i = 0;
	int j = startPos;
	int k = startPos;
	String dir = "down";
	int[] onedarr = new int[numInLayer];
	int[] ret1darr = new int[numInLayer];

	//debug("rl - w="+w+" h="+h+" start="+startPos+" numl="+numInLayer);

	//load 1darray
	while (i < numInLayer){
		//debug("rl - a i="+i+" j="+j+" k="+k+" dir="+dir);
		onedarr[i] = twodarr[j][k];
		if (dir.equals("left")){
			if (k > startPos) k--; else break;
		}
		if (dir.equals("up")){
			if (j > startPos){
				j--;
			}else{
				k--;
				dir = "left";
			}
		}
		if (dir.equals("right")){
			if (k < w-1){
				k++;
			}else{
				j--;
				dir = "up";
			}
		}
		if (dir.equals("down")){
			if (j < h-1){
				j++;
			}else{
				k++;
				dir = "right";
			}
		}
		if (j == startPos && k == startPos) break;
		i++;
	}

	//display1darray(onedarr);

	//move 1darray n chars along
	i = 0;
	j = numrot;
	if (j > numInLayer) j = j - numInLayer; 
	while (i < numInLayer){
		ret1darr[j] = onedarr[i];
		i++;
		j++;
		if (j > numInLayer - 1) j = 0;
	}

	//display1darray(ret1darr);

	//return 1darray to 2darray
	dir = "down";
	i = 0;
	j = startPos;
	k = startPos;
	while (i < numInLayer){
		//debug("rl - b i="+i+" j="+j+" k="+k);
		twodarr[j][k] = ret1darr[i];
		if (dir.equals("left")){
			if (k > startPos) k--; 
			else break;
		}
		if (dir.equals("up")){
			if (j > startPos){
				j--;
			}else{
				k--;
				dir = "left";
			}
		}
		if (dir.equals("right")){
			if (k < w-1){
				k++;
			}else{
				j--;
				dir = "up";
			}
		}
		if (dir.equals("down")){
			if (j < h-1){
				j++;
			}else{
				k++;
				dir = "right";
			}
		}
		i++;
	}
	return twodarr;
    }

    //====================================================
    static void display1darray(int[] onedarr) {

	int w = onedarr.length;
	int i = 0;
	String xx = "";

	while (i < w){
		xx += onedarr[i] + " ";
		i++;
	}
	System.out.println(xx);
    }

    //====================================================
    static void display2darray(int[][] twodarr) {

	int h = twodarr.length;
	int w = twodarr[0].length;
	int i = 0;
	int j = 0;
	String xx = "";

	while (i < h){
		j = 0;
		xx = "";
		while (j < w){
			if (twodarr[i][j] < 10) xx += twodarr[i][j] + "  "; else xx += twodarr[i][j] + " ";
			j++;
		}
		System.out.println(xx);
		i++;
	}
	System.out.println("---------------");
    }

    //====================================================
    static void matrixRotation(int[][] twodarr, int numrot) {

	int h = twodarr.length;
	int w = twodarr[0].length;

	int numOfLayers = 0;
	int startPos = 0;
	if (w < h) numOfLayers = w/2; else numOfLayers = h/2;

	//get layers
	display2darray(twodarr);
	while (startPos < numOfLayers){
		twodarr = rotateLayer(twodarr,numrot,w,h,startPos);
		startPos++;
	}
	display2darray(twodarr);
    }

    //===================================================================================
    static public void main(String[] args)
    {

	int[][] arr = new int[4][4];
	arr[0] =  new int[] {1,2,3,4};
	arr[1] =  new int[] {5,6,7,8};
	arr[2] =  new int[] {9,10,11,12};
	arr[3] =  new int[] {13,14,15,16};
	matrixRotation(arr,2);

	System.out.println("==========================");

	int[][] arr1 = new int[5][4];
	arr1[0] =  new int[] {1, 2, 3, 4};
	arr1[1] =  new int[] {7, 8, 9, 10};
	arr1[2] =  new int[] {13, 14, 15, 16};
	arr1[3] =  new int[] {19, 20, 21, 22};
	arr1[4] =  new int[] {25, 26, 27, 28};
	matrixRotation(arr1,7);

	System.out.println("==========================");

	int[][] arr2 = new int[6][6];
	arr2[0] =  new int[] {1, 2, 3, 4, 5, 6};
	arr2[1] =  new int[] {7, 8, 9, 10, 11, 12};
	arr2[2] =  new int[] {13, 14, 15, 16, 17, 18};
	arr2[3] =  new int[] {19, 20, 21, 22, 23, 24};
	arr2[4] =  new int[] {25, 26, 27, 28, 29, 30};
	arr2[5] =  new int[] {31, 32, 33, 34, 35, 36};
	matrixRotation(arr2,5);
    }
}
