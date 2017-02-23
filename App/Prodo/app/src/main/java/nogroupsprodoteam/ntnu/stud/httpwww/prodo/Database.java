package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

/**
 * Created by Christian on 21.02.2017.
 */

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static String mysqlAddr = "jdbc:mysql://mysql.stud.ntnu.no:3306/prodoteam_db?allowMultiQueries=true";
    private static String mysqlUser = "chrisnyv_demo";
    private static String mysqlPass = "rM48DmzH";



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
            return null;
        }
        catch(SQLException e){
            return e.toString();
        }
    }

    public static ArrayList<String> courses(){
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
            return course;
        }
        catch(SQLException e){
            System.out.println(e);
            course.add("No courses");
            return course;
        }
    }

    public static ArrayList<String> lectures(Integer ID){
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
            return lectures;
        }
        catch(SQLException e){
            System.out.println(e);
            lectures.add("No lectures");
            return lectures;
        }
    }
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
            return lectureID;
        }
        catch(SQLException e){
            System.out.println(e);
            lectureID = 999;
            return lectureID;
        }
    }

    public static Integer countLectures(Integer lectureID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Integer numberOfLectures = null;
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM topic WHERE lectureID = " + lectureID.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                numberOfLectures = Integer.parseInt(rs.getString(1));
            }
            return numberOfLectures;
        }
        catch(SQLException e){
            System.out.println(e);
            numberOfLectures = 9;
            return numberOfLectures;
        }
    }

}
