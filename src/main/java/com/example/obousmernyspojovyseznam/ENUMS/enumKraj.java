package com.example.obousmernyspojovyseznam.ENUMS;

public enum enumKraj {
    NULA("nula", 0),
    HLAVNYMESTOPRAHA("Hlavní město Praha",1),
    JIHOCESKY("Jihočeský", 2),
    JIHOMORAVSKY("Jihomoravský", 3),
    KARLOVARSKY("Karlovarský", 4),
    VYSOCINA("Vysočina", 5),
    KRALOVEHRADECKY("Králové Hradecký", 6),
    LIBERECKY("Liberecký", 7),
    MORAVSKOSLEZSKY("Moravskoslezský", 8),
    OLOMOUCKY("Olomoucký", 9),
    PARDUBICKY("Pardubický", 10),
    PLZENSKY("Plzeňský", 11),
    STREDOCESKY("Středočeský", 12),
    USTECKY("Ústecký", 13),
    ZLINSKY("Zlínský", 14);

    private final int idKraje;
    private final String kraj;

    enumKraj(String kraj, int idKraje) {
        this.idKraje = idKraje;
        this.kraj = kraj;
    }

    public int getIdKraje() {
        return idKraje;
    }

    public String getKraj() {
        return kraj;
    }
}
