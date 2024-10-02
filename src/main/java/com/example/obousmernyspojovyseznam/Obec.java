package com.example.obousmernyspojovyseznam;

import java.io.Serializable;

public class Obec implements Serializable {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Obec{");
        sb.append("psc=").append(psc);
        sb.append(", obec='").append(obec).append('\'');
        sb.append(", pocetMuzu=").append(pocetMuzu);
        sb.append(", pocetZen=").append(pocetZen);
        sb.append(", pocetCelkem=").append(pocetCelkem);
        sb.append('}');
        return sb.toString();
    }
}
