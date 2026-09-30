/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.petcare.controller;

/**
 *
 * @author losha
 */

import com.petcare.config.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReportController {
    
    
    public static void generateReport(String reportFileName) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            
            // Reports folder එකෙන් .jrxml හෝ .jasper file එක Load කිරීම
            String reportPath = "/com/petcare/reports/" + reportFileName;
            InputStream inputStream = ReportController.class.getResourceAsStream(reportPath);

            if (inputStream == null) {
                javax.swing.JOptionPane.showMessageDialog(null, "Report file not found: " + reportFileName);
                return;
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), conn);

            // Report එක Window එකකින් Display කිරීම
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Pet Care Management - System Report");
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Report Generation Error: " + e.getMessage());
        }
    }
}
