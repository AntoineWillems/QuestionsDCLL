package dcll.kowa.ProjetQuestions.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		defaultAnswer = new DefaultAnswer();
		defaultAnswer.setTextValue("test");
		defaultAnswer.setPercentCredit((float)10.2);
		defaultAnswer.setIdentifier("id1");
		defaultAnswer.setFeedback("feedback");
	}
	
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		defaultAnswer=null;
	}
	
	@Test
	public void testEquals(){
		assertEquals("Est ce que testEquals est correct",true, defaultAnswer.equals(defaultAnswer));
		assertEquals("Est ce que testEquals est correct",false, defaultAnswer.equals(null));
		String s = "s";
		assertEquals("Est ce que testEquals est correct",false, defaultAnswer.equals(s));
		
		DefaultAnswer da = new DefaultAnswer();
		da.setIdentifier("OtherId");
		assertEquals("Est ce que testEquals est correct",false, defaultAnswer.equals(da));
		
		DefaultAnswer da1 = new DefaultAnswer();
		da1.setIdentifier("id1");
		assertEquals("Est ce que testEquals est correct",true, defaultAnswer.equals(da1));
	}
	
	@Test
	public void testHashCode(){
		assertEquals("Est ce que HashCode est correct","id1".hashCode(), defaultAnswer.hashCode());
	}
	
	@Test
	public void testGetTextValue(){
		assertEquals("Est ce que TextValue est correct", "test", defaultAnswer.getTextValue());
	}
	
	@Test
	public void testSetTextValue(){
		defaultAnswer.setTextValue("antoine");
		assertEquals("Est ce que TextValue est correct", "antoine", defaultAnswer.getTextValue());
	}
	
	@Test
	public void testGetPercentCredit(){
		assertEquals("Est ce que PercentCredit est correct", (float)10.2, defaultAnswer.getPercentCredit());
	}
	
	@Test
	public void testSetPercentCredit(){
		defaultAnswer.setPercentCredit((float)5.1);
		assertEquals("Est ce que TextValue est correct",(float)5.1, defaultAnswer.getPercentCredit());
	}
	
	@Test
	public void testGetIdentifier(){
		assertEquals("Est ce que Identifier est correct", "id1", defaultAnswer.getIdentifier());
	}
	
	@Test
	public void testSetIdentifier(){
		defaultAnswer.setIdentifier("id2");
		assertEquals("Est ce que Identifier est correct", "id2", defaultAnswer.getIdentifier());
	}
	
	@Test
	public void testSetFeedback(){
		assertEquals("Est ce que FeedBack est correct", "feedback", defaultAnswer.getFeedBack());
	}
	
	@Test
	public void testGetFeedback(){
		defaultAnswer.setFeedback("Other feedback");
		assertEquals("Est ce que FeedBack est correct", "Other feedback", defaultAnswer.getFeedBack());
	}
}
