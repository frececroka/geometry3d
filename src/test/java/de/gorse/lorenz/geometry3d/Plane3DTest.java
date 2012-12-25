package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.commons.math.linear.SingularMatrixException;
import org.junit.Ignore;
import org.junit.Test;

import de.gorse.lorenz.geometry3d.Geometry3DUtils;
import de.gorse.lorenz.geometry3d.Plane3D;
import de.gorse.lorenz.geometry3d.Point3D;

public class Plane3DTest {

	@Test
	public void testPlaneCreationFromThreePoints() {
		try {
			new Plane3D( new Point3D( 0, 0, 0 ), new Point3D( 1, 1, 1 ), new Point3D( 2, 2, 2 ) );
			fail( "SingularMatrixException expected" );
		} catch ( SingularMatrixException e ) {
		}
		
		Plane3D p1 = new Plane3D( new Point3D( 0, 0, 1 ), new Point3D( 1, 0, 1 ), new Point3D( 0, 1, 1 ) );
		assertEquals( 0, p1.getA(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p1.getB(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p1.getC(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p1.getD(), Geometry3DUtils.getDelta() );

		Plane3D p2 = new Plane3D( new Point3D( 0, 0, 0 ), new Point3D( 1, 0, 0 ), new Point3D( 0, 1, 0 ) );
		assertEquals( 0, p2.getA(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p2.getB(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p2.getC(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p2.getD(), Geometry3DUtils.getDelta() );
	}

}
