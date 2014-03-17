package dcll.kowa.ProjetQuestions;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;
import dcll.kowa.ProjetQuestions.impl.DefaultQuiz;

public class DefaultQuizTest extends TestCase {

	@Test
	public void testAddQuestion() {
		Question qq = new DefaultQuestion();
		DefaultQuiz dqq = new DefaultQuiz();
		dqq.addQuestion(qq);
		assertEquals(qq,dqq.getQuestionList().get(0));
	}

	@Test
	public void testGetQuestionList() {
		Question qq = new DefaultQuestion();
		List<Question> blockList = new ArrayList<Question>();
	      assertEquals(blockList,qq.getBlockList());
	}

}
