package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.gorse.lorenz.geometry3d.Geometry3DUtils;
import de.gorse.lorenz.geometry3d.Point3D;

public class Point3DTest {

	@Test
	public void testDistance() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );

		assertEquals( 0, Point3D.getDistance( p1, p1 ), Geometry3DUtils.getDelta() );
		assertEquals( 5.477225575051661, Point3D.getDistance( p1, p2 ), Geometry3DUtils.getDelta() );
		assertEquals( 7.0710678118654755, Point3D.getDistance( p1, p3 ), Geometry3DUtils.getDelta() );
		assertEquals( 4.123105625617661, Point3D.getDistance( p1, p4 ), Geometry3DUtils.getDelta() );
		
		assertEquals( 0, Point3D.getDistance( p2, p2 ), Geometry3DUtils.getDelta() );
		assertEquals( 11.74734012447073, Point3D.getDistance( p2, p3 ), Geometry3DUtils.getDelta() );
		assertEquals( 7.14142842854285, Point3D.getDistance( p2, p4 ), Geometry3DUtils.getDelta() );
		
		assertEquals( 0, Point3D.getDistance( p3, p3 ), Geometry3DUtils.getDelta() );
		assertEquals( 6.708203932499369, Point3D.getDistance( p3, p4 ), Geometry3DUtils.getDelta() );
		
		assertEquals( 0, Point3D.getDistance( p4, p4 ), Geometry3DUtils.getDelta() );
	}
	
	@Test
	public void testEquals() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 3, 4, 5 );
		Point3D p3 = new Point3D( 3, 4, 1 );
		Point3D p4 = new Point3D( 3, 3, 5 );
		Point3D p5 = new Point3D( 2, 4, 5 );

		assertTrue( p1.equals( p1 ) );
		assertTrue( p1.equals( p2 ) );

		assertFalse( p1.equals( p3 ) );
		assertFalse( p1.equals( p4 ) );
		assertFalse( p1.equals( p5 ) );
		assertFalse( p1.equals( null ) );
	}

}
