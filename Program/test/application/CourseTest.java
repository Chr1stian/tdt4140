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
		test.setCourseID(testCourseID);
		String courseID = test.getCourseID();
		assertEquals(testCourseID, (test.courseIDProperty()).getValue());
		assertEquals(testCourseID, courseID);
	}
	@Test
	public void testCourseCode() {
		Course test = new Course(testCourseID, testCourseCode, testCourseName);
		test.setCourseCode(testCourseCode);
		String courseCode = test.getCourseCode();
		assertEquals(testCourseCode, (test.courseCodeProperty()).getValue());
		assertEquals(testCourseCode, courseCode);
	}
	@Test
	public void testCourseName() {
		Course test = new Course(testCourseID, testCourseCode, testCourseName);
		test.setCourseName(testCourseName);
		String courseName = test.getCourseName();
		assertEquals(testCourseName, (test.courseNameProperty()).getValue());
		assertEquals(testCourseName, courseName);
	}
}
