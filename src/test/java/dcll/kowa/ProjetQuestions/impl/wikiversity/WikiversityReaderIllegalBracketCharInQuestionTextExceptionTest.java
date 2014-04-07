package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;

public class WikiversityReaderIllegalBracketCharInQuestionTextExceptionTest extends TestCase{
	
	
	@Test
	public void testWikiversityReaderIllegalBracketCharInQuestionTextException() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("{{");
		}
		catch(WikiversityReaderIllegalBracketCharInQuestionTextException e) {
			assertTrue(true);
		}
	     
		
	}

}
