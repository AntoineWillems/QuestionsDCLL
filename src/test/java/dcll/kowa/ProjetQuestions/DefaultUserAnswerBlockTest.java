package dcll.kowa.ProjetQuestions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultUserAnswerBlock;
import junit.framework.TestCase;

public class DefaultUserAnswerBlockTest extends TestCase {
	
	private DefaultUserAnswerBlock userAnswerBlock;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.userAnswerBlock = new DefaultUserAnswerBlock();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.userAnswerBlock = null;
	}
	
	@Test
	public void testGetAnswerList() {
		assertEquals((this.userAnswerBlock.getAnswerList() != null), true);
	}
	
	@Test
	public void testEvaluatePercentCredit() {
		assertEquals((this.userAnswerBlock.evaluatePercentCredit() instanceof Float), true);
	}
}
