import static org.junit.Assert.*;

import org.junit.Test;


public class StudentTests {

	@Test
	public void testCourseRoster() {
		CourseRoster sched = new CourseRoster();
		Student s = new Student("Mack", 1000);
		sched.addStudent(s);
		String list = sched.getNames();
		int Val = sched.getValues();
		assertTrue(list.equals("Mack"));
		assertTrue(Val == 1000);
		
	}
	/* You should implement the remaining tests you desire */
	
	@Test
	public void NullRemoveTest() {
		CourseRoster sched = new CourseRoster();
		Student s = new Student("John", 9001);
		sched.addStudent(s);
		assertTrue(sched.removeStudent("John"));
		assertTrue(sched == null);
	}
}
