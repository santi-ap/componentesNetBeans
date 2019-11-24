/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author santialfonso
 */
@ManagedBean(name = "answerService")
@SessionScoped
public class AnswerService extends Service {

    /**
     * SELECT MAX(anonymous_id) FROM conponentes_project.Answer; used to get the
     * max anonymous id in order to add a new id by adding one to the previous
     *
     * @return the max anonymous id from the DB
     */
    public int SelectMaxAnonymous_id() {
        int maxId = 0;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //Execute a query
            super.connect();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            System.out.println("35...");
            sql = "SELECT MAX(anonymous_id) FROM conponentes_project.Answer;";
            System.out.println("37...");
            rs = stmt.executeQuery(sql);
            System.out.println("39...");
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                maxId = rs.getInt(1);
            }
            System.out.println("42...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                super.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //returns the max id
        System.out.println("Done getting MAX id");
        return maxId;
    }

    /**
     * SELECT MAX(id) FROM conponentes_project.Answer; used to get the max
     * answer id in order to add a new id by adding one to the previous
     *
     * @return the max answer id from the DB
     */
    public int SelectMaxId() {
        int maxId = 0;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            //Execute a query
            super.connect();
            System.out.println("Creando statement...");
            stmt = conn.createStatement();
            String sql;

            System.out.println("81...");
            sql = "SELECT MAX(id) FROM conponentes_project.Answer;";
            System.out.println("83...");
            rs = stmt.executeQuery(sql);
            System.out.println("85...");
            //STEP 3.1: Extract data from result set
            while (rs.next()) {
                maxId = rs.getInt(1);
            }
            System.out.println("90...");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                super.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //returns the max id
        System.out.println("Done getting MAX id for answer");
        return maxId;
    }

    public int getNumOfAnswerees(int id) {
        int numAns = 0;
        ResultSet rs = null;
        try {
            //Execute a query
            super.connect();
            System.out.println("Creando statement...");
            String sql;

            System.out.println("119...");
            sql = "SELECT COUNT(t.anonymous_id) FROM (SELECT anonymous_id FROM conponentes_project.Answer WHERE question_id IN (SELECT id FROM conponentes_project.Question WHERE id_form = ?) group by anonymous_id) t;";

            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, id);

            //STEP 3.1: Extract data from result set
            System.out.println("90...");
            rs = preparedStmt.executeQuery();
           // rs = preparedStmt.executeQuery(sql);
            while (rs.next()) {
                numAns = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                super.disconnect();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return numAns;

    }
}
