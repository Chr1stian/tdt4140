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
	private static String mysqlAddr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11164632?allowMultiQueries=true";
    private static String mysqlUser = "sql11164632";
    private static String mysqlPass = "JKb6SqBp59";
	//private static String mysqlAddr = "jdbc:mysql://mysql.stud.ntnu.no:3306/prodoteam_testdb?allowMultiQueries=true";
    //private static String mysqlUser = "jonaseth_tdt4140";
    //private static String mysqlPass = "tdt4140";
    
    @SuppressWarnings("static-access")
	public Database(String adr, String user, String password){
    	this.mysqlAddr = adr;
    	this.mysqlUser = user;
    	this.mysqlPass = password;
    }
    
    // ------------SIDEBAR------------
    
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
            conn.close();
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
            conn.close();
            return lectureList;
        }
        catch(SQLException e){
        	System.out.println(e);
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
            conn.close();
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
            conn.close();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    // Edit lecture
    public static void editLecture(Lecture lecture){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            //System.out.println(("UPDATE lecture SET number = '" + lecture.getlectureNumber() + "', name = '" + lecture.getlectureName() + "' WHERE lectureID = " + lecture.getLectureID()));
            PreparedStatement stmt = conn.prepareStatement(
            		"UPDATE lecture SET number = '" + lecture.getlectureNumber() + "', name = '" + 
            		lecture.getlectureName() + "' WHERE lectureID = " + lecture.getLectureID());
            stmt.executeUpdate();
            conn.close();
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
            conn.close();
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
            conn.close();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    //Edit topic
    public static void editTopic(Topic topic){
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement(
            		"UPDATE topic SET number = '" + topic.getTopicNumber() + "', name = '" + 
            		topic.getTopicName() + "' WHERE topicID = " + topic.getTopicID());
            stmt.executeUpdate();
            conn.close();
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
            conn.close();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    
    // ------------QUESTION TAB------------
    /*
     * Display questions from students
     */
    // Finds all questions from a given topic (topicID)
        
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
            	for(int i = 1; i < 6; i++){
            		question.add(rs.getString(i));
            	}questionList.add(new Question(question.get(0), question.get(1), question.get(2), question.get(3), question.get(4)));
            }
           	conn.close();
            return questionList;
        }
        catch(SQLException e){
        	return null;
        }
    }
    
    // Answer Question
    public static void answerQuestion(Question question, String answer){
    	try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("UPDATE question SET answer = '" + answer + "' WHERE questionID = " + question.getQuestionID());
            stmt.executeUpdate();
            conn.close();
    	}
        catch(SQLException e){
        	System.out.println(e);
        }
    }
    
    
    // ------------FEEDBACK TAB------------
    
    // Get average rating for all lectures in a given course
    public static ObservableList<Rating> lectureRating(String courseID){
    	ObservableList<Rating> ratingList = FXCollections.observableArrayList();    	
    	if(courseID == "empty"){
    		return ratingList;
    	}
    	String getRatings = "SELECT table2.number, table2.name, ROUND(AVG(table2.stars),1) AS average, COUNT(table2.stars) AS votes "
    					  + "FROM "
    					  +		"(SELECT lecture.number, lecture.lectureID, lecture.name, table1.topicID ,table1.stars "
					   	  + 	"FROM lecture "
					      + 	"RIGHT JOIN "
					      +			"(SELECT topic.lectureID, topic.topicID, rating.stars "
					      +			"FROM rating "
					      +			"RIGHT JOIN topic ON topic.topicID = rating.topicID "
					      +			"WHERE topic.lectureID IN "
					      +				"(SELECT lecture.lectureID "
					      +				"FROM lecture "
					      +				"INNER JOIN course ON course.courseID = lecture.courseID "
					      +				"WHERE lecture.courseID = " + courseID
					      +     		") "
					      +   		") table1 "
					      + 	"ON lecture.lectureID = table1.lectureID "
					      +		") table2 "
					      + "GROUP BY table2.name "
					      + "ORDER BY table2.number ASC";
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement(getRatings);
           	ResultSet rs = stmt.executeQuery();
           	while(rs.next()){
           		if(rs.getString(3) != null){
           			ratingList.add(new Rating(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
           		}else{
           			ratingList.add(new Rating(rs.getString(1), rs.getString(2), "Not yet rated", rs.getString(4)));
           		}
            }
           	conn.close();
            return ratingList;
        }
        catch(SQLException e){
        	System.out.println(e);
        	return null;
        }
    }
    
    // Get average and total votes on a given course
    public static ArrayList<String> courseAvgRating(String courseID){
    	ArrayList<String> rating = new ArrayList<String>();
    	// Statement in one line:
    	// "SELECT AVG(rating.stars) AS average, COUNT(rating.stars) AS votes FROM rating INNER JOIN (SELECT topic.topicID FROM topic INNER JOIN lecture ON lecture.lectureID = topic.lectureID WHERE lecture.courseID = " + courseID + ") table1 ON rating.topicID = table1.topicID "
    	String statement = "SELECT AVG(rating.stars) AS average, COUNT(rating.stars) AS votes "
    					 + "FROM rating "
    					 + "INNER JOIN (SELECT topic.topicID "
    					 +		"FROM topic "
    					 +		"INNER JOIN lecture ON lecture.lectureID = topic.lectureID "
    					 +		"WHERE lecture.courseID = " + courseID + ") table1 "
    					 + "ON rating.topicID = table1.topicID ";
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement(statement);
           	ResultSet rs = stmt.executeQuery();
           	while(rs.next()){
           		rating.add(rs.getString(1));
           		rating.add(rs.getString(2));
            }
           	conn.close();
            return rating;
        }
        catch(SQLException e){
        	System.out.println(e);
        	return null;
        }
    }
    
    // Get average rating for all topics in a given lecture
    public static ObservableList<Rating> topicRating(String lectureID){
    	ObservableList<Rating> ratingList = FXCollections.observableArrayList();    	
    	// Statement in one line:
    	// "SELECT table1.number, table1.name, ROUND(AVG(table1.stars),1) AS average, COUNT(table1.stars) as votes FROM (SELECT topic.number, topic.name, rating.stars FROM rating RIGHT JOIN topic ON topic.topicID = rating.topicID WHERE topic.lectureID = " + lectureID + ") table1 GROUP BY table1.name ORDER BY table1.number ASC"
    	String getRatings = "SELECT table1.number, table1.name, ROUND(AVG(table1.stars),1) AS average, COUNT(table1.stars) as votes "
    					  + "FROM "
    					  + 	"(SELECT topic.number, topic.name, rating.stars "
    					  +		"FROM rating "
    					  +		"RIGHT JOIN topic ON topic.topicID = rating.topicID "
    					  +		"WHERE topic.lectureID = " + lectureID + ") table1 "
    					  + "GROUP BY table1.name "
    					  + "ORDER BY table1.number ASC";
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement(getRatings);
           	ResultSet rs = stmt.executeQuery();
           	while(rs.next()){
           		if(rs.getString(3) != null){
           			ratingList.add(new Rating(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
           		}else{
           			ratingList.add(new Rating(rs.getString(1), rs.getString(2), "Not yet rated", rs.getString(4)));
           		}
            }
           	conn.close();
            return ratingList;
        }
        catch(SQLException e){
        	System.out.println(e);
        	return null;
        }
    }
    
    // Get average and total votes on a given course
    public static ArrayList<String> lectureAvgRating(String lectureID){
    	ArrayList<String> rating = new ArrayList<String>();
    	// Statement in one line:
    	// "SELECT AVG(rating.stars) AS average, COUNT(rating.stars) AS votes FROM rating INNER JOIN topic ON rating.topicID = topic.topicID WHERE topic.lectureID = " + lectureID;
    	String statement = "SELECT AVG(rating.stars) AS average, COUNT(rating.stars) AS votes "
    					 + "FROM rating "
    					 + "INNER JOIN topic ON rating.topicID = topic.topicID "
    					 + "WHERE topic.lectureID = " + lectureID;
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement(statement);
           	ResultSet rs = stmt.executeQuery();
           	while(rs.next()){
           		rating.add(rs.getString(1));
           		rating.add(rs.getString(2));
            }
           	conn.close();
            return rating;
        }
        catch(SQLException e){
        	System.out.println(e);
        	return null;
        }
    }
    
    
    // ------------STATISTICS TAB------------
    
    // Get topics with average rating less than 3
    public static ObservableList<Rating> badTopics(String courseID){
    	ObservableList<Rating> ratingList = FXCollections.observableArrayList();
    	if(courseID == "empty"){
    		return ratingList;
    	}
    	// Statement in one line:
    	// "SELECT * FROM (SELECT lecture.number lectureNumber, lecture.name lectureName, table1.number topicNumber, table1.name topicName, ROUND(AVG(table1.stars),1) average, COUNT(table1.stars) votes FROM lecture RIGHT JOIN (SELECT topic.lectureID, topic.name, topic.topicID, topic.number, rating.stars FROM rating RIGHT JOIN topic ON topic.topicID = rating.topicID WHERE topic.lectureID IN (SELECT lecture.lectureID FROM lecture INNER JOIN course ON course.courseID = lecture.courseID WHERE lecture.courseID = " + courseID + ") ) table1 ON lecture.lectureID = table1.lectureID GROUP BY topicID ) table2 WHERE table2.average <= 3 ORDER BY lectureNumber, topicNumber ASC"
    	String statement = "SELECT * "
    					 + "FROM "
    					 + 		"(SELECT lecture.number lectureNumber, lecture.name lectureName, table1.number topicNumber, table1.name topicName, ROUND(AVG(table1.stars),1) average, COUNT(table1.stars) votes "
    					 + 		"FROM lecture "
    					 + 		"RIGHT JOIN "
    					 + 			"(SELECT topic.lectureID, topic.name, topic.topicID, topic.number, rating.stars "
    					 + 			"FROM rating "
    					 + 			"RIGHT JOIN topic ON topic.topicID = rating.topicID "
    					 + 			"WHERE topic.lectureID IN "
    					 + 				"(SELECT lecture.lectureID "
    					 + 				"FROM lecture "
    					 + 				"INNER JOIN course ON course.courseID = lecture.courseID "
    					 + 				"WHERE lecture.courseID = " + courseID + ") "
    					 + 			") table1 "
    					 + 			"ON lecture.lectureID = table1.lectureID "
    					 + 			"GROUP BY topicID "
    					 + 		") table2 "
    					 + 		"WHERE table2.average <= 3 "
    					 + 		"ORDER BY lectureNumber, topicNumber ASC";
    	try{
    		Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
    		PreparedStatement stmt = conn.prepareStatement(statement);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
           		ratingList.add(new Rating(rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
           	conn.close();
            return ratingList;
        }
        catch(SQLException e){
        	System.out.println(e);
        	return null;
        }
    }
    
}
