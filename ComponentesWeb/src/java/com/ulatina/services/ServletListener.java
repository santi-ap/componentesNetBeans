/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.services;

import com.ulatina.pagesController.*;
import com.ulatina.controllers.Controller;
import com.ulatina.controllers.FormController;
import com.ulatina.controllers.UserController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 *
 * @author Asus
 */
@SessionScoped
@WebListener

public class ServletListener implements ServletContextListener {
    private Controller controller; 
    //This is to call and use the same instance of the VerificationController class. Ask Santi for more info
    @ManagedProperty(value = "#{VerificationController}")
    private VerificationController verificationController; 
    public VerificationController getVerificationController() {
        return verificationController;
    }
    public void setVerificationController(VerificationController verificationController) {
        this.verificationController = verificationController;
    }
    public Controller getController() {
        return controller;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }
    public ServletListener() {
        this.controller = new UserController();
    }
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("okboomer37");
        this.getController().startEntityManagerFactory();
        System.out.println("okboomer39");
    }
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("okboomer345");
        this.getController().stopEntityManagerFactory();
        System.out.println("okboomer48");
    }
}