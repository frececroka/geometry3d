package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.gorse.lorenz.geometry3d.Geometry3DUtils;
import de.gorse.lorenz.geometry3d.Point3D;
import de.gorse.lorenz.geometry3d.Vector3D;

public class Vector3DTest {

	@Test
	public void testVectorBeweenTwoPoints() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 6, 1, 9 );
		Point3D p3 = new Point3D( 1, 24, 7 );
		Point3D p4 = new Point3D( 8, 5, 3 );
		
		Vector3D p1p2 = new Vector3D( p1, p2 );
		Vector3D p1p3 = new Vector3D( p1, p3 );
		Vector3D p2p4 = new Vector3D( p2, p4 );

		assertEquals( new Vector3D( 3, -3, 4 ), p1p2 );
		assertEquals( new Vector3D( -2, 20, 2 ), p1p3 );
		assertEquals( new Vector3D( 2, 4, -6 ), p2p4 );
	}
	
	@Test
	public void testDotProduct() {
		Vector3D v1 = new Vector3D( 1, 2, 3 );
		Vector3D v2 = new Vector3D( 3, 4, 5 );
		Vector3D v3 = new Vector3D( 6, 1, 4 );

		assertEquals( 14, Vector3D.dotProduct( v1, v1 ), Geometry3DUtils.getDelta() );
		assertEquals( 26, Vector3D.dotProduct( v1, v2 ), Geometry3DUtils.getDelta() );
		assertEquals( 20, Vector3D.dotProduct( v1, v3 ), Geometry3DUtils.getDelta() );
		assertEquals( 26, Vector3D.dotProduct( v2, v1 ), Geometry3DUtils.getDelta() );
		assertEquals( 50, Vector3D.dotProduct( v2, v2 ), Geometry3DUtils.getDelta() );
		assertEquals( 42, Vector3D.dotProduct( v2, v3 ), Geometry3DUtils.getDelta() );
	}
	
	@Test
	public void testCrossProduct() {
		Vector3D v1 = new Vector3D( 1, 2, 3 );
		Vector3D v2 = new Vector3D( 3, 4, 5 );
		Vector3D v3 = new Vector3D( 6, 1, 4 );

		assertEquals( new Vector3D( 0, 0, 0 ), Vector3D.crossProduct( v1, v1 ) );
		assertEquals( new Vector3D( -2, 4, -2 ), Vector3D.crossProduct( v1, v2 ) );
		assertEquals( new Vector3D( 5, 14, -11 ), Vector3D.crossProduct( v1, v3 ) );
		assertEquals( new Vector3D( 2, -4, 2 ), Vector3D.crossProduct( v2, v1 ) );
		assertEquals( new Vector3D( 0, 0, 0 ), Vector3D.crossProduct( v2, v2 ) );
		assertEquals( new Vector3D( 11, 18, -21 ), Vector3D.crossProduct( v2, v3 ) );
	}
	
	@Test
	public void testEquals() {
		Vector3D v1 = new Vector3D( 1, 2, 3 );
		Vector3D v4 = new Vector3D( 1, 2, 3 );
		
		Vector3D v2 = new Vector3D( 1, 2, 5 );
		Vector3D v3 = new Vector3D( 1, 6, 3 );
		Vector3D v5 = new Vector3D( 5, 2, 3 );

		assertEquals( v1, v1 );
		assertEquals( v1, v4 );

		assertFalse( v1.equals( v2 ) );
		assertFalse( v1.equals( v3 ) );
		assertFalse( v1.equals( v5 ) );
		assertFalse( v1.equals( null ) );
	}
	
	@Test
	public void testTwoVectorsAreParallel() {
		Vector3D v1 = new Vector3D( 1, 2, 3 );		
		Vector3D v2 = new Vector3D( 2, 4, 6 );
		Vector3D v3 = new Vector3D( 1 * 0.234564, 2 * 0.234564, 3 * 0.234564 );
		Vector3D v4 = new Vector3D( 5, 2, 3 );
		Vector3D v5 = new Vector3D( 1, 5, 2 );
		Vector3D v6 = new Vector3D( 1, 2, 6 );

		assertTrue( Vector3D.areParallel( v1, v1 ) );
		assertTrue( Vector3D.areParallel( v1, v2 ) );
		assertTrue( Vector3D.areParallel( v1, v3 ) );
		
		assertFalse( Vector3D.areParallel( v1, v4 ) );
		assertFalse( Vector3D.areParallel( v1, v5 ) );
		assertFalse( Vector3D.areParallel( v1, v6 ) );
	}
	
	@Test
	public void testMultiply() {
		Vector3D v1 = new Vector3D( 1, 2, 3 );		
		Vector3D v2 = new Vector3D( 2, 4, 6 );

		v1.multiply( 2 );
		assertEquals( v2, v1 );
		
		v1.multiply( 1/3 );
		v2.multiply( 1/3 );
		assertEquals( v2, v1 );
	}
	
	@Test
	public void testAngleBetween() {
		Vector3D v1 = new Vector3D( 1, 0, 0 );
		Vector3D v2 = new Vector3D( 0, 1, 0 );
		
		assertEquals( 90, Vector3D.getAngleBetween( v1, v2 ), Geometry3DUtils.getDelta() );
	}

}
