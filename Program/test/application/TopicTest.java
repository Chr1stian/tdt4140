package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class TopicTest {
	String testTopicID = "11";
	String testLectureID = "12";
	String testTopicNumber = "13";
	String testTopicName = "testTopic";
	
	@Test
	public void testTopicID() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		String topicID = test.getTopicID();
		assertEquals(testTopicID, topicID);
	}
	@Test
	public void testLectureID() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		String LectureID = test.getLectureID();
		assertEquals(testLectureID, LectureID);
	}
	@Test
	public void testTopicNumber() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		String topicNumber = test.getTopicNumber();
		assertEquals(testTopicNumber, topicNumber);
	}
	@Test
	public void testTopicName() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		String topicName = test.getTopicName();
		assertEquals(testTopicName, topicName);
	}

}
