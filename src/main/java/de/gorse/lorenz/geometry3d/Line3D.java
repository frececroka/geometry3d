package de.gorse.lorenz.geometry3d;

/**
 * This complete implementation of an <code>AbstractLine3D</code> is a simple,
 * infinite line.
 * 
 * @author frececroka
 * 
 */
public class Line3D extends AbstractLine3D {

	/**
	 * Creates a line through the two given points.
	 * 
	 * @param a
	 * @param b
	 */
	public Line3D( Point3D a, Point3D b ) {
		super( a, b );
	}

	public Line3D( Point3D a, Vector3D v ) {
		super( a, v );
	}

	@Override
	public boolean isValidCoefficient( double r ) {
		return true;
	}

	@Override
	public Line3D clone() {
		return new Line3D( this.getStart().clone(), this.getDirection().clone() );
	}

}
