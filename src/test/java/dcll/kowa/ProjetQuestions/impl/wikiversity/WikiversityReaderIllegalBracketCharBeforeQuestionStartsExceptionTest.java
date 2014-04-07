package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class WikiversityReaderIllegalBracketCharBeforeQuestionStartsExceptionTest extends TestCase {

	@Test
	public void testWikiversityReaderIllegalBracketCharBeforeQuestionStartsException() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("}");
		}
		catch(WikiversityReaderIllegalBracketCharBeforeQuestionStartsException e) {
			assertTrue(true);
		}
	     
		
	}
	
}
