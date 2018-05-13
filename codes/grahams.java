package scan;
import java.text.DecimalFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class CartesianToPolar { 
    double radius(double a, double b,double x,double y) { 
       
        double r = Math.sqrt((a-x)*(a-x) +(b-y)*(b-y));
        return r;
      }
    double theta(double a, double b,double x,double y ){
    
        double angle = Math.atan2((b-y),(a-x));
        return angle;   
    }
}

public class apple{
	static double PI = 3.14159;
	private static DecimalFormat df2 = new DecimalFormat(".####");
	
	public static void main(String args[])throws FileNotFoundException{
		CartesianToPolar c = new CartesianToPolar();
		// reading file input.txt
		File fp = new File("C:/Users/avina/workspace/exe/src/scan/input1.txt");
		//File fp = new File("C:/Users/avina/workspace/exe/src/scan/input1.txt");
		  Scanner s=new Scanner(fp);
		  int i=0;		  
		  //int[] arr = new int[n];
		  int n=s.nextInt();                                   // reading each int value from file
		  point p[] = new point[n];
		  double[] rad = new double[100];
		 // System.out.println(x);
		  double min_x=100000,y1=0,max_x=0,y2=0;              //consider here
		  while(i<n)
		  {
			  double X = s.nextDouble();
			  double Y = s.nextDouble();
			  if(X<min_x)
			  {
				  min_x=X;
				  y1=Y;
			  }
			  if(X>max_x)
			  {
				  max_x=X;
				  y2=Y;
			  }
			  point p1 = new point();
			  p1.x = X;
			  p1.y = Y;
			 // System.out.println(p1.x);
			 // System.out.println(p1.y);
			  p[i] = p1;
			 // System.out.println(p[i].x);
			 // System.out.println(p[i].y);
			  i++;
		  }
		  s.close();  
		/* for(int j=0; j<x; j++)
		  {
			 System.out.println(p[j].x);
			 System.out.println(p[j].y);
		  }*/	
		  double mean_x=(min_x+max_x)/2,mean_y=(y1+y2)/2;
		  
		 
		  for(int j=0;j<n;j++)
		  {
			 p[j].r=c.radius(p[j].x,p[j].y, mean_x, mean_y); 
			 p[j].angle = c.theta(p[j].x, p[j].y, mean_x, mean_y);
			 if(p[j].angle<0)
			 {
				 p[j].angle+=2*PI;
			 }
		  }
		    
		  Arrays.sort(p, new Comparator<point>() {
			   public int compare(point b1,point b2) {
			    if(b1.angle>b2.angle)
			    	return 1;
			    else if(b1.angle<b2.angle)
			    	return -1;
			    else
			    	return 0;
			   }
			});
		  
		  // removing small r when angles are equal;
		 for(int j=1;j<n;j++)
		 {
			 if(p[j].angle==p[j-1].angle)
			 {
				 if(p[j].r>p[j-1].r)
				 {
					 p[j-1]=null;
				 }else
				 {
					 p[j]=p[j-1];
					 p[j-1]=null;
				 }
			 }
			 
		 }
		  
		  
	/*	 for(int l=0;l<n;l++)
		  {
			 
			  System.out.println(p[l].x+" "+p[l].y+" "+p[l].angle+" "+p[l].r);
		  }
		*/   
		  double r_max=0;int init=0;                             // init for max radius index
		  for(int k=0;k<n;k++)
		  {
			  //rad[k]=c.radius(p[k].x,p[k].y, mean_x, mean_y);
			  if(r_max<p[k].r)
			  {
				  r_max=p[k].r;
				  init=k;
			  }
		  }  
		//  System.out.println(init);
		 
		stack st = new stack();
		st.push(init);
		st.push((init+1)%n);
		int index=init,ind=init+2;
		//System.out.println(init+" "+n+" "+st.top_index());
		while(index!=st.next_top())
		{
			
			int t3=ind%n;
			int t2=st.top();
			
			int t1=st.next_top();
			double area = p[t1].x*(p[t2].y-p[t3].y)+p[t2].x*(p[t3].y-p[t1].y)+p[t3].x*(p[t1].y-p[t2].y);
			if(area>0)
			{
				st.push(t2);
				st.push(t3);
				
			}else
			{
				st.push(t3);
			}	
			ind++;
			//System.out.println(ind%n+" "+index+" "+st.top_index());
		}
		//System.out.println("stack elements\n");
		//st.print_stack();
		System.out.println("vertices of convex hull");
		int len=st.top_index();
		while(len>0)
		{
			int p_index=st.top();
			System.out.println(p[p_index].x+" "+p[p_index].y);
			len--;
		}	
	}
}