/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.patient;

import docsys.RequiredFunctions;
import docsys.sql.SQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ninad
 */
public class PatientManager {
    
    public PatientManager()
    {
        
    }
    
    public static PatientBean getBean(String id)
    {
        PatientBean u = new PatientBean();
        try {
            String col[] = new String[1];
            col[0] = "p_id";
            String value[] = new String[1];
            value[0] = id;
            ResultSet rs = SQLConnection.checkFromTable("patient", col, value);
            while(rs.next())
            {
                u.setP_id(id);
                u.setP_name(rs.getString("p_name"));
                u.setP_sex(rs.getString("p_sex"));
                u.setP_age(rs.getString("p_age"));
                u.setP_addr(rs.getString("p_addr"));
                u.setP_contact(rs.getString("p_contact"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    public static boolean addPatient()
    {
//        PreparedStatement stmt = SQLConnection.con.prepareStatement("insert into users(id,isbn,name,publication,branch");
        
            int exec = 0;
            
            String val[] = new String[7];
            val[0] = String.valueOf(getRandomId());
            val[1] = AddPatientPanel.patientNameText.getText();
            val[2] = AddPatientPanel.patientSexText.getText();
            val[3] = AddPatientPanel.patientAgeText.getText();
            val[4] = AddPatientPanel.patientAddressText.getText();
            val[5] = AddPatientPanel.patientContactText.getText();
            val[6] = AddPatientPanel.patientPrevCondText.getText();
            String col[] = new String[7];
            col[0] = "p_id";
            col[1] = "p_name";
            col[2] = "p_sex";
            col[3] = "p_age";
            col[4] = "p_addr";
            col[5] = "p_contact";
            col[6] = "p_prevcond";
            exec = SQLConnection.insertInTable("patient", col, val);
            
            
            if(exec == 1)
                return true;
        
        
        return false;
    }
    
    public static boolean checkText()
    {
        if(!AddPatientPanel.patientNameText.getText().equalsIgnoreCase("")&&
                !AddPatientPanel.patientSexText.getText().equalsIgnoreCase("")&&
                !AddPatientPanel.patientAgeText.getText().equalsIgnoreCase(""))
        {
            if(!RequiredFunctions.isAlpha(AddPatientPanel.patientNameText.getText())&&
                    !RequiredFunctions.isAlpha(AddPatientPanel.patientSexText.getText())&&
                    !RequiredFunctions.isNumber(AddPatientPanel.patientAgeText.getText()))
                return false;
            else
                return true;
        }
                    
        return false;
    }
    
    public static int getRandomId()
    {
        int p_id;
        ResultSet rs = SQLConnection.getFromTable("patient");
        boolean found = false;
        while(!found)
        {
            try {
                Random n = new Random();
                p_id = n.nextInt((999999-100000)+1)+100000;
                boolean exists = false;
                while(rs.next())
                {
                    exists = true;
                        if(p_id == Integer.parseInt(rs.getString("p_id")))
                        {
                            found = true;
                            return p_id;
                        }
                    }
                if(!exists)
                    return p_id;
            } catch (SQLException ex) {
                Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
}
