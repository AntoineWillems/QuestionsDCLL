package dcll.kowa.ProjetQuestions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;

public class DefaultQuestionTest {

	@Test
	public void testGetTitle() {
		DefaultQuestion dq = new DefaultQuestion();
		dq.setTitle("");
		assertEquals("",dq.getTitle());
	}

	@Test
	public void testGetQuestionType() {
		DefaultQuestion dq = new DefaultQuestion();
		dq.setQuestionType(QuestionType.TrueFalse);
	    assertEquals(QuestionType.TrueFalse,dq.getQuestionType());
	}

	@Test
	public void testSetQuestionType() {
		DefaultQuestion dq = new DefaultQuestion();
		dq.setQuestionType(QuestionType.TrueFalse);
		assertTrue(dq.getQuestionType()==QuestionType.TrueFalse);
	}

	@Test
	public void testSetTitle() {
		DefaultQuestion dq = new DefaultQuestion();
		dq.setTitle("test");
		assertTrue(dq.getTitle()=="test");
	}

	@Test
	public void testGetBlockList() {
		DefaultQuestion dq = new DefaultQuestion();
		List<QuestionBlock> blockList = new ArrayList<QuestionBlock>();
	    assertEquals(blockList,dq.getBlockList());
	}

	@Test
	public void testAddAnswerBlock() {
		AnswerBlock fragment = new DefaultAnswerBlock();
		List<QuestionBlock> blockList = new ArrayList<QuestionBlock>();
		List<AnswerBlock> answerBlockList = new ArrayList<AnswerBlock>();
		blockList.add(fragment);
		answerBlockList.add(fragment);
		assertEquals(blockList.size(),1);
		assertEquals(answerBlockList.size(),1);
	}

	@Test
	public void testAddTextBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAnswerBlockList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTextBlockList() {
		fail("Not yet implemented");
	}

}
