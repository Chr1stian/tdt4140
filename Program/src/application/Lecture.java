package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lecture {
	private final StringProperty lectureID;
	private final StringProperty courseID;
	private final StringProperty lectureNumber;
	private final StringProperty lectureName;

	public Lecture(String lectureID, String courseID, String lectureNumber, String lectureName) {
		this.lectureID = new SimpleStringProperty(lectureID);
		this.courseID = new SimpleStringProperty(courseID);
		this.lectureNumber = new SimpleStringProperty(lectureNumber);
		this.lectureName = new SimpleStringProperty(lectureName);
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
	
	public String getCourseID() {
		return courseID.get();
	}

	public void setCourseID(String courseID) {
		this.courseID.set(courseID);
	}
	
	public StringProperty courseIDProperty() {
		return courseID;
	}
	
	public String getlectureNumber() {
		return lectureNumber.get();
	}

	public void setlectureNumber(String lectureNumber) {
		this.lectureNumber.set(lectureNumber);
	}
	
	public StringProperty lectureNumberProperty() {
		return lectureNumber;
	}

	public String getlectureName() {
		return lectureName.get();
	}

	public void setlectureName(String lectureName) {
		this.lectureName.set(lectureName);
	}
	
	public StringProperty lectureNameProperty() {
		return lectureName;
	}
}
