package de.gorse.lorenz.geometry3d;

/**
 * This class provides common geometrical operations.
 * 
 * @author frececroka
 */
public class Geometry3DUtils {

	private static double delta = 0.000001;

	/**
	 * Returns the point both the lines have in common or null, if they are skew
	 * or parallel.
	 * 
	 * @param r
	 *            The first line
	 * @param s
	 *            The second line
	 * @return The point both lines have in common or null, if they are skew or
	 *         parallel.
	 */
	public static Point3D intersectionOfTwoLines( AbstractLine3D r, AbstractLine3D s ) {
		double[] t = Geometry3DUtils.intersectionOfTwoLinesRaw( r, s );

		if ( t == null || !r.isValidCoefficient( t[0] ) || !s.isValidCoefficient( t[1] ) ) {
			return null;
		} else {
			return r.getPoint( t[0] );
		}
	}

	/**
	 * Calculates the intersection of the lines <code>a</code> and
	 * <code>b</code> as two doubles <code>r</code> and <code>s</code> with
	 * <code>a.getPoint( r ).equals( b.getPoint( s ) )</code>.
	 * 
	 * @param a
	 *            The first line
	 * @param b
	 *            The second line
	 * @return An array with two doubles. The first element is the coefficient
	 *         <code>r</code>, the second element is the coefficient
	 *         <code>s</code>.
	 */
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

		// We have a system of linear equations:
		//
		// @formatter:off
		// ax + r * aa = bx + s * ba
		// ay + r * ab = by + s * bb
		// az + r * ac = bz + s * bc
		// @formatter:on

		// We try to get either r or s. We can calculate r if we have one
		// equation where the coefficient of s is zero but the one of r isn't.
		// We can calculate s if we have one equation where the coefficient of r
		// is zero but the one of s isn't.
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
			// If none of the above conditions is true, we know that the
			// elements of one of the pairs (aa; ba), (ab; bb) and (ac; bc) can
			// be both zero. All other elements are different from zero.
			if ( aa != 0 && ba != 0 && ab != 0 && bb != 0 ) {
				// The pairs (aa; ba) and (ab; bb) are different from zero. We
				// use the first and the second equation to calculate r and s.
				s = ( by * aa + ax * ab - bx * ab - ay * aa ) / ( ab * ba - aa * bb );
				r = ( bx - ax + s * ba ) / aa;
			} else if ( ab != 0 && bb != 0 && ac != 0 && bc != 0 ) {
				// The pairs (ab; bb) and (ac; bc) are different from zero. We
				// use the second and the third equation to calculate r and s.
				s = ( bz * ab + ay * ac - by * ac - az * ab ) / ( ac * bb - ab * bc );
				r = ( by - ay + s * bb ) / ab;
			} else if ( ac != 0 && bc != 0 && aa != 0 && ba != 0 ) {
				// The pairs (ac; bc) and (aa; bb) are different from zero. We
				// use the third and the first equation to calculate r and s.
				s = ( bx * ac + az * aa - bz * aa - ax * ac ) / ( aa * bc - ac * ba );
				r = ( bz - az + s * bc ) / ac;
			} else {
				// We cannot calculate r and s. This isn't expected to happen,
				// so we throw a RuntimeException.
				throw new RuntimeException( "I couldn't calculate r nor s" );
			}

