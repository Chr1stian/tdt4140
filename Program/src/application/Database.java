package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    // Finds all topics from a given lecture (lectureID)
    public static ObservableList<Topic> topics(String lectureID){
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
    
    // Finds all lectures from a given course (courseID)
    public static ObservableList<Lecture> lectures(String courseID){
    	ObservableList<Lecture> lectureList = FXCollections.observableArrayList();
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM lecture WHERE courseID = " + courseID);

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
    
    // Finds all courses from the database
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
     * Database methods and queries for Lecture add-delete-update
     */
    
    // Add lecture
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
    
    // Edit lecture
    public static void editLecture(Lecture lecture){
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
    
    // Delete lecture
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
     * Database methods and queries for Topic add-delete-update
     */
    // Add topic
    public static void createTopic(Topic topic){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO topic (lectureID, number, name) VALUES (?,?,?)");
            stmt.setInt(1, Integer.parseInt(topic.getLectureID()));
            stmt.setInt(2, Integer.parseInt(topic.getTopicNumber()));
            stmt.setString(3, topic.getTopicName());
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    //Edit topic
    public static void editTopic(Topic topic){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO topic (lectureID, number, name) VALUES (?,?,?)");
            stmt.setInt(1, Integer.parseInt(topic.getLectureID()));
            stmt.setInt(2, Integer.parseInt(topic.getTopicNumber()));
            stmt.setString(3, topic.getTopicName());
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    // Delete topic
    public static void deleteTopic(Topic topic){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM topic WHERE topicID = ?");
            stmt.setInt(1, Integer.parseInt(topic.getTopicID()));
            
            stmt.executeUpdate();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    /*
     * Display questions from students
     */
    // Finds all questions from a given topic (topicID)
    
    // Currently the only question path:	Database -> Introduksjon til faget og databaser -> fagintroduksjon
    
    public static ObservableList<Question> Question(String topicID){
    	ObservableList<Question> questionList = FXCollections.observableArrayList();
    	if(topicID == "empty"){
    		return questionList;
    	}
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
           	PreparedStatement stmt = conn.prepareStatement("SELECT * FROM question WHERE topicID = " + topicID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	ArrayList<String> question = new ArrayList<String>();
            	for(int i = 1; i < 5; i++){
            		question.add(rs.getString(i));
            	}questionList.add(new Question(question.get(0), question.get(1), question.get(2), question.get(3)));
            }
            return questionList;
        }
        catch(SQLException e){
            return null;
        }
    }
    
}
