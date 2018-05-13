package hull;

import java.lang.reflect.Array;
import java.util.ArrayList;
// class pair to store pair of points
class pair
{
	point p1,p2;
	double slope;
}

class Upper_bridge                                       //find the upper bridge
{
	public ArrayList<point> upperbridge(ArrayList<point> list, double a)
	{
		//step 1
		// initial empty list
		ArrayList<point> cand = new ArrayList<point>();
		
		//step 2
	     // return when list size is 2
		//System.out.println(list.size());
		
	/*	for(int j=0;j<list.size();j++)
		{
			System.out.println(list.get(j).x+" "+list.get(j).y);
		}
	*/	
		
		if(list.size()==2)
		{
			if(list.get(0).x > list.get(1).x){
				point p = list.get(0);
				list.remove(0);
				list.add(p);
				return list;
			}
			else
				return list;
		}
		//step 3
		//storing  some random pairs 
		ArrayList<pair> pr = new ArrayList<pair>();
		int c=0;
		int sz=list.size()/2;
		int i=1;
		for(i=1;i<list.size();i=i+2)
		{
			pair pp = new pair();
			if(list.get(i).x>=list.get(i-1).x)
			{
				pp.p1=list.get(i-1);
				pp.p2=list.get(i);
				
			}else
			{
				pp.p1=list.get(i);
				pp.p2=list.get(i-1);
				
			}
			pr.add(pp);	
		}
		
		//if size is odd, the last one left after the loop will be added
		if(list.size()%2 == 1)
			cand.add(list.get(i-1));
		
		//step 4
		for(int j=0; j<pr.size(); j++){
			pair p = pr.get(j);
			if(p.p1.x == p.p2.x){
				if(p.p1.y > p.p2.y)
				{
					cand.add(p.p1);   // remove from the list when slope is infinity
					pr.remove(j);
				}	
				else
				{
					cand.add(p.p2);
					pr.remove(j);
				}
					
			}
			else{
				p.slope = (p.p1.y - p.p2.y)/(p.p1.x - p.p2.x);   
			}
		}
		
		//step 5  finding median of slopes
		int n = pr.size();
		Slope sp = new Slope();
		double median;
		if(n%2 != 0){         // when n is odd only one median is present
			  int index = (int) Math.floor(n/2);
			  median = sp.kthSmallest(pr,0,n-1,index+1);
		}
		else{
				 int index1 = (int) Math.floor(n/2);
				 int index2 = (int) Math.floor((n/2) + 1);
				 double median1 = sp.kthSmallest(pr,0,n-1,index1);
				 double median2 = sp.kthSmallest(pr,0,n-1,index2);
				 median = (median1 + median2)/2;
		}
		//step 6
		
	/*	for(int j=0;j<pr.size();j++)
		{
			System.out.println(pr.get(j).p1.x+" "+pr.get(j).p1.y);
			System.out.println(pr.get(j).p2.x+" "+pr.get(j).p2.y);
			System.out.println(pr.get(j).slope);
		}
		*/
       ArrayList<pair> large = new ArrayList<pair>();
       ArrayList<pair> equal = new ArrayList<pair>();
       ArrayList<pair> small = new ArrayList<pair>();
       
       for(int j=0;j<pr.size();j++)
       {
    	   if(pr.get(j).slope==median)
    	   {
    		   equal.add(pr.get(j));
    	   }else if(pr.get(j).slope<median)
    	   {
    		   small.add(pr.get(j)); 
    	   }else
    	   {
    		   large.add(pr.get(j));
    	   }
       }
       
       // step 7
       // points in s which has highest intercept
       double m=0;
       for(int j=0;j<list.size();j++)
       {
    	   double intercept = list.get(j).y-median*list.get(j).x;
    	   if(intercept>m)
    	   {
    		    m=intercept;
    	   }
       }
       
       double minx=100000,maxx=0;
       point pk = new point(); // minimum x coordinate
       point pm = new point(); // maximum x coordinate
       System.out.println("intercept max value "+m);
       for(int j=0;j<list.size();j++)
       {
    	   double intercept = list.get(j).y-median*list.get(j).x;
    	   System.out.println("intercept values "+intercept);
    	   System.out.println(list.get(j).x+" "+list.get(j));
    	   if(intercept==m)
    	   {
    		   if(minx>list.get(j).x)
    			   pk=list.get(j);
    		   if(maxx<list.get(j).x)
    			   pm=list.get(j);
    	   }
       }
       
       // step 8 step 9 and step 10
       // based on the x intercept add the points into candidate
       ArrayList<point> next = new ArrayList<point>();
       if(pk.x<=a&&pm.x>a)
       {
    	   next.add(pk);
    	   next.add(pm);
    	   return next;
       }
       else if(pm.x<=a)
       {
    	   for(int j=0;j<large.size();j++)
    	   {
    		   cand.add(large.get(j).p2);
    	   }
    	   for(int j=0;j<equal.size();j++)
    	   {
    		   cand.add(equal.get(j).p2);
    	   }
    	   for(int j=0;j<small.size();j++)
    	   {
    		   cand.add(small.get(j).p1);
    		   cand.add(small.get(j).p2);
    	   }
       }else if(pk.x>a)
       {
    	   for(int j=0;j<small.size();j++)
    	   {
    		   cand.add(small.get(j).p1);
    	   }
    	   for(int j=0;j<equal.size();j++)
    	   {
    		   cand.add(equal.get(j).p1);
    	   }
    	   for(int j=0;j<large.size();j++)
    	   {
    		   cand.add(large.get(j).p1);
    		   cand.add(large.get(j).p2);
    	   }
       }
    
      return upperbridge(cand,a);  
	}
}


