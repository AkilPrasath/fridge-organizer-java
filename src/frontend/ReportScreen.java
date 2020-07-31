
package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;


public class ReportScreen extends Frame implements ActionListener{
    
    JButton expenditureReport,maintainanceReport;
    JLabel l1;
    JFrame reportPage=new JFrame();
    ReportScreen(){
        reportPage.setLayout(null);
        
        Dimension dimensionToCenter=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(int)((dimensionToCenter.getWidth()-reportPage.getWidth())/2.7);
        int y=(int)((dimensionToCenter.getHeight()-reportPage.getHeight())/3);
        reportPage.setLocation(x, y);
        
        reportPage.setSize(500, 200);
        
        l1=new JLabel("GENERATE REPORT");
        expenditureReport=new JButton("Expenditure");
        maintainanceReport=new JButton("Maintainance");
        
        l1.setBounds(180, 20, 200, 50);
        expenditureReport.setBounds(30, 100,200,50);
        maintainanceReport.setBounds(250,100,200,50);
        
        reportPage.add(l1);
        reportPage.add(expenditureReport);
        reportPage.add(maintainanceReport);
        
        expenditureReport.addActionListener(this);
        maintainanceReport.addActionListener(this);
 
        reportPage.setResizable(false);
        reportPage.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== expenditureReport){
             
            // goToFridgeListFid();
            String a[]=new String[10];
            ExpenditureReport.main(a);
            reportPage.dispose();
             
        }
        if(e.getSource()==maintainanceReport){
           String b[]=new String[10];
           MaintainceReport.main(b);
           reportPage.dispose();
        }
    }

    public static void main(String[] args) {
        
       ReportScreen report=new ReportScreen();
    }

    
}
/*if (dt_1.compareTo(dt_2) < 0) {
   System.out.println("Date 1 occurs after Date 2");
  } */