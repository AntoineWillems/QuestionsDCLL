/*
 * Copyright 2013 Tsaap Development Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dcll.kowa.ProjetQuestions.impl;

import dcll.kowa.ProjetQuestions.Question;
import dcll.kowa.ProjetQuestions.UserAnswerBlock;
import dcll.kowa.ProjetQuestions.UserResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franck Silvestre
 */
public class DefaultUserResponse implements UserResponse {

    private String userIdentifier;
    private Question question;
    private List<UserAnswerBlock> userAnswerBlockList = new ArrayList<UserAnswerBlock>();


    /**
     * Get the user identifier
     *
     * @return the user identifier
     */
    public  final String getUserIdentifier() {
        return userIdentifier;
    }

    /**
     * Get the question
     *
     * @return the question the response is for
     */
    public final Question getQuestion() {
        return question;
    }

    /**
     * Get the user answer block list
     *
     * @return the user answer block list
     */
    public final List<UserAnswerBlock> getUserAnswerBlockList() {
        return userAnswerBlockList;
    }


    /**
     * Set the user identifier
     * @param userIdentifier the user identifier
     */
    public final void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    /**
     * Set the question
     * @param question the question
     */
    public final void setQuestion(Question question) {
        this.question = question;
    }



    /**
     * Get the percent credit the user receive for his answers on the question
     *
     * @return Float
     */
    public final Float evaluatePercentCredit() {
        Float res = 0f;
        for (UserAnswerBlock userAnswerBlock : userAnswerBlockList) {
          res += userAnswerBlock.evaluatePercentCredit();
        }
        return res;
    }
}