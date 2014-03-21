package dcll.kowa.ProjetQuestions;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;
import dcll.kowa.ProjetQuestions.impl.DefaultUserResponse;


public class DefaultUserResponseTest extends TestCase {
	
	private DefaultUserResponse userResponse;
	private DefaultQuestion questionTest;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		
		this.userResponse = new DefaultUserResponse();
		this.questionTest = new DefaultQuestion();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
		this.userResponse = null;
		this.questionTest = null;
	}
	
	@Test
	public void testGetUserIdentifier() {
		this.testSetUserIdentifier();
	}
	
	@Test
	public void testGetQuestion() {
		this.testSetQuestion();
	}
	
	@Test
	public void testGetUserAnswerBlockList() {
		assertEquals((this.userResponse.getUserAnswerBlockList() != null), true);
	}
	
	@Test
	public void testSetUserIdentifier() {
		this.userResponse.setUserIdentifier("userTest");
		assertEquals(this.userResponse.getUserIdentifier(), "userTest");
	}
	
	@Test
	public void testSetQuestion() {
		this.userResponse.setQuestion(this.questionTest);
		assertEquals(this.userResponse.getQuestion(), this.questionTest);
	}
	
	@Test
	public void testEvaluatePercentCredit() {
		assertEquals((this.userResponse.evaluatePercentCredit() instanceof Float), true);
	}
}
