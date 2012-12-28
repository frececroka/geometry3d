package de.gorse.lorenz.geometry3d;

public class Line3D extends AbstractLine3D {

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
