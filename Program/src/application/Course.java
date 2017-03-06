package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {

	private final StringProperty courseID;
	private final StringProperty courseCode;
	private final StringProperty courseName;

	public Course(String courseID, String courseCode, String courseName) {
		this.courseID = new SimpleStringProperty(courseID);
		this.courseCode = new SimpleStringProperty(courseCode);
		this.courseName = new SimpleStringProperty(courseName);
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
	
	public String getCourseCode() {
		return courseCode.get();
	}

	public void setCourseCode(String courseCode) {
		this.courseCode.set(courseCode);
	}
	
	public StringProperty courseCodeProperty() {
		return courseCode;
	}

	public String getCourseName() {
		return courseName.get();
	}

	public void setCourseName(String courseName) {
		this.courseName.set(courseName);
	}
	
	public StringProperty courseNameProperty() {
		return courseName;
	}
}