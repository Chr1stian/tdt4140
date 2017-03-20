package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

/**
 * Created by Christian on 21.02.2017.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static String mysqlAddr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11164632?allowMultiQueries=true";
    private static String mysqlUser = "sql11164632";
    private static String mysqlPass = "JKb6SqBp59";



    public static String test(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bruker");

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return rs.getString(2);
            conn.close();
            return null;

        }
        catch(SQLException e){
            return e.toString();
        }
    }
    //returns ArrayList of courses from database
    public static ArrayList<String> getCourses(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> course = new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                course.add(rs.getString(2) + " " + rs.getString(3));
            }
            conn.close();
            return course;
        }
        catch(SQLException e){
            System.out.println(e);
            course.add("No courses");
            return course;
        }
    }
    //returns ArrayList of lectures from database
    public static ArrayList<String> getLectures(Integer ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> lectures = new ArrayList<String>();
        //Integer lectureID;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lecture WHERE courseID = " + ID.toString());

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lectures.add(rs.getString(3) + " " + rs.getString(4));
                //lectureID = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return lectures;
        }
        catch(SQLException e){
            System.out.println(e);
            lectures.add("No lectures");
            return lectures;
        }
    }
    //returns lectureID from database
    public static Integer getLectureID(Integer courseID, Integer number){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       Integer lectureID = null;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT lectureID FROM lecture WHERE courseID = " + courseID.toString() +
                    " AND number = " + number.toString());

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lectureID = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return lectureID;
        }
        catch(SQLException e){
            System.out.println(e);
            lectureID = 999;
            return lectureID;
        }
    }

    //returns number of topics for selected lecture from database
    public static Integer countTopics(Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer numberOfTopics = null;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM topic WHERE lectureID = " + lectureID.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                numberOfTopics = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return numberOfTopics;
        }
        catch(SQLException e){
            System.out.println(e);
            numberOfTopics = 9;
            return numberOfTopics;
        }
    }
    //returns topic from database
    public static String getTopic(Integer number, Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String topic = "";

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM topic WHERE number = " + number.toString() +
                    " AND lectureID = " + lectureID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                topic = rs.getString(1);
            }
            conn.close();
            return topic;
        }
        catch(SQLException e){
            System.out.println(e);
            topic = "Database error:" + e;
            return topic;
        }


    }
    //returns topicID from database
    public static Integer getTopicID(Integer number, Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer topicID = null;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT topicID FROM topic WHERE number = " + number.toString() +
                    " AND lectureID = " + lectureID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                topicID = Integer.parseInt(rs.getString(1));
            }
            conn.close();
            return topicID;
        }
        catch(SQLException e){
            System.out.println(e);
            topicID = null;
            return topicID;
        }


    }

    //Uploading the rating to the database
    public static String setRating(Integer topicID, Integer stars){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer rating = null;
        Integer userID = 1;
        String error ="Rating submitted";
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO rating(topicID,userID,stars) VALUES ('" + topicID.toString() + "','" + userID.toString() + "','" + stars.toString() + "')");
            stmt.execute();
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
            error = "Database error:" + e;
            return error;
        }
        return error;
    }
    //Checks if nickname is already registered in database
    public static boolean checkNickname(String nickname){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean exists = false;
        Integer check = null;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM user WHERE name = '" + nickname + "'") ;
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                check = Integer.parseInt(rs.getString(1));
                }
            if(check > 0){
                exists = false;
            }
            else{
                exists = true;
            }
            conn.close();
            return exists;
        }
        catch(SQLException e){
            return false;
        }
    }
    //registers username to database
    public static void registerNickname(String nickname){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(name) VALUES ('" + nickname + "')");
            stmt.execute();
            conn.close();

        }
        catch(SQLException e){
        }
    }

    public static String sendQuestion(Integer topicID, String questionString) {


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Integer userID = 1;
        String answer = "Not answered yet..";
        String error ="*Question submitted*";
        Integer rating = 0;

        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO question(topicID,userID,question,answer,rating) VALUES ('" + topicID.toString() + "','" + userID.toString() + "','" + questionString +"','" + answer +"','" + rating.toString() + "')");
            //INSERT INTO `question`(`questionID`, `topicID`, `userID`, `question`, `answer`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5])
            stmt.execute();
            conn.close();
        }
        catch(SQLException e){
            System.out.println(e);
            error = "Database error:" + e;
            return error;
        }
        return error;

    }
}
