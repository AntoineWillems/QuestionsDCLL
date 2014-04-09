package dcll.kowa.ProjetQuestions.impl.wikiversity;

import org.apache.log4j.Logger;

import dcll.kowa.ProjetQuestions.Question;
import dcll.kowa.ProjetQuestions.QuestionType;
import dcll.kowa.ProjetQuestions.QuizContentHandler;
import dcll.kowa.ProjetQuestions.TextBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultQuestion;
import dcll.kowa.ProjetQuestions.impl.DefaultQuiz;

/**
 * WikiversityQuizContentHandler
 * @author thegame
 *
 */
public class WikiversityQuizContentHandler implements QuizContentHandler {

	private static final float CORRECT_ANSWER_CREDIT = 100f;
	private static final float BAD_ANSWER_CREDIT = 0f;
	private static final float ERROR_ANSWER_CREDIT = -1f;
	/**
     * Get the quiz
     *
     * @return the quiz
     */
    private static Logger logger = Logger.getLogger(WikiversityQuizContentHandler.class);

    private DefaultQuiz quiz;
    private DefaultQuestion currentQuestion;
    private DefaultAnswerBlock currentAnswerBlock;
    private DefaultAnswer currentAnswer;
    private boolean answerCreditIsBeenBuilt;
    private boolean feedbackIsBeenBuilt;
    private int answerCounter;
    
    /**
     * Retourne un quiz par Default
     * @return un qui par défaut
     */
    public final DefaultQuiz getQuiz() {
        return quiz;
    }


    /**
     * Receive notification of the beginning of a quiz
     */
    public final void onStartQuiz() {
        quiz = new DefaultQuiz();
    }

    /**
     * Receive notification of the end of a quiz
     */
    public final void onEndQuiz() {
    }

    /**
     * Receive notification of the beginning of a question
     */
    public final void onStartQuestion() {
        currentQuestion = new DefaultQuestion();
        currentQuestion.setQuestionType(QuestionType.MultipleChoice);
    }

    /**
     * Receive notification of the end of a question
     */
    public final void onEndQuestion() {
        postProcess(currentQuestion);
        quiz.addQuestion(currentQuestion);
        currentQuestion = null;
    }



    /**
     * Receive notification of the beginning of an answer fragment
     */
    public final void onStartAnswerBlock() {
        currentAnswerBlock = new DefaultAnswerBlock();
        answerCounter = 0;
    }

    /**
     * Receive notification of the end of an answer fragment
     */
    public final void onEndAnswerBlock() {
        currentQuestion.addAnswerBlock(currentAnswerBlock);
        currentAnswerBlock = null;
    }

    /**
     * Receive notification of the beginning of an answer
     * @param prefix + ou -
     */
    public final void onStartAnswer(String prefix) {
        currentAnswer = new DefaultAnswer();
        currentAnswer.setIdentifier(String.valueOf(answerCounter++));
        if ("+".equals(prefix)) {
            currentAnswer.setPercentCredit(CORRECT_ANSWER_CREDIT);
        } else if ("-".equals(prefix)) {
            currentAnswer.setPercentCredit(BAD_ANSWER_CREDIT);
        } else {
        	currentAnswer.setPercentCredit(ERROR_ANSWER_CREDIT);
        }
    }

    /**
     * Receive notification of the end of an answer
     */
    public final void onEndAnswer() {
        currentAnswerBlock.addAnswer(currentAnswer);
        logger.debug("Inserting Answer => " + currentAnswer.getTextValue());
        currentAnswer = null;
    }

    /**
     * Notification of the beginning of a credit specification
     */
    public final void onStartAnswerCredit() {
        answerCreditIsBeenBuilt = true;
    }

    /**
     * Notification of the end of a credit specification
     */
    public final void onEndAnswerCredit() {
        answerCreditIsBeenBuilt = false;
    }

    /**
     * Receive notification of the beginning feedback
     */
    public final void onStartAnswerFeedBack() {
        feedbackIsBeenBuilt = true;
    }

    /**
     * Receive notification of the end of a feedback
     */
    public final void onEndAnswerFeedBack() {
        feedbackIsBeenBuilt = false;
    }

    /**
     * Receive notification of a new string
     *
     * @param str the received string
     */
    public final void onString(final String str) {
        String trimedStr = str.trim();
        if (answerCreditIsBeenBuilt) {
            currentAnswer.setPercentCredit(new Float(trimedStr));
        } else if (feedbackIsBeenBuilt) {
            currentAnswer.setFeedback(trimedStr);
        } else if (currentAnswer != null) {
            currentAnswer.setTextValue(trimedStr);
        } else if (currentQuestion != null && currentAnswerBlock == null) {
        	
        	if (str.endsWith("type=\"()\"")) {
        		currentQuestion.setQuestionType(QuestionType.ExclusiveChoice);
        	} else {
	            logger.debug("Text fragment => " + str);
	            currentQuestion.addTextBlock(new TextBlock() {
	                public String getText() {
	                    return str;
	                }
	            });
        	}
        }
    }

    /**
     * log qui affiche: Post processing of the current question
     * @param question Current question
     */
    private final void postProcess(Question question) {
       logger.debug("Post processing of the current question");
    }


	
}
