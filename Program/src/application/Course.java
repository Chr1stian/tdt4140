package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Temporary class for the course untill database connection and javafx display is functional.
 * 
 * @author Trygve Karlsrud
 */
public class Course {

	private final StringProperty DBID;
	private final StringProperty courseID;
	private final StringProperty courseName;

	public Course(String DBID, String courseID, String courseName) {
		this.DBID = new SimpleStringProperty(DBID);
		this.courseID = new SimpleStringProperty(courseID);
		this.courseName = new SimpleStringProperty(courseName);
	}
	
	public String getDBID() {
		return DBID.get();
	}

	public void setDBID(String DBID) {
		this.DBID.set(DBID);
	}
	
	public StringProperty DBIDProperty() {
		return DBID;
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