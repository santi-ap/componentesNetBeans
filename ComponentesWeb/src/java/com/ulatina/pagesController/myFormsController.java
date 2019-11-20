/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.entity.Form;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import com.ulatina.controllers.Controller;
import com.ulatina.pagesController.ViewFormAnswerController;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author santialfonso
 */



@ManagedBean(name="myFormsController")
@SessionScoped

public class myFormsController {
    
    ViewFormAnswerController vFAC = new ViewFormAnswerController();
    
    //this variable is set to 0 each time the page gets reloaded (check last line of method "addMessage")
    private int messageToShow = 0;//this is used to determine wich message to show when loading the page
    
    /**
     *
     */ 
    public final String HOSTPORT = "http://localhost:8061";
    
    
    public int getMessageToShow() {
        return messageToShow;
    }

    public void setMessageToShow(int messageToShow) {
        this.messageToShow = messageToShow;
    }

    public ViewFormAnswerController getViewFormAnswerController() {
        return viewFormAnswerController;
    }

    public void setViewFormAnswerController(ViewFormAnswerController viewFormAnswerController) {
        this.viewFormAnswerController = viewFormAnswerController;
    }

    
   @ManagedProperty(value = "#{viewFormAnswerController}")
    private ViewFormAnswerController viewFormAnswerController;
    
    /**
     * if messageToShow variable, I will call the method to display the right message depending on what's needed
     */
    public void showMessageOnLoad(){
        
        switch (this.getMessageToShow()){
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
     * @param summary
     * @param detail 
     */
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
        this.setMessageToShow(0); //the variable is set back to 0 so we dont show messages each time we load
    }
    /**
     * this method redirects the user to the answer page of a specific form, so you need to pass the ID
     * @param formId the form id that you want to show to the user
     */
    public void redirectToFormAnswer (Form form)
    {
        
        this.viewFormAnswerController.setForm(form);
        System.out.println("tes1" +form.getTitle());
        try
        {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect(
                            request.getContextPath()
                            + "/faces/viewFormAnswerPage.xhtml?id=" + form.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public String getViewFormLink(int formId){
        return (HOSTPORT+"/ComponentesWeb/faces/newFormPage.xhtml?id="+formId);
        
    }
    
}
