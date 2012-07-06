package de.javandry.minigolfapp.entities;

public class Ball {

    private Integer id;
    private String hersteller;
    private String name;
    private String groesse;
    private String lackierung;
    private Double sprunghoehe;
    private Double haerte;
    private Double gewicht;

    public Integer getId() {
        return id;
    }

    public String getHersteller() {
        return hersteller;
    }

    public String getName() {
        return name;
    }

    public String getGroesse() {
        return groesse;
    }

    public String getLackierung() {
        return lackierung;
    }

    public Double getSprunghoehe() {
        return sprunghoehe;
    }

    public Double getHaerte() {
        // ball.getString("h") == null || ball.getString("h").length() == 0 ? null : Double.valueOf(ball.getString("h"))
        return haerte;
    }

    public Double getGewicht() {
        // ball.getString("gw") == null || ball.getString("gw").length() == 0 ? null : Double.valueOf(ball.getString("gw"))
        return gewicht;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public void setLackierung(String lackierung) {
        this.lackierung = lackierung;
    }

    public void setSprunghoehe(Double sprunghoehe) {
        this.sprunghoehe = sprunghoehe;
    }

    public void setHaerte(Double haerte) {
        this.haerte = haerte;
    }

    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }
}