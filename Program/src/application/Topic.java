package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Topic {
	private final StringProperty topicID;
	private final StringProperty lectureID;
	private final StringProperty number;
	private final StringProperty name;

	public Topic(String topicID, String lectureID, String number, String name) {
		this.topicID = new SimpleStringProperty(topicID);
		this.lectureID = new SimpleStringProperty(lectureID);
		this.number = new SimpleStringProperty(number);
		this.name = new SimpleStringProperty(name);
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
	
	public String getNumber() {
		return number.get();
	}

	public void setNumber(String number) {
		this.number.set(number);
	}
	
	public StringProperty numberProperty() {
		return number;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
}
