/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

/**
 *
 * @author Rick
 */
public class EntPatient {

    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getVoornaam() {
        return Voornaam;
    }

    private String Voornaam;

    public void setVoornaam(String Voornaam) {
        this.Voornaam = Voornaam;
    }

    private String Achternaam;

    public String getAchternaam() {
        return Achternaam;
    }

    public void setAchternaam(String Achternaam) {
        this.Achternaam = Achternaam;
    }

    public EntPatient(int Id, String Voornaam, String Achternaam) {
        this.Id = Id;
        this.Voornaam = Voornaam;
        this.Achternaam = Achternaam;
    }

    @Override
    public String toString() {
        return Voornaam + " " + Achternaam;
    }

}
