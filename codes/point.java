package scan;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//point class to store x,y coordinates and angle and distance r
public class point
{	
		double x,y;
		public int index;
		public double r,angle;		
}

class stack {
	
	static int max=10000;
	int[] arr = new int[max];
	int c=-1;
   public void push(int l)
   {
	   c++;
	   arr[c]=l;   
   }
   
   public int top()
   {
	   int t=arr[c];
	   c--;
	   return t;
   }
   // this method will return the top element
   public int next_top()
   {
	   return arr[c];     
   }
  
   // this method will return the top index
   public int top_index()
   {
	   return c;
   }
   
   public void print_stack()
   {
	   int a=c;
	   while(a>=0)
	   {
		   System.out.println(arr[a]);
		   a--;
	   }  
   }
}


