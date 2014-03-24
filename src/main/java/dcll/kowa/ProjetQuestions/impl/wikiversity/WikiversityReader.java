package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import dcll.kowa.ProjetQuestions.QuizContentHandler;
import dcll.kowa.ProjetQuestions.QuizReader;
import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderException;
import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderNotEscapedCharacterException;
import dcll.kowa.ProjetQuestions.impl.gift.GiftReaderQuestionWithInvalidFormatException;

public class WikiversityReader implements QuizReader{
	private static Logger logger = Logger.getLogger(WikiversityReader.class);
	

    private QuizContentHandler quizContentHandler; //
    private StringBuffer accumulator; //
    private int controlCharAccumulator = -1; //
    //private boolean readingType; //
    
    private int nextChar;
    private boolean questionHasStarted; //
    private boolean questionHasEnded; //
    
    private boolean textHasStarted; ////
    private boolean textHasEnded; ////
    private boolean hasSpecifiedType = false;
    private boolean answersCanStart; ////
    
    private boolean answerFragmentHasStarted;//
    private boolean answerFragmentHasEnded;//
    private boolean answerHasStarted;//
    private boolean answerFeedbackHasStarted;
    private boolean answerCreditHasStarted;
    private boolean answerCreditHasEnded;
    
    public void parse(Reader reader) throws WikiversityReaderException, WikiversityReaderNotEscapedCharacterException, IOException {
        int currentChar;
        quizContentHandler.onStartQuiz();
        
        nextChar = reader.read();
        currentChar = nextChar;
        while (currentChar != -1) {
        	nextChar = reader.read();
            checkQuestionHasStarted();
            if (currentChar == '{') {
                processLeftBracketCharacter();
            } else if (currentChar == '}') {
                processRightBracketCharacter();
            } else if (currentChar == '+') {
                processPlusCharacter();
            } else if (currentChar == '-') {
                processMinusCharacter();
            } else if (currentChar == '|') {
                processBarMonospaceCharacter();
            } else if (currentChar == '\n'){
            	processNewLineCharacter();
            } else{
                processAnyCharacter(currentChar);
            }
            logger.debug("Current char  => " + (char) currentChar);
            if (accumulator != null) {
                logger.debug("Accumulator => " + accumulator.toString());
            }
            logger.debug("control caracter accumulator => " + (char) controlCharAccumulator);
            logger.debug("------------------------------------------------------------------");
            currentChar = nextChar;
        }
        endQuiz();
        quizContentHandler.onEndQuiz();

    }

    private void processNewLineCharacter() {
		// TODO Auto-generated method stub
		if (textHasStarted && !hasSpecifiedType)
		{
			flushAccumulator();
		}
	}

	private void checkQuestionHasStarted() {
        if (!questionHasStarted) {
            questionHasStarted = true;
            quizContentHandler.onStartQuestion();
        }
    }

    private void endQuiz() throws WikiversityReaderQuestionWithInvalidFormatException {
        if (!questionHasEnded && !answerFragmentHasEnded) {
            throw new WikiversityReaderQuestionWithInvalidFormatException(null);
        }
        if (!questionHasEnded) {
            flushAccumulator();
            questionHasEnded = true;
            quizContentHandler.onEndQuestion();
        }

    }

    private void processLeftBracketCharacter() throws WikiversityReaderNotEscapedCharacterException {
        
        if (textHasStarted) {
            throw new WikiversityReaderNotEscapedCharacterException(null);
        }
        flushAccumulator();
        textHasStarted = true;
        textHasEnded = false;
        //quizContentHandler.onStartAnswerBlock();

    }

