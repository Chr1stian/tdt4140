package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {
	
	private final StringProperty questionID;
	private final StringProperty topicID;
	private final StringProperty questionNumber;
	private final StringProperty questionName;

	public Question(String questionID, String topicID, String questionNumber, String questionName) {
		this.questionID = new SimpleStringProperty(questionID);
		this.topicID = new SimpleStringProperty(topicID);
		this.questionNumber = new SimpleStringProperty(questionNumber);
		this.questionName = new SimpleStringProperty(questionName);
	}
	
	public String getquestionID() {
		return questionID.get();
	}

	public void setquestionID(String topicID) {
		this.questionID.set(topicID);
	}
	
	public StringProperty questionIDProperty() {
		return questionID;
	}
	
	public String gettopicID() {
		return topicID.get();
	}

	public void settopicID(String topicID) {
		this.topicID.set(topicID);
	}
	
	public StringProperty topicIDProperty() {
		return topicID;
	}
	
	public String getquestionNumber() {
		return questionNumber.get();
	}

	public void setquestionNumber(String questionNumber) {
		this.questionNumber.set(questionNumber);
	}
	
	public StringProperty questionNumberProperty() {
		return questionNumber;
	}

	public String getquestionName() {
		return questionName.get();
	}

	public void setquestionName(String questionName) {
		this.questionName.set(questionName);
	}
	
	public StringProperty questionNameProperty() {
		return questionName;
	}
}