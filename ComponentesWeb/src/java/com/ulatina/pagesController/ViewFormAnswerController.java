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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Asus
 */
@ManagedBean(name = "viewFormAnswerController")
@SessionScoped
public class ViewFormAnswerController {

    private boolean showTable;

    private int formId;
    private Form form;
    private double percentage;

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    private double numAns = 5;

    public Form getForm() {
        return form;
    }

    public ViewFormAnswerController() {
    }

    public void setForm(Form form) {
        this.form = form;
    }
    Controller aController = new AnswerController();
    Controller qController = new QuestionController();
    Controller fController = new FormController();

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
    }

<<<<<<< HEAD
  

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

=======
>>>>>>> origin/master
    /**
     * this method takes a question and it goes through the answers and
     * calculates the percentages of the results int he case of mult o single
     * choice
     *
     */
    public Set<String> generateResults(Question q) { // gets the from from myFormsController
        Set<String> resultList = new HashSet<>();

<<<<<<< HEAD
        if (q.getType().getId() == 1 || q.getType().getId() == 2) {
=======
        if (q.getType().getId() == 1 || q.getType().getId() == 2) { // if the question type is multiple choice or single choice it does the stuff to calculate the percentages
>>>>>>> origin/master
            for (Choice c : q.getChoiceList()) {

                double amountOfAnswers = 0;
                String result;

                if (q.getAnswerList().size()==0) {
                    resultList.add("No Answers");
                } else {
                    for (Answer a : q.getAnswerList()) {  // calculates how many people chose a certain asnwer
                        if (a.getChoice().getId() == c.getId()) {
                            amountOfAnswers = amountOfAnswers + 1;
                        }
                    }

                    this.percentage = ((amountOfAnswers / this.numAns) * 100);
                    result = c.getChoice() + ": " + this.percentage + "%";
                    resultList.add(result);
                }
<<<<<<< HEAD

                this.percentage = ((amountOfAnswers / this.numAns) * 100);
                result = c.getChoice() + ": " + this.percentage + "%";
                resultList.add(result);

=======
>>>>>>> origin/master
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
    
    public String enableTable()
    {
        return (this.getShowTable()?"true":"false") ;
        
    }
    public String disableTable ()
    {
        
    return( this.getShowTable()? "false":"true");
}
}
