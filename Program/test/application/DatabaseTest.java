package application;

import static org.junit.Assert.*;
import org.junit.Before;

import java.util.ArrayList;

import org.junit.Test;

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
	
	String testmySQLAdr = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11165164?allowMultiQueries=true";
    String testmySQLUser = "sql11165164";
    String testmySQLPass = "FPJxTYsVA3";
	
	Database db = null;
	
	@Before
	public void changeDatabase(){
		this.db = new Database(testmySQLAdr, testmySQLUser, testmySQLPass);
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
		Question testQuestion = new Question(null, testTopicID, testUserID, testQuest, testAnswer);
		ObservableList<Question> questions = db.Question(testTopicID);
		Question question = questions.get(0);
		assertEquals(question.getAnswer(), testQuestion.getAnswer());
		assertEquals(question.getQuestion(), testQuestion.getQuestion());
		assertEquals(question.getUserID(), testQuestion.getUserID());
		assertEquals(question.getTopicID(), testQuestion.getTopicID());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testAnswerQuestion(){
		String testAnswer = "testAnswer";
		ObservableList<Question> questions = db.Question(testTopicID);
		Question question = questions.get(0);
		db.answerQuestion(question, testAnswer);
		ObservableList<Question> testQuestions = db.Question(testTopicID);
		Question testQuestion = testQuestions.get(0);
		assertEquals(testQuestion.getAnswer(), testAnswer);
		db.answerQuestion(testQuestion, "3");	
	}
}
