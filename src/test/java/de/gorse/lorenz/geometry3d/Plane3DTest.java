package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math.linear.SingularMatrixException;
import org.junit.Test;

public class Plane3DTest {

	@Test( expected = SingularMatrixException.class )
	public void testPlaneCreationFromThreePointsWithAllThreePointsOnTheSameLine() {
		new Plane3D( new Point3D( 0, 0, 0 ), new Point3D( 1, 1, 1 ), new Point3D( 2, 2, 2 ) );
	}

	@Test
	public void testPlaneCreationFromThreeArbitraryPoints() {
		Plane3D p1 = new Plane3D( new Point3D( 0, 0, 1 ), new Point3D( 1, 0, 1 ), new Point3D( 0, 1, 1 ) );
		assertEquals( 0, p1.getA(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p1.getB(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p1.getC(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p1.getD(), Geometry3DUtils.getDelta() );
	}

	@Test
	public void testPlaneCreationFromThreePointsLocatedInXYPlane() {
		Plane3D p2 = new Plane3D( new Point3D( 0, 0, 0 ), new Point3D( 1, 0, 0 ), new Point3D( 0, 1, 0 ) );
		assertEquals( 0, p2.getA(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p2.getB(), Geometry3DUtils.getDelta() );
		assertEquals( 1, p2.getC(), Geometry3DUtils.getDelta() );
		assertEquals( 0, p2.getD(), Geometry3DUtils.getDelta() );
	}

}
