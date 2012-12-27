# Geometry3D #

Geometry3D is a Java library which provides support for vector based calculations.

Currently, it is possible to create planes as well as lines, line segments and rays. The library is able to calculate the intersection of two lines and the intersection of a line with a plane. Support for calculating the intersection of two planes will be implemented soon.

## Usage ##
There are two basic types available, points and vectors. Points are defined in the class Point3D, vectors are defined in the class Vector3D. A point can be created using three dobules, one for the x-coordinate, one for the y-coordinate and one for the z-coordinate.

    Point3D p = new Point3D( 2.05, 1.73, -0.26 );

A vector can be created from three doubles, two points or nothing.

	Vector3D a = new Vector3D( 4.23, -5.22, -0.02 );
	Vector3D b = new Vector3D( new Point3D( 2.05, 1.73, -0.26 ), new Point3D( 4.13, -2.37, -2.06 ) );
	Vector3D c = new Vector3D();

The first vector `a` has `4.23` as x-part, `-5.33` as y-part and `-0.02` as z-part. The second vector `b` is the vector between the first point and the second point with `2.08` as x-part, `-4.1` as y-part and `-1.8` as z-part. The third vector is the null-vector with `0` as x-, y- and z-part.