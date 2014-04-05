package dcll.kowa.ProjetQuestions;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import dcll.kowa.ProjetQuestions.impl.gift.GiftQuestionService;
import junit.framework.TestCase;

public class GiftQuestionServiceTest extends TestCase {
	
private GiftQuestionService questionService;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
		this.questionService = new GiftQuestionService();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		this.questionService = null;
	}
	
	@Test
	public void testGetQuestionFromGiftText() {
		boolean result = false;
		
		try {
			this.questionService.getQuestionFromGiftText("{Sélectionnez les langages dynamiques | type=\"[]\"}"
    			+ "+ Clojure."
    			+ "- Java."
    			+ "+ Groovy."
    			+ "- Scala.");
			
			result = true;
		}
		catch(Exception e) {}
		
		assertEquals(result, true);
	}
	
	@Test
	public void testGetQuizFromGiftText() {
		boolean result = false;
		
		try {
			this.questionService.getQuizFromGiftText("{Sélectionnez les langages dynamiques | type=\"[]\"}"
    			+ "+ Clojure."
    			+ "- Java."
    			+ "+ Groovy."
    			+ "- Scala.");
			
			result = true;
		}
		catch(Exception e) {}
		
		assertEquals(result, true);
	}
	
	@Test
	public void testCreateUserResponseForQuestionAndAnswerBlockList() {
		boolean result = false;
		
		try {
			Question q = this.questionService.getQuestionFromGiftText("{Sélectionnez les langages dynamiques | type=\"[]\"}"
	    			+ "+ Clojure."
	    			+ "- Java."
	    			+ "+ Groovy."
	    			+ "- Scala.");
			
			List<List<String>> test = new ArrayList();
			this.questionService.createUserResponseForQuestionAndAnswerBlockList("test", q, test);
			result = true;
		}
		catch(Exception e) {}
		
		assertEquals(result, true);
	}
	
	@Test
	public void testGetNoResponseAnswer() {
		assertEquals((this.questionService.getNoResponseAnswer() instanceof DefaultAnswer), true);
	}
}
