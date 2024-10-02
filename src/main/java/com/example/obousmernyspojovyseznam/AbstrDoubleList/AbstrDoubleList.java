package com.example.obousmernyspojovyseznam.AbstrDoubleList;

import java.util.Iterator;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private Prvek<T> prvni;
    private Prvek<T> aktualni;
    //private int pocet = 0;

    private static class Prvek<T> {
        private Prvek<T> predchozi;
        private final T prvek;
        private Prvek<T> nasledujici;

        public Prvek(Prvek<T> predchozi, T prvek, Prvek<T> nasledujici) {
            this.predchozi = predchozi;
            this.prvek = prvek;
            this.nasledujici = nasledujici;
        }
    }

    @Override
    public void zrus() {
        prvni = null;
        aktualni = null;
        //pocet = 0;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public void vlozPrvni(T data) {

        if (data == null) {
            //TODO přidat vyhazování chyby
            System.err.println("??");
            return;
        }

        if (prvni != null) {
            //vložení prvního při počtu 1 a více
            Prvek<T> novyPrvek = new Prvek<>(prvni.predchozi, data, prvni);
            Prvek<T> posledni = prvni.predchozi;
            prvni.predchozi = novyPrvek;
            posledni.nasledujici = novyPrvek;

            prvni = novyPrvek;
        } else {
            //vložení, když je jen jeden prvek.
            //nemůže se při vytváření vložit rovnou první, jako předchozí/následující, protože první je zatím null
            prvni = new Prvek<>(null, data, null);
            prvni.predchozi = prvni;
            prvni.nasledujici = prvni;
        }
    }

    @Override
    public void vlozPosledni(T data) {

        if (data == null) {
            //TODO přidat vyhazování chyby
            System.err.println("??");
            return;
        }

        if (prvni != null) {
            //když není list prázdný
            Prvek<T> novyPrvek = new Prvek<>(prvni.predchozi, data, prvni);
            Prvek<T> posledni = prvni.predchozi; //teoreticky jde napsat prvni.predchozi.nasledujici, ale to je moc matoucí
            posledni.nasledujici = novyPrvek;
            prvni.predchozi = novyPrvek;
        } else {
            //vlozPrvni(data); - teoreticky pomalejší alternativa, ale bez žádné duplikace
            prvni = new Prvek<>(null, data, null);
            prvni.predchozi = prvni;
            prvni.nasledujici = prvni;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        if (data == null || aktualni == null) {
            //TODO vyhazovat chybu a rozdělit na 2 ify, kde bude specifická chyba
            System.err.println("???");
            return;
        }

        if (prvni.predchozi == aktualni) {
            vlozPosledni(data);
            return;
        }

        Prvek<T> zaVlozenymPrvkem = aktualni.nasledujici;
        Prvek<T> vlozenyPrvek = new Prvek<>(aktualni, data, zaVlozenymPrvkem);

        aktualni.nasledujici = vlozenyPrvek;
        zaVlozenymPrvkem.predchozi = vlozenyPrvek;
    }

    @Override
    public void vlozPredchudce(T data) {
        if (data == null || aktualni == null) {
            //TODO vyhazovat chybu a rozdělit na 2 ify, kde bude specifická chyba
            System.err.println("???");
            return;
        }

        if (aktualni == prvni) {
            vlozPrvni(data);
            return;
        }

        Prvek<T> predVlozenymPrvkem = aktualni.nasledujici;
        Prvek<T> vlozenyPrvek = new Prvek<>(predVlozenymPrvkem, data, aktualni);

        aktualni.predchozi = vlozenyPrvek;
        predVlozenymPrvkem.nasledujici = vlozenyPrvek;
    }

    @Override
    public T zpristupniAktualni() {
        return aktualni.prvek;
    }

    @Override
    public T zpristupniPrvni() {
        aktualni = prvni;
        return aktualni.prvek;
    }

    @Override
    public T zpristupniPosledni() {
        aktualni = prvni.predchozi;
        return aktualni.prvek;
    }

    @Override
    public T zpristupniNaslednika() {
        aktualni = aktualni.nasledujici;
        return aktualni.prvek;
    }

    @Override
    public T zpristupniPredchudce() {
        aktualni = aktualni.predchozi;
        return aktualni.prvek;
    }

    /**
     * odebere prvek mezi dalšími prvky, které spojí a vátí data z prvku
     */
    private T odeber(Prvek<T> odebrany) {
        T odebranyPrvek = prvni.prvek;

        Prvek<T> predOdebranym = odebrany.predchozi;
        Prvek<T> zaOdebranym = odebrany.nasledujici;

        predOdebranym.nasledujici = zaOdebranym;
        zaOdebranym.predchozi = predOdebranym;

        odebrany.predchozi = null;
        odebrany.nasledujici = null;

        return odebranyPrvek;
    }

    @Override
    public T odeberAktualni() {
        //TODO zeptat se, co s aktuálním, jestli po odstranění nechat null, nebo posunout na další/předchozí
        //TODO implementovat tady odeber()
        if (aktualni == null) {
            System.err.println("není nastaven aktuální");
            return null;
        }
        if (prvni == prvni.predchozi) {
            zrus();
        }

        if (aktualni == prvni) {
            return odeberPrvni();
        }

        T odebrany = aktualni.prvek;

        Prvek<T> predAktualnim = aktualni.predchozi;
        Prvek<T> zaAktualnim = aktualni.nasledujici;

        aktualni = null;

        predAktualnim.nasledujici = zaAktualnim;
        zaAktualnim.predchozi = predAktualnim;


        return odebrany;
    }

    @Override
    public T odeberPrvni() {
        if (jePrazdny()) {
            //TODO vyhazovat chybu?
            System.err.println("prázdný list");
            return null;
        } else if (prvni == prvni.predchozi) {
            T odebranyPrvek = prvni.prvek;
            zrus();
            return odebranyPrvek;
        }

        Prvek<T> novyPrvni = prvni.nasledujici;
        T odebranyPrvek = odeber(prvni);


        prvni = novyPrvni;

        return odebranyPrvek;
    }

    @Override
    public T odeberPosledni() {
        if (jePrazdny()) {
            System.err.println("prázdný seznam");
            return null;
        }
        if (prvni == prvni.predchozi) {
            T odebranyPrvek = prvni.prvek;
            prvni = null;
            return odebranyPrvek;
        }

        Prvek<T> odebrany = prvni.predchozi;

        return odeber(odebrany);
    }

    @Override
    public T odeberNaslednika() {
        if (aktualni == null) {
            //TODO přidat vyhazování chyby
            System.err.println("není aktuální");
            return null;
        }
        if (prvni == prvni.predchozi) {
            T odebranyPrvek = prvni.prvek;
            prvni = null;
            return odebranyPrvek;
        }

        return odeber(aktualni.nasledujici);
    }

    @Override
    public T odeberPredchudce() {
        if (aktualni == null) {
            //TODO přidat vyhazování chyby
            System.err.println("není aktuální");
            return null;
        }
        if (prvni == prvni.predchozi) {
            T odebranyPrvek = prvni.prvek;
            prvni = null;
            return odebranyPrvek;
        }

        return odeber(aktualni.predchozi);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Prvek<T> nastaveny = prvni;
            boolean jePrvniIterace = true;

            @Override
            public boolean hasNext() {
                return nastaveny.nasledujici != prvni;
            }


            @Override
            public T next() {
                if (jePrazdny()) {
                    //TODO vyhazovat chybu?
                    System.err.println("prázdné pole");
                    return null;
                }
                if (jePrvniIterace) {
                    jePrvniIterace = false;
                    return nastaveny.prvek;
                }
                if (hasNext()) {
                    nastaveny = nastaveny.nasledujici;
                    return nastaveny.prvek;
                } else {
                    System.err.println("konec seznamu");
                    return null;
                }
            }

//            public boolean hasPrevious() {
//                return nastaveny != prvni;
//            }
//            //TODO zeptat se jak má fungovat iterace, jestli je možnost iterovat zároveň dopředu a
//            // dozadu, nebo jen jedno a má se loopovat, když je na sebe navázaný?
//            public T previous() {
//                if (jePrazdny()) {
//                    //TODO vyhazovat chybu?
//                    System.err.println("prázdné pole");
//                    return null;
//                }
//                if (jePrvniIterace) {
//                    jePrvniIterace = false;
//                    return nastaveny.prvek;
//                }
//                if (hasPrevious()) {
//                    nastaveny = nastaveny.predchozi;
//                    return nastaveny.prvek;
//                } else {
//                    System.err.println("konec seznamu");
//                    return null;
//                }
//            }
        };
    }
}
