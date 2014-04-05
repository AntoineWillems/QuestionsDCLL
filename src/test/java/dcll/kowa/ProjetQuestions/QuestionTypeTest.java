package dcll.kowa.ProjetQuestions;

import junit.framework.TestCase;

import org.junit.Test;

public class QuestionTypeTest extends TestCase{

	@Test
	public void testGetCode() {
		QuestionType qt = QuestionType.ExclusiveChoice;
		assertEquals(1, qt.getCode());
	}

}
