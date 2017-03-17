package application;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

public class CourseTest {
	String testCourseID = "4";
	String testCourseCode = "5";
	String testCourseName = "testCourse";

	@Test
	public void testCourseID() {
		Course test = new Course(testCourseID, testCourseCode, testCourseName);
		String courseID = test.getCourseID();
		assertEquals(testCourseID, courseID);
	}
	@Test
	public void testCourseCode() {
		Course test = new Course(testCourseID, testCourseCode, testCourseName);
		String courseCode = test.getCourseCode();
		assertEquals(testCourseCode, courseCode);
	}
	@Test
	public void testCourseName() {
		Course test = new Course(testCourseID, testCourseCode, testCourseName);
		String courseName = test.getCourseName();
		assertEquals(testCourseName, courseName);
	}
}
