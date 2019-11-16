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

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    //Variables for login
    private String email;
    private String password;
    //variables for sing up
    private String emails;
    private String name;
    private String pass;

    public void register() {

        users.setName(getName());
        users.setEmail(getEmails());
        users.setPassword(getPass());
        us.insert(users);
        if (users != null) {
            System.out.println(users.getName());
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .addResponseCookie("name", users.getName(), null);

            this.redirect("myFormsPage");
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
        }
    }

    public void login() {
        users = us.loginClient(this.email, this.password);

        if (users != null) {
            System.out.println(users.getName());
//            FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .addResponseCookie("name", users.getName(), null);
            this.redirect("myFormsPage");
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
        }
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
