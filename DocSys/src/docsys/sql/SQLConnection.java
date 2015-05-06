/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ninad
 */
public class SQLConnection {
    public static Connection con;
    private String username = "ninad";
    private String password = "root";
    private String URL = "jdbc:sqlite:docsys";
    
    public SQLConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(
                    URL,
                    username,
                    password);
                    } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet checkFromTable(String table, String col[], String value[])
    {
        ResultSet rs = null;
        try {
            StringBuffer query = new StringBuffer();
            query.append("select * from "+table+" where");
            
            for(int i=0; i<col.length; i++)
            {
                query.append(" "+col[i].toString()+"=?");
                if(i!=col.length-1)
                    query.append(" AND");
            }
            
            PreparedStatement stmt = con.prepareStatement(query.toString());
            for(int i=0;i<value.length;i++)
            {
                stmt.setString(i+1, value[i].toString());
            }
//            stmt.setString(1, query);
            
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public static int insertInTable(String table, String col[], String val[])
    {
        try {
            StringBuffer query = new StringBuffer();
            query.append("insert into "+table+"(");
            for(int i=0;i<col.length;i++)
            {
                query.append(col[i]);
                if(i!=col.length-1)
                    query.append(",");
            }
            query.append(") values(");
            for (int i = 0; i < val.length; i++) {
                query.append("?");
                if(i!=col.length-1)
                    query.append(",");
            }
            query.append(")");
            PreparedStatement stmt = con.prepareStatement(query.toString());
            for(int i=0;i<val.length;i++)
            {
                stmt.setString(i+1, val[i].toString());
            }
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static int updateInTable(String table, String col[], String val[], String idCol, String idColVal)
    {
        try {
            StringBuffer query = new StringBuffer();
            query.append("update "+table+" set ");
            for (int i = 0; i < col.length; i++)
            {
                query.append(col[i]+"=?");
                if(i!=col.length-1)
                    query.append(",");
            }
            query.append(" where "+idCol+"=?");
//            query.append("update patient set p_name='ninad' where p_id='909609'");
            for (int i = 0; i < 6; i++) {
                System.out.println(val[i]);    
                }
            System.out.println(query);
            PreparedStatement stmt = con.prepareStatement(query.toString());
            for(int i=0;i<val.length;i++)
            {
                stmt.setString(i+1, val[i].toString());
            }
            stmt.setString(val.length+1, idColVal);
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static ResultSet getFromTable(String table, String col[])
    {
        try {
            ResultSet rs = null;
            
            StringBuffer query = new StringBuffer();
            query.append("select ");
            for(int i=0;i<col.length;i++)
            {
                query.append(col[i]);
                if(i!=col.length-1)
                    query.append(",");
            }
            query.append(" from "+table);
            return con.prepareStatement(query.toString()).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ResultSet getFromTable(String table)
    {
        try {
            ResultSet rs = null;
            
            StringBuffer query = new StringBuffer();
            query.append("select * from "+ table);
            
            return con.prepareStatement(query.toString()).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
