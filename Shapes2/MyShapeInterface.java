package application;

import java.util.ArrayList;
import java.util.HashSet;

public interface MyShapeInterface {
	MyRectangle getMyBoundingRectangle();
	ArrayList<MyPoint> getMyArea();
	
	static ArrayList<MyPoint> overlapMyShapes(MyShape shape1, MyShape shape2) {
		// Get area of two shapes
		ArrayList<MyPoint> first = shape1.getMyArea();
		ArrayList<MyPoint> second = shape2.getMyArea();
		
		if (first.size() == 0 || second.size() == 0)
			return null;
		
		// Find intersection of two shapes
		HashSet<MyPoint> map = new HashSet<>();
		for (MyPoint p : first)
			map.add(p);
		
		first.clear();
		for (MyPoint p : second)
			if (map.contains(p))
				first.add(p);
		
		// If they share nothing, return null
		if (first.size() == 0)
			return null;
		
		return first;
	}
}
