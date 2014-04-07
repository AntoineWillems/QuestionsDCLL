package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class WikiversityReaderWrongTypeDefinitionTest extends TestCase {
	@Test
	public void testWikiversityReaderWrongTypeDefinition() throws WikiversityReaderException, IOException{

		WikiversityQuestionService wqs = new WikiversityQuestionService();

		try{
			wqs.getQuestionFromGiftText("{Séléctionnez un des \n le bon truc" 
    				+ "| type=\"aa\"}");
		}
		catch(WikiversityReaderWrongTypeDefinition e) {
			assertTrue(true);
		}
	     
		
	}
}
