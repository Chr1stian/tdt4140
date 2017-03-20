package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class LectureTest {
	String testLectureID = "6";
	String testCourseID = "7";
	String testLectureNumber = "8";
	String testLectureName = "testLecture";
	
	@Test
	public void testLectureID() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		test.setLectureID(testLectureID);
		String lectureID = test.getLectureID();
		assertEquals(testLectureID, test.lectureIDProperty().getValue());
		assertEquals(testLectureID, lectureID);
	}
	@Test
	public void testCourseID() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		test.setCourseID(testCourseID);
		String courseID = test.getCourseID();
		assertEquals(testCourseID, test.courseIDProperty().getValue());
		assertEquals(testCourseID, courseID);
	}
	@Test
	public void testLectureNumber() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		test.setlectureNumber(testLectureNumber);
		String lectureNumber = test.getlectureNumber();
		assertEquals(testLectureNumber, test.lectureNumberProperty().getValue());
		assertEquals(testLectureNumber, lectureNumber);
	}
	@Test
	public void testLectureName() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		test.setlectureName(testLectureName);
		String lectureName = test.getlectureName();
		assertEquals(testLectureName, test.lectureNameProperty().getValue());
		assertEquals(testLectureName, lectureName);
	}
}
