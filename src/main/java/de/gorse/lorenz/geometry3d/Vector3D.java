package de.gorse.lorenz.geometry3d;

public class Vector3D {

	private double x;
	private double y;
	private double z;

	public Vector3D( double x, double y, double z ) {
		this.setX( x );
		this.setY( y );
		this.setZ( z );
	}

	public Vector3D( Point3D a, Point3D b ) {
		this.setX( b.getX() - a.getX() );
		this.setY( b.getY() - a.getY() );
		this.setZ( b.getZ() - a.getZ() );
	}

	public Vector3D() {
		this.setX( 0 );
		this.setY( 0 );
		this.setZ( 0 );
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

	public void multiply( double f ) {
		this.setX( this.getX() * f );
		this.setY( this.getY() * f );
		this.setZ( this.getZ() * f );
	}

	public boolean isNull() {
		return this.getX() == 0 && this.getY() == 0 && this.getZ() == 0;
	}

	public double getLenght() {
		return Math.sqrt( this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ() );
	}

	@Override
	public String toString() {
		return "Vector3D [ " + this.getX() + " | " + this.getY() + " | " + this.getZ() + "]";
	}

	@Override
	public boolean equals( Object o ) {
		if ( o == null )
			return false;

		if ( o == this )
			return true;

		Vector3D v = ( Vector3D ) o;

		return v.getX() == this.getX() && v.getY() == this.getY() && v.getZ() == this.getZ();
	}

	/*
	 * Static methods
	 */
	public static double dotProduct( Vector3D a, Vector3D b ) {
		return a.getX() * b.getX() + a.getY() * b.getY() + a.getZ() * b.getZ();
	}

	public static Vector3D crossProduct( Vector3D a, Vector3D b ) {
		Vector3D r = new Vector3D();

		r.setX( a.getY() * b.getZ() - a.getZ() * b.getY() );
		r.setY( a.getZ() * b.getX() - a.getX() * b.getZ() );
		r.setZ( a.getX() * b.getY() - a.getY() * b.getX() );

		return r;
	}

	public static boolean areParallel( Vector3D a, Vector3D b ) {
		return a.getX() / b.getX() == a.getY() / b.getY() && a.getY() / b.getY() == a.getZ() / b.getZ();
	}

	public static double getAngleBetween( Vector3D a, Vector3D b ) {
		double cos = Vector3D.dotProduct( a, b ) / ( a.getLenght() * b.getLenght() );
		return Math.acos( cos ) * 180 / Math.PI;
	}
}
