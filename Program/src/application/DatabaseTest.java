package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseTest {

	String testLectureID = "3";

	@Test
	public void testTopics() {
		ObservableList<Topic> test = FXCollections.observableArrayList();
		ArrayList<String> testArray = new ArrayList<String>();
		ObservableList<Topic> topics = Database.Topic(testLectureID);
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

}
