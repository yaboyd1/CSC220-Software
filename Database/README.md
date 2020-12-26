# Software Design Lab Project 4

### Shapes Hierarchy Part 4

#### Consider the Student database and Listing 25.2 in Chapter 25 of the textbook. Your tasks are:

#### Amend the database to include the following Tables:
1. Students(__empID__, firstName, lastName, email, sex)
2. Courses(__courseID__, courseTitle, department)
3. Classes(__courseID__, __studentID__, __sectionNo__, year, semester, grade)

The bolded attributes are the primary keys of theirs corresponding tables. The value of attribute sex may be either F, M, or U. The only letter grades allowed in the database are A, B, C, D, F, and W.

#### Amend Listing 25.2 to build and test a Java application that connects to the database, creates, populates, and updates the Students, Courses, and Classes Tables.
1. Amend Listing 25.2 to return and display the number of students for each letter grade in CSc 22100 [Software Design Laboratory] in the Fall 2020 semester.
2. Utilize the Java classes in Assignment 3 to build and display a pie chart showing the proportion of students for each grade. In the pie chart:
	- Each segment has a different color
	- Each segment has a legend showing the corresponding grades and number of students
	- The segments for the grades are displayed in alphabetical order.

#### JavaFX: 
You may only use JavaFX graphics and your own classes and methods for the operations included. Further,

1. The code is applicable to canvases of variable height and width
2. The size of the pie chart are proportional to the smallest dimension of the canvas
3. The segments of the pie chart are filled with different colors of your choice, specified through a MyColor enum reference type

#### Additional Info
1. The report should show sample input tables and output table for the stated query and corresponding pie chart for a sufficient amount of input data.

2. Explicitly specify all the classes imported and used in your Java application.

3. Your application should utilize “PreparedStatement” objects for the execution of SQL statements/queries.

4. You may use any Relational Database Management System (RDBMS) of your choice.