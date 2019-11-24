/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

import com.ulatina.entity.Question;
import java.util.Date;

/**
 *
 * @author santialfonso
 */
public class Quest {
    private Question qQuestion;
    private String questAnswer;
    private Date dateAnswer;

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
