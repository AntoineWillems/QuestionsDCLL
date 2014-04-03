package dcll.kowa.ProjetQuestions;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;


import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderException;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityQuestionService;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityQuizContentHandler;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReader;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReaderException;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReaderNotEscapedCharacterException;

import junit.framework.TestCase;

public class WikiversityReaderTest extends TestCase{

	private WikiversityReader wk;
	StringReader reader;
	
	protected void setUp() throws Exception {
		super.setUp();
		reader = new StringReader("{Séléctionnez un bon truc"
    				+ "| type=\"()\"}"
    				+ "+ Clojure."
    				+ "||FeedBack de Clojure"
    				+ "- Java."
    				+ "||FeedBack de Java"
    				+ "+ Groovy."
    				+ "- Scala. ");
		 wk = new WikiversityReader();
	}
	

	
	@Test
	public void testGetQuizContentHandler(){
		assertEquals(null,wk.getQuizContentHandler());
	}
	
	@Test
	public void testSetQuizContentHandler(){
		QuizContentHandler quizContentHandler = new WikiversityQuizContentHandler();
		wk.setQuizContentHandler(quizContentHandler);
		assertEquals(quizContentHandler,wk.getQuizContentHandler());
	}
	
	
	/*
	@Test
	public void testParse() throws WikiversityReaderNotEscapedCharacterException, WikiversityReaderException, IOException, GiftReaderException{
		StringReader reader1 = new StringReader("{{{{");
		wk.parse(reader1);
		
	}
	
	@Test
	public void testParse1() throws WikiversityReaderNotEscapedCharacterException, WikiversityReaderException, IOException, GiftReaderException{
		StringReader reader1 = new StringReader("}}}");
		wk.parse(reader1);
		
	}
	*/
}
