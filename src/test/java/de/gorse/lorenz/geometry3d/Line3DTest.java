package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Line3DTest {
	
	@Test
	public void testPointIsOnLineWithPointsOnLine() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );

		Line3D l1 = new Line3D( p2, p3 );
		Line3D l2 = new Line3D( p1, p4 );

		assertTrue( l1.pointIsOnLine( p2 ) );
		assertTrue( l1.pointIsOnLine( p3 ) );

		assertTrue( l2.pointIsOnLine( p1 ) );
		assertTrue( l2.pointIsOnLine( p4 ) );
	}

	@Test
	public void testPointIsOnLineWithPointsBeneathLine() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );

		Line3D l1 = new Line3D( p2, p3 );
		Line3D l2 = new Line3D( p1, p4 );

		assertFalse( l1.pointIsOnLine( p1 ) );
		assertFalse( l1.pointIsOnLine( p4 ) );

		assertFalse( l2.pointIsOnLine( p2 ) );
		assertFalse( l2.pointIsOnLine( p3 ) );
		
		assertFalse( l2.pointIsOnLine( null ) );
	}

}
