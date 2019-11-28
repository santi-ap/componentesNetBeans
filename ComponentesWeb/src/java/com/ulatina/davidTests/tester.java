/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.davidTests;
import com.ulatina.controllers.Controller;
import com.ulatina.entity.Question;
import com.ulatina.pagesController.newFormController;
import com.ulatina.services.AnswerService;


/**
 *
 * @author coded
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       AnswerService as = new AnswerService();
       
       
       
        System.out.println(as.getNumOfAnswerees(335));
    }
    
}
