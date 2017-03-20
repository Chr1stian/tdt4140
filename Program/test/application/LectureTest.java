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
		String lectureID = test.getLectureID();
		assertEquals(testLectureID, lectureID);
	}
	@Test
	public void testCourseID() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		String courseID = test.getCourseID();
		assertEquals(testCourseID, courseID);
	}
	@Test
	public void testLectureNumber() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		String lectureNumber = test.getlectureNumber();
		assertEquals(testLectureNumber, lectureNumber);
	}
	@Test
	public void testLectureName() {
		Lecture test = new Lecture(testLectureID, testCourseID, testLectureNumber, testLectureName);
		String lectureName = test.getlectureName();
		assertEquals(testLectureName, lectureName);
	}
}
