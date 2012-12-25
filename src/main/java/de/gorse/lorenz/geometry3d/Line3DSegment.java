package de.gorse.lorenz.geometry3d;

public class Line3DSegment extends AbstractLine3D {

	public Line3DSegment( Point3D start, Point3D end ) {
		super( start, new Vector3D( start, end ) );
	}

	@Override
	public boolean isValidCoefficient( double c ) {
		return c >= 0 && c <= 1;
	}

	@Override
	public String toString() {
		return "Line3DSegment [From " + this.getPoint( 0 ) + " to " + this.getPoint( 1 ) + "]";
	}
}
