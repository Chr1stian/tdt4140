package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseTest {
	String testLectureID = "3";
	String testCourseID = "3";
	String testLectureNumber = "5";
	String testLectureName = "testLecture";
	String testTopicNumber = "2";
	String testTopicName = "testTopic";

	@Test
	public void testTopics() {
		ObservableList<Topic> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Topic> topics = Database.topics(testLectureID);
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
	@Test
	public void testLectures() {
		ObservableList<Lecture> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Lecture> lectures = Database.lectures(testCourseID);
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
	
	@Test
	public void testCourses() {
		ObservableList<Course> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Course> courses = Database.courses();
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
	
	@Test
	public void testCreateLecture(){
		Lecture testLecture = new Lecture(null, testCourseID, testLectureNumber, testLectureName);
		Database.createLecture(testLecture);
		ObservableList<Lecture> lectures = Database.lectures(testCourseID);
		Lecture lecture = lectures.get(4);
		assertEquals(testLecture.getCourseID(), lecture.getCourseID());
		assertEquals(testLecture.getlectureName(), lecture.getlectureName());
		assertEquals(testLecture.getlectureNumber(), lecture.getlectureNumber());
		Database.deleteLecture(lecture);
	}
	
	@Test
	public void testDeleteLecture(){
		Lecture testLecture = new Lecture(null, testCourseID, testLectureNumber, testLectureName);
		Database.createLecture(testLecture);
		ObservableList<Lecture> lectures = Database.lectures(testCourseID);
		Lecture lecture = lectures.get(4);
		Database.deleteLecture(lecture);
		ObservableList<Lecture> testLectures = Database.lectures(testCourseID);
		int size = testLectures.size();
		assertEquals(size, 4);
	}
	@Test
	public void testCreateTopic(){
		Topic testTopic = new Topic(null, testLectureID, testTopicNumber, testTopicName);
		Database.createTopic(testTopic);
		ObservableList<Topic> topics = Database.topics(testLectureID);
		Topic topic = topics.get(1);
		assertEquals(testTopic.getLectureID(), topic.getLectureID());
		assertEquals(testTopic.getTopicName(), topic.getTopicName());
		assertEquals(testTopic.getTopicNumber(), topic.getTopicNumber());
		Database.deleteTopic(topic);
	}
	
	@Test
	public void testDeleteTopic(){
		Topic testTopic = new Topic(null, testLectureID, testTopicNumber, testTopicName);
		Database.createTopic(testTopic);
		ObservableList<Topic> topics = Database.topics(testLectureID);
		Topic topic = topics.get(1);
		System.out.println(topic.getTopicID());
		Database.deleteTopic(topic);
		ObservableList<Topic> testTopics = Database.topics(testLectureID);
		int size = testTopics.size();
		assertEquals(size, 1);	
	}
}
