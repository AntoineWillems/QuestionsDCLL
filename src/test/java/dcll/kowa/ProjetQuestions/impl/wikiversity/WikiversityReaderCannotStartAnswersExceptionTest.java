package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class WikiversityReaderCannotStartAnswersExceptionTest extends TestCase{
	@Test
	public void testWikiversityReaderCannotStartAnswersException() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("+reponse");
		}
		catch(WikiversityReaderCannotStartAnswersException e) {
			assertTrue(true);
		}
	     
		
	}
}
