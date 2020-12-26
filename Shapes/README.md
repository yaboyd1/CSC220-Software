# Software Design Lab Project 1

### Shapes Hierarchy Part 1

#### Class MyShape:

Class MyShape is the hierarchy’s superclass and inherits the Java class Object. An implementation of the class defines a reference point (x, y) and the color of the shape. The class includes appropriate class constructors and methods, including methods that perform the following operations:

1. **getX, getY, getColor** – returns the x- and y-coordinates of the reference point and color of the MyShape object
2. **setX, setY, setColor**– sets the x- and y-coordinates of the reference point and colorfor the MyShape object
3. **toString**– returns the object's description as a String. This method must be overridden in each subclass in the hierarchy
4. **draw**– draws a MyShape object. This method must be overridden in each subclass in the hierarchy. For the MyShape object, it paints the drawing canvas in the color specified

#### Class MyLine:

Class MyLine inherits class MyShape. The MyLine object is a straight line defined by the endpoints (x1, y1) and (x2, y2). The MyLine object may be of any color. The class includes appropriate class constructors and methods that perform the following operations:

1. **getLength**— returns the length of the MyLine object
2. **get_xAngle**— return the angle (in degrees) of the MyLine object with the x-axis
3. **toString**— returns a string representation of the MyLine object, including the line’s endpoints, length, and angle with the x-axis;
4. **draw**— draws a MyLine object whose end points are (x1, y1) and (x2, y2)

#### Class MyPolygon:

Class MyPolygon inherits class MyShape. The MyPolygon object is a regular polygon defined by the integer parameter, N— the number of the polygon’s equal side lengths and equal interior angles, and the center (x, y) and radius, r, of the circle in which it is inscribed. The MyPolygon object may be filled with a color. The class includes appropriate class constructors and methods that perform the following operations:

1. **getArea**— returns the area of the MyPolygon object
2. **getPerimeter**— returns the perimeter of the MyPolygon object
3. **getAngle**— returns the interior angle (in degrees) of the MyPolygon object
4. **getSide**— returns the side length of the MyPolygon object
5. **toString**— returns a string representation of the MyPolygon object, including the number of sides, side length, interior angle, perimeter, and area
6. **draw**— draws a MyPolygon object inscribed in a circle centered at (x, y) and of radius r

#### Class MyCircle:

Class MyCircle inherits class MyShape. The MyCircle object is defined by its center (x, y) and radius r, and may be filled with a color. The MyCircle class includes appropriate class constructors and methods that perform the following operations:

1. **getArea**— returns the area of the MyCircle object
2. **getPerimeter**— returns the perimeter of the MyCircle object
3. **getRadius**— returns the radius of the MyCircle object
4. **toString**— returns a string representation of the MyCircle object, including its center point, radius, perimeter, and area
5. **draw**— draws a MyCircle object centered at (x, y) and of radius r

#### JavaFX:

Use JavaFX graphics and the class hierarchy to draw a geometric configuration comprised of a sequence of alternating concentric circles and their inscribed hexagons as illustrated below, subject to the following additional requirements:

1. The code is applicable to canvases of variable height and width
2. The dimensions of the shapes are proportional to the smallest dimension of the canvas
3. The hexagons and circles are filled with different colors of your choice, specified through a MyColor enum reference type
