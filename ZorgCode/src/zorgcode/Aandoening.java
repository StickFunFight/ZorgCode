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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
           public ObservableList<EntAanDoening> VulLijstAandoening()
    {
        try
        {
            Statement stmt = this.conn.createStatement();
            ObservableList<EntAanDoening> lijstNamen = FXCollections.observableArrayList();
            ResultSet rs;
            if (stmt.execute("SELECT Naam,id FROM aandoening")) 
            {
                rs = stmt.getResultSet();
                while(rs.next())
                {
                    EntAanDoening deAandoening = new EntAanDoening(rs.getInt("id"),rs.getString("Naam"));
                    lijstNamen.add(deAandoening); 
                    //Hier word een List gemaakt van Aandoeningen 
                    //in deze list zitten de naam en het ID
                }
            }
            return lijstNamen;
         }catch(SQLException e)
         {
             return null;
         }
    } 
           
      public Aandoening GeefAandoening(int AID)
    {
        Aandoening aandoening = new Aandoening();
        try
        {
            Statement stmt = this.conn.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM aandoening Where id =" +AID)) 
            {
                rs = stmt.getResultSet();
                while(rs.next())
                {
                    aandoening.Naam = rs.getString("Naam");
                    aandoening.id = rs.getInt("Id");
                    aandoening.Omschrijving = rs.getString("Omschrijving");
                    aandoening.ConstateerDatum = rs.getString("ConstateerDatum");
                    aandoening.Genezen = rs.getBoolean("Genezen");
                   
                }
            }
            return aandoening;
         }catch(SQLException e)
         {
             return null;
         }
    } 
    
}
