/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

import com.ulatina.entity.Form;
import com.ulatina.entity.Question;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author santialfonso
 */

@ManagedBean(name="ATest")
@SessionScoped
public class ATest implements Serializable {
    private Form form;

    public ATest() {
    }
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    
    public Set<Question> returnMultipleChoiceQuestion(){
        Set<Question> multQuestion = new HashSet<>();
        for(Question q: this.getForm().getQuestionList()){
            if (q.getType().getId()==1){
                multQuestion.add(q);
            }
        }
        return multQuestion;
    }
    
    public void test(){
        System.out.println("test complete.....");
    }
    
}
