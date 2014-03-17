package dcll.kowa.ProjetQuestions;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;

public class DefaultQuestionTest extends TestCase{

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
		DefaultQuestion dq = new DefaultQuestion();
		AnswerBlock fragment = new DefaultAnswerBlock();
		dq.addAnswerBlock(fragment);
		
		assertEquals(fragment,dq.getBlockList().get(0));
		assertEquals(fragment,dq.getAnswerBlockList().get(0));
		

	}

	@Test
	public void testAddTextBlock() {
		
	}

	@Test
	public void testGetAnswerBlockList() {
		DefaultQuestion dq = new DefaultQuestion();
		List<AnswerBlock> answerBlockList = new ArrayList<AnswerBlock>();
	    assertEquals(answerBlockList,dq.getAnswerBlockList());
	}

	@Test
	public void testGetTextBlockList() {
		DefaultQuestion dq = new DefaultQuestion();
		List<TextBlock> textBlockList = new ArrayList<TextBlock>();
	    assertEquals(textBlockList,dq.getTextBlockList());
	}

}
