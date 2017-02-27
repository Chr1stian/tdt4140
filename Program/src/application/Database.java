package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
	private static String mysqlAddr = "jdbc:mysql://mysql.stud.ntnu.no:3306/prodoteam_db?allowMultiQueries=true";
    private static String mysqlUser = "chrisnyv_demo";
    private static String mysqlPass = "rM48DmzH";
    
    
    public static boolean runQuery(String query, String... args){
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement(query);
            int i = 0;
            for(String arg : args)
                stmt.setString(++i, arg);
            return stmt.executeUpdate() != 0;
        }
        catch(SQLException e){
            return false;
        }
    }
    
    public static String loadSingleValue(String query, String... args){
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement(query);

            int i = 0;
            for(String arg : args)
                stmt.setString(++i, arg);

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return rs.getString(1);
            return null;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public static String test(){
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bruker");

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return rs.getString(2);
            return null;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public static ObservableList<Topic> Topic(String lectureID){
    	ObservableList<Topic> topicList = FXCollections.observableArrayList();
    	if(lectureID == "empty"){
    		return topicList;
    	}
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM topic WHERE lectureID = " + lectureID);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	ArrayList<String> topic = new ArrayList<String>();
            	for(int i = 1; i < 5; i++){
            		topic.add(rs.getString(i));
            	}topicList.add(new Topic(topic.get(0), topic.get(1), topic.get(2), topic.get(3)));
            }
            return topicList;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public static ObservableList<Lecture> lectures(String id){
    	ObservableList<Lecture> lectureList = FXCollections.observableArrayList();
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lecture WHERE courseID = " + id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	ArrayList<String> lecture = new ArrayList<String>();
            	for(int i = 1; i < 5; i++){
            		lecture.add(rs.getString(i));
            	}lectureList.add(new Lecture(lecture.get(0), lecture.get(1), lecture.get(2), lecture.get(3)));
            }
            return lectureList;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    public static ObservableList<Course> courses(){
    	// Makes a new observable list
    	ObservableList<Course> courseList = FXCollections.observableArrayList();
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course");
            
            //
            ResultSet rs = stmt.executeQuery();
            
            // While the
            while(rs.next()){
            	ArrayList<String> course = new ArrayList<String>();
            	for(int i = 1; i < 4; i++){
            		course.add(rs.getString(i));
            	}courseList.add(new Course(course.get(0), course.get(1), course.get(2)));
            }
            return courseList;
        }
        catch(SQLException e){
        	System.out.println(e);
            return null;
        }	
    }
    
    /*
     * Database metoder og querys for Lecture add-delete-update
     */
    
    //Legge til lecture
    public static void createLecture(Lecture lecture){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO lecture (courseID, number, name) VALUES (?,?,?)");
            stmt.setInt(1, Integer.parseInt(lecture.getCourseID()));
            stmt.setInt(2, Integer.parseInt(lecture.getlectureNumber()));
            stmt.setString(3, lecture.getlectureName());
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    //Slette lecture
    public static void deleteLecture(Lecture lecture){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM lecture WHERE lectureID = ?");
            //DELETE FROM 'prodoteam_db'.'lecture'WHERE 'lecture'.'lectureID' = 15
            stmt.setInt(1, Integer.parseInt(lecture.getLectureID()));
            //
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    /*
     * Database metoder og querys for Topic add-delete-update
     */
    public static void createTopic(Topic topic){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO topic (lectureID, number, name) VALUES (?,?,?)");
            stmt.setInt(1, Integer.parseInt(topic.getNumber()));
            stmt.setString(2, topic.getName());
            stmt.setString(3, topic.getLectureID());
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
}
