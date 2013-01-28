# Geometry3D #

Geometry3D is a Java library which provides support for vector based calculations.

Currently, it is possible to create planes as well as lines, line segments and rays. The library is able to calculate the intersection of two lines and the intersection of a line with a plane.

## Basic types ##
There are two basic types available, points and vectors. Points are defined in the class Point3D, vectors are defined in the class Vector3D. A point can be created using three dobules, one for the x-coordinate, one for the y-coordinate and one for the z-coordinate.

    Point3D p = new Point3D( 2.05, 1.73, -0.26 );

A vector can be created from three doubles, two points or nothing.

	Vector3D a = new Vector3D( 4.23, -5.22, -0.02 );
	Vector3D b = new Vector3D( new Point3D( 2.05, 1.73, -0.26 ), new Point3D( 4.13, -2.37, -2.06 ) );
	Vector3D c = new Vector3D();

The first vector `a` has `4.23` as x-part, `-5.33` as y-part and `-0.02` as z-part. The second vector `b` is the vector between the first point and the second point with `2.08` as x-part, `-4.1` as y-part and `-1.8` as z-part. The third vector is the null-vector with `0` as x-, y- and z-part.

## Complex types ##
There are a few complex types available, which are made up using the previously explained basic types.

### AbstractLine3D ###
All linear objects are represented by a subclass of `AbstractLine3D`. Available subclasses are `Line3D`, `Ray3D` and `Line3DSegment` which are the representation of a line, a ray or, respectively, a line segment.

A `Line3D` can be created from either two instances of `Point3D` or one instance of `Point3D` and one instance of `Vector3D`. The resulting `Line3D` contains both `Point3D`s in the first case or contains the `Point3D` and has the `Vector3D` as its direction in the second case.

A `Ray3D` can be created from two `Point3D`s. The first `Point3D` is used as the origin of the `Ray3D`, while the second `Point3D` is just some other point of the `Ray3D`.

A `Line3DSegment` can be created from two `Point3D`s. The first `Point3D` is the beginning, the second `Point3D` is the end of the `Line3DSegment`.

### Plane3D ###
A `Plane3D` is made up of three `Point3D`s. Each `Point3D` will be part of the resulting `Plane3D`.

## Calculating intersections ##
To calculate intersections between two complex types, static methods of the class `Geometry3DUtils` are used. The intersection of two `AbstractLine3D`s is calculated by calling `Geometry3DUtils.intersectionOfTwoLines( a, b )`:

    Point3D intersection = Geometry3DUtils.intersectionOfTwoLines( someRay, someLineSegment );

The intersection of an `AbstractLine3D` and a `Plane3D` is calculated by calling `Geometry3DUtils.intersectionOfLineWithPlane( l, p )`:

    Point3D intersection = Geometry3DUtils.intersectionOfLineWithPlane( someLine, somePlane );

Or with a `Ray3D`:

    Point3D intersection = Geometry3DUtils.intersectionOfLineWithPlane( someRay, somePlane );
