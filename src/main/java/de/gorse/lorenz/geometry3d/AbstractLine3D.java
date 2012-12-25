package de.gorse.lorenz.geometry3d;

public abstract class AbstractLine3D {

	private Point3D start;
	private Vector3D direction;

	public AbstractLine3D( Point3D s, Vector3D d ) {
		this.start = s;
		this.direction = d;
	}

	public AbstractLine3D( Point3D a, Point3D b ) {
		this.start = a;
		this.direction = new Vector3D( a, b );
	}

	public Point3D getStart() {
		return start;
	}

	public void setStart( Point3D start ) {
		this.start = start;
	}

	public Vector3D getDirection() {
		return direction;
	}

	public void setDirection( Vector3D direction ) {
		this.direction = direction;
	}
	
	public abstract boolean isValidCoefficient( double r );

	public Point3D getPoint( double c ) {
		return new Point3D( this.getStart().getX() + c * this.getDirection().getX(), this.getStart().getY() + c
				* this.getDirection().getY(), this.getStart().getZ() + c * this.getDirection().getZ() );
	}

	public boolean pointIsOnLine( Point3D p ) {
		if ( p == null )
			return false;

		double t1 = ( p.getX() - this.getStart().getX() ) / this.getDirection().getX();
		double t2 = ( p.getY() - this.getStart().getY() ) / this.getDirection().getY();
		double t3 = ( p.getZ() - this.getStart().getZ() ) / this.getDirection().getZ();
		
		if( !this.isValidCoefficient( t1 ) && !this.isValidCoefficient( t2 ) && !this.isValidCoefficient( t3 ) )
			return false;

		return ( Geometry3DUtils.equal( t1, t2 )
				|| ( this.getDirection().getX() == 0 && p.getX() == this.getStart().getX() ) || ( this.getDirection()
				.getY() == 0 && p.getY() == this.getStart().getY() ) )
				&& ( Geometry3DUtils.equal( t2, t3 )
						|| ( this.getDirection().getZ() == 0 && p.getZ() == this.getStart().getZ() ) || ( this
						.getDirection().getY() == 0 && p.getY() == this.getStart().getY() ) )
				&& ( Geometry3DUtils.equal( t3, t1 )
						|| ( this.getDirection().getX() == 0 && p.getX() == this.getStart().getX() ) || ( this
						.getDirection().getZ() == 0 && p.getZ() == this.getStart().getZ() ) );
	}

	@Override
	public String toString() {
		return "Line [" + this.getStart() + " + t * " + this.getDirection() + "]";
	}
	
}
