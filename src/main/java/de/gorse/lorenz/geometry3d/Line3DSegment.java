package de.gorse.lorenz.geometry3d;

/**
 * This complete implementation of an <code>AbstractLine3D</code> is a line
 * segment.
 * 
 * @author frececroka
 */
public class Line3DSegment extends AbstractLine3D {

	/**
	 * Creates a line segment between the two given points.
	 * 
	 * @param a
	 * @param b
	 */
	public Line3DSegment( Point3D a, Point3D b ) {
		super( a, new Vector3D( a, b ) );
	}

	@Override
	public boolean isValidCoefficient( double c ) {
		return c >= 0 && c <= 1;
	}

	@Override
	public String toString() {
		return "Line3DSegment [From " + this.getPoint( 0 ) + " to " + this.getPoint( 1 ) + "]";
	}

	@Override
	public Line3DSegment clone() {
		return new Line3DSegment( this.getStart().clone(), this.getPoint( 1 ).clone() );
	}
}
