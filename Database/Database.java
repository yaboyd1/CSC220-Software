package application;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Demonstrates interaction between a Java program and a database. *
 * @author Java Foundations
 * @version 4.0
 */
public class Database {
    /**
     * Carries out various CRUD operations after establishing the * database connection.
     */
    private static Connection conn;
    private static final String user = "root";
    private static final String pass = "dewantahmid";

    public Database() {
        try {
            // Loads the class object for the mysql driver into the DriverManager.
            Class.forName("com.mysql.cj.jdbc.Driver");

            //  Attempt to establish a connection to the specified database via the DriverManager
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mySQL?user=" + user + "&password=" + pass + "&useUnicode=true&characterEncoding=UTF-8");
            // Check the connection
            if (conn != null) {
                //System.out.println("We have connected to our database!");

                // Create Students Table
                createTables();

                // Insert into Students Database
                populateTables();

                /*System.out.println("Create the table and show the table structure");
                showColumns(conn, "Students");
                showColumns(conn, "Courses");
                showColumns(conn, "Classes");*/

                System.out.println("Showing values of each table");
                showValues(conn, "Students");
                showValues(conn, "Classes");
                showValues(conn, "Courses");

                /*curveGrades();
                Map<Character, Integer> grades = getGrades();
                for (Character c : grades.keySet())
                    System.out.println(c + " " + grades.get(c));*/
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Creating all databases
    public static void createTables() {
        try {
            // Delete databases if already exists
            deleteTables();

            // Create databases
            PreparedStatement createStudents = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Students "
                    + "(emplID INT UNSIGNED NOT NULL, "
                    + "firstName varchar(255), "
                    + "lastName varchar(255), "
                    + "email varchar(255), "
                    + "sex ENUM('M', 'F', 'U'), "
                    + "PRIMARY KEY (emplID))"
            );
            PreparedStatement createCourses = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Courses "
                    + "(courseID INT UNSIGNED NOT NULL, "
                    + "courseTitle varchar(255), "
                    + "department varchar(255), "
                    + "PRIMARY KEY (courseID))"
            );
            PreparedStatement createClasses = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Classes "
                    + "(courseID INT UNSIGNED NOT NULL, "
                    + "studentID INT UNSIGNED NOT NULL, "
                    + "sectionNO INT UNSIGNED NOT NULL, "
                    + "year INT UNSIGNED NOT NULL, "
                    + "semester varchar(255), "
                    + "grade ENUM('A', 'B', 'C', 'D', 'F', 'W'), "
                    + "FOREIGN KEY (courseID) REFERENCES Courses(courseID), "
                    + "FOREIGN KEY (studentID) REFERENCES Students(emplID), "
                    + "PRIMARY KEY (courseID, studentID, sectionNO))"
            );
            createStudents.execute();
            createCourses.execute();
            createClasses.execute();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Inserting random values into database
    public static void populateTables() {
        try {
            // Insert random things into databases
            PreparedStatement insertStudents = conn.prepareStatement("INSERT INTO Students (emplID, firstName, lastName, email, sex) VALUES "
                    + "(12345678, 'Bruce', 'Jenner', 'bjenner@gmail.com', 'M'), "
                    + "(11345678, 'Simon', 'Cowell', 'nofromme@gmail.com', 'M'), "
                    + "(11145678, 'Ellen', 'DeGeneres', 'edge@gmail.com', 'F'), "
                    + "(11115678, 'Johnny', 'Depp', 'jdeep@gmail.com', 'M'), "
                    + "(11111678, 'Ariana', 'Grande', 'cat@gmail.com', 'F'), "
                    + "(11111178, 'Donald', 'Trump', 'dtrump@gmail.com', 'M'), "
                    + "(11111118, 'Oprah', 'Winfrey', 'oprah@gmail.com', 'F'), "
                    + "(11111111, 'Harry', 'Styles', 'oneD@gmail.com', 'M'), "
                    + "(22222222, 'Nicolas', 'Cage', 'cage@gmail.com', 'M'), "
                    + "(33333333, 'Dwayne', 'Johnson', 'rock@gmail.com', 'M'), "
                    + "(44444444, 'Demi', 'Lovato', 'demigod@gmail.com', 'F'), "
                    + "(55555555, 'Hulk', 'Hogan', 'avengers@gmail.com', 'M'), "
                    + "(66666666, 'Kanye', 'West', 'stronger@gmail.com', 'M'), "
                    + "(77777777, 'Emma', 'Wattson', 'dead@gmail.com', 'M'), "
                    + "(88888888, 'Vin', 'Diesel', 'vinnie@gmail.com', 'M'), "
                    + "(99999999, 'Mark', 'Wahlberg', 'markywall@gmail.com', 'M'), "
                    + "(11112222, 'Elon', 'Musk', 'father@gmail.com', 'M'), "
                    + "(22223333, 'Kim', 'Kardashian', 'kimmiecardash@gmail.com', 'M'), "
                    + "(33334444, 'Tom', 'Brady', 'sonkisser@gmail.com', 'M'), "
                    + "(44445555, 'Charlie', 'Sheen', 'halfman@gmail.com', 'M'), "
                    + "(55556666, 'Megan', 'Fox', 'foxmeg@gmail.com', 'M'), "
                    + "(66667777, 'Miley', 'Cyrus', 'wreckingball@gmail.com', 'M'), "
                    + "(77778888, 'William', 'Shatner', 'starwars@gmail.com', 'M'), "
                    + "(88889999, 'Selena', 'Gomez', 'disney@gmail.com', 'M'), "
                    + "(99998888, 'Bob', 'Marley', 'classics@gmail.com', 'M'), "
                    + "(88887777, 'Shia', 'LaBeouf', 'justdoit@gmail.com', 'M'), "
                    + "(77776666, 'Taylor', 'Swift', 'trouble@gmail.com', 'M'), "
                    + "(66665555, 'Angelina', 'Jolie', 'stolen@gmail.com', 'M'), "
                    + "(55554444, 'Ben', 'Affleck', 'benjamin@gmail.com', 'M'), "
                    + "(44443333, 'Lil', 'Wayne', 'eminem@gmail.com', 'M'), "
                    + "(33332222, 'Will', 'Smith', 'freshprince@gmail.com', 'M'), "
                    + "(22221111, 'LeBron', 'James', 'basketball@gmail.com', 'M')"
            );
            PreparedStatement insertCourses = conn.prepareStatement("INSERT INTO Courses (courseID, courseTitle, department) VALUES "
                    + "(10300, 'Introduction to Computing', 'Computer Science'), "
                    + "(21200, 'Calculus 3', 'Mathematics'), "
                    + "(22100, 'Software Design Lab', 'Computer Science')"
            );
            PreparedStatement insertClasses = conn.prepareStatement("INSERT INTO Classes (courseID, studentID, sectionNO, year, semester, grade) VALUES "
                    + "(22100, 22221111, 100, 2020, 'Fall', 'W'), "
                    + "(22100, 33332222, 100, 2020, 'Fall', 'F'), "
                    + "(22100, 44443333, 100, 2020, 'Fall', 'W'), "
                    + "(22100, 55554444, 100, 2020, 'Fall', 'W'), "
                    + "(22100, 66665555, 100, 2020, 'Fall', 'D'), "
                    + "(22100, 77776666, 100, 2020, 'Fall', 'D'), "
                    + "(22100, 88887777, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 99998888, 100, 2020, 'Fall', 'D'), "
                    + "(22100, 88889999, 100, 2020, 'Fall', 'W'), "
                    + "(22100, 77778888, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 66667777, 100, 2020, 'Fall', 'F'), "
                    + "(22100, 55556666, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 44445555, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 33334444, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 22223333, 100, 2020, 'Fall', 'B'), "
                    + "(22100, 11112222, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 99999999, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 88888888, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 77777777, 100, 2020, 'Fall', 'F'), "
                    + "(22100, 66666666, 100, 2020, 'Fall', 'B'), "
                    + "(22100, 55555555, 100, 2020, 'Fall', 'F'), "
                    + "(22100, 44444444, 100, 2020, 'Fall', 'W'), "
                    + "(22100, 33333333, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 22222222, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 11111111, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 11111118, 100, 2020, 'Fall', 'D'), "
                    + "(22100, 11111178, 100, 2020, 'Fall', 'F'), "
                    + "(22100, 11111678, 100, 2020, 'Fall', 'C'), "
                    + "(22100, 11115678, 100, 2020, 'Fall', 'A'), "
                    + "(22100, 11145678, 100, 2020, 'Fall', 'B'), "
                    + "(22100, 11345678, 100, 2020, 'Fall', 'B'), "
                    + "(22100, 12345678, 100, 2020, 'Fall', 'A')"
            );
            insertStudents.execute();
            insertCourses.execute();
            insertClasses.execute();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Updating students grades by curving one grade into its higher grade
    public static void curveGrades() {
        try {
            PreparedStatement curveB = conn.prepareStatement("UPDATE Classes SET grade = 'A' WHERE grade = 'B'");
            PreparedStatement curveC = conn.prepareStatement("UPDATE Classes SET grade = 'B' WHERE grade = 'C'");
            PreparedStatement curveD = conn.prepareStatement("UPDATE Classes SET grade = 'C' WHERE grade = 'D'");
            PreparedStatement curveF = conn.prepareStatement("UPDATE Classes SET grade = 'D' WHERE grade = 'F'");
            curveB.execute();
            curveC.execute();
            curveD.execute();
            curveF.execute();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Gets how many students got what grade
    public static Map<Character, Integer> getGrades() {
        Map<Character, Integer> grades = new LinkedHashMap<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT grade, COUNT(studentID) FROM Classes WHERE courseID = 22100 AND year = 2020 AND semester = 'Fall' GROUP BY grade ORDER BY grade ASC");
            ResultSet rSet = statement.executeQuery();

            /*System.out.println("Displaying Result Set");
            showResults("Classes", rSet);*/

            while (rSet.next())
                grades.putIfAbsent(rSet.getString(1).charAt(0), rSet.getInt(2));

            /*System.out.println("Displaying Map");
            for (Character c : grades.keySet())
                System.out.println(c + " " + grades.get(c));*/
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return grades;
    }

    public static int getTotal() {
        int output = 0;
        try {
            PreparedStatement totalStudents = conn.prepareStatement("SELECT COUNT(studentID) FROM Classes WHERE courseID = 22100 AND year = 2020 AND semester = 'Fall'");
            ResultSet students = totalStudents.executeQuery();
            students.next();
            output = students.getInt(1);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return output;
    }

    public static void deleteTables() {
        try {
            PreparedStatement dropStudents = conn.prepareStatement("DROP TABLE IF EXISTS Students");
            PreparedStatement dropCourses = conn.prepareStatement("DROP TABLE IF EXISTS Courses");
            PreparedStatement dropClasses = conn.prepareStatement("DROP TABLE IF EXISTS Classes");
            dropClasses.execute();
            dropStudents.execute();
            dropCourses.execute();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Obtains and displays a ResultSet from the Student table.
     */
    public static void showValues(Connection conn, String tablename) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM " + tablename);
            Database.showResults(tablename, rset);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Displays the structure of the Student table.
     */
    public static void showColumns(Connection conn, String tablename) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SHOW COLUMNS FROM " + tablename);
            Database.showResults(tablename, rset);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Displays the contents of the specified ResultSet.
     */
    public static void showResults(String tableName, ResultSet rSet) {
        try {
            ResultSetMetaData rsmd = rSet.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String resultString = null;
            if (numColumns > 0) {
                resultString = "\nTable: " + tableName + "\n" +
                        "=======================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                    resultString += rsmd.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println(
                    "=======================================================");
            while (rSet.next()) {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++) {
                    String column = rSet.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                System.out.println(resultString + '\n' +
                        "------------------------------------------------------------");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}