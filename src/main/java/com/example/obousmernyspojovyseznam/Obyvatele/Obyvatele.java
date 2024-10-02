package com.example.obousmernyspojovyseznam.Obyvatele;

import com.example.obousmernyspojovyseznam.AbstrDoubleList.AbstrDoubleList;
import com.example.obousmernyspojovyseznam.AbstrDoubleList.IAbstrDoubleList;
import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import com.example.obousmernyspojovyseznam.ENUMS.enumPozice;
import com.example.obousmernyspojovyseznam.Obec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

public class Obyvatele implements IObyvatele {
    //TODO zeptat se, jestli pole má mít fixní velikost (14), nebo zvětšovat při vkládání
    IAbstrDoubleList<Obec>[] pole;

    @Override
    public int importData(String soubor) {
        int korektneNactene = 0;
        try (BufferedReader nactenySoubor = new BufferedReader(new FileReader((soubor)))) {
            String radek = nactenySoubor.readLine();
            while (radek != null) {
                //0 = id kraje, 1 = ENUM kraj, 2 = PSČ, 3 = obec, 4 = pocet muzu, 5 = pocet zen, 6 = pocet celkem
                String[] rozdelenyRadek = radek.split(";");

                int idKraje = Integer.parseInt(rozdelenyRadek[0]);
                int psc = Integer.parseInt(rozdelenyRadek[2]);
                String nazevObce = rozdelenyRadek[3];
                int pocetMuzu = Integer.parseInt(rozdelenyRadek[4]);
                int pocetZen = Integer.parseInt(rozdelenyRadek[5]);


                //TODO smazat, jestli má být fixní velikost
                if (pole == null) {
                    pole = new AbstrDoubleList[idKraje];
                } else if (pole.length < idKraje) {
                    zmenVelikostPole(idKraje);
                }

                if(pole[idKraje-1] == null){
                    pole[idKraje -1] = new AbstrDoubleList<>();
                }
                pole[idKraje - 1].vlozPosledni(new Obec(psc,nazevObce,pocetMuzu,pocetZen));
                korektneNactene++;

                //System.out.println(radek);
                radek = nactenySoubor.readLine();
            }
            //TODO chytat konkrétní chyby
        } catch (Exception x) {
            //TODO vyhazovat chybu
            System.err.println("chyba při načítání souboru");
        }
        return korektneNactene;
    }

    @Override
    public void vlozObec(Obec obec, enumPozice pozice, enumKraj kraj) {
        //TODO získat z kraje idKraje - v pole[idKraje - 1], nebo jak udělat
        try {
            pole[kraj.getIdKraje() - 1].jePrazdny();
        }catch (IndexOutOfBoundsException | NullPointerException x){
            zmenVelikostPole(kraj.getIdKraje());
        }
        switch (pozice){
            case PRVNI -> pole[kraj.getIdKraje() - 1].vlozPrvni(obec);
            case POSLEDNI -> pole[kraj.getIdKraje() - 1].vlozPosledni(obec);
            case PREDCHUDCE -> pole[kraj.getIdKraje() - 1].vlozPredchudce(obec);
            case NASLEDNIK -> pole[kraj.getIdKraje() - 1].vlozNaslednika(obec);
            case AKTUALNI -> //TODO vyhazovat chybu?
                    System.out.println("Nelze vložit, jako aktuální");
        }
    }

    @Override
    public Obec zpristupniObec(enumPozice pozice, enumKraj kraj) {
        //TODO chytat, když bude pozice null?
        switch (pozice){
            case PRVNI -> {
                return pole[kraj.getIdKraje() - 1].zpristupniPrvni();
            }
            case POSLEDNI -> {
                return pole[kraj.getIdKraje() - 1].zpristupniPosledni();
            }
            case PREDCHUDCE -> {
                return pole[kraj.getIdKraje() - 1].zpristupniPredchudce();
            }
            case NASLEDNIK -> {
                return pole[kraj.getIdKraje() - 1].zpristupniNaslednika();
            }
            case AKTUALNI ->{
                return pole[kraj.getIdKraje() - 1].zpristupniAktualni();
            }
            default -> {
                return null;
            }

        }
    }

    @Override
    public Obec odeberObec(enumPozice pozice, enumKraj kraj) {
        //TODO stejný jak u zpřístupnění?
        switch (pozice) {
            case PRVNI -> {
                return pole[kraj.getIdKraje() - 1].odeberPrvni();
            }
            case POSLEDNI -> {
                return pole[kraj.getIdKraje() - 1].odeberPosledni();
            }
            case PREDCHUDCE -> {
                return pole[kraj.getIdKraje() - 1].odeberPredchudce();
            }
            case NASLEDNIK -> {
                return pole[kraj.getIdKraje() - 1].odeberNaslednika();
            }
            case AKTUALNI -> {
                return pole[kraj.getIdKraje() - 1].odeberAktualni();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public float zjistiPrumer(enumKraj kraj) {

        float prumer = 0;
        int pocet = 0;
        //TODO zeptat se, jestli hodnota rovna nule je myšlen průměr, nebo nula jako kraj
        if(kraj == enumKraj.NULA){
            for (IAbstrDoubleList<Obec> obec : pole) {
                Iterator<Obec> iterator = obec.iterator();

                while (iterator.hasNext()){
                    prumer += iterator.next().getCelkem();
                    pocet++;
                }

            }
        }else {
            Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();
            while (iterator.hasNext()){
                prumer += iterator.next().getCelkem();
                pocet++;
            }
        }

        return prumer/pocet;
    }

    @Override
    public void zobrazObce(enumKraj kraj) {
        //výpis v případě, že je kraj nula
        //TODO zeptat se, jestli to je myšlený jako prázdný kraj, nebo nula jako nula
        if(kraj == enumKraj.NULA){
            for (IAbstrDoubleList<Obec> list: pole){
                Iterator<Obec> iterator = list.iterator();
                while (iterator.hasNext()){
                    System.out.println(iterator.next().toString());
                }
            }
            return;
        }

        Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
    }

    @Override
    public void zobrazObceNadPrumer(enumKraj kraj) {
        float prumer = zjistiPrumer(kraj);
        //TODO zeptat se co znamená nula
        if(kraj == enumKraj.NULA){
            for (IAbstrDoubleList<Obec> list: pole){
                Iterator<Obec> iterator = list.iterator();
                while (iterator.hasNext()){
                    Obec dalsi = iterator.next();
                    if(dalsi.getCelkem() > prumer){
                        System.out.println(dalsi);
                    }
                }
            }
            return;
        }

        Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();
        while (iterator.hasNext()){

            Obec dalsi = iterator.next();
            if(dalsi.getCelkem() > prumer){
                System.out.println(dalsi);
            }
        }
    }

    @Override
    public void zrus(enumKraj kraj) {
        //TODO nula
        if(kraj == enumKraj.NULA){
            for (IAbstrDoubleList<Obec> obec : pole){
                obec.zrus();
            }
            return;
        }
        pole[kraj.getIdKraje()-1].zrus();
    }

    private void zmenVelikostPole(int idKraje) {
        IAbstrDoubleList<Obec>[] pomocna = pole;
        pole = new AbstrDoubleList[idKraje];
        System.arraycopy(pomocna, 0, pole, 0, pomocna.length);
    }
}
