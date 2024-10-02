package com.example.obousmernyspojovyseznam.ENUMS;

public enum enumPozice {
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
