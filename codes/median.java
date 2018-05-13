package hull;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
  // point class to store both x and y coordinate
class point
{	
		double x,y;
}

// find_median is to find the median of x among the points
public class Find_median {
	//finding index of the pivot
	public int partition(ArrayList<point> arr, int l, int r)
	{
             double x = arr.get(r).x;
             int i = l;
             for (int j = l; j <= r - 1; j++)
             {
                if (arr.get(j).x <= x)
                {
                  double x1,y1;
                  point temp = arr.get(i);
                  x1=temp.x;y1=temp.y;

                  point temp1=arr.get(j);

                  temp.x=temp1.x;
                  temp.y=temp1.y;
                  temp1.x=x1;
                  temp1.y=y1;
                   i++;
                  }
               }
              point temp2 = arr.get(i);
              double x2,y2;
              x2=temp2.x; y2=temp2.y;
              point temp3=arr.get(r);
              temp2.x=temp3.x; temp2.y = temp3.y;
              temp3.x=x2; temp3.y=y2;
              return i;
}
	    //nth smallest element using quick sort
	    public double nthSmallest(ArrayList<point> ar, int l, 
	                                         int r, int k)
	    {
	        
	        if (k > 0 && k <= r - l + 1)
	        {
	         
	            int pos = partition(ar, l, r);
	 
	            if (pos-l == k-1)
	                return ar.get(pos).x;
	          
	            if (pos-l > k-1) 
	                return nthSmallest(ar, l, pos-1, k);
	 
	            return nthSmallest(ar, pos+1, r, k-pos+l-1);
	        }
	 
	        return Double.MAX_VALUE;
	    }
}

 // finding median for the slopes
class Slope{
	//finding index of the pivot
	public int partition(ArrayList<pair> list, int l, int r)
	{
		pair p = list.get(r);
		double x = p.slope;
		int i = l;
		for (int j = l; j <= r - 1; j++)
		{
			pair pz = list.get(j);	
			if (pz.slope <= x)
			{
				point p1,p2;
				double slope;
				pair tem = list.get(i);
				p1 = tem.p1;
				p2 = tem.p2;
				slope = tem.slope;
				tem.p1 = pz.p1;
				tem.p2 = pz.p2;
				tem.slope = pz.slope;
				pz.p1 = p1;
				pz.p2 = p2;
				pz.slope = slope;
				i++;
			}
		}
		point p1,p2;
		double slope;
		pair tem = list.get(i);
		pair pz = list.get(r);
		p1 = tem.p1;
		p2 = tem.p2;
		slope = tem.slope;
		tem.p1 = pz.p1;
		tem.p2 = pz.p2;
		tem.slope = pz.slope;
		pz.p1 = p1;
		pz.p2 = p2;
		pz.slope = slope;
		
		return i;
	}
    //nth smallest element using quick sort	
	 public double kthSmallest(ArrayList<pair> arr, int l,  int r, int k)
	 {

		 if (k > 0 && k <= r - l + 1)
		 {

			 int pos = partition(arr, l, r);
			 if (pos-l == k-1){
				 pair p = arr.get(pos);
				 return p.slope;
			 }

			 if (pos-l > k-1) 
				 return kthSmallest(arr, l, pos-1, k);

			 return kthSmallest(arr, pos+1, r, k-pos+l-1);
		 }

		 return Double.MAX_VALUE;
		 }
}