    private void processRightBracketCharacter() throws WikiversityReaderNotEscapedCharacterException, WikiversityReaderWrongTypeDefinition {
        
        if (!textHasStarted) {
            throw  new WikiversityReaderNotEscapedCharacterException(null);
        }
        logger.debug("Result: "+accumulator.toString()+" | "+"type=\"()\""+accumulator.toString().endsWith("type=\"()\""));
        if(hasSpecifiedType && !((accumulator.toString().endsWith("type=\"()\"") || (accumulator.toString().endsWith("type=\"[]\"")))))
        {
        	throw new WikiversityReaderWrongTypeDefinition();
        }
        flushAccumulator();
        textHasStarted = false;
        textHasEnded = true;
        answersCanStart = true; // les réponses peuvent commencer après ça
    }

    private void processPlusCharacter() throws WikiversityReaderNotEscapedCharacterException {
        processAnswerPrefix('+');
    }

    private void processMinusCharacter() throws WikiversityReaderNotEscapedCharacterException {
        processAnswerPrefix('-');
    }

    private void processAnswerPrefix(char prefix) throws WikiversityReaderNotEscapedCharacterException {
        
    	//Si le text n'a pas fini on ne peux pas donner de reponses
        if (!answersCanStart) {
            throw new WikiversityReaderNotEscapedCharacterException(null);
        }
        if(!answerFragmentHasStarted)
        {
        	answerFragmentHasStarted = true;
        	getQuizContentHandler().onStartAnswerBlock();
        }
        flushAccumulator();
        if (answerFeedbackHasStarted) {
            answerFeedbackHasStarted = false;
            getQuizContentHandler().onEndAnswerFeedBack();
        }
        if (answerHasStarted) { // the '=' or '~' char marks the end of the current answer
            getQuizContentHandler().onEndAnswer();
        } else {
            answerHasStarted = true;
        }
        answerCreditHasStarted = false;
        answerCreditHasEnded = false;
        getQuizContentHandler().onStartAnswer(String.valueOf(prefix)); // it marks the beginning of a new one too
    }

    /**
     * A refaire pour gerer la lecture du type et des coefficients
     * @throws GiftReaderNotEscapedCharacterException
     */
    private void processBarMonospaceCharacter() throws WikiversityReaderNotEscapedCharacterException {
    	
    	if ((!answerHasStarted && !textHasStarted) || answerFeedbackHasStarted) {
            throw new WikiversityReaderNotEscapedCharacterException(null);
        }
    	
    	if(textHasStarted)
    	{
    		flushAccumulator();
    		hasSpecifiedType = true;
    	}
                
        if (controlCharAccumulator == '|')
        {
        	flushAccumulator();
            answerFeedbackHasStarted = true;
            getQuizContentHandler().onStartAnswerFeedBack(); // it marks the beginning of a new one too
        }
        
        else
        {
        	controlCharAccumulator = '|';
        }
        
    }

    private void processAnyCharacter(int currentChar) throws WikiversityReaderNotEscapedCharacterException {
        if (accumulator == null) {
            accumulator = new StringBuffer();
        }
        accumulator.append((char) currentChar);
        if (answerHasStarted && (nextChar == '\n')) { // the '=' or '~' char marks the end of the current answer
        	flushAccumulator();
            getQuizContentHandler().onEndAnswer();
            getQuizContentHandler().onEndAnswerBlock();
            answerFragmentHasEnded = true;
            answerFragmentHasStarted = false;
            hasSpecifiedType = false;
        }
            
        if (controlCharAccumulator != -1) { // if a control caracter is present,
            if (controlCharAccumulator != '|') {  // it must be a \
                throw new WikiversityReaderNotEscapedCharacterException(null);
            }
            controlCharAccumulator = -1;
        }
    }

    private void flushAccumulator() {
        if (accumulator != null) {
            quizContentHandler.onString(accumulator.toString());
            accumulator = null;
        }
    }

    public QuizContentHandler getQuizContentHandler() {
        return quizContentHandler;
    }

    public void setQuizContentHandler(QuizContentHandler quizContentHandler) {
        this.quizContentHandler = quizContentHandler;
    }

}
