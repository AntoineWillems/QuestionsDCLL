package dcll.kowa.ProjetQuestions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;

public class DefaultQuizTest {

	@Test
	public void testAddQuestion() {
		Question qq = new DefaultQuestion();
		List<Question> questionList = new ArrayList<Question>();
		questionList.add(qq);
		assertEquals(questionList.size(),1);
	}

	@Test
	public void testGetQuestionList() {
		Question qq = new DefaultQuestion();
		List<Question> blockList = new ArrayList<Question>();
	      assertEquals(blockList,qq.getBlockList());
	}

}
