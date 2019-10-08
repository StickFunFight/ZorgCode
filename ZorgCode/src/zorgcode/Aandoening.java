/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rickd
 */
public class Aandoening {
    
    private Connection conn;
    int id;
    String Naam;
    String Omschrijving;
    String ConstateerDatum;
    boolean Genezen; 
    
    Aandoening() {
    this.id = 0;
    this.Naam = "";
    this.Omschrijving = "";
    this.ConstateerDatum = "";
    this.Genezen = false;
  }
    
        public boolean connectDb()
    {
         try{
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost/zorgcode?" + "user=root&password=");
        return true;
        }catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
        
    }
           public ArrayList<String> GeefNaam()
    {
        try
        {
            Statement stmt = this.conn.createStatement();
            ArrayList<String> lijstNamen = new ArrayList<String>();
            ResultSet rs;
            if (stmt.execute("SELECT Naam,id FROM aandoening")) 
            {
                rs = stmt.getResultSet();
                while(rs.next())
                {
                    lijstNamen.add(rs.getString("Naam"));
                }
            }
            return lijstNamen;
         }catch(SQLException e)
         {
             return null;
         }
    } 
    
}
