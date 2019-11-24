/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.entity.Form;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name = "myFormsController")
@SessionScoped

public class myFormsController {

    //this variable is set to 0 each time the page gets reloaded (check last line of method "addMessage")
    private int messageToShow = 0;//this is used to determine wich message to show when loading the page

    private String viewFormURL;

//    //HOSTPORT FOR ALEXIS
//    public final String HOSTPORT = "http://localhost:8061";
    //HOSTPORT FOR SANTI
    public final String HOSTPORT = "http://localhost:9080";

    @ManagedProperty(value = "#{SampleFormController}")
    private SampleFormController sampleFormController;

    public SampleFormController getSampleFormController() {
        return sampleFormController;
    }

    public void setSampleFormController(SampleFormController sampleFormController) {
        this.sampleFormController = sampleFormController;
    }

    public String getViewFormURL() {
        return viewFormURL;
    }

    public void setViewFormURL(String viewFormURL) {
        this.viewFormURL = viewFormURL;
    }

    public int getMessageToShow() {
        return messageToShow;
    }

    public void setMessageToShow(int messageToShow) {
        this.messageToShow = messageToShow;
    }

    /**
     * if messageToShow variable, I will call the method to display the right
     * message depending on what's needed
     */
    public void showMessageOnLoad() {

        switch (this.getMessageToShow()) {
            case 0:
                return;
            case 1:
                addMessage("Succes!", "New Form has been saved!");
                return;
            case 2:
                addMessage("Succes!", "Canceled form");
                return;
        }

    }

    /**
     * displays a message
     *
     * @param summary
     * @param detail
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
        this.setMessageToShow(0); //the variable is set back to 0 so we dont show messages each time we load
    }

    /**
     * this method redirects the user to the answer page of a specific form, so
     * you need to pass the ID
     *
     * @param formId the form id that you want to show to the user
     */
    public void redirectToFormAnswer(int formId) {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/viewFormAnswerPage.xhtml?id=" + formId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getViewFormLink() {
        return this.getViewFormURL();

    }

    public void generateViewFormURL(int formId) {
        this.setViewFormURL(HOSTPORT + "/ComponentesWeb/faces/answereePage.xhtml?id=" + formId);
    }

    /**
     * this method redirects the user to the sample form, so you need to pass
     * the ID
     *
     * @param formId the form id that you want to show to the user
     */
    public void redirectToSampleForm(Form form) {

        //This is to call and use the same instance of the VerificationController class. Ask Santi for more info
        this.getSampleFormController().setForm(form);
        this.getSampleFormController().init();

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/sampleForm.xhtml?id=" + form.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
