/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.TomTests;

import com.ulatina.controllers.Controller;
import com.ulatina.controllers.UserController;
import com.ulatina.entity.User;
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
        User usTest = new User();
        usTest.setEmail("tuesday1@test.com");
        uc.insert(usTest);
        System.out.println("----");
    }
}
