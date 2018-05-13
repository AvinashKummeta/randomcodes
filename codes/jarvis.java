package jarvis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jarvismarch {
	
	public static void main(String args[]) throws FileNotFoundException
	{
	    // file reading 
		File fp = new File("C:/Users/avina/workspace/jarvis/src/jarvis/input2.txt");
		  Scanner s=new Scanner(fp);
		  int i=0;		  
		  int n=s.nextInt();                                   // reading each int value from file
		  point p[] = new point[n];
		  double min_x=100000,min_y=100000,max_y=0;int y_ind=0;    
		  while(i<n)
		  {
			  double X = s.nextDouble();
			  double Y = s.nextDouble();
			  if(X<min_x)
			  {
				  min_x=X;  
			  }                                 // min_x contains minimum value of x coordinates
			  if(Y<min_y)                       // min_y contains minimum value of y coordinates
			  { 
				  min_y=Y;
			  }
			  if(max_y<Y)
			  {
				  max_y=Y;
				  y_ind=i;
			  }
			  point p1 = new point();                         
			  p1.x = X;
			  p1.y = Y;
			  p[i] = p1;
			  i++;                                            // find both min x and min y
		  }
		  //System.out.println(min_x+" "+min_y+" "+max_y);
		  s.close();  
		 double or_x=min_x-1,or_y=min_y-1,angle=0;   // origin (or_x,or_y)
		 
		 int index=0;
		 double min=2*3.14159;
		 
		 for(int k=0;k<n;k++)
		  {
			   angle = Math.atan2((p[k].y-or_y),(p[k].x-or_x));          // o_x and o_y is the initial origin
			   if(angle<=min)
			   {
				   index=k;
				   min=angle;
			   }
		  }
		 // find the point point with min angle about or 
		  stack stk=new stack();       //push the index on to the stack
		  stk.push(p[index].x,p[index].y);
		  int t=index; 
		 or_x=p[t].x;or_y=p[t].y;

		 while(true)
		 {
			 double m=2*3.14159;int ind=index;
			 for(int j=0;j<n;j++)
			 {
				 if(p[j]==null||j==index)
				 {
					 continue;
				 }
				 else
				 { 
					 angle=Math.atan2((p[j].y-or_y),(p[j].x-or_x));   
					 if(angle<0)
					 {
						 angle+=2*3.14159;
					 }
					 //System.out.println(angle+" "+j+" "+p[j].x);
					 if(angle<m)
					 {
						 m=angle;
						 ind=j;
					 }	 
				 } 
			 }
				 
			 if(ind==y_ind)
			 {
				 break;
			 }else 
			 {	
				 stk.push(p[ind].x,p[ind].y);
				 or_x=p[ind].x;or_y=p[ind].y;
				 p[ind]=null;
			 } 
		 }
		 
		 or_x=p[y_ind].x;or_y=p[y_ind].y;
		 while(true)
		 {
			 double m=2*3.14159;int ind=index;
			 for(int j=0;j<n;j++)
			 {
				 if(p[j]==null||j==index)
				 {
					 continue;
				 }
				 else
				 {	
					 angle=Math.atan2((or_y-p[j].y),(or_x-p[j].x));
					 if(angle<0)
					 {
						 angle+=2*3.14159;
					 }
					 if(angle<m)
					 {
						 m=angle;
						 ind=j;
					 }	 
				 } 
			 }
			 //System.out.println(" "+ind+"\n");
			 if(Math.atan2((or_y-p[index].y),(or_x-p[index].x))<m)
				 break;
			 if(ind==index)
			 {
				 break;
			 }else
			 {
				 stk.push(p[ind].x,p[ind].y);
				 or_x=p[ind].x;or_y=p[ind].y;
				 p[ind]=null;
			 } 
		 }
		 
		 System.out.println("x coordinates"+" "+"y coordinates");
		 stk.print_stack();
		 
	}
}
