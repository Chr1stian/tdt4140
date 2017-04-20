package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionTest {
	String testQuestionID = "1";
	String testTopicID = "2";
	String testUserID = "3";
	String testQuestion = "testQuest";
	String testAnswer = "testAnsw";
	String testRating = "4";

	@Test
	public void testQuestionID() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setQuestionID(testQuestionID);
		String questionID = test.getQuestionID();
		assertEquals(testQuestionID, test.questionIDProperty().getValue());
		assertEquals(testQuestionID, questionID);
	}
	@Test
	public void testTopicID() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setTopicID(testTopicID);
		String topicID = test.getTopicID();
		assertEquals(testTopicID, test.topicIDProperty().getValue());
		assertEquals(testTopicID, topicID);
	}
	@Test
	public void testUserID() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setUserID(testUserID);
		String userID = test.getUserID();
		assertEquals(testUserID, test.userIDProperty().getValue());
		assertEquals(testUserID, userID);
	}
	@Test
	public void testQuestion() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setQuestion(testQuestion);
		String question = test.getQuestion();
		assertEquals(testQuestion, test.questionProperty().getValue());
		assertEquals(testQuestion, question);
	}
	@Test
	public void testAnswer() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setAnswer(testAnswer);
		String answer = test.getAnswer();
		assertEquals(testAnswer, test.answerProperty().getValue());
		assertEquals(testAnswer, answer);
	}
	@Test
	public void testRating() {
		Question test = new Question(testQuestionID, testTopicID, testUserID, testQuestion, testAnswer, testRating);
		test.setRating(testRating);
		String rating = test.getRating();
		assertEquals(testRating, test.ratingProperty().getValue());
		assertEquals(testRating, rating);
	}

}
