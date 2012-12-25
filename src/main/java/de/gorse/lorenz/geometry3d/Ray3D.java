package de.gorse.lorenz.geometry3d;

public class Ray3D extends AbstractLine3D {
	
	private Point3D other;

	public Ray3D( Point3D start, Point3D other ) {
		super( start, new Vector3D( start, other ) );
		this.other = other;
	}
	
	@Override
	public boolean isValidCoefficient( double c ) {
		return c >= 0;
	}
	
	@Override
	public void setStart( Point3D start ) {
		super.setStart( start );
		super.setDirection( new Vector3D( start, this.other ) );
	}
	
	public void setOther( Point3D other ) {
		this.other = other;
		super.setDirection( new Vector3D( this.getStart(), other ) );
	}
	
	@Override
	public String toString() {
		return "Ray3D [From " + this.getPoint( 0 ) + " through " + this.getPoint( 1 ) + "]";
	}

}
