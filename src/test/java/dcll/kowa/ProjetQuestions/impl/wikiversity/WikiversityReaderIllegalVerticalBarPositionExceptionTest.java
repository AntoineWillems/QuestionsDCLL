package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class WikiversityReaderIllegalVerticalBarPositionExceptionTest extends TestCase{
	@Test
	public void testWikiversityReaderIllegalVerticalBarPositionException() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("|");
		}
		catch(WikiversityReaderIllegalVerticalBarPositionException e) {
			assertTrue(true);
		}
	     
		
	}

}
