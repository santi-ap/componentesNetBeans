/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.santiTests.QuestionType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name="newFormController")
@ViewScoped
public class newFormController {
    
    //this question list is to populate the NewQuestion area. Ask Santi for more
    private List<QuestionType> questionList = new ArrayList<>();
   

    public List<QuestionType> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionType> questionList) {
        this.questionList = questionList;
    }
    
    public void addToQuestionList(QuestionType questionType){
        this.getQuestionList().add(questionType);
    }
    
    
    /**
     * this is what gets called when you try to close a panel
     * @param event 
     */ 
    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    /**
     * adds a new QuestionType object to the list as type 1 which is MultipleChoice.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addMultiple() {
        QuestionType newQuestionType = new QuestionType(1,this.getQuestionList().size()+1,"New Multiple Choice Question:");
        this.addToQuestionList(newQuestionType);
        addMessage("Succes", "Added Multiple Choice Question");
    }
    
    /**
     * adds a new QuestionType object to the list as type 2 which is SingleleChoice.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addSingle() {
        QuestionType newQuestionType = new QuestionType(2,this.getQuestionList().size()+1,"New Single Choice Question:");
        this.addToQuestionList(newQuestionType);
        addMessage("Succes", "Added Single Choice Question");
    }
    
    /**
     * adds a new QuestionType object to the list as type 3 which is Text.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addText() {
        QuestionType newQuestionType = new QuestionType(3,this.getQuestionList().size()+1,"New Input Text Question:");
        this.addToQuestionList(newQuestionType);
        addMessage("Succes", "Added Input Text Question");
    }
    
    /**
     * adds a new QuestionType object to the list as type 4 which is Date.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addDate() {
        QuestionType newQuestionType = new QuestionType(4,this.getQuestionList().size()+1,"New Input Date Question:");
        this.addToQuestionList(newQuestionType);
        addMessage("Succes", "Added Input Date Question");
    }
    
    /**
     * shows the message after adding a question
     * @param summary
     * @param detail 
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    

}
