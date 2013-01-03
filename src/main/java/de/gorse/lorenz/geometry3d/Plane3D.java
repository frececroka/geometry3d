package de.gorse.lorenz.geometry3d;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.DecompositionSolver;
import org.apache.commons.math.linear.LUDecompositionImpl;
import org.apache.commons.math.linear.RealMatrix;

/**
 * A plane.
 * 
 * @author frececroka
 */
public class Plane3D {

	private double a;
	private double b;
	private double c;
	private double d;

	private Vector3D vectorInPlane;
	private Vector3D normalVector;

	/**
	 * Creates a new plane, going through each of the three given points.
	 * 
	 * @param a
	 *            The first point.
	 * @param b
	 *            The second point.
	 * @param c
	 *            The third point.
	 */
	public Plane3D( Point3D a, Point3D b, Point3D c ) {
		double[] values = { 1, 1, 1 };
		double[][] coefficients = { { a.getX(), a.getY(), a.getZ() }, { b.getX(), b.getY(), b.getZ() },
				{ c.getX(), c.getY(), c.getZ() } };
		RealMatrix matrix = new Array2DRowRealMatrix( coefficients );
		DecompositionSolver solver = new LUDecompositionImpl( matrix ).getSolver();

		double[] solution = solver.solve( values );
		this.setA( solution[0] );
		this.setB( solution[1] );
		this.setC( solution[2] );
		this.setD( 1 );

		Vector3D ab = new Vector3D( a, b );
		Vector3D ac = new Vector3D( a, c );

		this.setVectorInPlane( ab );
		this.setNormalVector( Vector3D.crossProduct( ab, ac ) );
	}

	public double getA() {
		return a;
	}

	public void setA( double a ) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB( double b ) {
		this.b = b;
	}

	public double getC() {
		return c;
	}

	public void setC( double c ) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	public void setD( double d ) {
		this.d = d;
	}

	public Vector3D getVectorInPlane() {
		return this.vectorInPlane;
	}

	public void setVectorInPlane( Vector3D v ) {
		this.vectorInPlane = v;
	}

	public Vector3D getNormalVector() {
		return normalVector;
	}

	public void setNormalVector( Vector3D n ) {
		this.normalVector = n;
	}

	public boolean isValidPoint( Point3D p ) {
		return Geometry3DUtils.equal( this.getA() * p.getX() + this.getB() * p.getY() + this.getC() * p.getZ(),
				this.getD() );
	}

	@Override
	public String toString() {
		return "Plane3D [" + this.getA() + "x + " + this.getB() + "y + " + this.getC() + "z = " + this.getD() + "]";
	}

}
