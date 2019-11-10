/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name="myFormsController")
@SessionScoped
public class myFormsController {
    
    private int messageToShow = 0;//this is used to determine wich message to show when loading the page

    public int getMessageToShow() {
        return messageToShow;
    }

    public void setMessageToShow(int messageToShow) {
        this.messageToShow = messageToShow;
    }

   
    
    /**
     * if true, it will call the method to display the success message after saving a new form
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
        this.setMessageToShow(0);
    }
}
