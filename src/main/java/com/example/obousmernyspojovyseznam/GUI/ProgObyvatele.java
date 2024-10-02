package com.example.obousmernyspojovyseznam.GUI;

import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import com.example.obousmernyspojovyseznam.ENUMS.enumPozice;
import com.example.obousmernyspojovyseznam.Obec;
import com.example.obousmernyspojovyseznam.Obyvatele.Obyvatele;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;


public class ProgObyvatele extends Application {
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(observableList);
    private Obyvatele obyvatele = new Obyvatele();
    private enumKraj kraj;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        listView.setFocusTraversable(false);
        BorderPane root = new BorderPane();
        VBox vBox = new VBox();
        root.setCenter(listView);
        root.setRight(vBox);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        vBox.setSpacing(5);

        vBox.getChildren().add(newButton("importuj data", importujData()));
        vBox.getChildren().add(newButton("vlož obec", vlozObec()));
        vBox.getChildren().add(newButton("ulož", uloz()));
        vBox.getChildren().add(newButton("načti", nacti()));

        ChoiceBox<enumKraj> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(enumKraj.HLAVNYMESTOPRAHA, enumKraj.JIHOCESKY,
                enumKraj.JIHOMORAVSKY, enumKraj.KARLOVARSKY, enumKraj.VYSOCINA, enumKraj.KRALOVEHRADECKY,
                enumKraj.LIBERECKY, enumKraj.MORAVSKOSLEZSKY, enumKraj.OLOMOUCKY, enumKraj.PARDUBICKY,
                enumKraj.PLZENSKY, enumKraj.STREDOCESKY, enumKraj.USTECKY, enumKraj.ZLINSKY);

        choiceBox.getSelectionModel().selectFirst();
        kraj = choiceBox.getValue();
        choiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (choiceBox.getSelectionModel().getSelectedItem() != null) {
                    kraj = choiceBox.getSelectionModel().getSelectedItem();
                    aktualizujListView();
                }
            }
        });
        vBox.getChildren().add(choiceBox);

        Scene scene = new Scene(root);
        stage.setTitle("Václavík - AbstrDoubleList");
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(800);
        stage.show();
    }

    private EventHandler<ActionEvent> nacti() {
        return EventHandler -> {

//            Seznam seznam = new SpojovySeznam();
//            try {
//                Objects.requireNonNull(seznam);
//                ObjectInputStream vstup =
//                        new ObjectInputStream(
//                                new FileInputStream(nazevBinSouboru));
//                seznam.zrus();
//
//                int pocet = vstup.readInt();
//                for (int i = 0; i < pocet; i++) {
//                    seznam.vlozPosledni(vstup.readObject());
//                }
//                vstup.close();
//
//            } catch (IOException | KolekceException | ClassNotFoundException ex) {
//                System.err.println("Chyba při obnovování dat");
//            }
//            return seznam;
        };
    }

    private EventHandler<ActionEvent> uloz() {
        return EventHandler -> {
//            try {
//                Objects.requireNonNull(seznam);
//
//                ObjectOutputStream vystup =
//                        new ObjectOutputStream(
//                                new FileOutputStream(nazevBinSouboru));
//
//
//                vystup.writeInt(seznam.size());
//
//
//                Iterator<Seznam> it = seznam.iterator();
//                while (it.hasNext()) {
//                    vystup.writeObject(it.next());
//                }
//
//                vystup.close();
//            } catch (IOException ex) {
//                System.err.println("Chyba v zálohování dat");
//            }
        };
    }

    private EventHandler<ActionEvent> importujData() {
        return EventHandler -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Text Files", "*.csv"));
            File soubor = fileChooser.showOpenDialog(new Stage());
            if (soubor != null) {
                obyvatele.importData(String.valueOf(soubor));
            }
            aktualizujListView();
        };
    }

    private void aktualizujListView() {
        observableList.clear();
        Obec prvni = obyvatele.zpristupniObec(enumPozice.PRVNI, kraj);
        Obec aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, kraj);
        if (prvni != null) {
            observableList.add(prvni.toString());

            while (aktualni != prvni) {
                observableList.add(aktualni.toString());
                aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, kraj);
            }
        }

    }

    private EventHandler<ActionEvent> vlozObec() {
        return EventHandler -> {

        };
    }

    private Button newButton(String nazev, EventHandler<ActionEvent> handler) {
        Button button = new Button(nazev);
        button.setOnAction(handler);
        button.setPrefWidth(70);
        return button;
    }
}
