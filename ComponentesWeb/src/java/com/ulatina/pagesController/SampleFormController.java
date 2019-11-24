/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.controllers.AnswerController;
import com.ulatina.controllers.FormController;
import com.ulatina.controllers.QuestionController;
import com.ulatina.entity.Answer;
import com.ulatina.entity.Choice;
import com.ulatina.entity.Form;
import com.ulatina.entity.Question;
import com.ulatina.santiTests.Quest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author santialfonso
 */

@ManagedBean(name="SampleFormController")
@SessionScoped
public class SampleFormController {
    public FormController formController = new FormController();
    public QuestionController questionController = new QuestionController();
    public AnswerController answerController = new AnswerController();

    public SampleFormController() {
    }

    private String formId;//used to save the form ID taken from the URL
    private Form form;//the current form that's beeing answered. Found using the formID variable above
    
    /**
     * these are a set(list) of type Quest(a new object Santi created to help view the questions and save the answers)
     * there is one set for every type of question
     * the Quest object has a Question(type Question) attribute, a questAnswer(type String) attribute and a dateAnswer(type Date) attribute
     */
    private Set<Quest> multQuestionSet;
    private Set<Quest> singleQuestionSet;
    private Set<Quest> textQuestionSet;
    private Set<Quest> dateQuestionSet;
    
    private Answer newAnswer;//Answer type variable that will be used to save a new answer in every question


    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Answer getNewAnswer() {
        return newAnswer;
    }

    public void setNewAnswer(Answer newAnswer) {
        this.newAnswer = newAnswer;
    }

    public Set<Quest> getSingleQuestionSet() {
        return singleQuestionSet;
    }
    
    public void setSingleQuestionSet(Set<Quest> singleQuestionSet) {
        this.singleQuestionSet = singleQuestionSet;
    }

    public void setMultQuestionSet(Set<Quest> multQuestionSet) {
        this.multQuestionSet = multQuestionSet;
    }

    public Set<Quest> getMultQuestionSet() {
        return multQuestionSet;
    }

    public void setTextQuestionSet(Set<Quest> textQuestionSet) {
        this.textQuestionSet = textQuestionSet;
    }

    public Set<Quest> getTextQuestionSet() {
        return textQuestionSet;
    }

    public Set<Quest> getDateQuestionSet() {
        return dateQuestionSet;
    }

    public void setDateQuestionSet(Set<Quest> dateQuestionSet) {
        this.dateQuestionSet = dateQuestionSet;
    }

    /**
     * instantiates a new list of type Quest with only type Multiple Questions
     */
    public void startMultQuestionSet() {
        this.multQuestionSet = new HashSet<>();//creates a new multQuestionSet
        for (Question q : this.getForm().getQuestionList()) {//for every question in the question list of the current form
            if (q.getType().getId() == 1) {//if the question is type Multiple Question
                Quest newQuest = new Quest();//creates a new Quest
                newQuest.setqQuestion(q);//adds the current question to the new Quest
                this.multQuestionSet.add(newQuest);//adds the new Quest to the new multQuestionSet
            }
        }
    }
    
    /**
     * instantiates a new list of type Quest with only type Single Questions
     */
    public void startSingleQuestionSet() {
        this.singleQuestionSet = new HashSet<>();//creates a new singleQuestionSet
        for (Question q : this.getForm().getQuestionList()) {//for every question in the question list of the current form
            if (q.getType().getId() == 2) {//if the question is type Single Question
                Quest newQuest = new Quest();//creates a new Quest
                newQuest.setqQuestion(q);//adds the current question to the new Quest
                this.singleQuestionSet.add(newQuest);//adds the new Quest to the new singleQuestionSet
            }
        }
    }
    
    /**
     * instantiates a new list of type Quest with only type Text Questions
     */
    public void startTextQuestionSet() {
        this.textQuestionSet = new HashSet<>();//creates a new textQuestionSet
        for (Question q : this.getForm().getQuestionList()) {//for every question in the question list of the current form
            if (q.getType().getId() == 3) {//if the question is type Text Question
                Quest newQuest = new Quest();//creates a new Quest
                newQuest.setqQuestion(q);//adds the current question to the new Quest
                this.textQuestionSet.add(newQuest);//adds the new Quest to the new textQuestionSet
            }
        }
    }
    
    /**
     * instantiates a new list of type Quest with only type Date Questions
     */
    public void startDateQuestionSet() {
        this.dateQuestionSet = new HashSet<>();//creates a new dateQuestionSet
        for (Question q : this.getForm().getQuestionList()) {//for every question in the question list of the current form
            if (q.getType().getId() == 4) {//if the question is type Date Question
                Quest newQuest = new Quest();//creates a new Quest
                newQuest.setqQuestion(q);//adds the current question to the new Quest
                this.dateQuestionSet.add(newQuest);//adds the new Quest to the new dateQuestionSet
            }
        }
    }


    /**
     * first method to be executed in this class
     */
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();//gets the info from the URL
        this.setFormId(paramMap.get("id"));//gets the form ID from the URL and sets it to the variable


        this.startDateQuestionSet();//executes the method
        this.startMultQuestionSet();//executes the method
        this.startSingleQuestionSet();//executes the method
        this.startTextQuestionSet();//executes the method
    }

    /**
     * will return the list of choice from the given question
     * @param question the question which we want the choice list from
     * @return the list of choices from the given method
     */
    public Set<Choice> returnChoiceList(Question question) {
        return question.getChoiceList();
    }
    
    /**
     * just for testing button presses
     */
    public void testing() {
        System.out.println("button pressed!");
    }

   

    public Set<Answer> returnAnswerList(Question question) {
        return question.getAnswerList();
    }

    public Set<String> returnStringList() {
        Set<String> listTest = new HashSet<>();
        listTest.add("1");
        listTest.add("2");
        listTest.add("3");
        return listTest;
    }

    /**
     * resets the values of the Quest lists
     * gets executed when the cancel button gets pressed
     */
    public void cancelButton() {
        this.setMultQuestionSet(null);
        this.setSingleQuestionSet(null);
        this.setTextQuestionSet(null);
        this.setDateQuestionSet(null);
        System.out.println("Done pressing cancel button");
    }
    
}
