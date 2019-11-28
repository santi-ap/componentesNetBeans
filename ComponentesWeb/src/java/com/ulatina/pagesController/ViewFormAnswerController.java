/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.controllers.Controller;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.ulatina.controllers.FormController;
import com.ulatina.entity.Form;
import com.ulatina.controllers.AnswerController;
import com.ulatina.controllers.QuestionController;
import com.ulatina.entity.Answer;
import com.ulatina.entity.Choice;
import com.ulatina.entity.Question;
import com.ulatina.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.ulatina.services.AnswerService;
import java.text.DecimalFormat;
import javax.persistence.NonUniqueResultException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "viewFormAnswerController")
@SessionScoped
public class ViewFormAnswerController {

    private boolean showTable;

    private int formId;
    //FORM COMES FROM VIEWFORMCONTROLLER MANAGED PROPERTY
    private Form form;
    private double percentage;
    private Set<Integer> answereeId;

    public Set<Integer> getAnswereeId() {
        return answereeId;
    }

    public void setAnswereeId(Set<Integer> answereeId) {
        this.answereeId = answereeId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Form getForm() {
        return form;
    }

    public ViewFormAnswerController() {
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    Controller aController = new AnswerController();
    Controller qController = new QuestionController();
    Controller fController = new FormController();
    AnswerService AnsService = new AnswerService();
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    /**
     * this method takes the info sent through the URL and sets it to the local
     * variable formId
     */
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String projectId = paramMap.get("id");
        this.setFormId(Integer.parseInt(projectId));
        System.out.println(" test");
        System.out.println(this.form.getTitle());

        //the answereelist is being loaded from service
        this.setAnswereeId(this.AnsService.getAnswerByForm(this.getFormId()));
    }

    /**
     * this method takes a question and it goes through the answers and
     * calculates the percentages of the results int he case of mult o single
     * choice
     *
     */
    public Set<String> generateResults(Question q) { // gets the from from myFormsController
        Set<String> resultList = new HashSet<>();

        if (q.getType().getId() == 1 || q.getType().getId() == 2) { // if the question type is multiple choice or single choice it does the stuff to calculate the percentages

            for (Choice c : q.getChoiceList()) {

                double amountOfAnswers = 0;
                String result;

                if (q.getAnswerList().size() == 0) {
                    resultList.add("No Answers");
                } else {
                    for (Answer a : q.getAnswerList()) {  // calculates how many people chose a certain asnwer
                        if (a.getChoice().getId() == c.getId()) {
                            amountOfAnswers = amountOfAnswers + 1;
                        }
                        System.out.println(formId);
                    }
                    this.percentage = ((amountOfAnswers / this.AnsService.getNumOfAnswerees(formId)) * 100);
                    result = c.getChoice() + ": " + df2.format(this.percentage) + "%";
                    resultList.add(result);
                }
            }
        } else {

            for (Answer a : q.getAnswerList()) {
                resultList.add(a.getAnswer());
            }

        }

        return resultList;

    }

    public boolean getShowTable() {
        return showTable;
    }

    public void setShowTable(boolean showTable) {
        this.showTable = showTable;
    }

    public String enableTable() {
        return (this.getShowTable() ? "true" : "false");

    }

    public String disableTable() {

        return (this.getShowTable() ? "false" : "true");
    }

    public String getAnswerById(Question q, int answeree) {
        //return this.AnsService.getAnswerByAnsweree(answeree, formId, q.getId());
        //lets get some answers from a particular question q
        String returnString = "";
        for (Answer a : q.getAnswerList()) {
            //if the id of the answeree matches... 
            if (a.getAnonymous_id() == answeree) {
                //...check if it's a date or text question, if so...
                if (!(a.getAnswer() == null || a.getAnswer().isEmpty())) {
                    //return it
                    return " - " + a.getAnswer();
                } else { // else we gotta look for the choices
                    //so for each choice of this question
                    for (Choice c : q.getChoiceList())
                    {
                        //check if we have it on the answer
                        if (a.getChoice() == c)
                        {
                            //if so, return it
                            returnString = returnString +  "\n- " + c.getChoice();
                        } 
                    }
                    
                }
            } 
        }
        return returnString;
    }
}
