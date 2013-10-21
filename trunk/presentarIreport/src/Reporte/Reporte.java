/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reporte;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author jaimerojas
 */
public class Reporte {
    Connection cn;
    public Reporte() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost/empleados","root","sql");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void MostrarReporte(){
  try {
      JasperReport reporte = JasperCompileManager.compileReport("empleadosWizard.jrxml");
      JasperPrint p=JasperFillManager.fillReport(reporte, null, cn);
      JasperViewer view = new JasperViewer(p,false);
      view.setTitle("Mi primer reporte");
      view.setExtendedState(Frame.MAXIMIZED_BOTH);
      view.setVisible(true);      
  } catch (Exception e) {
           
        }
    }
    
    public void MostrarReporte1(){
      try {
        JasperReport report = JasperCompileManager.compileReport("empleadosWizard.jrxml");
      JasperPrint print = JasperFillManager.fillReport(report, null, cn);
      // Exporta el informe a PDF
      JasperExportManager.exportReportToPdfFile(print,"C:\\Practica.pdf");
      //Para visualizar el pdf directamente desde java
      JasperViewer.viewReport(print, false);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    }
}
