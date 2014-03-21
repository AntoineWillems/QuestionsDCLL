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

import  dcll.kowa.ProjetQuestions.Answer;

/**
 * @author franck Silvestre
 */
public class DefaultAnswer implements Answer {

    private String textValue;
    private Float percentCredit;
    private String identifier;
    private String feedback;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultAnswer that = (DefaultAnswer) o;

        if (!identifier.equals(that.identifier)) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        return identifier.hashCode();
    }

    /**
     * Get the text value of the answer
     *
     * @return the text value of the answer
     */
    public final String getTextValue() {
        return textValue;
    }

    /**
     * Set te text value of the answer
     * @param textValue the new text value
     */
    public final void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    /**
     * Get the percent credit the answer represents in the answer set
     *
     * @return the percent credit
     */
    public final Float getPercentCredit() {
        return percentCredit;
    }

    /**
     * Set the percent credit of the answer
     * @param percentCredit the percent credit
     */
    public final void setPercentCredit(Float percentCredit) {
        this.percentCredit = percentCredit;
    }

    /**
     * Get the identifier of the answer relative to the question
     *
     * @return the identifier
     */
    public final String getIdentifier() {
        return identifier;
    }

    /**
     * Set the identifier
     * @param identifier  the new identifier
     */
    public  final void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Set the feedback
     * @param feedback a String
     */
    public final void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    /**
     * Get the feedback to present to a student for this answer
     *
     * @return feedback a String
     */
    public final String getFeedBack() {
        return feedback;
    }
}
