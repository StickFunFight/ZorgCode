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
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Rick
 */
public class Patient {

    private Connection conn;

    public boolean connectDb() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/zorgcode?" + "user=root&password=");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean NieuwePatient(String Voornaam, String Achternaam, LocalDateTime GeboorteDatum, double Lengte, double Gewicht, String TelefoonNummer, String NoodNummer,  String ExtraNoodNummer, String allergieen  ) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO patient VALUES (0,'" + Voornaam + "','" + Achternaam + "','" + GeboorteDatum + "','" + Lengte + "','" + Gewicht + "','" + TelefoonNummer + "','" + NoodNummer + "','" + ExtraNoodNummer + "','" + allergieen + "' )");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
        public ObservableList<EntPatient> VulLijstPatienten() {
        try {
            Statement stmt = this.conn.createStatement();
            ObservableList<EntPatient> LijstPatienten = FXCollections.observableArrayList();
            ResultSet rs;
            if (stmt.execute("SELECT Voornaam,Achternaam, Id FROM patient")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    EntPatient Patient = new EntPatient(rs.getInt("Id"), rs.getString("Voornaam"), rs.getString("Achternaam") );
                    LijstPatienten.add(Patient);
                }
            }
            return LijstPatienten;
        } catch (SQLException e) {
            return null;
        }
    }
}
