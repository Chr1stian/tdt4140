package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
	
	private final StringProperty questionID;
	private final StringProperty topicID;
	private final StringProperty userID;
	private final StringProperty question;
	private final StringProperty answer;
	private final StringProperty rating;

	public Question(String questionID, String topicID, String userID, String question, String answer, String rating) {
		this.questionID = new SimpleStringProperty(questionID);
		this.topicID = new SimpleStringProperty(topicID);
		this.userID = new SimpleStringProperty(userID);
		this.question = new SimpleStringProperty(question);
		this.answer = new SimpleStringProperty(answer);
		this.rating = new SimpleStringProperty(rating);
	}

	public String getQuestionID() {
		return questionID.get();
	}

	public void setQuestionID(String topicID) {
		this.questionID.set(topicID);
	}
	
	public StringProperty questionIDProperty() {
		return questionID;
	}
	
	public String getTopicID() {
		return topicID.get();
	}

	public void setTopicID(String topicID) {
		this.topicID.set(topicID);
	}
	
	public StringProperty topicIDProperty() {
		return topicID;
	}
	
	public String getUserID() {
		return userID.get();
	}

	public void setUserID(String userID) {
		this.userID.set(userID);
	}
	
	public StringProperty userIDProperty() {
		return userID;
	}

	public String getQuestion() {
		return question.get();
	}

	public void setQuestion(String question) {
		this.question.set(question);
	}
	
	public StringProperty questionProperty() {
		return question;
	}
	
	public String getAnswer(){
		return answer.get();
	}
	
	public void setAnswer(String answer){
		this.answer.set(answer);
	}
	
	public StringProperty answerProperty(){
		return answer;
	}
	
	public String getRating(){
		return rating.get();
	}
	
	public void setRating(String rating){
		this.rating.set(rating);
	}
	
	public StringProperty ratingProperty(){
		return rating;
	}

}