# Software Design Lab Project 3

### Shapes Hierarchy Part 3
Now including a Pie Chart of N most frequent characters of a file

#### Amend the MyShape class hierarchy in Assignment 2 as follows:
1. MyArc is_a MyShape;

#### Class MyArc:
Class MyArc inherits class MyShape. The MyArc object is a segment of the boundary of a MyOval object, defined by the endpoints (x1, y1) and (x2, y2), or their corresponding angles, on the MyOval boundary. The MyArc object may be of or filled with, any color of MyColor enum reference type. The class includes appropriate class constructors and methods, including methods that perform the following operations:

1. **toString**— returns a string representation of the MyArc object
2. **draw**— draws a MyArc object

#### Class MyPieChart:
Implement a Java class MyPieChart that displays a circular pie chart of the probabilities of the n most frequent occurrences of an event to be specified in part 5 of the Assignment. 

1. Each event is represented by a segment of the pie chart. The area of the segment is proportional to the probability of the corresponding event
2. Each segment has a different color
3. Each segment has a legend showing the corresponding event and its probability
4. The segments are displayed in order of decreasing probability
5. The last segment represents “All Other Events” and their cumulative probability. As an example, in the graph below where the event is the occurrence of a letter in a text: n = 3, and the probability of All Other Events is one minus the sum of the probabilities of events e, s, and i

The MyPieChart class utilizes the MyShape class hierarchy and includes
appropriate constructors and a method draw that draws the pie chart. The
drawing canvas may include appropriate GUI components to input the number of
events, n, and display the pie chart together with the events and their
corresponding probabilities.

#### Class HistogramAlphabet
Implement a Java class HistogramAlphaBet that calculates the n most frequent alphabet characters in “Emma” by Jane Austen (file Emma.txt) and their probabilities. The HistogramAlphaBet class utilizes a Map collection for statistical calculations and the drawing canvas above to draw a pie chart of the probabilities.

#### JavaFX:
You may only use JavaFX graphics and your own classes and methods for the
operations included. Further,

1. The code is applicable to canvases of variable height and width
2. The size of the pie chart are proportional to the smallest dimension of the canvas
3. The segments of the pie chart are filled with different colors of your choice, specified through a MyColor enum reference type
