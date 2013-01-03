package de.gorse.lorenz.geometry3d;

/**
 * A point.
 * 
 * @author frececroka
 */
public class Point3D {

	private double x;
	private double y;
	private double z;

	public Point3D( double x, double y, double z ) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public void setX( double x ) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY( double y ) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ( double z ) {
		this.z = z;
	}

	/**
	 * This moves the point to a new location by adding the given vector
	 * <code>v</code>, multiplicated by the given value <code>d</code>, to the
	 * original location of this point.
	 * 
	 * @param v
	 *            The vector.
	 * @param d
	 *            The multiplicator.
	 */
	public void addVector( Vector3D v, double d ) {
		this.setX( this.getX() + v.getX() * d );
		this.setY( this.getY() + v.getY() * d );
		this.setZ( this.getZ() + v.getZ() * d );
	}

	@Override
	public Point3D clone() {
		return new Point3D( this.getX(), this.getY(), this.getZ() );
	}

	@Override
	public String toString() {
		return "Point3D [" + this.getX() + " | " + this.getY() + " | " + this.getZ() + "]";
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o )
			return true;

		if ( o == null )
			return false;

		Point3D p = ( Point3D ) o;

		return Geometry3DUtils.equal( this.getX(), p.getX() ) && Geometry3DUtils.equal( this.getY(), p.getY() )
				&& Geometry3DUtils.equal( this.getZ(), p.getZ() );
	}

	/**
	 * Calculates the distance between two points <code>a</code> and
	 * <code>b</code>.
	 * 
	 * @param a
	 *            The first point.
	 * @param b
	 *            The second point.
	 * @return The distance between both points.
	 */
	public static double getDistance( Point3D a, Point3D b ) {
		return Math.sqrt( Math.pow( a.getX() - b.getX(), 2 ) + Math.pow( a.getY() - b.getY(), 2 )
				+ Math.pow( a.getZ() - b.getZ(), 2 ) );
	}

}
