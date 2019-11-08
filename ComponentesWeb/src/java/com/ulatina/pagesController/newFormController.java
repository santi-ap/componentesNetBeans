/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.controllers.*;
import com.ulatina.entity.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name="newFormController")
@SessionScoped
public class newFormController {
    
    Controller typeController = new TypeController();
    Controller questionController = new QuestionController();
    Controller formController = new FormController();
    Controller choiceController = new ChoiceController();
    private Form testForm = (Form)formController.selectRegister("2");

    public newFormController() {
        
    }
    
    //this question list is to populate the NewQuestion area. Ask Santi for more
    private List<Question> questionList = new ArrayList<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
    
    public Form getTestForm() {
        return testForm;
    }

    public void setTestForm(Form testForm) {
        this.testForm = testForm;
    }
    
    public void addToQuestionList(Question questionType){
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
        
        Type type = (Type)typeController.selectRegister("1");
        Question newQuestion = new Question();
        
        newQuestion.setType(type);
        newQuestion.setForm(this.getTestForm());
        this.getTestForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);
        
        questionController.insert(newQuestion);
        
        
        addMessage("Succes", "Added Multiple Choice Question");
    }
    
    /**
     * adds a new QuestionType object to the list as type 2 which is SingleleChoice.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addSingle() {
        Type type = (Type)typeController.selectRegister("2");
        Question newQuestion = new Question();
        
        newQuestion.setType(type);
        newQuestion.setForm(this.getTestForm());
        this.getTestForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);
        
        questionController.insert(newQuestion);
        addMessage("Succes", "Added Single Choice Question");
    }
    
    /**
     * adds a new QuestionType object to the list as type 3 which is Text.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addText() {
        Type type = (Type)typeController.selectRegister("3");
        Question newQuestion = new Question();
        
        newQuestion.setType(type);
        newQuestion.setForm(this.getTestForm());
        this.getTestForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);
        
        questionController.insert(newQuestion);
        addMessage("Succes", "Added Input Text Question");
        
        
    }
    
    /**
     * adds a new QuestionType object to the list as type 4 which is Date.
     * This in turn generates a new question area
     * Then shows a message letting the user know
     */
    public void addDate() {
        Type type = (Type)typeController.selectRegister("4");
        Question newQuestion = new Question();
        
        newQuestion.setType(type);
        newQuestion.setForm(this.getTestForm());
        this.getTestForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);
        
        questionController.insert(newQuestion);
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
    
    public String checkRendered (Question question, String id)
    {
        if (question.getType().getId()<3 && id.equals("answerMultiple"))
                return "true";
        else if (question.getType().getId() == 3 && id.equals("answerText"))
                return "true";
        else if (question.getType().getId() == 4 && id.equals("answerDate"))
                return "true";
        else    return "false";
    }
    
    public void addChoice (Question question)
    {
        Choice newChoice = new Choice();
        question.getChoiceList().add(newChoice);
        newChoice.setQuestion(question);
        choiceController.insert(newChoice);
        addMessage("Succes", "New Choice Added");
    }
    
    

}
