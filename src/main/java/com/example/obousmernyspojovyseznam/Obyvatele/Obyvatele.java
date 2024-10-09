package com.example.obousmernyspojovyseznam.Obyvatele;

import com.example.obousmernyspojovyseznam.AbstrDoubleList.AbstrDoubleList;
import com.example.obousmernyspojovyseznam.AbstrDoubleList.AbstrDoubleListException;
import com.example.obousmernyspojovyseznam.AbstrDoubleList.IAbstrDoubleList;
import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import com.example.obousmernyspojovyseznam.ENUMS.enumPozice;
import com.example.obousmernyspojovyseznam.Obec;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class Obyvatele implements IObyvatele {
    private final IAbstrDoubleList<Obec>[] pole = new AbstrDoubleList[14];

    //inicializuje AbstrDoubleList v každé části pole
    public Obyvatele() {
        for (int i = 0; i < pole.length; i++) {
            pole[i] = new AbstrDoubleList<>();
        }
    }

    @Override
    public int importData(String soubor) throws ObyvateleException {
        int korektneNactene = 0;
        //TODO zeptat se, jestli při importování dat mazat všechny data, nebo je jen přidat ke stávajícím
        // - odkomentovat pro mazání dat
//       for (IAbstrDoubleList<Obec> obec : pole) {
//                obec.zrus();
//            }

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

                if (pole[idKraje - 1] == null) {
                    pole[idKraje - 1] = new AbstrDoubleList<>();
                }
                pole[idKraje - 1].vlozPosledni(new Obec(psc, nazevObce, pocetMuzu, pocetZen));
                korektneNactene++;

                //System.out.println(radek);
                radek = nactenySoubor.readLine();
            }
        } catch (IOException x) {
            throw new ObyvateleException("Chyba v načítání souboru");
        } catch (AbstrDoubleListException x) {
            throw new ObyvateleException(x.getMessage());
        }
        return korektneNactene;
    }

    @Override
    public void vlozObec(Obec obec, enumPozice pozice, enumKraj kraj) throws ObyvateleException {
        if (obec == null || pozice == null || kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        try {
            switch (pozice) {
                case PRVNI -> pole[kraj.getIdKraje() - 1].vlozPrvni(obec);
                case POSLEDNI -> pole[kraj.getIdKraje() - 1].vlozPosledni(obec);
                case PREDCHUDCE -> pole[kraj.getIdKraje() - 1].vlozPredchudce(obec);
                case NASLEDNIK -> pole[kraj.getIdKraje() - 1].vlozNaslednika(obec);
                case AKTUALNI -> throw new ObyvateleException("Nelze vložit jako aktuální");
            }

        } catch (AbstrDoubleListException x) {
            throw new ObyvateleException(x.getMessage());
        }

    }

    @Override
    public Obec zpristupniObec(enumPozice pozice, enumKraj kraj) throws ObyvateleException {
        if (pozice == null || kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        if (pole[kraj.getIdKraje() - 1] == null) {
            throw new ObyvateleException("Nelze zpřístupnit prázdný kraj");
        }

        try {
            switch (pozice) {
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
                case AKTUALNI -> {
                    return pole[kraj.getIdKraje() - 1].zpristupniAktualni();
                }
                default -> {
                    return null;
                }

            }
        } catch (AbstrDoubleListException x) {
            throw new ObyvateleException(x.getMessage());
        }
    }

    @Override
    public Obec odeberObec(enumPozice pozice, enumKraj kraj) throws ObyvateleException {
        if (pozice == null || kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        if (pole[kraj.getIdKraje() - 1] == null) {
            throw new ObyvateleException("Nelze odebrat v prázdném kraji");
        }

        try {
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
        } catch (AbstrDoubleListException x) {
            throw new ObyvateleException(x.getMessage());
        }
    }

    @Override
    public float zjistiPrumer(enumKraj kraj) throws ObyvateleException {
        if (kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }
        float prumer = 0;
        int pocet = 0;
        //TODO zeptat se, jestli hodnota rovna nule je myšlen průměr, nebo nula jako kraj
        //if (kraj == enumKraj.NULA) {
        if (pole[kraj.getIdKraje() - 1].jePrazdny()) {
            for (IAbstrDoubleList<Obec> obec : pole) {
                Iterator<Obec> iterator = obec.iterator();

                if (!obec.jePrazdny()) {
                    prumer += iterator.next().getPocetCelkem();
                    pocet++;
                }
                while (iterator.hasNext()) {
                    prumer += iterator.next().getPocetCelkem();
                    pocet++;
                }

            }
        } else {
            Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();
            if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
                prumer += iterator.next().getPocetCelkem();
                pocet++;
            }
            while (iterator.hasNext()) {
                prumer += iterator.next().getPocetCelkem();
                pocet++;
            }
        }

        return prumer / pocet;
    }

    @Override
    public void zobrazObce(enumKraj kraj) throws ObyvateleException {

        //TODO zeptat se, jestli to je myšlený jako prázdný kraj, nebo nula jako nula
        if (kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        //výpis v případě, že je kraj nula
//        if (kraj == enumKraj.NULA) {
        if (pole[kraj.getIdKraje() - 1].jePrazdny()) {
            for (IAbstrDoubleList<Obec> list : pole) {
                Iterator<Obec> iterator = list.iterator();

                if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
                    System.out.println(iterator.next().toString());
                }
                while (iterator.hasNext()) {
                    System.out.println(iterator.next().toString());
                }
            }
        } else {
            Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();

            if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
                System.out.println(iterator.next().toString());
            }
            while (iterator.hasNext()) {
                System.out.println(iterator.next().toString());
            }
        }
    }

    @Override
    public void zobrazObceNadPrumer(enumKraj kraj) throws ObyvateleException {
        if (kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        float prumer = zjistiPrumer(kraj);
        //TODO zeptat se co znamená nula
//        if (kraj == enumKraj.NULA) {
        if (pole[kraj.getIdKraje() - 1].jePrazdny()) {
            for (IAbstrDoubleList<Obec> list : pole) {
                Iterator<Obec> iterator = list.iterator();

                if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
                    Obec dalsi = iterator.next();
                    if (dalsi.getPocetCelkem() > prumer) {
                        System.out.println(dalsi);
                    }
                }
                while (iterator.hasNext()) {
                    Obec dalsi = iterator.next();
                    if (dalsi.getPocetCelkem() > prumer) {
                        System.out.println(dalsi);
                    }
                }
            }

        } else {
            Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();

            if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
                Obec dalsi = iterator.next();
                if (dalsi.getPocetCelkem() > prumer) {
                    System.out.println(dalsi);
                }

                while (iterator.hasNext()) {
                    dalsi = iterator.next();
                    if (dalsi.getPocetCelkem() > prumer) {
                        System.out.println(dalsi);
                    }
                }
            }
        }
    }

    @Override
    public void zrus(enumKraj kraj) throws ObyvateleException {

        if (kraj == null) {
            throw new ObyvateleException("Nelze vkládat null");
        }

        //TODO nula
//        if (kraj == enumKraj.NULA) {
        if (pole[kraj.getIdKraje() - 1].jePrazdny()) {
            for (IAbstrDoubleList<Obec> obec : pole) {
                obec.zrus();
            }
        } else {
            pole[kraj.getIdKraje() - 1].zrus();
        }
    }

    public void uloz(String nazevSouboru) throws ObyvateleException {
        try {
            Objects.requireNonNull(pole);

            ObjectOutputStream vystup =
                    new ObjectOutputStream(
                            new FileOutputStream(nazevSouboru));

            for (int i = 0; i < pole.length; i++) {
                Iterator<Obec> it = pole[i].iterator();

                if (!pole[i].jePrazdny()) {
                    vystup.writeInt(i);
                    vystup.writeObject(it.next());
                }
                while (it.hasNext()) {
                    vystup.writeInt(i);
                    vystup.writeObject(it.next());
                }
            }
            vystup.writeInt(-1);

            vystup.close();
            System.out.println("Úspěšně uloženo");
        } catch (Exception x) {
            throw new ObyvateleException("Chyba při ukládání souboru");
        }
    }

    public void nacti(String nazevSouboru) throws ObyvateleException {

        try {
            int pocet = 0;
//            Objects.requireNonNull(pole);
            ObjectInputStream vstup =
                    new ObjectInputStream(
                            new FileInputStream(nazevSouboru));
            for (IAbstrDoubleList<Obec> obec : pole) {
                obec.zrus();
            }

            int kraj = vstup.readInt();

            while (kraj != -1) {
                Obec obec = (Obec) vstup.readObject();
                //System.out.println(obec);
                if (obec != null) {
                    pole[kraj].vlozPosledni(obec);
                    pocet++;
                }

                kraj = vstup.readInt();

            }
            vstup.close();
            System.out.println("Úspěšně načteno " + pocet + " obcí");

        } catch (Exception x) {
            throw new ObyvateleException("Chyba při načítání dat");
        }
    }

    public ObservableList<String> dejDoObservableListu(enumKraj kraj) {
        ObservableList<String> observableList = FXCollections.observableArrayList();

        Iterator<Obec> iterator = pole[kraj.getIdKraje() - 1].iterator();

        //když má pole jeden prvek
        if (!pole[kraj.getIdKraje() - 1].jePrazdny()) {
            observableList.add(iterator.next().toString());
        }
        while (iterator.hasNext()) {
            observableList.add(iterator.next().toString());
        }


        return observableList;
    }
}