			iHaveR = true;
			iHaveS = true;
		}

		// Now, that we have at least one of r and s, we can easily calculate
		// the missing one.
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
		}

		if ( !iHaveR || !iHaveS ) {
			// We cannot calculate r and s. This isn't expected to happen,
			// so we throw a RuntimeException.
			throw new RuntimeException( "I couldn't calculate r or s" );
		}

		// We insert r and s in the original equations and check, whether they
		// are approximately equal. If they are, we return r and s. If they
		// aren't, we return null, which indicates that no intersection was
		// found.
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

	/**
	 * Returns the point both the line and the plane have in common or null, if
	 * they are parallel.
	 * 
	 * @param l
	 *            The line
	 * @param p
	 *            The plane
	 * @return The point both elements have in common or null if thay have zero
	 *         or infinite points in common.
	 */
	public static Point3D intersectionOfLineWithPlane( AbstractLine3D l, Plane3D p ) {
		double s = Geometry3DUtils.intersectionOfLineWithPlaneRaw( l, p );

		if ( l.isValidCoefficient( s ) )
			return l.getPoint( s );
		else
			return null;
	}

	/**
	 * Calculates the intersection of the given line <code>l</code> with the
	 * given plane <code>p</code>. The intersection is returned as a double
	 * <code>d</code> with <code>l.getPoint( d )</code> as the intersecting
	 * point. This method returns <code>d</code> regardless of whether it is a
	 * valid coefficient of this line or not.
	 * 
	 * @param l
	 *            The line
	 * @param p
	 *            The plane
	 * @return The double <code>d</code> with <code>l.getPoint( d )</code> as
	 *         the intersecting point.
	 */
	public static double intersectionOfLineWithPlaneRaw( AbstractLine3D l, Plane3D p ) {
		return ( p.getD() - p.getA() * l.getStart().getX() - p.getB() * l.getStart().getY() - p.getC()
				* l.getStart().getZ() )
				/ ( p.getA() * l.getDirection().getX() + p.getB() * l.getDirection().getY() + p.getC()
						* l.getDirection().getZ() );
	}

	/**
	 * Calculates the two coefficients <code>p</code> and <code>q</code> with
	 * <code>s + p*u + q*v = t</code>.
	 * 
	 * @param s
	 *            The first point.
	 * @param u
	 *            The first vector which can be used to reach the second point
	 *            from the first point.
	 * @param v
	 *            The second vector which can be used to reach the second point
	 *            from the first point.
	 * @param t
	 *            The second point.
	 * @return The two coefficients <code>p</code> and <code>q</code> as an
	 *         array.
	 */
	public static double[] reachPoint( Point3D s, Vector3D u, Vector3D v, Point3D t ) {
		// This problem is essentially the same as the intersection of two
		// lines. The first line includes the point s and has the vector u as
		// its direction. The second line includes the point t and has the
		// vector -v as its direction.

		// @formatter:off
		// px + p * ux = tx + q * -vx
		// py + p * uy = ty + q * -vy
		// pz + p * uz = tz + q * -vz
		// @formatter:on

		// intersection[0] == p
		// intersection[1] == -q
		double[] intersection = Geometry3DUtils.intersectionOfTwoLinesRaw( new Line3D( s, u ), new Line3D( t, v ) );
		intersection[1] = -intersection[1];
		return intersection;
	}

	/**
	 * Calculates the coefficient <code>p</code> with <code>s + p*v = t</code>.
	 * 
	 * @param s
	 *            The first point.
	 * @param v
	 *            The vector which can be used to reach the second point from
	 *            the first point.
	 * @param t
	 *            The second point.
	 * @return The coefficient <code>p</code>.
	 */
	public static double reachPoint( Point3D s, Vector3D v, Point3D t ) {
		double px = s.getX();
		double py = s.getY();
		double pz = s.getZ();

		double vx = v.getX();
		double vy = v.getY();
		double vz = v.getZ();

		double tx = t.getX();
		double ty = t.getY();
		double tz = t.getZ();

		// @formatter:off
		// px + r * vx = tx
		// py + r * vy = ty
		// pz + r * vz = tz
		// @formatter:on

		double r;
		if ( vx != 0 ) {
			r = ( tx - px ) / vx;
		} else if ( vy != 0 ) {
			r = ( ty - py ) / vy;
		} else if ( vz != 0 ) {
			r = ( tz - pz ) / vz;
		} else {
			throw new IllegalArgumentException( "Cannot reach point t from given point p" );
		}

		if ( Geometry3DUtils.equal( px + r * vx, tx ) && Geometry3DUtils.equal( px + r * vy, ty )
				&& Geometry3DUtils.equal( pz + r * vz, tz ) ) {
			return r;
		} else {
			throw new IllegalArgumentException( "Cannot reach point t from given point p" );
		}
	}

	/**
	 * Returns <code>true</code>, if the absolute value of the difference
	 * between <code>a</code> and <code>b</code> is less than the predefined
	 * <code>Geometry3DUtils.getDelta()</code> value.
	 * 
	 * @param a
	 *            The first double.
	 * @param b
	 *            The second double.
	 * @return <code>true</code>, if <code>a</code> and <code>b</code> are
	 *         approximately equal, otherwise <code>false</code>.
	 */
	public static boolean equal( double a, double b ) {
		return Math.abs( a - b ) < Geometry3DUtils.getDelta();
	}

	public static double getDelta() {
		return Geometry3DUtils.delta;
	}

	public static void setDelta( double newD ) {
		if ( newD >= 0 ) {
			Geometry3DUtils.delta = newD;
		} else {
			throw new IllegalArgumentException( "A delta of less than zero isn't valid:" );
		}
	}

}
