package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Topic {
	
	private final StringProperty topicID;
	private final StringProperty lectureID;
	private final StringProperty topicNumber;
	private final StringProperty topicName;

	public Topic(String topicID, String lectureID, String topicNumber, String topicName) {
		this.topicID = new SimpleStringProperty(topicID);
		this.lectureID = new SimpleStringProperty(lectureID);
		this.topicNumber = new SimpleStringProperty(topicNumber);
		this.topicName = new SimpleStringProperty(topicName);
	}
	
	public String getTopicID() {
		return topicID.get();
	}

	public void setTopicID(String lectureID) {
		this.topicID.set(lectureID);
	}
	
	public StringProperty topicIDProperty() {
		return topicID;
	}
	
	public String getLectureID() {
		return lectureID.get();
	}

	public void setLectureID(String lectureID) {
		this.lectureID.set(lectureID);
	}
	
	public StringProperty lectureIDProperty() {
		return lectureID;
	}
	
	public String getTopicNumber() {
		return topicNumber.get();
	}

	public void setTopicNumber(String topicNumber) {
		this.topicNumber.set(topicNumber);
	}
	
	public StringProperty topicNumberProperty() {
		return topicNumber;
	}

	public String getTopicName() {
		return topicName.get();
	}

	public void setTopicName(String topicName) {
		this.topicName.set(topicName);
	}
	
	public StringProperty topicNameProperty() {
		return topicName;
	}
}
