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
import com.ulatina.services.AnswerService;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name = "AnswereeController")
@SessionScoped
public class AnswereeController implements Serializable {

    public AnswereeController() {
    }

    public FormController formController = new FormController();
    public QuestionController questionController = new QuestionController();
    public AnswerService answerService = new AnswerService();
    public AnswerController answerController = new AnswerController();

    private String formId;//used to save the form ID taken from the URL
    private Form form;//the current form that's beeing answered. Found using the formID variable above
    private int anonymousId;//the new anonymousID that will be linked to the answers of the current form

    /**
     * these are a set(list) of type Quest(a new object Santi created to help
     * view the questions and save the answers) there is one set for every type
     * of question the Quest object has a Question(type Question) attribute, a
     * questAnswer(type String) attribute and a dateAnswer(type Date) attribute
     */
    private Set<Quest> multQuestionSet;
    private Set<Quest> singleQuestionSet;
    private Set<Quest> textQuestionSet;
    private Set<Quest> dateQuestionSet;

    private Answer newAnswer;//Answer type variable that will be used to save a new answer in every question

    public int getAnonymousId() {
        return anonymousId;
    }

    public void setAnonymousId(int anonymousId) {
        this.anonymousId = anonymousId;
    }

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
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();//gets the info from the URL
        this.setFormId(paramMap.get("id"));//gets the form ID from the URL and sets it to the variable

        this.setForm((Form) this.formController.selectRegister(this.getFormId()));//looks for the correct form in the DB and sets it to the variable
        this.setAnonymousId(this.answerService.SelectMaxAnonymous_id() + 1);//looks for the MAX anonymous ID in the DB and adds 1 to it and sets it to the variable

