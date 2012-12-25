package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.gorse.lorenz.geometry3d.Point3D;
import de.gorse.lorenz.geometry3d.Ray3D;
import de.gorse.lorenz.geometry3d.Vector3D;

public class Ray3DTest {

	@Test
	public void testDirection() {
		Ray3D r = new Ray3D( new Point3D( 3, 4, -2 ), new Point3D( 10, 10, 10 ) );
		assertEquals( new Vector3D( 7, 6, 12 ), r.getDirection() );
	}

	@Test
	public void testChangeOfStartPoint() {
		Ray3D r = new Ray3D( new Point3D( 3, 4, -2 ), new Point3D( 10, 10, 10 ) );
		assertEquals( new Vector3D( 7, 6, 12 ), r.getDirection() );
		
		r.setStart( new Point3D( 4, -2, 3 ) );
		assertEquals( new Vector3D( 6, 12, 7 ), r.getDirection() );	
		
		r.setOther( new Point3D( 0, 0, 0 ) );
		assertEquals( new Vector3D( -4, 2, -3 ), r.getDirection() );		
	}

}
