package de.gorse.lorenz.geometry3d;

/**
 * This complete implementation of an <code>AbstractLine3D</code> is a ray.
 * 
 * @author frececroka
 */
public class Ray3D extends AbstractLine3D {

	private Point3D other;

	/**
	 * Creates a ray beginning at the point <code>start</code> and going through
	 * the point <code>other</code>.
	 * 
	 * @param start
	 * @param other
	 */
	public Ray3D( Point3D start, Point3D other ) {
		super( start, new Vector3D( start, other ) );
		this.other = other;
	}

	@Override
	public boolean isValidCoefficient( double c ) {
		return c >= 0;
	}

	/**
	 * Moves the start point to a new location
	 * 
	 * @param start
	 */
	@Override
	public void setStart( Point3D start ) {
		super.setStart( start );
		super.setDirection( new Vector3D( start, this.other ) );
	}

	/**
	 * Moves the other point to a new location.
	 * 
	 * @param other
	 */
	public void setOther( Point3D other ) {
		this.other = other;
		super.setDirection( new Vector3D( this.getStart(), other ) );
	}

	@Override
	public String toString() {
		return "Ray3D [From " + this.getPoint( 0 ) + " through " + this.getPoint( 1 ) + "]";
	}

}
