package de.gorse.lorenz.geometry3d;

public class Geometry3DUtils {

	private static double delta = 0.000001;

	public static Point3D intersectionOfTwoLines( AbstractLine3D r, AbstractLine3D s ) {
		double[] t = Geometry3DUtils.intersectionOfTwoLinesRaw( r, s );

		if ( t == null || !r.isValidCoefficient( t[0] ) || !s.isValidCoefficient( t[1] ) ) {
			return null;
		} else {
			return r.getPoint( t[0] );
		}
	}

	public static double[] intersectionOfTwoLinesRaw( AbstractLine3D a, AbstractLine3D b ) {
		if ( a.getDirection().isNull() || b.getDirection().isNull() ) {
			// At least one line is no real line
			return null;
		}

		if ( Vector3D.areParallel( a.getDirection(), b.getDirection() ) ) {
			// Both lines are parallel
			return null;
		}

		double ax = a.getStart().getX();
		double ay = a.getStart().getY();
		double az = a.getStart().getZ();

		double aa = a.getDirection().getX();
		double ab = a.getDirection().getY();
		double ac = a.getDirection().getZ();

		double bx = b.getStart().getX();
		double by = b.getStart().getY();
		double bz = b.getStart().getZ();

		double ba = b.getDirection().getX();
		double bb = b.getDirection().getY();
		double bc = b.getDirection().getZ();

		double r = 0;
		double s = 0;

		boolean iHaveR = false;
		boolean iHaveS = false;
		
		/*
		 * We have a system of linear equations:
		 * 
		 * ax + r * aa = bx + s * ba
		 * ay + r * ab = by + s * bb
		 * az + r * ac = bz + s * bc
		 */

		if ( aa != 0 && ba == 0 ) {
			r = Geometry3DUtils.getR( ax, aa, bx );
			iHaveR = true;
		} else if ( ab != 0 && bb == 0 ) {
			r = Geometry3DUtils.getR( ay, ab, by );
			iHaveR = true;
		} else if ( ac != 0 && bc == 0 ) {
			r = Geometry3DUtils.getR( az, ac, bz );
			iHaveR = true;
		} else if ( ba != 0 && aa == 0 ) {
			s = Geometry3DUtils.getS( bx, ba, ax );
			iHaveS = true;
		} else if ( bb != 0 && ab == 0 ) {
			s = Geometry3DUtils.getS( by, bb, ay );
			iHaveS = true;
		} else if ( bc != 0 && ac == 0 ) {
			s = Geometry3DUtils.getS( bz, bc, az );
			iHaveS = true;
		} else {
			s = ( by * aa + ax * ab - bx * ab - ay * aa ) / ( ba * ab - bb * aa );
			r = ( bx - ax + s * ba ) / aa;

			iHaveR = true;
			iHaveS = true;
		}

		if ( iHaveR && !iHaveS ) {
			if ( ba != 0 ) {
				s = ( ax + r * aa - bx ) / ba;
				iHaveS = true;
			} else if ( bb != 0 ) {
				s = ( ay + r * ab - by ) / bb;
				iHaveS = true;
			} else if ( bc != 0 ) {
				s = ( az + r * ac - bz ) / bc;
				iHaveS = true;
			}
		} else if ( iHaveS && !iHaveR ) {
			if ( aa != 0 ) {
				r = ( bx + s * ba - ax ) / aa;
				iHaveR = true;
			} else if ( ab != 0 ) {
				r = ( by + s * bb - ay ) / ab;
				iHaveR = true;
			} else if ( ac != 0 ) {
				r = ( bz + s * bc - az ) / ac;
				iHaveR = true;
			}
		} else if ( !iHaveR && !iHaveS ) {
			throw new RuntimeException( "I couldn't calculate r nor s" );
		}

		if ( !iHaveR || !iHaveS )
			throw new RuntimeException( "I couldn't calculate r or s" );

		if ( equal( ax + r * aa, bx + s * ba ) && equal( ay + r * ab, by + s * bb ) && equal( az + r * ac, bz + s * bc )
				&& a.isValidCoefficient( r ) && b.isValidCoefficient( s ) ) {
			return new double[] { r, s };
		} else {
			return null;
		}
	}

	private static double getR( double ax, double aa, double bx ) {
		return ( bx - ax ) / aa;
	}

	private static double getS( double bx, double ba, double ax ) {
		return ( ax - bx ) / ba;
	}

	public static Point3D intersectionOfLineWithPlane( AbstractLine3D r, Plane3D p ) {
		double s = Geometry3DUtils.intersectionOfLineWithPlaneRaw( r, p );

		if ( r.isValidCoefficient( s ) )
			return r.getPoint( s );
		else
			return null;
	}

	public static double intersectionOfLineWithPlaneRaw( AbstractLine3D l, Plane3D p ) {
		return ( p.getD() - p.getA() * l.getStart().getX() - p.getB() * l.getStart().getY() - p.getC()
				* l.getStart().getZ() )
				/ ( p.getA() * l.getDirection().getX() + p.getB() * l.getDirection().getY() + p.getC()
						* l.getDirection().getZ() );
	}

	public static boolean equal( double a, double b ) {
		return Math.abs( a - b ) < Geometry3DUtils.delta;
	}

	public static double getDelta() {
		return Geometry3DUtils.delta;
	}

}
