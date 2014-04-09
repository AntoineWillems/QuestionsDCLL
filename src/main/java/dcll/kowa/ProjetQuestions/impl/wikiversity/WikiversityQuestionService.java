package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;
import java.io.StringReader;
import dcll.kowa.ProjetQuestions.Question;
import dcll.kowa.ProjetQuestions.Quiz;

/**
 * WikiversityQuestionService
 * @author thegame
 *
 */
public class WikiversityQuestionService {
	public static final String NO_RESPONSE = "_NO_RESPONSE_";

    /**
     * Get question from its gift text specification
     *
     * @param giftText the gift text
     * @return the result question
     * @throws WikiversityReaderException WikiversityReaderException
     * @throws IOException IOException
     */
    public final Question getQuestionFromGiftText(String giftText) throws IOException, WikiversityReaderException {
        Quiz quiz = getQuizFromGiftText(giftText);
        return quiz.getQuestionList().get(0);
    }

    /**
     * Get quiz from its gift text specification
     *
     * @param giftText the gift text
     * @return the result quiz
     * @throws WikiversityReaderException  WikiversityReaderException
     * @throws IOException IOException
     */
    public final Quiz getQuizFromGiftText(String giftText) throws IOException, WikiversityReaderException {
        WikiversityQuizContentHandler handler = new WikiversityQuizContentHandler();
        WikiversityReader quizReader = new WikiversityReader();
        quizReader.setQuizContentHandler(handler);
        StringReader reader = new StringReader(giftText);
        quizReader.parse(reader);
        return handler.getQuiz();
    }


}
