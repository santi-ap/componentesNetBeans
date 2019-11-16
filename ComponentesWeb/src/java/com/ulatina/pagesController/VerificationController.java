/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.pagesController;

import com.ulatina.controllers.UserController;
import com.ulatina.entity.User;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "VerificationController")
@SessionScoped
/**
 *
 * @author jairf
 */
public class VerificationController implements Serializable {

    public VerificationController() {
    }
    private static final long serialVersionUID = 1L;
    UserController us = new UserController();
    User users = new User();

    //Variables for login
    private String email;
    private String password;
    //variables for sing up
    private String emails;
    private String name;
    private String pass;

    public String register() {

        users.setName(getName());
        users.setEmail(getEmails());
        users.setPassword(getPass());
        us.insert(users);
        if (users != null) {
            System.out.println(users.getName());
            //  FacesContext.getCurrentInstance()
            //       .getExternalContext()
            //      .addResponseCookie("name", users.getName(), null);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
            return null;
        }
        return "myFormsPage.xhtml?faces-redirect=true";
    }

    public void cancelar() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.redirect("index");
    }

    public String login() {
        users = us.loginClient(this.email, this.password);

        if (users != null) {
            System.out.println(users.getName());
//            FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .addResponseCookie("name", users.getName(), null);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
            return null;
        }
        return "myFormsPage.xhtml?faces-redirect=true";
    }

    public UserController getUs() {
        return us;
    }

    public void setUs(UserController us) {
        this.us = us;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    /**
     * @return the emails
     */
    public String getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(String emails) {
        this.emails = emails;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void reset() {
        PrimeFaces.current().resetInputs("form:panel");
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
}
