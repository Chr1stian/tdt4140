package application;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runners.JUnit4;

public class CourseTest {

	@Test
	public void testCourse() {
		Course test = new Course("8", "9", "testCourse");
		String DBID = test.getDBID();
		assertEquals("8", DBID);
	}

}
