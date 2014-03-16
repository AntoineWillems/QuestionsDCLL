package dcll.kowa.ProjetQuestions;

import java.util.ArrayList;
import java.util.List;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import junit.framework.TestCase;

public class DefaultAnswerBlockTest extends TestCase {

	private DefaultAnswerBlock defaultAnswerBlock;
	private List<Answer> answerList = new ArrayList<Answer>();
	
	public DefaultAnswerBlockTest(String test){
		super(test);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		defaultAnswerBlock = new DefaultAnswerBlock();
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		defaultAnswerBlock=null;
	}
	
	public void testGetAnswerList(){
		assertEquals("Est ce que getAnswerList est correct",answerList, defaultAnswerBlock.getAnswerList());
	}
	
	public void testAddAnswer(){
		DefaultAnswer answer = new DefaultAnswer();
		answer.setIdentifier("id");
		defaultAnswerBlock.addAnswer(answer);
		assertEquals("Est ce que addAnswer est correct",answer, defaultAnswerBlock.getAnswerList().get(0));
	}
}
