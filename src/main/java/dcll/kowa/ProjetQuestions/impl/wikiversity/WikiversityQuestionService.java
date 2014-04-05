package dcll.kowa.ProjetQuestions.impl.wikiversity;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import dcll.kowa.ProjetQuestions.Answer;
import dcll.kowa.ProjetQuestions.Question;
import dcll.kowa.ProjetQuestions.Quiz;
import dcll.kowa.ProjetQuestions.UserResponse;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswer;
import dcll.kowa.ProjetQuestions.impl.DefaultAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultUserAnswerBlock;
import dcll.kowa.ProjetQuestions.impl.DefaultUserResponse;

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

    /**
     * Create a user response for a question. The user response is specified by a text
     * representation of the response.
     *
     * @param userId              the user identifier
     * @param question            the question
     * @param answerBlockTextList the list of answer text block  of the response (each answer is represented by its identifier)
     * @return the created user response
     * @throws WikiversityUserResponseAnswerBlockListSizeIsNotValidInResponse Exception
     * @throws WikiversityUserResponseAnswerNotFoundInChoiceList Exception
     */
    public final UserResponse createUserResponseForQuestionAndAnswerBlockList(String userId, Question question, List<List<String>> answerBlockTextList) throws WikiversityUserResponseAnswerBlockListSizeIsNotValidInResponse, WikiversityUserResponseAnswerNotFoundInChoiceList {
        if (question.getAnswerBlockList().size() != answerBlockTextList.size()) {
            throw new WikiversityUserResponseAnswerBlockListSizeIsNotValidInResponse("");
        }
        DefaultUserResponse userResponse = new DefaultUserResponse();
        userResponse.setUserIdentifier(userId);
        userResponse.setQuestion(question);
        for (int i = 0; i < question.getAnswerBlockList().size(); i++) {
            DefaultAnswerBlock currentAnsBlock = (DefaultAnswerBlock) question.getAnswerBlockList().get(i);
            DefaultUserAnswerBlock currentUserAnsBlock = new DefaultUserAnswerBlock();
            userResponse.getUserAnswerBlockList().add(currentUserAnsBlock);
            boolean answerHasBeenFound = false;
            if (answerBlockTextList.get(i).isEmpty()) {
                currentUserAnsBlock.getAnswerList().add(getNoResponseAnswer());
                answerHasBeenFound = true;
            } else {
                for (String userAnsString : answerBlockTextList.get(i)) {
                    for (Answer answer : currentAnsBlock.getAnswerList()) {
                        if (answer.getIdentifier().equals(userAnsString)) {
                            currentUserAnsBlock.getAnswerList().add(answer);
                            answerHasBeenFound = true;
                            break;
                        }
                    }
                }
            }
            if (!answerHasBeenFound) {
                throw new WikiversityUserResponseAnswerNotFoundInChoiceList("");
            }
        }

        return userResponse;
    }

    private DefaultAnswer noResponseAnswer;

    /**
     * @return the no response answer
     */
    public final DefaultAnswer getNoResponseAnswer() {
        if (noResponseAnswer == null) {
            noResponseAnswer = new DefaultAnswer();
            noResponseAnswer.setPercentCredit(0f);
            noResponseAnswer.setIdentifier(NO_RESPONSE);
            noResponseAnswer.setTextValue(NO_RESPONSE);
        }
        return noResponseAnswer;
    }
}
