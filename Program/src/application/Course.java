package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Temporary class for the course untill database connection and javafx display is functional.
 * 
 * @author Trygve Karlsrud
 */
public class Course {

	private final StringProperty courseID;
	private final StringProperty courseName;

	public Course(String courseID, String courseName) {
		this.courseID = new SimpleStringProperty(courseID);
		this.courseName = new SimpleStringProperty(courseName);
	}
	
	public String getcourseID() {
		return courseID.get();
	}

	public void setcourseID(String courseID) {
		this.courseID.set(courseID);
	}
	
	public StringProperty courseIDProperty() {
		return courseID;
	}

	public String getcourseName() {
		return courseName.get();
	}

	public void setcourseName(String courseName) {
		this.courseName.set(courseName);
	}
	
	public StringProperty courseNameProperty() {
		return courseName;
	}
}