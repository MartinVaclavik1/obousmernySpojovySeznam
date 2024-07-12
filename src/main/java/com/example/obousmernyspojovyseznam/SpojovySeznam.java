package com.example.obousmernyspojovyseznam;

public class SpojovySeznam<E> {
    private Prvek<E> prvni;
    private Prvek<E> aktualni;
    private Prvek<E> posledni;
    private int pocet = 0;

    private class Prvek<E> {
        private Prvek<E> predchozi;
        private final E prvek;
        private Prvek<E> nasledujici;

        public Prvek(Prvek<E> predchozi, E prvek, Prvek<E> nasledujici) {
            this.predchozi = predchozi;
            this.prvek = prvek;
            this.nasledujici = nasledujici;
        }
    }

    public void vlozPrvni(E prvek) {

        if (prvek != null) {
            Prvek<E> novy = new Prvek<>(null, prvek, prvni);

            if (prvni != null) {
                prvni.predchozi = novy;
            }
            prvni = novy;

            if (pocet == 0) {
                posledni = prvni;
            }
            pocet++;
        } else {
            System.err.println("more co sem dáváš null");
        }
    }

    public void vlozDalsi(E prvek) {

        if (prvek != null && aktualni != null) {
            Prvek<E> novy = new Prvek<>(aktualni, prvek, aktualni.nasledujici);
            if (aktualni.nasledujici != null)
                aktualni.nasledujici.predchozi = novy;

            aktualni.nasledujici = novy;
            if (aktualni == posledni)
                posledni = novy;

            pocet++;
        } else {
            System.err.println("more co sem dáváš null");
        }

    }

    public void vlozPosledni(E prvek) {
        if (prvek != null) {
            Prvek<E> novy = new Prvek<>(posledni, prvek, null);

            if (posledni != null) {
                posledni.nasledujici = novy;
            }
            posledni = novy;

            if (pocet == 0) {
                prvni = posledni;
            }
            pocet++;
        } else {
            System.err.println("more co sem dáváš null");
        }
    }

    public E odeberPrvni() {
        if (!jePrazdny()) {
            E odebrany = prvni.prvek;

            Prvek novyPrvni = prvni.nasledujici;

            prvni.nasledujici = null;
            novyPrvni.predchozi = null;

            prvni = novyPrvni;

            return odebrany;
        }
        return null;    //vyhodit chybu?
    }

    public E odeberPosledni() {
        if (!jePrazdny()) {
            E odebrany = posledni.prvek;

            Prvek novyPosledni = prvni.predchozi;

            posledni.predchozi = null;
            novyPosledni.nasledujici = null;

            posledni = novyPosledni;

            return odebrany;
        }
        return null;    //vyhodit chybu?
    }

    public E odeberAktualni() {
        if (!jePrazdny() && aktualni != null) {
            E odebrany = aktualni.prvek;

            if (aktualni == posledni) {
                odeberPosledni();
            } else if (aktualni == prvni) {
                odeberPrvni();
            } else {
                Prvek predAktualnim = null;
                Prvek zaAktualnim = null;
                if (jePredchozi())
                    predAktualnim = aktualni.predchozi;
                if (jeDalsi())
                    zaAktualnim = aktualni.nasledujici;

                aktualni.predchozi = null;
                aktualni.nasledujici = null;

                if (jePredchozi())
                    predAktualnim.nasledujici = zaAktualnim;
                if (jeDalsi())
                    zaAktualnim.predchozi = predAktualnim;
            }

            return odebrany;
        }
        return null; //vyhodit chybu?
    }

    public E odeberPredAktualnim() {
        if (aktualni != null && jePredchozi()) {
            E odebrany = aktualni.predchozi.prvek;

            Prvek<E> predchozi = aktualni.predchozi;
            if (predchozi == prvni) {
                odeberPrvni();
            } else {
                Prvek<E> predPredchozim = null;

                if (predchozi.predchozi != null) {  //pro jistotu. při kontrole prvního je to zbytečný
                    predPredchozim = predchozi.predchozi;
                    predPredchozim.nasledujici = aktualni;
                }

                aktualni.predchozi = predPredchozim;

                predchozi.nasledujici = null;
                predchozi.predchozi = null;
            }
            return odebrany;
        }
        return null;    //vyhodit chybu?
    }

    public E odeberZaAktualnim() {
        if (aktualni != null && jeDalsi()) {
            E odebrany = aktualni.nasledujici.prvek;

            Prvek<E> nasledujici = aktualni.nasledujici;
            if (nasledujici == posledni) {
                odeberPosledni();
            } else {
                Prvek<E> zaNasledujicim = null;

                if (nasledujici.nasledujici != null) {  //taky zbytený, když se kontroluje posledni
                    zaNasledujicim = nasledujici.nasledujici;
                    zaNasledujicim.predchozi = aktualni;
                }

                aktualni.nasledujici = zaNasledujicim;

                nasledujici.nasledujici = null;
                nasledujici.predchozi = null;
            }


            return odebrany;
        }
        return null;    //vyhodit chybu?
    }

    public boolean jeDalsi() {
        return aktualni.nasledujici != null;
    }

    public boolean jePredchozi() {
        return aktualni.predchozi != null;
    }

    public boolean jePrazdny() {
        return pocet == 0;
    }

    public int dejPocet() {
        return pocet;
    }

    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        pocet = 0;
    }

    public void nastavPrvni() {
        aktualni = prvni;
    }

    public void nastavPosledni() {
        aktualni = posledni;
    }

    public void nastavDalsi() {
        if (jeDalsi())
            aktualni = aktualni.nasledujici;
    }

    public void nastavPredchozi() {
        if (aktualni.nasledujici != null)
            aktualni = aktualni.predchozi;
    }

    public E dejAktualni() {
        return aktualni.prvek;
    }

    public E dejPrvni() {
        return prvni.prvek;
    }

    public E dejPosledni() {
        return posledni.prvek;
    }

    public E dejZaAktualnim() {
        if (jeDalsi()) {
            return aktualni.nasledujici.prvek;
        }
        return null; //asi přidat throw chybu sem
    }

    public E dejPredAktualnim() {
        if (jePredchozi()) {
            return aktualni.predchozi.prvek;
        }
        return null; //asi přidat throw chybu sem
    }

}
