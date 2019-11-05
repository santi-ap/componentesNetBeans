/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.TomTests;

import com.ulatina.controllers.*;
import com.ulatina.entity.*;


/**
 *
 * @author Asus
 */
public class TomTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // helooo
        
        Controller uc = new UserController();
        User usTest = new User();
        usTest.setEmail("JARTest");
        uc.insert(usTest);
    }
    
}
