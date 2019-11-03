/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name="questionType")
public class QuestionType {
    private int type;
    private int id;
    private String question;
    
    public QuestionType(int type, int id, String question){
        this.setId(id);
        this.setType(type);
        this.setQuestion(question+" "+id);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
