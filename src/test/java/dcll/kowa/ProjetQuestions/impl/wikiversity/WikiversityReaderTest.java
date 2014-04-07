package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import dcll.kowa.ProjetQuestions.QuizContentHandler;
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
//	@Test
//	public void testCheckQuestionHasStarted() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		wk = new WikiversityReader();
//		setPrivateWKReader("questionHasStarted", false);
//		genericInvokMethod(wk, "checkQuestionHasStarted");
//		boolean questionHasStartedBis = (Boolean) getPrivateWKReader("questionHasStarted");
//        assertEquals(true, questionHasStartedBis);
//    }
//
//	@Test
//    public void testEndQuiz() throws WikiversityReaderQuestionWithInvalidFormatException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		wk = new WikiversityReader();
//		setPrivateWKReader("questionHasEnded", false);
//    	setPrivateWKReader("answerFragmentHasEnded", true);
//    	setPrivateWKReader("questionHasEnded", true);
//		genericInvokMethod(wk, "endQuiz");
//		boolean questionHasEndedBis = (Boolean) getPrivateWKReader("questionHasEnded");
//        assertEquals(true, questionHasEndedBis);
//
//    }
//	
//	@Test
//    private void testProcessLeftBracketCharacter() throws WikiversityReaderNotEscapedCharacterException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		wk = new WikiversityReader();
//		setPrivateWKReader("textHasStarted", false);
//		boolean textHasStarted = (Boolean) getPrivateWKReader("textHasStarted");//true
//		boolean textHasEnded = (Boolean) getPrivateWKReader("textHasEnded");//false
//		genericInvokMethod(wk, "ProcessLeftBracketCharacter");
//		assertEquals(true, textHasStarted);
//		assertEquals(false, textHasEnded);
//    }
//
//    private void processRightBracketCharacter() throws WikiversityReaderNotEscapedCharacterException, WikiversityReaderWrongTypeDefinition {
//        
//        if (!textHasStarted) {
//            throw  new WikiversityReaderNotEscapedCharacterException(null);
//        }
//        logger.debug("Result: "+accumulator.toString()+" | "+"type=\"()\""+accumulator.toString().endsWith("type=\"()\""));
//        if(hasSpecifiedType && !((accumulator.toString().endsWith("type=\"()\"") || (accumulator.toString().endsWith("type=\"[]\"")))))
//        {
//        	throw new WikiversityReaderWrongTypeDefinition();
//        }
//        flushAccumulator();
//        textHasStarted = false;
//        textHasEnded = true;
//        answersCanStart = true; // les réponses peuvent commencer après ça
//    }
//
//    private void processPlusCharacter() throws WikiversityReaderNotEscapedCharacterException {
//        processAnswerPrefix('+');
//    }
//
//    private void processMinusCharacter() throws WikiversityReaderNotEscapedCharacterException {
//        processAnswerPrefix('-');
//    }
//
//    private void processAnswerPrefix(char prefix) throws WikiversityReaderNotEscapedCharacterException {
//        
//    	//Si le text n'a pas fini on ne peux pas donner de reponses
//        if (!answersCanStart) {
//            throw new WikiversityReaderNotEscapedCharacterException(null);
//        }
//        if(!answerFragmentHasStarted)
//        {
//        	answerFragmentHasStarted = true;
//        	getQuizContentHandler().onStartAnswerBlock();
//        }
//        flushAccumulator();
//        if (answerFeedbackHasStarted) {
//            answerFeedbackHasStarted = false;
//            getQuizContentHandler().onEndAnswerFeedBack();
//        }
//        if (answerHasStarted) { // the '=' or '~' char marks the end of the current answer
//            getQuizContentHandler().onEndAnswer();
//        } else {
//            answerHasStarted = true;
//        }
//        answerCreditHasStarted = false;
//        answerCreditHasEnded = false;
//        getQuizContentHandler().onStartAnswer(String.valueOf(prefix)); // it marks the beginning of a new one too
//    }
//
//    private void processBarMonospaceCharacter() throws WikiversityReaderNotEscapedCharacterException {
//    	
//    	if ((!answerHasStarted && !textHasStarted) || answerFeedbackHasStarted) {
//            throw new WikiversityReaderNotEscapedCharacterException(null);
//        }
//    	
//    	if(textHasStarted)
//    	{
//    		flushAccumulator();
//    		hasSpecifiedType = true;
//    	}
//                
//        if (controlCharAccumulator == '|')
//        {
//        	flushAccumulator();
//            answerFeedbackHasStarted = true;
//            getQuizContentHandler().onStartAnswerFeedBack(); // it marks the beginning of a new one too
//        }
//        
//        else
//        {
//        	controlCharAccumulator = '|';
//        }
//        
//    }
//
//    private void processAnyCharacter(int currentChar) throws WikiversityReaderNotEscapedCharacterException {
//        if (accumulator == null) {
//            accumulator = new StringBuffer();
//        }
//        accumulator.append((char) currentChar);
//        if (answerHasStarted && (nextChar == -1)) { //Vérifier si c'est la dernière réponse
//        	flushAccumulator();
//            getQuizContentHandler().onEndAnswer();
//            getQuizContentHandler().onEndAnswerBlock();
//            answerFragmentHasEnded = true;
//            answerFragmentHasStarted = false;
//            questionHasStarted = false;
//            questionHasEnded = true;
//            controlCharAccumulator = -1;
//            hasSpecifiedType = false;
//            answersCanStart = false;
//        }
//            
//        if (controlCharAccumulator != -1) { // if a control caracter is present,
//            if (controlCharAccumulator != '|') {  // it must be a \
//                throw new WikiversityReaderNotEscapedCharacterException(null);
//            }
//            controlCharAccumulator = -1;
//        }
//    }
//
//    private void flushAccumulator() {
//        if (accumulator != null) {
//            quizContentHandler.onString(accumulator.toString());
//            accumulator = null;
//        }
//    }
//
//    public QuizContentHandler getQuizContentHandler() {
//        return quizContentHandler;
//    }
//
//    public void setQuizContentHandler(QuizContentHandler quizContentHandler) {
//        this.quizContentHandler = quizContentHandler;
//    }

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
