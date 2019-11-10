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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name = "newFormController")
@SessionScoped
public class newFormController {

    Controller typeController = new TypeController();
    Controller questionController = new QuestionController();
    Controller formController = new FormController();
    Controller choiceController = new ChoiceController();
    Controller userController = new UserController();
    Controller answerController = new AnswerController();

    //this question list is to populate the NewQuestion area. Ask Santi for more
    private List<Question> questionList = new ArrayList<>();

    private Form newForm;

    String deleteRendered = "true";

    public String getDeleteRendered() {
        return deleteRendered;
    }

    public void setDeleteRendered(String deleteRendered) {
        this.deleteRendered = deleteRendered;
    }

    //This is to call and use the same instance of the myFormsController class. Ask Santi for more info
    @ManagedProperty(value = "#{myFormsController}")
    private myFormsController myFormsController;

    public myFormsController getMyFormsController() {
        return myFormsController;
    }

    public void setMyFormsController(myFormsController myFormsController) {
        this.myFormsController = myFormsController;
    }

    public newFormController() {

    }

    public Form getNewForm() {
        return newForm;
    }

    public void setNewForm(Form newForm) {
        this.newForm = newForm;
    }

    /**
     * creates a new form and inserts it into the database
     */
    public void createNewForm(){
        this.setNewForm(new Form());
        User user = (User) userController.selectRegister("tomasso@gmail.com");//once Alexis pushes his part, I can change this to be the actual current user
        this.getNewForm().setUser(user);
        this.formController.insert(this.getNewForm());
        
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void addToQuestionList(Question questionType) {
        this.getQuestionList().add(questionType);
    }

    /**
     * this is what gets called when you try to close a panel
     *
     * @param event
     */
    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Question Deleted", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * adds a new QuestionType object to the list as type 1 which is
     * MultipleChoice. This in turn generates a new question area Then shows a
     * message letting the user know
     */
    public void addMultiple() {

        Type type = (Type) typeController.selectRegister("1");
        Question newQuestion = new Question();

        newQuestion.setType(type);
        newQuestion.setForm(this.getNewForm());
        this.getNewForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);

        questionController.insert(newQuestion);

        addMessage("Succes", "Added Multiple Choice Question");
    }

    /**
     * adds a new QuestionType object to the list as type 2 which is
     * SingleleChoice. This in turn generates a new question area Then shows a
     * message letting the user know
     */
    public void addSingle() {
        Type type = (Type) typeController.selectRegister("2");
        Question newQuestion = new Question();

        newQuestion.setType(type);
        newQuestion.setForm(this.getNewForm());
        this.getNewForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);

        questionController.insert(newQuestion);
        addMessage("Succes", "Added Single Choice Question");
    }

    /**
     * adds a new QuestionType object to the list as type 3 which is Text. This
     * in turn generates a new question area Then shows a message letting the
     * user know
     */
    public void addText() {
        Type type = (Type) typeController.selectRegister("3");
        Question newQuestion = new Question();

        newQuestion.setType(type);
        newQuestion.setForm(this.getNewForm());
        this.getNewForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);

        questionController.insert(newQuestion);
        addMessage("Succes", "Added Input Text Question");

    }

    /**
     * adds a new QuestionType object to the list as type 4 which is Date. This
     * in turn generates a new question area Then shows a message letting the
     * user know
     */
    public void addDate() {
        Type type = (Type) typeController.selectRegister("4");
        Question newQuestion = new Question();

        newQuestion.setType(type);
        newQuestion.setForm(this.getNewForm());
        this.getNewForm().getQuestionList().add(newQuestion);
        type.getQuestionList().add(newQuestion);

        questionController.insert(newQuestion);
        addMessage("Succes", "Added Input Date Question");
    }

    /**
     * shows the message after adding a question
     *
     * @param summary
     * @param detail
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * hides or shows the answer areas depending on the type of question
     *
     * @param question the question in area
     * @param id the name of the panel in question
     * @return true or false
     */
    public String checkRendered(Question question, String id) {
        if (question.getType().getId() < 3 && id.equals("answerMultiple")) {
            return "true";
        } else if (question.getType().getId() == 3 && id.equals("answerText")) {
            return "true";
        } else if (question.getType().getId() == 4 && id.equals("answerDate")) {
            return "true";
        } else {
            return "false";
        }
    }

    public void addChoice(Question question) {
        Choice newChoice = new Choice();
        question.getChoiceList().add(newChoice);
        newChoice.setQuestion(question);
        choiceController.insert(newChoice);
        addMessage("Succes", "New Choice Added");
    }

    /**
     * dictates what happens when the save button gets pressed
     */
    public void saveButton() {
        this.getMyFormsController().setMessageToShow(1);//sets a certain variable from the myFormsController class to 'true' to show the success message on that page
        this.redirect("myFormsPage");//redirects to the myForms page
    }

    /**
     * redirects to the specified page
     *
     * @param page name of page as named in the project without the .xhtml
     */
    public void redirect(String page) {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/" + page + ".xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(Question question) {

        for (Choice c : question.getChoiceList()) {
            this.choiceController.delete(c);
        }

        question.setChoiceList(null);
//        for (Answer a : question.getAnswerList()){
//            this.answerController.delete(a);
//        }
        this.questionController.delete(question);
        this.getNewForm().getQuestionList().remove(question);
        System.out.println("deleted");
        addMessage("Succes", "Question Deleted");
    }

    public void deleteForm() {

        for (Question q : newForm.getQuestionList()) {
            for (Choice c : q.getChoiceList()) {
                this.choiceController.delete(c);
            }
            q.setChoiceList(null);
            this.questionController.delete(q);
            
        }
        this.getNewForm().setQuestionList(null);
        
        this.formController.delete(this.newForm);
        this.getMyFormsController().setMessageToShow(2);
        this.redirect("myFormsPage");
    }
    

}
