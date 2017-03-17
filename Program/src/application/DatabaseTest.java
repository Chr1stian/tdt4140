package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseTest {
	String testLectureID = "3";
	String testCourseID = "3";

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

}