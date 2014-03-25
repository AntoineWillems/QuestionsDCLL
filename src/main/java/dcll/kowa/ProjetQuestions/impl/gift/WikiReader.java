package dcll.kowa.ProjetQuestions.impl.gift;

import org.apache.log4j.Logger;
import dcll.kowa.ProjetQuestions.QuizContentHandler;
import dcll.kowa.ProjetQuestions.QuizReader;

import java.io.IOException;
import java.io.Reader;

/**
 * @author franck Silvestre
 */
public class WikiReader implements QuizReader {

    private static Logger logger = Logger.getLogger(GiftReader.class);

    public void parse(Reader reader) throws IOException, GiftReaderException {
        int currentChar;
        quizContentHandler.onStartQuiz();
        while ((currentChar = reader.read()) != -1) {
            checkQuestionHasStarted();
            if (currentChar == '{') {
            	processAccoladeGCaractere();
            } else if (currentChar == '}') {
            	processAccoladeDCaractere();
            } else if (currentChar == '|') {
            	processPipeCaractere();
            } else if (currentChar == '+') {
            	processPlusCharacter();
            } else if (currentChar == '-') {
            	processMoinsCharacter();
            } else if (currentChar == '(') {
            	processParentheseGCaractere();
            } else if (currentChar == ')') {
            	//processParentheseDCaractere();
            	processAnyCharacter(currentChar);
            } else if (currentChar == '[') {
            	processCrochetGCaractere();
            } else if (currentChar == ']') {
            	//processCrochetDCaractere();
            	processAnyCharacter(currentChar);
            } else {
                processAnyCharacter(currentChar);
            }
            logger.debug("Current char  | " + (char) currentChar);
            if (accumulator != null) {
                logger.debug("Accumulator | " + accumulator.toString());
            }
            logger.debug("control caracter accumulator | " + (char) controlCharAccumulator);
        }
        /*
         * A revoir
         */
        flushAccumulator();
        quizContentHandler.onEndAnswer();
        quizContentHandler.onEndAnswerBlock();
        endQuiz();
        quizContentHandler.onEndQuiz();

    }

    private void checkQuestionHasStarted() {
        if (!questionHasStarted) {
            questionHasStarted = true;
            quizContentHandler.onStartQuestion();
        }
    }

    private void endQuiz() throws GiftReaderQuestionWithInvalidFormatException {
        if (!questionHasEnded && !answerFragmentHasEnded) {
            //throw new GiftReaderQuestionWithInvalidFormatException();
        }
        if (!questionHasEnded) {
            flushAccumulator();
            questionHasEnded = true;
            quizContentHandler.onEndQuestion();
        }

    }
    private void processAccoladeGCaractere() throws GiftReaderNotEscapedCharacterException{
    	if (answerMode) {
            processAnyCharacter('{');
            return;
        }
    	questionMode = true;
    }
    
    private void processAccoladeDCaractere() throws GiftReaderNotEscapedCharacterException{
    	if (answerMode) {
            processAnyCharacter('}');
            return;
        }
    	questionMode = false;
    	answerMode = true;
    	accumulator=null;
    	answerFragmentHasStarted = true;
    	answerFragmentHasEnded = false;
    	quizContentHandler.onStartAnswerBlock();
    }
    
    private void processPipeCaractere(){
    	if (questionMode) {
            /*
             * Alors feedback
             */
            return;
        }
    	quizContentHandler.setEndQuestion(true);
    	flushAccumulator();
    }
    
    private void processPlusCharacter() throws GiftReaderException {
    	if (questionMode) {
            processAnyCharacter('+');
            return;
        }
        processAnswerPrefix('+');
    }

    private void processMoinsCharacter() throws GiftReaderException {
    	if (questionMode) {
            processAnyCharacter('-');
            return;
        }
        processAnswerPrefix('-');
    }
    
    private void processParentheseGCaractere() throws GiftReaderNotEscapedCharacterException{
    	if (answerMode || questionMode) {
            processAnyCharacter('(');
            return;
        }
    	quizContentHandler.chooseTypeQuestion("(");
    }
    
    private void processParentheseDCaractere(){
    	
    }
    
    private void processCrochetGCaractere() throws GiftReaderNotEscapedCharacterException{
    	if (answerMode || questionMode) {
            processAnyCharacter('[');
            return;
        }
    	quizContentHandler.chooseTypeQuestion("[");
    }
    
    private void processCrochetDCaractere(){
    	
    }

    private void processAnswerPrefix(char prefix) throws GiftReaderNotEscapedCharacterException {
        if (escapeMode) {
            processAnyCharacter(prefix);
            return;
        }
        if (!answerFragmentHasStarted) {
            throw new GiftReaderNotEscapedCharacterException();
        }
        flushAccumulator();
        if (answerHasStarted) { // the '+' or '-' char marks the end of the current answer
            getQuizContentHandler().onEndAnswer();
        } else {
            answerHasStarted = true;
        }
        getQuizContentHandler().onStartAnswer(String.valueOf(prefix)); // it marks the beginning of a new one too
    }

    

    private void processAnyCharacter(int currentChar) throws GiftReaderNotEscapedCharacterException {
        if (accumulator == null) {
            accumulator = new StringBuffer();
        }
        accumulator.append((char) currentChar);
        if (controlCharAccumulator != -1) { // if a control caracter is present,
            if (controlCharAccumulator != '\\') {  // it must be a \
                throw new GiftReaderNotEscapedCharacterException();
            }
            controlCharAccumulator = -1;
        }
        escapeMode = false;
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

    private QuizContentHandler quizContentHandler;
    private StringBuffer accumulator;
    private int controlCharAccumulator = -1;
    private boolean escapeMode;
    private boolean answerMode;
    private boolean questionMode;
    
    private boolean questionHasStarted;
    private boolean questionHasEnded;
    private boolean answerFragmentHasStarted;
    private boolean answerFragmentHasEnded;
    private boolean answerHasStarted;


}
