/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

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
}