// class upperhull for finding upper hull 
public class UpperHull {
	
	// find min class for finding minimum value of x among coordinates
	public point  find_min(ArrayList<point> p)
	{
		ArrayList<Integer> iMax = new ArrayList<Integer>();
		double temp = p.get(0).x;
		
		for(int i=0;i<p.size();i++)
		{
			if(temp > p.get(i).x){
				iMax.clear();
				iMax.add(i);
				temp = p.get(i).x;
			}
			else if(temp == p.get(i).x){
				iMax.add(i);
			}
			
			/*if(min_x>=p.get(i).x)
			{
				min_x=p.get(i).x;
				min_index=i;
			}*/
		}
		int min = iMax.get(0);
		//if two or more points have same min value for x, we send the point with max y
		for(int i=0; i<iMax.size();i++){
			if(p.get(min).y < p.get(iMax.get(i)).y){
				min = iMax.get(i);
			}
		}
		 point p_min = new point();
		 p_min.x = p.get(min).x;
	     p_min.y = p.get(min).y;
	     return p_min;
	}
	// find max class for finding maximum value of x among coordinates (pl,pr)
	public point  find_max(ArrayList<point> p)
	{
		ArrayList<Integer> iMin = new ArrayList<Integer>();
		double temp = p.get(0).x;
		
		for(int i=0;i<p.size();i++)
		{
			if(temp < p.get(i).x){
				iMin.clear();
				iMin.add(i);
				temp = p.get(i).x;
			}
			else if(temp == p.get(i).x){
				iMin.add(i);
			}
			
		}
		//if two or more points have same max value for x, we send the point with max y
		int max = iMin.get(0);
		for(int i=0; i<iMin.size();i++){
			if(p.get(max).y < p.get(iMin.get(i)).y){
				max = iMin.get(i);
			}
		}
		 point p_max = new point();
		 p_max.x = p.get(max).x;
	     p_max.y = p.get(max).y;
	     return p_max;
	}
	public ArrayList<point> upper_hull(ArrayList<point> p)
	{
		point pmin = find_min(p);
		point pmax = find_max(p);
		if((pmin.x==pmax.x && pmin.y == pmax.y)||p.size()<=2)
		{
			System.out.println("13");
			return p;
		}	
		else
		{
			System.out.println(p.size());
			Find_median fm = new Find_median();
			int sz = p.size();
			double x_median;
			if(sz%2 != 0){
				  int index = (int) Math.floor(sz/2);
				  x_median = fm.nthSmallest(p,0,sz-1,index+1);
			}
			else{
					 int index1 = (int) Math.floor(sz/2);
					 int index2 = (int) Math.floor((sz/2) + 1);
					 System.out.println(index1 + " " + index2 );
					 double median1 = fm.nthSmallest(p,0,sz-1,index1);
					 double median2 = fm.nthSmallest(p,0,sz-1,index2);
					 x_median = (median1 + median2)/2;
					
			}
			

			
			Upper_bridge ub = new Upper_bridge();
			ArrayList<point> bp = ub.upperbridge(p,x_median);
			
			// two array lists for tleft and tright
			
			ArrayList<point> t_left = new ArrayList<point>();
			ArrayList<point> t_right = new ArrayList<point>();
			point pleft = new point();
			point pright = new point();
			pleft = bp.get(0);
			pright = bp.get(1);
		//	t_left.add(pleft);
		//	t_right.add(pright);
			System.out.println("points");
			System.out.println(pleft.x+" "+pleft.y);
			System.out.println(pright.x+" "+pright.y);
			
			
		/*	for(int k=0;k<p.size();k++)
			{
				double area = pleft.x*(pmin.y-p.get(k).y)+pmin.x*(p.get(k).y-pleft.y)+p.get(k).x*(pleft.y-pmin.y);
				if(area<=0)
				{
					point temp=new point();
					temp=p.get(k);
					t_left.add(temp);
				}
				
				double area1 = pright.x*(pmax.y-p.get(k).y)+pmax.x*(p.get(k).y-pright.y)+p.get(k).x*(pright.y-pmax.y);
			    if(area1>=0)
			    {
			    	point temp1=new point();
					temp1=p.get(k);
					t_right.add(temp1);
			    }
			}
			*/
			// dividing total points 
			for(int k=0;k<p.size();k++)
			{
				point temp=new point();
				temp=p.get(k);
				if(p.get(k).x<=pleft.x)
				{
					t_left.add(temp);
				}
				if(p.get(k).x>=pright.x)
				{
					t_right.add(temp);
				}
			}
			
			System.out.println("t_left array ........");
			for(int k=0;k<t_left.size();k++)
			{
				System.out.println(p.get(k).x+" "+p.get(k).y);
			}
			 
			//ArrayList<point> left = new ArrayList<point>();
			//ArrayList<point> right = new ArrayList<point>();
			
			ArrayList<point> left = upper_hull(t_left);
			ArrayList<point> right = upper_hull(t_right);
		    left.addAll(right);
		
		   return left;
		}
			
	}

}
