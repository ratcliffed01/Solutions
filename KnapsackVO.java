
package Codility_test;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.time.*;

import Codility_test.DRList;

//Example of a one-dimensional (constraint) knapsack problem: which boxes should be chosen to maximize the amount of 
//money while still keeping the overall weight under or equal to 15 kg? A multiple constrained problem could consider 
//both the weight and volume of the boxes. 
//(Solution: if any number of each box is available, then three yellow boxes and three grey boxes; 
//if only the shown boxes are available, then all but the green box.)
// green1(1) - 12kg - $4
// blue(2)   - 2kg  - $2
// brown(3)  - 1kg  - $1
// yellow(4) - 4kg  - $10
// grey(5)   - 1kg  - $2

public class KnapsackVO
{
	//=====================================================
	public static void displayvo(knapVO[] vos){

		int i = 0;
		int j = 0;
		String xx = "";
		while (i < vos.length){
			xx = vos[i].getCol()+" "+vos[i].getWght()+" "+vos[i].getPrice()+" "+vos[i].getRatio();
			System.out.println(xx);
			i++;
		}
	}

	//========================================================================
	public float unlKnapsack(knapVO[] vos, float W){

		//calculate ratio

		int adjustor = 0;
		int i = 0;
		while (i < vos.length){
			if (vos[i].getPrice() > 0.0) vos[i].setRatio((vos[i].getWght()) / vos[i].getPrice());
			i++;
		}

		//now sort by ratio
		Arrays.sort(vos, new Comparator<knapVO>() {
			@Override
                       	//arguments to this method represent the arrays to be sorted   
			public int compare(knapVO vo1, knapVO vo2) {
                       	       	//get the item ids which are at index 0 of the array
				Float itemIdOne = vo1.ratio;
				Float itemIdTwo = vo2.ratio;
				// sort on item id
				return itemIdOne.compareTo(itemIdTwo);
			}
		});

		displayvo(vos);

		//now find the best valus
		i = 0;
		int j = 0;
		int cnt = 0;
		float totw = 0.0f;
		float totp = 0.0f;
		String xx = "";
		while (i < vos.length){
			j = 0;
			cnt = 0;
			if (vos[i].getWght() > 0.0){
				while (totw <= W){
					totw += vos[i].getWght();
					totp += vos[i].getPrice();
					cnt++;
					j++;
				}
				totw -= vos[i].getWght();
				totp -= vos[i].getPrice();
				cnt--;
				if (cnt > 0) xx += vos[i].getCol()+"("+cnt+") ";
			}
			i++;
		}
		System.out.println(xx + " totp="+totp+" totw="+totw);

		//use the boxes listed
		i = 0;
		j = 0;
		cnt = 0;
		totw = 0.0f;
		totp = 0.0f;
		xx = "";
		while (i < vos.length){
			j = 0;
			cnt = 0;
			if (vos[i].getRatio() > 0.0 && (totw + vos[i].getWght()) <= W){
				totw += vos[i].getWght();
				totp += vos[i].getPrice();
				cnt++;
				if (cnt > 0) xx += vos[i].getCol()+"("+cnt+") ";
			}
			i++;
		}
		System.out.println(xx + " totp="+totp+" totw="+totw);
		return totp;
	}

	//=================================================
	public static void main(String[] args) {

		// {colour,weight,price,ratiox10}
		KnapsackVO ksv = new KnapsackVO();

		knapVO[] vos = {
			new knapVO("green",12.0f,4.0f,0.0f),
			new knapVO("blue",2.0f,2.0f,0.0f),
			new knapVO("brown",1.0f,1.0f,0.0f),
			new knapVO("yellow",4.0f,10.0f,0.0f),
			new knapVO("grey",1.0f,2.0f,0.0f)
		};

		float tot = ksv.unlKnapsack(vos, 15);

		// {colour,weight,price,ratiox10}
		knapVO[] vos1 = {
			new knapVO("green",8.0f,894.0f,0.0f),
			new knapVO("blue",6.0f,260.0f,0.0f),
			new knapVO("brown",4.0f,392.0f,0.0f),
			new knapVO("yellow",0.0f,281.0f,0.0f),
			new knapVO("grey",21.0f,27.0f,0.0f)
		};
		tot = ksv.unlKnapsack(vos1, 15);

	}

	public static class knapVO {

		private String col;
		private float wght;
		private float price;
		private float ratio;

		public knapVO(){
		}

		public knapVO(String c, float w, float p, float r){
			this.col = c;
			this.wght = w;
			this.price = p;
			this.ratio = r;
		}

		public String getCol(){
			return this.col;
		}
		public void setCol(String x){
			this.col = x;
		}

		public float getWght(){
			return this.wght;
		}
		public void setWght(float x){
			this.wght = x;
		}

		public float getPrice(){
			return this.price;
		}
		public void setPrice(float x){
			this.price = x;
		}
		
		public float getRatio(){
			return this.ratio;
		}
		public void setRatio(float x){
			this.ratio = x;
		}
	}

}






