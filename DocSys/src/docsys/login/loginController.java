/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.login;

import docsys.sql.SQLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ninad
 */
public class loginController {
    
    public static boolean login(String username,String pass)
    {
        try {
            String col[] = new String[] {"L_USER", "L_PASS"};
            String value[] = new String[] {username, pass};

            ResultSet rs = SQLConnection.checkFromTable("login", col, value);
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
