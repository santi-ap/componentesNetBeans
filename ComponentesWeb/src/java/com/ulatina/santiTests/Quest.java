/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

import com.ulatina.entity.Choice;
import com.ulatina.entity.Question;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author santialfonso
 */
public class Quest {
    private Question qQuestion;
    private String questAnswer;
    private Date dateAnswer;
    private Set<String> questAnswerList;
    private String questOptionId;
    private Set<String> questOptionIdList;

    public Set<String> getQuestOptionIdList() {
        return questOptionIdList;
    }

    public void setQuestOptionIdList(Set<String> questOptionIdList) {
        this.questOptionIdList = questOptionIdList;
    }

    public String getQuestOptionId() {
        return questOptionId;
    }

    public void setQuestOptionId(String questOptionId) {
        this.questOptionId = questOptionId;
    }

    public Set<String> getQuestAnswerList() {
        return questAnswerList;
    }

    public void setQuestAnswerList(Set<String> questAnswerList) {
        this.questAnswerList = questAnswerList;
    }

    public Date getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Date dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public Quest() {
    }

    public Question getqQuestion() {
        return qQuestion;
    }

    public void setqQuestion(Question qQuestion) {
        this.qQuestion = qQuestion;
    }

    public String getQuestAnswer() {
        return questAnswer;
    }

    public void setQuestAnswer(String questAnswer) {
        this.questAnswer = questAnswer;
    }
    
}
