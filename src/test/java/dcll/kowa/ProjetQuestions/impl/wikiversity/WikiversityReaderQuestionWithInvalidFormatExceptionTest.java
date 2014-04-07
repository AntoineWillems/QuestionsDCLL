package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class WikiversityReaderQuestionWithInvalidFormatExceptionTest extends TestCase{

	@Test
	public void testWikiversityReaderQuestionWithInvalidFormatException() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("{Séléctionnez un des \n le bon truc" 
    				+ "| type=\"()\"}" );
		}
		catch(WikiversityReaderQuestionWithInvalidFormatException e) {
			assertTrue(true);
		}
	     
		
	}
}
