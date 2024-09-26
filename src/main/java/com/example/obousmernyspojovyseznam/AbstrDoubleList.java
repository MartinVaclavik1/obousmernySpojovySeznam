package com.example.obousmernyspojovyseznam;

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
        }else {
            //vlozPrvni(data); - teoreticky pomalejší alternativa, ale bez žádné duplikace
            prvni = new Prvek<>(null, data, null);
            prvni.predchozi = prvni;
            prvni.nasledujici = prvni;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        if(data == null || aktualni == null){
            //TODO vyhazovat chybu a rozdělit na 2 ify, kde bude specifická chyba
            System.err.println("???");
            return;
        }

        if(prvni.predchozi == aktualni){
            vlozPosledni(data);
            return;
        }

        Prvek<T> zaVlozenymPrvkem = aktualni.nasledujici;
        Prvek<T> vlozenyPrvek = new Prvek<>(aktualni,data,zaVlozenymPrvkem);

        aktualni.nasledujici = vlozenyPrvek;
        zaVlozenymPrvkem.predchozi = vlozenyPrvek;
    }

    @Override
    public void vlozPredchudce(T data) {
        if(data == null || aktualni == null){
            //TODO vyhazovat chybu a rozdělit na 2 ify, kde bude specifická chyba
            System.err.println("???");
            return;
        }

        if(aktualni == prvni){
            vlozPrvni(data);
            return;
        }

        Prvek<T> predVlozenymPrvkem = aktualni.nasledujici;
        Prvek<T> vlozenyPrvek = new Prvek<>(predVlozenymPrvkem,data,aktualni);

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

    @Override
    public T odeberAktualni() {
        return null;
    }

    @Override
    public T odeberPrvni() {
        return null;
    }

    @Override
    public T odeberPosledni() {
        return null;
    }

    @Override
    public T odeberNaslednika() {
        return null;
    }

    @Override
    public T odeberPredchudce() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
