package com.example.obousmernyspojovyseznam;

public class Obec {
    //možná dát PSČ string?
    private final int psc;
    private final String obec;
    private int pocetMuzu;
    private int pocetZen;
    private int pocetCelkem;

    //TODO zeptat se jestli počítat pocetCelkem, když je to v csv jako samostatný pole - bylo by to rychlejší
    public Obec(int psc, String obec, int pocetMuzu, int pocetZen) {
        this.psc = psc;
        this.obec = obec;
        this.pocetMuzu = pocetMuzu;
        this.pocetZen = pocetZen;
        this.pocetCelkem = pocetMuzu + pocetZen;
    }

    public int getPsc() {
        return psc;
    }

    public String getObec() {
        return obec;
    }


    public int getPocetMuzu() {
        return pocetMuzu;
    }

    public int getPocetZen() {
        return pocetZen;
    }

    public int getCelkem() {
        return pocetCelkem;
    }

    public void setPocetMuzu(int pocetMuzu) {
        this.pocetMuzu = pocetMuzu;
        pocetCelkem = pocetMuzu + pocetZen;
    }

    public void setPocetZen(int pocetZen) {
        this.pocetZen = pocetZen;
        pocetCelkem = pocetMuzu + pocetZen;
    }
}
