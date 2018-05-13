package jarvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

// point class to store x,y coordinates and angle and distance r
class point
{	
		double x,y;
		public int index;
		public double r,angle;		
}

// stack implementation
class stack {
	
	static int max=10000;
	double[][] arr = new double[max][2];
	int c=-1;
   public void push(double x,double y)   
   {
	   c++;
	   arr[c][0]=x;
	   arr[c][1]=y;
   }
   
   public void print_stack()
   {
	   int a=c;
	   while(a>=0)
	   {
		   System.out.println(arr[a][0]+" "+arr[a][1]);
		   a--;
	   }  
   }
}



