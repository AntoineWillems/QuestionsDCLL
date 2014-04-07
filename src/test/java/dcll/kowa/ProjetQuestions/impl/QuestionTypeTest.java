package dcll.kowa.ProjetQuestions.impl;

import junit.framework.TestCase;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.QuestionType;

public class QuestionTypeTest extends TestCase{

	@Test
	public void testGetCode() {
		QuestionType qt = QuestionType.ExclusiveChoice;
		assertEquals(1, qt.getCode());
	}

}
