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

    String Voornaam;
    String Achternaam;
    String GeboorteDatum;
    Double Lengte;
    Double Gewicht;
    String Telefoonnummer;
    String Noodnummer;
    String ExtraNoodnummer;
    String Allergieen;

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

    public boolean NieuwePatient(String Voornaam, String Achternaam, String GeboorteDatum, double Lengte, double Gewicht, String TelefoonNummer, String NoodNummer, String ExtraNoodNummer, String allergieen) {
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
            if (stmt.execute("SELECT Voornaam, Achternaam, Id FROM patient")) {
                rs = stmt.getResultSet();
                while (rs.next()) {
                    EntPatient Patient = new EntPatient(rs.getInt("Id"), rs.getString("Voornaam"), rs.getString("Achternaam"));
                    LijstPatienten.add(Patient);
                }
            }
            return LijstPatienten;
        } catch (SQLException e) {
            return null;
        }
    }

    public Patient GeefPatient(int AID) {
        Patient patient = new Patient();
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs;
            if (stmt.execute("SELECT * FROM patient Where Id =" + AID)) {
                rs = stmt.getResultSet();
                while (rs.next()) {

                    patient.Voornaam = rs.getString("Voornaam");
                    patient.Achternaam = rs.getString("Achternaam");
                    patient.GeboorteDatum = rs.getString("GeboorteDatum");
                    patient.Lengte = rs.getDouble("Lengte");
                    patient.Gewicht = rs.getDouble("Gewicht");
                    patient.Telefoonnummer = rs.getString("TelefoonNummer");
                    patient.Noodnummer = rs.getString("NoodNummer");
                    patient.ExtraNoodnummer = rs.getString("ExtraNoodNummer");
                    patient.Allergieen = rs.getString("Allergieen");
                }
            }
            return patient;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean AanpassenPatient(int id, String Voornaam, String Achternaam, String GeboorteDatum, Double Lengte, Double Gewicht, String Telefoonnummer, String Noodnummer, String ExtraNoodnummer, String Allergieen) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("update patient set Voornaam = '" + Voornaam + "', Achternaam = '" + Achternaam + "',GeboorteDatum = '" + GeboorteDatum + "',Lengte = " + Lengte + ", Gewicht = " + Gewicht + ",Telefoonnummer = '" + Telefoonnummer + "',Noodnummer = '" + Noodnummer + "',  ExtraNoodnummer = '" + ExtraNoodnummer + "',Allergieen = '" + Allergieen + "' where Id = " + id);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean DeletePatient(int id) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("Delete from patient_aandoening where PatientId = '" + id + "'");
            stmt.execute("Delete from patient where Id = '" + id + "'");
            stmt.execute("Delete from behandeling where PatientId = '" + id + "'");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean AandoeningToevoegen(int PID, int AID) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO patient_aandoening VALUES (" + PID + "," + AID + " )");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
