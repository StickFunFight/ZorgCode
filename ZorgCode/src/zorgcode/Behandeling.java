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

public class Behandeling {

    private Connection conn;
    int id;
    String Naam;
    String Omschrijving;
    String Medicijn;
    int Hoeveelheid;
    String BeginDatum;
    String EindDatum;
    int PatientId;

    public boolean connectDb() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/zorgcode?" + "user=root&password=");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    public ObservableList<EntBehandeling> VulLijstBehandeling() {
        try {
            Statement stmt = this.conn.createStatement();
            ObservableList<EntBehandeling> LijstPatienten = FXCollections.observableArrayList();
            ResultSet rs;
            if (stmt.execute("SELECT Naam,Id FROM Behandeling")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    EntBehandeling Behandeling = new EntBehandeling(rs.getInt("Id"), rs.getString("Naam"));
                    LijstPatienten.add(Behandeling);
                }
            }
            return LijstPatienten;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean NieuweAandoening(String Naam, String Omschrijving, String Medicijn, int Hoeveelheid, String BeginDatum, String EindDatum, int PatientId) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO behandeling VALUES (0,'" + Naam + "','" + Omschrijving + "','" + Medicijn + "','" + Hoeveelheid + "','" + BeginDatum + "','" + EindDatum + "','" + PatientId + "')");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public ObservableList<EntBehandeling> VulLijstBehandeeling(int PID) {
        try {
            Statement stmt = this.conn.createStatement();
            ObservableList<EntBehandeling> LijstBehandeling = FXCollections.observableArrayList();
            ResultSet rs;
            if (stmt.execute("SELECT Naam,Id FROM Behandeling where PatientId =" + PID)) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    EntBehandeling DeBehandeling = new EntBehandeling(rs.getInt("Id"), rs.getString("Naam"));
                    LijstBehandeling.add(DeBehandeling);
                }
            }
            return LijstBehandeling;
        } catch (SQLException e) {
            return null;
        }
    }
}
