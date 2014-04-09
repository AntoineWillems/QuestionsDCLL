package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;

import dcll.kowa.ProjetQuestions.QuizContentHandler;
import dcll.kowa.ProjetQuestions.QuizReader;

/**
 * WikiversityReader
 * @author thegame
 *
 */
public class WikiversityReader implements QuizReader {
	private static Logger logger = Logger.getLogger(WikiversityReader.class);
	

    private QuizContentHandler quizContentHandler;
    private StringBuffer accumulator;
    private int controlCharAccumulator = -1;    
    private int nextChar;
    private boolean questionHasStarted;
    private boolean questionHasEnded;
    private boolean textHasStarted;
    private boolean hasSpecifiedType = false;
    private boolean answersCanStart;
    private boolean answerFragmentHasStarted;
    private boolean answerFragmentHasEnded;
    private boolean answerHasStarted;
    private boolean answerFeedbackHasStarted;
    
    /**
     * Lance le parsing d'un text pour en extraire le graphe d'objets
     * @param reader la chaine de caractères à parser
     * @throws IOException IOException
     * @throws WikiversityReaderException WikiversityReaderException
     */
    public final void parse(Reader reader) throws IOException, WikiversityReaderException {
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
            } else {
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

    /**
     * Marque le commencement d'une question
     */
	private void checkQuestionHasStarted() {
        if (!questionHasStarted) {
            questionHasStarted = true;
            quizContentHandler.onStartQuestion();
        }
    }
	
	/**
	 * Termine la question
	 * @throws WikiversityReaderQuestionWithInvalidFormatException WikiversityException
	 */
    private void endQuiz() throws WikiversityReaderQuestionWithInvalidFormatException {
    	if (!answerFragmentHasEnded) {
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
    
    /**
     * Traiter le caractère '{' qui représente le début du texte de la question
     * @throws WikiversityReaderIllegalBracketCharInQuestionTextException WikiversityReaderException
     */
    private void processLeftBracketCharacter() throws WikiversityReaderIllegalBracketCharInQuestionTextException {
        
        if (textHasStarted) {
            throw new WikiversityReaderIllegalBracketCharInQuestionTextException("'{' ne peut être inséré dans le texte d'une question");
        }
        flushAccumulator();
        textHasStarted = true;

    }
    
    /**
     * Traiter le caractère '}' qui représente la fin du texte de la question
     * @throws WikiversityReaderIllegalBracketCharBeforeQuestionStartsException WikiversityReaderException
     * @throws WikiversityReaderWrongTypeDefinition WikiversityReaderException
     */
    private void processRightBracketCharacter() throws WikiversityReaderIllegalBracketCharBeforeQuestionStartsException, WikiversityReaderWrongTypeDefinition {
        
        if (!textHasStarted) {
            throw  new WikiversityReaderIllegalBracketCharBeforeQuestionStartsException("} ne peut être inséré que pour indiquer la fin du texte de la question");
        }
        logger.debug("Result: " + accumulator.toString() + " | " + "type=\"()\"" + accumulator.toString().endsWith("type=\"()\""));
        if (hasSpecifiedType && !((accumulator.toString().endsWith("type=\"()\"") || (accumulator.toString().endsWith("type=\"[]\"")))))
        {
        	throw new WikiversityReaderWrongTypeDefinition();
        }
        flushAccumulator();
        textHasStarted = false;
        answersCanStart = true; // les réponses peuvent commencer après ça
    }

    /**
     * Traiter le caractère '+' qui représente le début d'une nouvelle réponse
     * @throws WikiversityReaderCannotStartAnswersException WikiversityException
     */
    private void processPlusCharacter() throws WikiversityReaderCannotStartAnswersException {
        processAnswerPrefix('+');
    }
    
    /**
     * Traiter le caractère '-' qui représente le début d'une nouvelle réponse
     * @throws WikiversityReaderCannotStartAnswersException WikiversityException
     */
    private void processMinusCharacter() throws WikiversityReaderCannotStartAnswersException {
        processAnswerPrefix('-');
    }

    /**
     * Traite le prefixe d'une reponse, lance le début du parsing d'une reponse
     * @param prefix + ou -
     * @throws WikiversityReaderCannotStartAnswersException WikiversityException
     */
    private void processAnswerPrefix(char prefix) throws WikiversityReaderCannotStartAnswersException {
        
    	//Si le text n'a pas fini on ne peux pas donner de reponses
        if (!answersCanStart) {
            throw new WikiversityReaderCannotStartAnswersException("Il faut d'abord décrire la question entre { et } avant d'entrer les réponses");
        }
        if (!answerFragmentHasStarted) {
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
    
    /**
     * Traite le caractère '|', principalement utilisé pour le feedBack d'une réponse.
     * @throws WikiversityReaderIllegalVerticalBarPositionException WikiversityException
     */
    private void processBarMonospaceCharacter() throws WikiversityReaderIllegalVerticalBarPositionException {
    	
    	if ((!answerHasStarted && !textHasStarted) || answerFeedbackHasStarted) {
            throw new WikiversityReaderIllegalVerticalBarPositionException("'|' ne peut être inséré qu'après une réponse sous cette séquence: || pour indiquer un feedback");
        }
    	if (textHasStarted) {
    		flushAccumulator();
    		hasSpecifiedType = true;
    	} else if (controlCharAccumulator == '|') {
        	flushAccumulator();
            answerFeedbackHasStarted = true;
            getQuizContentHandler().onStartAnswerFeedBack(); // it marks the beginning of a new one too
        } else { 
        	controlCharAccumulator = '|';
        }
    	
    }

    /**
     * Traite n'importe quel caractère, en le concaténant avec le buffer pour former les unités lexicales
     * @param currentChar Caractère à traiter
     */
    private void processAnyCharacter(int currentChar) {
        if (accumulator == null) {
            accumulator = new StringBuffer();
        }
        accumulator.append((char) currentChar);
        if ((answerHasStarted) && (nextChar == '|')) {
        	controlCharAccumulator = -1;
        } else if (answerHasStarted && (nextChar == -1)) { //Vérifier si c'est la dernière réponse
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

    /**
     * Envoi l'unité lexicale se trouvant dans le buffer vers le contentHandler qui se chargera de créer l'objet<br>
     * correspondant
     */
    private void flushAccumulator() {
        if (accumulator != null) {
            quizContentHandler.onString(accumulator.toString());
            accumulator = null;
        }
    }

    /**
     * Retourne le quizContentHandler
     * @return quizContentHandler
     */
    public final QuizContentHandler getQuizContentHandler() {
        return quizContentHandler;
    }

    /**
     * Modifie le quizContentHandler 
     * @param quizContentHandler nouveau quizContentHandler
     */
    public final void setQuizContentHandler(QuizContentHandler quizContentHandler) {
        this.quizContentHandler = quizContentHandler;
    }

}
