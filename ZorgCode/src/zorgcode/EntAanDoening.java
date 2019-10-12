/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author rickd
 */
public class EntAanDoening {

    private String Omschrijving;

    public String getOmschrijving() {
        return Omschrijving;
    }

    public void setOmschrijving(String Omschrijving) {
        this.Omschrijving = Omschrijving;
    }

    private String naam;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public EntAanDoening(int id, String naam, String Omschrijving) {

        this.naam = naam;
        this.id = id;
        this.Omschrijving = Omschrijving;

    }

    @Override
    public String toString() {
        return this.naam;
    }

}
