package dcll.kowa.ProjetQuestions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;

public class DefaultQuestionTest {

	@Test
	public void testgetTitle(){
		DefaultQuestion dq = new DefaultQuestion();
		dq.setTitle("");
	assertEquals("",dq.getTitle());
	}
	
	@Test
	public void testgetQuestionType(){
		DefaultQuestion dq = new DefaultQuestion();
		dq.setQuestionType(QuestionType.TrueFalse);
	    assertEquals(QuestionType.TrueFalse,dq.getQuestionType());
	}
	
	@Test
	public void testgetBlockList(){
		DefaultQuestion dq = new DefaultQuestion();
		List<QuestionBlock> blockList = new ArrayList<QuestionBlock>();
	      assertEquals(blockList,dq.getBlockList());
	}
	
	
	@Test
	public void testsetTitle(){
		DefaultQuestion dq = new DefaultQuestion();
		dq.setTitle("test");
	assertTrue(dq.getTitle()=="test");
	}
	
	@Test
	public void testsetQuestionType(){
		DefaultQuestion dq = new DefaultQuestion();
		dq.setQuestionType(QuestionType.TrueFalse);
	assertTrue(dq.getQuestionType()==QuestionType.TrueFalse);
	}
	
	@Test
	public void testaddAnswerBlock(){
		AnswerBlock fragment = new DefaultAnswerBlock();
		List<QuestionBlock> blockList = new ArrayList<QuestionBlock>();
		List<AnswerBlock> answerBlockList = new ArrayList<AnswerBlock>();
		blockList.add(fragment);
		answerBlockList.add(fragment);
		assertEquals(blockList.size(),1);
		assertEquals(answerBlockList.size(),1);
	}

}
