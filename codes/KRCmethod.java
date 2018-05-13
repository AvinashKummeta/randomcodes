package hull;

import java.util.ArrayList;
import java.util.Iterator;
// main kirkaptrik class  
public class Kirkpatrik {
                                                /// main KPS function 
	public ArrayList<point>  KPS( ArrayList<point> p)
	{
		double min_x=100000,max_x=0;
		int min_index=0,max_index=0;
		ArrayList<Integer> iMax = new ArrayList<Integer>();
		ArrayList<Integer> iMin = new ArrayList<Integer>();
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
		for(int i=0; i<iMax.size();i++){
			if(p.get(min).y < p.get(iMax.get(i)).y){
				min = iMax.get(i);
			}
		}
		
		temp = p.get(0).x;
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
			/*if(max_x<p.get(i).x)
			{
				max_x=p.get(i).x;
				max_index=i;
			}*/
		}
		int max = iMin.get(0);
		for(int i=0; i<iMin.size();i++){
			if(p.get(max).y < p.get(iMin.get(i)).y){
				max = iMin.get(i);
			}
		}
        point p_min = new point();
        point p_max = new point();
        p_min.x = p.get(min).x;
        p_min.y = p.get(min).y;
        
        p_max.x = p.get(max).x;
        p_max.y = p.get(max).y;
        
		UpperHull h = new UpperHull();
		
	    ArrayList<point> upper = h.upper_hull(p);
		ArrayList<point> tp = new ArrayList<point>();
		tp.addAll(p);
		for(int k=0;k<tp.size();k++)
		{
			double x1,y1;
			point temp3 = new point();
			temp3 = tp.get(k);
			x1=temp3.x; y1 = -1*temp3.y;
			temp3.y = y1;		
		} 
		
		ArrayList<point> lower = h.upper_hull(tp);
		
		upper.addAll(lower);
		
		return upper;
		
	}
	
}
