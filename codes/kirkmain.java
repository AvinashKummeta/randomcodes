package hull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Kirikpat {                                 // main method class

	public static void main(String args[]) throws FileNotFoundException
	{
		// file reading  
		File fp = new File("C:/Users/avina/workspace/Kirik/src/hull/input2.txt");
		Scanner s=new Scanner(fp);		  
		int n =s.nextInt();
		int i=0,min_index=0,max_index=0;
	   // input points are stored in array list
		ArrayList<point> point_list = new ArrayList<point>();	
		while(i<n)
		{
			double X = s.nextDouble(),Y = s.nextDouble();
			point pt=new point();
			pt.x=X;pt.y=Y;
			point_list.add(pt);
			i++;
		 }
	/*	for(int l=0;l<point_list.size();l++)
		{
			System.out.println(point_list.get(l).x + " "+point_list.get(l).y+"\n");
		}
		*/
		 Kirkpatrik k = new Kirkpatrik();
		 ArrayList<point> flist = k.KPS(point_list); 
		 s.close();
		 //System.out.println("printing......");
		 for(int l=0;l<flist.size();l++)
		 {
			 System.out.println(flist.get(l).x + " "+ -1*flist.get(l).y);
		 }
		 
	}
}
