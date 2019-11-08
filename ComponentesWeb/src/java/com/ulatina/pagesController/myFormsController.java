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
    
    private boolean saved = false;//this is used to determine whether the success message after saving a new form should be shown or not

    public boolean getSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    
    /**
     * if true, it will call the method to display the success message after saving a new form
     */
    public void savedForm(){
        if(this.getSaved()){
            addMessage("Succes!", "New Form has been saved!");
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
        this.setSaved(false);
    }
}
