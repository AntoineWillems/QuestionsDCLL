package dcll.kowa.ProjetQuestions.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.Question;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;
import dcll.kowa.ProjetQuestions.impl.DefaultQuiz;

public class DefaultQuizTest extends TestCase {

	private Question qq;
	private DefaultQuiz dqq;
	private List<Question> blockList;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		qq = new DefaultQuestion();
		dqq = new DefaultQuiz();
		blockList = new ArrayList<Question>();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		qq= null;
		dqq = null;
		blockList = null;
	}

	@Test
	public void testAddQuestion() {
		dqq.addQuestion(qq);
		assertEquals(qq,dqq.getQuestionList().get(0));
	}

	@Test
	public void testGetQuestionList() {
		assertEquals(blockList,qq.getBlockList());
	}

}