        this.startDateQuestionSet();//executes the method
        this.startMultQuestionSet();//executes the method
        this.startSingleQuestionSet();//executes the method
        this.startTextQuestionSet();//executes the method
    }

    /**
     * will return the list of choice from the given question
     *
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

    /**
     * submits the text answers to the DB
     */
    public void submitTextAnswers() {
        int answerId = this.answerService.SelectMaxId();//looks for the MAX id for the answers and sets it to the variable

        for (Quest quest : this.getTextQuestionSet()) {//for every quest in the list
            if (quest.getQuestAnswer().equals("") || quest.getQuestAnswer() == null) {//if the answer isn't null or empty
                System.out.println("Not saving empty text answer");
            } else {//if it's not null or empty
                answerId++;//adds 1 to the answer ID
                Answer newAnswer = new Answer();//creates a new Answer
                newAnswer.setId(answerId);//sets the ID to the new Anser
                newAnswer.setAnonymous_id(this.getAnonymousId());//sets the anonymous ID to the new Answer
                newAnswer.setAnswer(quest.getQuestAnswer());//sets the actual answer(type String) to the new new Answer
                newAnswer.setQuestion(quest.getqQuestion());//links the question to the new Answer
                quest.getqQuestion().getAnswerList().add(newAnswer);//adds the new Answer to the list of Answers of the Question
                System.out.println("Text Question: " + quest.getqQuestion().getQuestion() + " Text Answer: " + quest.getQuestAnswer());//print for testing
                this.answerController.insert(newAnswer);//saves the new Answer into the DB
            }
        }
        System.out.println("Done submitting text answers");//for testing
    }

    /**
     * submits the date answers to the DB
     */
    public void submitDateAnswers() {
        int answerId = this.answerService.SelectMaxId();//looks for the MAX id for the answers and sets it to the variable

        for (Quest quest : this.getDateQuestionSet()) {//for every quest in the list
            if (quest.getDateAnswer() == null) {//if the answer isn't null or empty
                System.out.println("Not saving empty date answer");
            } else {//if it's not null or empty
                answerId++;//adds 1 to the answer ID
                Answer newAnswer = new Answer();//creates a new Answer
                newAnswer.setId(answerId);//sets the ID to the new Anser
                newAnswer.setAnonymous_id(this.getAnonymousId());//sets the anonymous ID to the new Answer
                newAnswer.setAnswer(this.fixDate(quest));//sets the actual answer(type String) to the new new Answer
                newAnswer.setQuestion(quest.getqQuestion());//links the question to the new Answer
                quest.getqQuestion().getAnswerList().add(newAnswer);//adds the new Answer to the list of Answers of the Question
                System.out.println("Date Question: " + quest.getqQuestion().getQuestion() + " Date Answer: " + newAnswer.getAnswer());//print for testing
                this.answerController.insert(newAnswer);//saves the new Answer into the DB
            }
        }
        System.out.println("Done submitting date answers");
    }

    /**
     * turns the date from the date answer into a String
     *
     * @param quest takes in the Quest with the date answer
     * @return a String with the date in the format dd/mm/yyyy
     */
    public String fixDate(Quest quest) {
        String day = String.valueOf(quest.getDateAnswer().getDate());
        String month = String.valueOf(quest.getDateAnswer().getMonth() + 1);
        String year = String.valueOf(quest.getDateAnswer().getYear() + 1900);
        return (day + "/" + month + "/" + year);
    }

    /**
     * submits the Single answers to the DB
     */
    public void submitSingleAnswer() {
        int answerId = this.answerService.SelectMaxId();//looks for the MAX id for the answers and sets it to the variable

        for (Quest quest : this.getSingleQuestionSet()) {//for every quest in the list
            if (quest.getQuestAnswer() == null) {//if the answer isn't null or empty
                System.out.println("Not saving empty single answer");
            } else {//if it's not null or empty
                answerId++;//adds 1 to the answer ID
                Answer newAnswer = new Answer();//creates a new Answer
                newAnswer.setId(answerId);//sets the ID to the new Anser
                newAnswer.setAnonymous_id(this.getAnonymousId());//sets the anonymous ID to the new Answer
                newAnswer.setAnswer(quest.getQuestAnswer());//sets the actual answer(type String) to the new new Answer
                newAnswer.setQuestion(quest.getqQuestion());//links the question to the new Answer
                quest.getqQuestion().getAnswerList().add(newAnswer);//adds the new Answer to the list of Answers of the Question
                System.out.println("Single Question: " + quest.getqQuestion().getQuestion() + " Single Answer: " + newAnswer.getAnswer());//print for testing
                this.answerController.insert(newAnswer);//saves the new Answer into the DB
            }
        }
        System.out.println("Done submitting single answers");
    }

    /**
     * submits the Multiple answers to the DB
     */
    public void submitMultipleAnswer() {
        int answerId = this.answerService.SelectMaxId();//looks for the MAX id for the answers and sets it to the variable
        Answer newAnswer = new Answer();//creates a new Answer

        for (Quest quest : this.getMultQuestionSet()) {//for every quest in the list
            if (quest.getQuestAnswerList().isEmpty() || quest.getQuestAnswerList() == null) {//if the answer list isn't null or empty
                System.out.println("Not saving empty single answer");
            } else {//if it's not null or empty
                for (String questAnswer : quest.getQuestAnswerList()) {//goes through every text answer of the mult choice question
                    answerId++;//adds 1 to the answer ID
                    newAnswer = new Answer();//creates a new Answer
                    newAnswer.setId(answerId);//sets the ID to the new Anser
                    newAnswer.setAnonymous_id(this.getAnonymousId());//sets the anonymous ID to the new Answer
                    newAnswer.setAnswer(questAnswer);//sets the actual answer(type String) to the new new Answer
                    newAnswer.setQuestion(quest.getqQuestion());//links the question to the new Answer
                    quest.getqQuestion().getAnswerList().add(newAnswer);//adds the new Answer to the list of Answers of the Question
                    System.out.println("Multiple Question: " + quest.getqQuestion().getQuestion() + " Multiple Answer: " + newAnswer.getAnswer());//print for testing
                    this.answerController.insert(newAnswer);//saves the new Answer into the DB
                }

            }
        }
        System.out.println("Done submitting multiple answers");
    }

    /**
     * saves all the type answers to the DB gets executed when the submit button
     * gets pressed
     */
    public void submitAllAnswers() {
        this.submitTextAnswers();

        this.submitDateAnswers();

        this.submitSingleAnswer();

        this.submitMultipleAnswer();

        System.out.println("Done submitting all answers");
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
     * resets the values of the Quest lists gets executed when the cancel button
     * gets pressed
     */
    public void cancelButton() {
        this.setMultQuestionSet(null);
        this.setSingleQuestionSet(null);
        this.setTextQuestionSet(null);
        this.setDateQuestionSet(null);
        System.out.println("Done pressing cancel button");
    }

}
