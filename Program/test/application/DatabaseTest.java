package application;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseTest {
	String testTopicID = "10";
	String testLectureID = "3";
	String testCourseID = "3";
	String testLectureNumber = "5";
	String testLectureName = "testLecture";
	String testTopicNumber = "2";
	String testTopicName = "testTopic";
	
	static String testmySQLAdr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11165164?allowMultiQueries=true";
    static String testmySQLUser = "sql11165164";
    static String testmySQLPass = "FPJxTYsVA3";
    
    private static boolean setUpIsDone = false;
	
	Database db = null;
	
	@Before
	public void changeDatabase(){
		this.db = new Database(testmySQLAdr, testmySQLUser, testmySQLPass);
	}
	
	@Before
	public void setupDatabase() throws Exception{
		if(setUpIsDone){
			return;
		}
		
		Connection mConnection = null;
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    mConnection =  DriverManager.getConnection(testmySQLAdr, testmySQLUser, testmySQLPass);
		} catch (ClassNotFoundException e) {
		    System.err.println("Unable to get mysql driver: " + e);
		} catch (SQLException e) {
		    System.err.println("Unable to connect to server: " + e);
		}
		ScriptRunner runner = new ScriptRunner(mConnection, false, false);
		String file = "./test/application/testDB.sql";
		runner.runScript(new BufferedReader(new FileReader(file)));
		mConnection.close();
		setUpIsDone = true;
	}

	@SuppressWarnings("static-access")
	@Test
	public void testTopics() {
		ObservableList<Topic> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Topic> topics = db.topics(testLectureID);
		Topic topic = topics.get(0);
		testArray.add("7");
		testArray.add("3");
		testArray.add("1");
		testArray.add("Semat");
		test.add(new Topic(testArray.get(0), testArray.get(1), testArray.get(2), testArray.get(3)));
		Topic testTopic = test.get(0);
		assertEquals(testTopic.getTopicID(), topic.getTopicID());
		assertEquals(testTopic.getLectureID(), topic.getLectureID());
		assertEquals(testTopic.getTopicName(), topic.getTopicName());
		assertEquals(testTopic.getTopicNumber(), topic.getTopicNumber());
	}
	@SuppressWarnings("static-access")
	@Test
	public void testLectures() {
		ObservableList<Lecture> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Lecture> lectures = db.lectures(testCourseID);
		Lecture lecture = lectures.get(0);
		testArray.add("10");
		testArray.add("3");
		testArray.add("1");
		testArray.add("Fagintroduksjon");
		test.add(new Lecture(testArray.get(0), testArray.get(1), testArray.get(2), testArray.get(3)));
		Lecture testLecture = test.get(0);
		assertEquals(testLecture.getCourseID(), lecture.getCourseID());
		assertEquals(testLecture.getLectureID(), lecture.getLectureID());
		assertEquals(testLecture.getlectureName(), lecture.getlectureName());
		assertEquals(testLecture.getlectureNumber(), lecture.getlectureNumber());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCourses() {
		ObservableList<Course> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Course> courses = db.courses();
		Course course = courses.get(0);
		testArray.add("1");
		testArray.add("TDT4140");
		testArray.add("Software Engineering");
		test.add(new Course(testArray.get(0), testArray.get(1), testArray.get(2)));
		Course testCourse = test.get(0);
		assertEquals(testCourse.getCourseID(), course.getCourseID());
		assertEquals(testCourse.getCourseID(), course.getCourseID());
		assertEquals(testCourse.getCourseName(), course.getCourseName());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCreateLecture(){
		Lecture testLecture = new Lecture(null, testCourseID, testLectureNumber, testLectureName);
		db.createLecture(testLecture);
		ObservableList<Lecture> lectures = db.lectures(testCourseID);
		Lecture lecture = lectures.get(4);
		assertEquals(testLecture.getCourseID(), lecture.getCourseID());
		assertEquals(testLecture.getlectureName(), lecture.getlectureName());
		assertEquals(testLecture.getlectureNumber(), lecture.getlectureNumber());
		db.deleteLecture(lecture);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteLecture(){
		Lecture testLecture = new Lecture(null, testCourseID, testLectureNumber, testLectureName);
		db.createLecture(testLecture);
		ObservableList<Lecture> lectures = db.lectures(testCourseID);
		Lecture lecture = lectures.get(4);
		db.deleteLecture(lecture);
		ObservableList<Lecture> testLectures = db.lectures(testCourseID);
		int size = testLectures.size();
		assertEquals(size, 4);
	}
	@SuppressWarnings("static-access")
	@Test
	public void testCreateTopic(){
		Topic testTopic = new Topic(null, testLectureID, testTopicNumber, testTopicName);
		db.createTopic(testTopic);
		ObservableList<Topic> topics = db.topics(testLectureID);
		Topic topic = topics.get(1);
		assertEquals(testTopic.getLectureID(), topic.getLectureID());
		assertEquals(testTopic.getTopicName(), topic.getTopicName());
		assertEquals(testTopic.getTopicNumber(), topic.getTopicNumber());
		db.deleteTopic(topic);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testDeleteTopic(){
		Topic testTopic = new Topic(null, testLectureID, testTopicNumber, testTopicName);
		db.createTopic(testTopic);
		ObservableList<Topic> topics = db.topics(testLectureID);
		Topic topic = topics.get(1);
		db.deleteTopic(topic);
		ObservableList<Topic> testTopics = db.topics(testLectureID);
		int size = testTopics.size();
		assertEquals(size, 1);	
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testQuestion(){
		String testUserID = "1";
		String testQuest = "Hvor mange øvinger må man ha godkjent";
		String testAnswer = "3";
		String testRating = "0";
		Question testQuestion = new Question(null, testTopicID, testUserID, testQuest, testAnswer, testRating);
		ObservableList<Question> questions = db.Question(testTopicID, "topic");
		Question question = questions.get(0);
		assertEquals(question.getAnswer(), testQuestion.getAnswer());
		assertEquals(question.getQuestion(), testQuestion.getQuestion());
		assertEquals(question.getUserID(), testQuestion.getUserID());
		assertEquals(question.getTopicID(), testQuestion.getTopicID());
		assertEquals(question.getRating(), testQuestion.getRating());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testAnswerQuestion(){
		String testAnswer = "testAnswer";
		ObservableList<Question> questions = db.Question(testTopicID, "topic");
		Question question = questions.get(0);
		db.answerQuestion(question, testAnswer);
		ObservableList<Question> testQuestions = db.Question(testTopicID, "topic");
		Question testQuestion = testQuestions.get(0);
		assertEquals(testQuestion.getAnswer(), testAnswer);
		db.answerQuestion(testQuestion, "3");	
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testGetStats(){
		ArrayList<String> stats = db.getStats(testCourseID);
		ArrayList<String> testStats = new ArrayList<String>();
		testStats.add("4");
		testStats.add("9");
		testStats.add("0");
		testStats.add("0");
		testStats.add("0");
		testStats.add("No questions registered in this course yet");
		testStats.add("0");
		testStats.add("0");
		testStats.add("0");
		testStats.add("9");
		testStats.add("0 votes on 'Fagintroduksjon' from lecture 1");
		testStats.add("No ratings registered in this course yet");
		testStats.add("No ratings registered in this course yet");
		assertEquals(testStats, stats);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testLectureRating(){
		ObservableList<Rating> ratings = db.lectureRating(testCourseID);
		assertEquals(ratings.get(0).getName(), "Fagintroduksjon");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testTopicRating(){
		ObservableList<Rating> ratings = db.topicRating(testLectureID);
		assertEquals(ratings.get(0).getName(), "Semat");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testBadTopics(){
		ObservableList<Rating> ratings = db.badTopics(testCourseID);
		assertEquals(ratings.size(), 0);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testCourseAvgRating(){
		ArrayList<String> ratings = db.courseAvgRating(testCourseID);
		assertEquals(ratings.get(0), null);
		assertEquals(ratings.get(1), "0");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testLectureAvgRating(){
		ArrayList<String> ratings = db.lectureAvgRating(testLectureID);
		assertEquals(ratings.get(0), null);
		assertEquals(ratings.get(1), "0");
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testEditLecture(){
		Lecture originalLecture = db.lectures(testCourseID).get(2);
		String testName = "EDITEDNAME";
		String testID = originalLecture.getLectureID();
		String testNumber = originalLecture.getlectureNumber();
		Lecture testLecture = new Lecture(testID, testCourseID, testNumber, testName);
		db.editLecture(testLecture);
		ObservableList<Lecture> lecture = db.lectures(testCourseID);
		assertEquals(lecture.get(2).getlectureName(), testName);
		assertEquals(lecture.get(2).getLectureID(), testID);
		assertEquals(lecture.get(2).getCourseID(), testCourseID);
		assertEquals(lecture.get(2).getlectureNumber(), testNumber);
		db.editLecture(originalLecture);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testEditTopic(){
		Topic originalTopic = db.topics(testLectureID).get(0);
		String testName = "EDITEDNAME";
		String testID = originalTopic.getTopicID();
		String testNumber = originalTopic.getTopicNumber();
		Topic testTopic = new Topic(testID, testLectureID, testNumber, testName);
		db.editTopic(testTopic);
		ObservableList<Topic> topic = db.topics(testCourseID);
		assertEquals(topic.get(0).getTopicName(), testName);
		assertEquals(topic.get(0).getTopicID(), testID);
		assertEquals(topic.get(0).getLectureID(), testLectureID);
		assertEquals(topic.get(0).getTopicNumber(), testNumber);
		db.editTopic(originalTopic);
	}
	
	@AfterClass
	public static void tearDatabase(){
		List<String> tablename = Arrays.asList("rating", "question", "topic", "lecture", "course", "user");
		for(int i = 0; i < tablename.size(); i++){
			try{
				
	            Connection conn = DriverManager.getConnection(testmySQLAdr, testmySQLUser, testmySQLPass);
	            PreparedStatement stmt = conn.prepareStatement("DROP TABLE IF EXISTS " + tablename.get(i));
	            stmt.execute();
	            conn.close();
			}
	        catch(SQLException e){
	            System.out.println(e);
			}
		}
	}
}
