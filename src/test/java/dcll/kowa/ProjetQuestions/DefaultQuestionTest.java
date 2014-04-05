package dcll.kowa.ProjetQuestions;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;

public class DefaultQuestionTest extends TestCase {

	  private DefaultQuestion dq ;
	  private List<QuestionBlock> blockList;
	  private AnswerBlock fragment;
	  private List<AnswerBlock> answerBlockList;
	  private List<TextBlock> textBlockList;
	  
	@Before
	public void setUp() throws Exception {
		super.setUp();
		dq = new DefaultQuestion();
		blockList = new ArrayList<QuestionBlock>();
		fragment = new DefaultAnswerBlock();
		answerBlockList = new ArrayList<AnswerBlock>();
		textBlockList = new ArrayList<TextBlock>();
		
		dq.setTitle("test");
		dq.setQuestionType(QuestionType.TrueFalse);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		dq=null;
		blockList = null;
		fragment = null;
		answerBlockList = null;
		textBlockList = null;
	}

	@Test
	public void testGetTitle() {
		assertEquals("test",dq.getTitle());
	}

	@Test
	public void testGetQuestionType() {
		assertEquals(QuestionType.TrueFalse,dq.getQuestionType());
	}

	@Test
	public void testSetQuestionType() {
		assertTrue(dq.getQuestionType()==QuestionType.TrueFalse);
	}

	@Test
	public void testSetTitle() {
		assertTrue(dq.getTitle()=="test");
	}

	@Test
	public void testGetBlockList() {
		assertEquals(blockList,dq.getBlockList());
	}

	@Test
	public void testAddAnswerBlock() {
		dq.addAnswerBlock(fragment);
		assertEquals(true, (this.dq.getBlockList().size() > 0));
		assertEquals(true, (this.dq.getAnswerBlockList().size() > 0));
	}

	@Test
	public void testAddTextBlock() {
		TextBlock testText = new TextBlock() {
            public String getText() {
                return "TEST";
            }
        };
        
        this.dq.addTextBlock(testText);
        assertEquals((this.dq.getBlockList().size() > 0), true);
        assertEquals((this.dq.getTextBlockList().size() > 0), true);
	}

	@Test
	public void testGetAnswerBlockList() {
		assertEquals(answerBlockList,dq.getAnswerBlockList());
	}

	@Test
	public void testGetTextBlockList() {
		assertEquals(textBlockList,dq.getTextBlockList());
	}

}
