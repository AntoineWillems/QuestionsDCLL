package dcll.kowa.ProjetQuestions;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityQuizContentHandler;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReader;
import dcll.kowa.ProjetQuestions.impl.wikiversity.WikiversityReaderException;
import junit.framework.TestCase;

public class WikiversityReaderTest extends TestCase{

	private WikiversityReader wk;
	StringReader reader;
	int controlCharAccumulator;
	int controlCharAccumulatorResponse = -1;
	int nextChar;
	int nextCharResponse = -1;
	boolean questionHasStarted;
	boolean questionHasStartedResponse = false;
    boolean questionHasEnded;
    boolean questionHasEndedResponse = true;
    boolean textHasStarted; 
    boolean textHasStartedResponse = false;
    boolean hasSpecifiedType; 
    boolean hasSpecifiedTypeResponse = false;
    boolean answersCanStart;  
    boolean answersCanStartResponse = false;
    boolean answerFragmentHasStarted; 
    boolean answerFragmentHasStartedResponse = false;
    boolean answerFragmentHasEnded; 
    boolean answerFragmentHasEndedResponse = true;
    boolean answerHasStarted; 
    boolean answerHasStartedResponse = false;
    boolean answerFeedbackHasStarted; 
    boolean answerFeedbackHasStartedResponse = false;
    boolean answerCreditHasStarted; 
    boolean answerCreditHasStartedResponse = false;
    
	protected void setUp() throws Exception {
		super.setUp();
		 
		reader = new StringReader("{Séléctionnez un des langages suivants: "
				+ "| type=\"()\"}"
				+ "+ Clojure."
				+ "||FeedBack de Clojure"
				+ "- Java."
				+ "||FeedBack de Java"
				+ "+ Groovy."
				+ "- Scala.");
		 /* TEST DES ATTRIBUTS PRIVES */
			
		   
		 BasicConfigurator.configure();
	}
	

	
	@Test
	public void testGetQuizContentHandler(){
		wk = new WikiversityReader();
		assertEquals(null,wk.getQuizContentHandler());
	}
	
	@Test
	public void testSetQuizContentHandler(){
		wk = new WikiversityReader();
		QuizContentHandler quizContentHandler = new WikiversityQuizContentHandler();
		wk.setQuizContentHandler(quizContentHandler);
		assertEquals(quizContentHandler,wk.getQuizContentHandler());
	}

	@Test
	public void testParse() throws WikiversityReaderException, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		//StringReader reader1 = new StringReader(reader);
		wk = new WikiversityReader();
		wk.setQuizContentHandler(new WikiversityQuizContentHandler());
		wk.parse(reader);	    
		/* Valeur attendue : -1 */
		controlCharAccumulator = (Integer) getPrivateWKReader("controlCharAccumulator");
		/* Valeur attendue : -1 */
		nextChar = (Integer) getPrivateWKReader("nextChar");
		
		/* Valeur attendue : false */
		questionHasStarted = (Boolean) getPrivateWKReader("questionHasStarted");
		
		/* Valeur attendue : true */
	    questionHasEnded = (Boolean) getPrivateWKReader("questionHasEnded");
	    
	    /* Valeur attendue : false */
	   textHasStarted = (Boolean) getPrivateWKReader("textHasStarted"); 
	    /* Valeur attendue : false */
	   hasSpecifiedType = (Boolean) getPrivateWKReader("hasSpecifiedType"); 
	    
	    /* Valeur attendue : false */
	   answersCanStart = (Boolean) getPrivateWKReader("answersCanStart");  
	    
	    /* Valeur attendue : false */	    
	   answerFragmentHasStarted = (Boolean) getPrivateWKReader("answerFragmentHasStarted"); 
	    
	    /* Valeur attendue : true */
	   answerFragmentHasEnded = (Boolean) getPrivateWKReader("answerFragmentHasEnded"); 
	    
	    /* Valeur attendue : false */
	   answerHasStarted = (Boolean) getPrivateWKReader("answerHasStarted"); 
	    
	    /* Valeur attendue : false */
	   answerFeedbackHasStarted = (Boolean) getPrivateWKReader("answerFeedbackHasStarted"); 
	    
	    /* TESTS */
	    assertEquals(controlCharAccumulatorResponse, controlCharAccumulator);
	    assertEquals(nextChar, nextCharResponse);
	    assertEquals(questionHasStarted, questionHasStartedResponse);
	    assertEquals(questionHasEnded, questionHasEndedResponse);
	    assertEquals(textHasStarted, textHasStartedResponse);
	    assertEquals(hasSpecifiedType, hasSpecifiedTypeResponse);
	    assertEquals(answersCanStart, answersCanStartResponse);
	    assertEquals(answerFragmentHasStarted, answerFragmentHasStartedResponse);
	    assertEquals(answerFragmentHasEnded, answerFragmentHasEndedResponse);
	    assertEquals(answerHasStarted, answerHasStartedResponse);
	    assertEquals(answerFeedbackHasStarted, answerFeedbackHasStartedResponse);
	    assertEquals(answerCreditHasStarted, answerCreditHasStartedResponse);
	    
	}

	public Object getPrivateWKReader(String field) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field f = WikiversityReader.class.getDeclaredField(field);
		f.setAccessible(true);
		return(f.get(wk));
	}
	
	public void setPrivateWKReader(String field, Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field f = WikiversityReader.class.getDeclaredField(field);
		f.setAccessible(true);
		f.set(wk, value);
	}
	
	public Object genericInvokMethod(Object obj, String methodName) {
        Method method;
        Object requiredObj = null;
        try {
            method = obj.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            requiredObj = method.invoke(obj);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return requiredObj;
    }
}
