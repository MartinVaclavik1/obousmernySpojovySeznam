package com.example.obousmernyspojovyseznam;

public enum enumPozice {
    //TODO prvni, posledni, predchuudce, naslednik, aktualni
    PRVNI("první"),
    POSLEDNI("poslední"),
    PREDCHUDCE("předchůdce"),
    NASLEDNIK("následník"),
    AKTUALNI("aktuální");

    private final String pozice;

    enumPozice(String pozice) {
        this.pozice = pozice;
    }

    public String getPozice() {
        return pozice;
    }
}
