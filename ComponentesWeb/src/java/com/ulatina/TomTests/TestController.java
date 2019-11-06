/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.TomTests;

import com.ulatina.controllers.Controller;
import com.ulatina.controllers.FormController;
import com.ulatina.controllers.UserController;
import com.ulatina.entity.Form;
import com.ulatina.entity.User;
import java.util.HashSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Asus
 */
@ManagedBean (name = "controller")
@SessionScoped
public class TestController {
    
        public void foo ()
    {
        Controller uc = new UserController();
        Controller fc = new FormController();
        User usTest = new User();
        usTest.setEmail("santi2@test.com");
        Form ft = new Form();
        usTest.setFormList(new HashSet<Form>());
        usTest.getFormList().add(ft);
        ft.setUser(usTest);
        ft.setId(1);
        
//        fc.insert(ft);
        
        uc.insert(usTest);
        System.out.println("----");
    }
}
