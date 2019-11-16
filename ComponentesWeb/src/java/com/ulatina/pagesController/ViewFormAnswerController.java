/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Asus
 */
@ManagedBean(name="viewFormAnswerController")
@SessionScoped
public class ViewFormAnswerController {
    private int formId;

    public int getFormId() 
    {
        return formId;
    }

    public void setFormId(int formId) 
    {
        this.formId = formId;
    }
    
    /**
     *  this method takes the info sent through the URL
     *  and sets it to the local variable formId
     */
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap=context.getExternalContext().getRequestParameterMap();
        String projectId = paramMap.get("id");
        this.setFormId(Integer.parseInt(projectId));
    }
}
