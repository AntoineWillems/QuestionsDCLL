package dcll.kowa.ProjetQuestions.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.Answer;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import junit.framework.TestCase;

public class DefaultAnswerBlockTest extends TestCase {

	private DefaultAnswerBlock defaultAnswerBlock;
	private List<Answer> answerList = new ArrayList<Answer>();
	
	public DefaultAnswerBlockTest(String test){
		super(test);
	}
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		defaultAnswerBlock = new DefaultAnswerBlock();
	}
	
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		defaultAnswerBlock=null;
	}
	
	@Test
	public void testGetAnswerList(){
		assertEquals("Est ce que getAnswerList est correct",answerList, defaultAnswerBlock.getAnswerList());
	}
	
	@Test
	public void testAddAnswer(){
		DefaultAnswer answer = new DefaultAnswer();
		answer.setIdentifier("id");
		defaultAnswerBlock.addAnswer(answer);
		assertEquals("Est ce que addAnswer est correct",answer, defaultAnswerBlock.getAnswerList().get(0));
	}
}
