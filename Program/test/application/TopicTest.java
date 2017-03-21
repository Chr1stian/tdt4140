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
		test.setTopicID(testTopicID);
		String topicID = test.getTopicID();
		assertEquals(testTopicID, test.topicIDProperty().getValue());
		assertEquals(testTopicID, topicID);
	}
	@Test
	public void testLectureID() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		test.setLectureID(testLectureID);
		String LectureID = test.getLectureID();
		assertEquals(testLectureID, test.lectureIDProperty().getValue());
		assertEquals(testLectureID, LectureID);
	}
	@Test
	public void testTopicNumber() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		test.setTopicNumber(testTopicNumber);
		String topicNumber = test.getTopicNumber();
		assertEquals(testTopicNumber, test.topicNumberProperty().getValue());
		assertEquals(testTopicNumber, topicNumber);
	}
	@Test
	public void testTopicName() {
		Topic test = new Topic(testTopicID, testLectureID, testTopicNumber, testTopicName);
		test.setTopicName(testTopicName);
		String topicName = test.getTopicName();
		assertEquals(testTopicName, test.topicNameProperty().getValue());
		assertEquals(testTopicName, topicName);
	}

}
