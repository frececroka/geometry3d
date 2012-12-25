package de.gorse.lorenz.geometry3d;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.gorse.lorenz.geometry3d.Geometry3DUtils;
import de.gorse.lorenz.geometry3d.Line3D;
import de.gorse.lorenz.geometry3d.Line3DSegment;
import de.gorse.lorenz.geometry3d.Plane3D;
import de.gorse.lorenz.geometry3d.Point3D;
import de.gorse.lorenz.geometry3d.Ray3D;
import de.gorse.lorenz.geometry3d.Vector3D;

public class Geometry3DUtilsTest {

	@Test
	public void testIntersectionOfLineWithArbitraryPlaneA() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );
		Point3D p5 = new Point3D( 0, 0, 0 );

		double intersection;
		Plane3D p = new Plane3D( p1, p2, p3 );

		Line3D l1 = new Line3D( p4, p5 );
		intersection = Geometry3DUtils.intersectionOfLineWithPlaneRaw( l1, p );
		assertEquals( -1.177215189873418, intersection, Geometry3DUtils.getDelta() );
	}

	@Test
	public void testIntersectionOfLineWithArbitraryPlaneB() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );

		double intersection;
		Plane3D p = new Plane3D( p1, p2, p3 );

		Line3D l2 = new Line3D( p2, p4 );
		intersection = Geometry3DUtils.intersectionOfLineWithPlaneRaw( l2, p );
		assertEquals( 0, intersection, Geometry3DUtils.getDelta() );
	}

	@Test
	public void intersectionOfLineWithPlaneParallelToXZ() {
		Point3D p1 = new Point3D( 1, 1, 1 );
		Point3D p2 = new Point3D( 2, 1, 1 );
		Point3D p3 = new Point3D( 1, 1, 2 );

		Plane3D p = new Plane3D( p1, p2, p3 );

		Point3D p4 = new Point3D( 3, 3, 3 );
		Point3D p5 = new Point3D( 3, -3, 3 );

		Line3D l = new Line3D( p4, p5 );

		assertEquals( new Point3D( 3, 1, 3 ), Geometry3DUtils.intersectionOfLineWithPlane( l, p ) );
	}

	@Test
	public void intersectionOfLineWithPlaneParallelToYZ() {
		Point3D p1 = new Point3D( 1, 1, 1 );
		Point3D p2 = new Point3D( 1, 2, 1 );
		Point3D p3 = new Point3D( 1, 1, 2 );

		Plane3D p = new Plane3D( p1, p2, p3 );

		Point3D p4 = new Point3D( 3, 13, 7 );
		Point3D p5 = new Point3D( -3, 5, -3 );

		Line3D l = new Line3D( p4, p5 );

		assertEquals( new Point3D( 1, 10.333333333333334, 3.666666666666667 ),
				Geometry3DUtils.intersectionOfLineWithPlane( l, p ) );
	}

	@Test
	public void testIntersectionOfTwoLines() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );

		// First line: Through p2 and p3
		Line3D l1 = new Line3D( p2, p3 );

		// Second line: Through p1 and ( p1 + 3 * [p1:p3] )
		Vector3D dir2 = new Vector3D( p1, p3 );
		dir2.multiply( 3 );
		Line3D l2 = new Line3D( p1, dir2 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );
		assertEquals( p3, intersect );
	}

	@Test
	public void testIntersectionOfTwoSkewLines() {

		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 3, 3, 1 );

		Line3D l1 = new Line3D( p2, p3 );
		Line3D l2 = new Line3D( p1, p4 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );
		assertEquals( null, intersect );
	}

	@Test
	public void testIntersectionOfTwoParallelLines() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 13, 14, 15 );
		Point3D p4 = new Point3D( 18, 12, 16 );

		Line3D l1 = new Line3D( p2, p1 );
		Line3D l2 = new Line3D( p1, new Vector3D( p3, p4 ) );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );

		assertEquals( null, intersect );
	}

	@Test
	public void testIntersectionOfTwoIdenticalLines() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );

		Line3D l1 = new Line3D( p2, p1 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l1 );

		assertEquals( null, intersect );
	}

	@Test
	public void testIntersectionOfTwoLinesWithXDirectionOfFirstLineZero() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );

		Line3D l1 = new Line3D( p2, new Vector3D( 0, 4, 3 ) );
		Line3D l2 = new Line3D( p2, p1 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );

		assertEquals( p2, intersect );
	}

	@Test
	public void testIntersectionOfTwoLinesWithXAndYDirectionOfFirstLineZero() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );

		Line3D l1 = new Line3D( p2, new Vector3D( 0, 0, 3 ) );
		Line3D l2 = new Line3D( p2, p1 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );

		assertEquals( p2, intersect );
	}

	@Test
	public void testIntersectionOfTwoLinesWithNullVectorAsDirectionOfFirstLine() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );

		Line3D l1 = new Line3D( p2, new Vector3D( 0, 0, 0 ) );
		Line3D l2 = new Line3D( p2, p1 );

		Point3D intersect = Geometry3DUtils.intersectionOfTwoLines( l1, l2 );

		assertEquals( null, intersect );
	}

	@Test
	public void testIntersectionOfLineWithLineSegmentWhereIntersectionsExists() {
		Point3D p1 = new Point3D( 3, 4, 5 );
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D intersect;

		Line3D l1 = new Line3D( p2, p3 );
		Line3D l2 = new Line3D( p1, p2 );
		Line3DSegment ls1 = new Line3DSegment( p1, l2.getPoint( 3 ) );

		intersect = Geometry3DUtils.intersectionOfTwoLines( l1, ls1 );
		assertEquals( p2, intersect );
	}

	@Test
	public void testIntersectionOfLineWithLineSegmentWhereIntersectionIsNotPartOfLineSegment() {
		Point3D p2 = new Point3D( 8, 2, 6 );
		Point3D p3 = new Point3D( 0, 9, 1 );
		Point3D p4 = new Point3D( 5, 7, 2 );
		Point3D intersect;

		Line3D l1 = new Line3D( p2, p3 );
		Line3D l2 = new Line3D( p2, p4 );
		Line3DSegment ls1 = new Line3DSegment( l2.getPoint( 0.01 ), p4 );

		intersect = Geometry3DUtils.intersectionOfTwoLines( l1, ls1 );
		assertEquals( null, intersect );
	}

	@Test
	public void testIntersectionOfLineWithLineSegmentWhereLinesAreSkew() {
		Point3D p1 = new Point3D( 8, 2, 6 );
		Point3D p2 = new Point3D( 0, 9, 1 );
		Point3D p3 = new Point3D( 5, 7, 2 );
		Point3D p4 = new Point3D( 3, 1, 0 );

		Line3D l1 = new Line3D( p1, p2 );
		Line3DSegment ls1 = new Line3DSegment( p3, p4 );

		assertEquals( null, Geometry3DUtils.intersectionOfTwoLines( l1, ls1 ) );
	}

	@Test
	public void testIntersectionOfRayWithLineSegmentWhereNoExactIntersectionExists() {		
		Ray3D r = new Ray3D( new Point3D( -2.4999999999999996, 6.281407035175878, -0.012562814070351487 ), new Point3D(
				-2.4999999999999996, 3.02, 1.7 ) );
		Line3DSegment l = new Line3DSegment( new Point3D( -2.5, 5.0, -3.0 ), new Point3D( -2.5, 5.0, 3.0 ) );
		
		Point3D intersection = Geometry3DUtils.intersectionOfTwoLines( r, l );
		
		assertEquals( new Point3D( -2.5, 5, 0.6603032264028841 ), intersection );
	}

}
