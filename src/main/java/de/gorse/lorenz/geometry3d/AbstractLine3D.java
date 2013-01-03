package de.gorse.lorenz.geometry3d;

/**
 * This abstract class can represent a linear object. This can be anything,
 * beginning from a simple line to a ray, line segment or any object, which
 * points belong to one line.
 * 
 * @author frececroka
 */
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

	/**
	 * Returns the "first" point of this linear object.
	 * 
	 * @return A point.
	 */
	public Point3D getStart() {
		return start;
	}

	/**
	 * Sets the "first" point of this linear object.
	 * 
	 * @param start
	 *            The new point,
	 */
	public void setStart( Point3D start ) {
		this.start = start;
	}

	/**
	 * Returns the direction of this linear object as a vector.
	 * 
	 * @return The direction of this linear object.
	 */
	public Vector3D getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of this linear object.
	 * 
	 * @param direction
	 *            The new direction.
	 */
	public void setDirection( Vector3D direction ) {
		this.direction = direction;
	}

	/**
	 * Returns <code>true</code>, if the point <code>this.getPoint( c )</code>
	 * is part of this linear object.
	 * 
	 * @param c
	 *            The coefficient.
	 * @return <code>true</code>, if the point <code>this.getPoint( c )</code>
	 *         is part of this linear object, otherwise <code>false</code>.
	 */
	public abstract boolean isValidCoefficient( double c );

	/**
	 * Returns a point of this linear object. A different parameter
	 * <code>c</code> results in a different point returned.
	 * 
	 * @param c
	 *            The coefficient of the direction.
	 * @return A point of the linear object.
	 */
	public Point3D getPoint( double c ) {
		return new Point3D( this.getStart().getX() + c * this.getDirection().getX(), this.getStart().getY() + c
				* this.getDirection().getY(), this.getStart().getZ() + c * this.getDirection().getZ() );
	}

	/**
	 * Returns <code>true</code> if the given point <code>p</code> belongs to
	 * the linear object, false if not.
	 * 
	 * @param p
	 *            The point.
	 * @return <code>true</code> if <code>p</code> belongs to this linear
	 *         object.
	 */
	public boolean pointIsOnLine( Point3D p ) {
		if ( p == null )
			return false;

		double t1 = ( p.getX() - this.getStart().getX() ) / this.getDirection().getX();
		double t2 = ( p.getY() - this.getStart().getY() ) / this.getDirection().getY();
		double t3 = ( p.getZ() - this.getStart().getZ() ) / this.getDirection().getZ();

		if ( !this.isValidCoefficient( t1 ) && !this.isValidCoefficient( t2 ) && !this.isValidCoefficient( t3 ) )
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
