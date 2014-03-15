package dcll.kowa.ProjetQuestions;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import junit.framework.TestCase;

public class DefaultAnswerTest extends TestCase{
	
	private DefaultAnswer defaultAnswer;
	
	public DefaultAnswerTest(String test){
		super(test);
	}
	
	/*
	 * Fonction effectuée avant les tests
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		defaultAnswer = new DefaultAnswer();
		defaultAnswer.setTextValue("test");
		defaultAnswer.setPercentCredit((float)10.2);
		defaultAnswer.setIdentifier("id1");
		defaultAnswer.setFeedback("feedback");
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		defaultAnswer=null;
	}
	
	
	public void testEquals(){
		
	}
	
	public void testHashCode(){
		
	}
	
	public void testGetTextValue(){
		assertEquals("Est ce que TextValue est correct", "test", defaultAnswer.getTextValue());
	}
	
	public void testSetTextValue(){
		defaultAnswer.setTextValue("antoine");
		assertEquals("Est ce que TextValue est correct", "antoine", defaultAnswer.getTextValue());
	}
	
	public void testGetPercentCredit(){
		assertEquals("Est ce que PercentCredit est correct", (float)10.2, defaultAnswer.getPercentCredit());
	}
	
	public void testSetPercentCredit(){
		defaultAnswer.setPercentCredit((float)5.1);
		assertEquals("Est ce que TextValue est correct",(float)5.1, defaultAnswer.getPercentCredit());
	}
	
	public void testGetIdentifier(){
		assertEquals("Est ce que Identifier est correct", "id1", defaultAnswer.getIdentifier());
	}
	
	public void testSetIdentifier(){
		defaultAnswer.setIdentifier("id2");
		assertEquals("Est ce que Identifier est correct", "id2", defaultAnswer.getIdentifier());
	}
	
	public void testSetFeedback(){
		assertEquals("Est ce que FeedBack est correct", "feedback", defaultAnswer.getFeedBack());
	}
	
	public void testGetFeedback(){
		defaultAnswer.setFeedback("Other feedback");
		assertEquals("Est ce que FeedBack est correct", "Other feedback", defaultAnswer.getFeedBack());
	}
}
