/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.davidTests;
import com.ulatina.controllers.Controller;
import com.ulatina.entity.Question;
import com.ulatina.pagesController.newFormController;


/**
 *
 * @author coded
 */
public class tester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       newFormController nfc = new newFormController();
       Question q = new Question();
       q.setId(48);
       nfc.deleteQuestion(q);
        System.out.println("HF");
    }
    
}
