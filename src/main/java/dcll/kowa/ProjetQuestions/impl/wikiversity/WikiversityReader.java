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
    private boolean hasSpecifiedType = false;
    private boolean answersCanStart; ////
    
    private boolean answerFragmentHasStarted;//
    private boolean answerFragmentHasEnded;//
    private boolean answerHasStarted;//
    private boolean answerFeedbackHasStarted;
    
    public void parse(Reader reader) throws IOException, WikiversityReaderException {
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


	private void checkQuestionHasStarted() {
        if (!questionHasStarted) {
            questionHasStarted = true;
            quizContentHandler.onStartQuestion();
        }
    }

    private void endQuiz() throws WikiversityReaderQuestionWithInvalidFormatException {
    	if (!answerFragmentHasEnded){
            throw new WikiversityReaderQuestionWithInvalidFormatException(null);
        }
    	if (!questionHasEnded) {
            throw new WikiversityReaderQuestionWithInvalidFormatException(null);
        }
        
        if (!questionHasEnded) {
            flushAccumulator();
            questionHasEnded = true;
            quizContentHandler.onEndQuestion();
        }

    }

    private void processLeftBracketCharacter() throws WikiversityReaderIllegalBracketCharInQuestionTextException {
        
        if (textHasStarted) {
            throw new WikiversityReaderIllegalBracketCharInQuestionTextException("'{' ne peut être inséré dans le texte d'une question");
        }
        flushAccumulator();
        textHasStarted = true;

    }

    private void processRightBracketCharacter() throws WikiversityReaderIllegalBracketCharBeforeQuestionStartsException, WikiversityReaderWrongTypeDefinition {
        
        if (!textHasStarted) {
            throw  new WikiversityReaderIllegalBracketCharBeforeQuestionStartsException("} ne peut être inséré que pour indiquer la fin du texte de la question");
        }
        logger.debug("Result: "+accumulator.toString()+" | "+"type=\"()\""+accumulator.toString().endsWith("type=\"()\""));
        if(hasSpecifiedType && !((accumulator.toString().endsWith("type=\"()\"") || (accumulator.toString().endsWith("type=\"[]\"")))))
        {
        	throw new WikiversityReaderWrongTypeDefinition();
        }
        flushAccumulator();
        textHasStarted = false;
        answersCanStart = true; // les réponses peuvent commencer après ça
    }

    private void processPlusCharacter() throws WikiversityReaderCannotStartAnswersException {
        processAnswerPrefix('+');
    }

    private void processMinusCharacter() throws WikiversityReaderCannotStartAnswersException {
        processAnswerPrefix('-');
    }

    private void processAnswerPrefix(char prefix) throws WikiversityReaderCannotStartAnswersException {
        
    	//Si le text n'a pas fini on ne peux pas donner de reponses
        if (!answersCanStart) {
            throw new WikiversityReaderCannotStartAnswersException("Il faut d'abord décrire la question entre { et } avant d'entrer les réponses");
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
        getQuizContentHandler().onStartAnswer(String.valueOf(prefix)); // it marks the beginning of a new one too
    }

    private void processBarMonospaceCharacter() throws WikiversityReaderIllegalVerticalBarPositionException {
    	
    	if ((!answerHasStarted && !textHasStarted) || answerFeedbackHasStarted) {
            throw new WikiversityReaderIllegalVerticalBarPositionException("'|' ne peut être inséré qu'après une réponse sous cette séquence: || pour indiquer un feedback");
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

    private void processAnyCharacter(int currentChar){
        if (accumulator == null) {
            accumulator = new StringBuffer();
        }
        accumulator.append((char) currentChar);
        if (answerHasStarted && (nextChar == -1)) { //Vérifier si c'est la dernière réponse
        	flushAccumulator();
            getQuizContentHandler().onEndAnswer();
            getQuizContentHandler().onEndAnswerBlock();
            answerFragmentHasEnded = true;
            answerFragmentHasStarted = false;
            questionHasStarted = false;
            questionHasEnded = true;
            controlCharAccumulator = -1;
            hasSpecifiedType = false;
            answersCanStart = false;
            answerHasStarted = false;
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
