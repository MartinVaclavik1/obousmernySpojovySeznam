package com.example.obousmernyspojovyseznam.GUI;

import com.example.obousmernyspojovyseznam.ENUMS.enumKraj;
import com.example.obousmernyspojovyseznam.ENUMS.enumPozice;
import com.example.obousmernyspojovyseznam.Obec;
import com.example.obousmernyspojovyseznam.Obyvatele.Obyvatele;
import com.example.obousmernyspojovyseznam.Obyvatele.ObyvateleException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.util.Optional;


public class ProgObyvatele extends Application {
    private final ObservableList<String> observableList = FXCollections.observableArrayList();
    private final ListView<String> listView = new ListView<>(observableList);
    private final Obyvatele obyvatele = new Obyvatele();
    private enumKraj kraj;
    private final ChoiceBox<enumKraj> choiceBox = new ChoiceBox<>();
    private final String nazevSouboru = "zaloha.bin";

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
        vBox.getChildren().add(newButton("odeber obec", odeberObec()));
        vBox.getChildren().add(newButton("zpřístupni předchozí", zpristupniPredchozi()));
        vBox.getChildren().add(newButton("zpřístupni obec", zpristupniObec()));
        vBox.getChildren().add(newButton("zpřístupni následující", zpristupniNasledujici()));
        vBox.getChildren().add(newButton("ulož", uloz()));
        vBox.getChildren().add(newButton("načti", nacti()));

        choiceBox.getItems().addAll(enumKraj.HLAVNIMESTOPRAHA, enumKraj.JIHOCESKY,
                enumKraj.JIHOMORAVSKY, enumKraj.KARLOVARSKY, enumKraj.VYSOCINA, enumKraj.KRALOVEHRADECKY,
                enumKraj.LIBERECKY, enumKraj.MORAVSKOSLEZSKY, enumKraj.OLOMOUCKY, enumKraj.PARDUBICKY,
                enumKraj.PLZENSKY, enumKraj.STREDOCESKY, enumKraj.USTECKY, enumKraj.ZLINSKY);

        choiceBox.getSelectionModel().selectFirst();
        kraj = choiceBox.getValue();
        choiceBox.setOnAction(actionEvent -> {
            if (choiceBox.getSelectionModel().getSelectedItem() != null) {
                kraj = choiceBox.getSelectionModel().getSelectedItem();
                aktualizujListView();
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


    private EventHandler<ActionEvent> zpristupniPredchozi() {
        return EventHandler -> {
            try {
                obyvatele.zpristupniObec(enumPozice.PREDCHUDCE, choiceBox.getValue());
                listView.getSelectionModel().selectPrevious();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> zpristupniObec() {
        return EventHandler -> {
            try {
                Pair<enumPozice, enumKraj> poziceAKraj = dialogPoziceAKraj();

                if (poziceAKraj == null) {
                    System.out.println("pozice, nebo kraj nebyl zadán");
                    return;
                }
                obyvatele.zpristupniObec(poziceAKraj.getKey(), poziceAKraj.getValue());
                if (poziceAKraj.getValue() != choiceBox.getValue()) {
                    choiceBox.setValue(poziceAKraj.getValue());
                    aktualizujListView();
                }
                switch (poziceAKraj.getKey()) {
                    case PRVNI -> listView.getSelectionModel().selectFirst();
                    case NASLEDNIK -> listView.getSelectionModel().selectNext();
                    case PREDCHUDCE -> listView.getSelectionModel().selectPrevious();
                    case POSLEDNI -> listView.getSelectionModel().selectLast();
                    default -> throw new ObyvateleException("Chyba v zadávání pozice");
                }
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> zpristupniNasledujici() {
        return EventHandler -> {
            try {
                obyvatele.zpristupniObec(enumPozice.NASLEDNIK, choiceBox.getValue());
                listView.getSelectionModel().selectNext();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> odeberObec() {
        return EventHandler -> {
            try {
                Pair<enumPozice, enumKraj> poziceAKraj = dialogPoziceAKraj();
                if (poziceAKraj == null) {
                    System.out.println("pozice, nebo kraj nebyl zadán");
                    return;
                }
                obyvatele.odeberObec(poziceAKraj.getKey(), poziceAKraj.getValue());

                aktualizujListView();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> nacti() {
        return EventHandler -> {

            try {
                obyvatele.nacti(nazevSouboru);
                aktualizujListView();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> uloz() {
        return EventHandler -> {
            try {
                obyvatele.uloz(nazevSouboru);
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private EventHandler<ActionEvent> importujData() {
        return EventHandler -> {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Text Files", "*.csv"));
                File soubor = fileChooser.showOpenDialog(new Stage());
                if (soubor != null) {
                    obyvatele.importData(String.valueOf(soubor));
                }
                aktualizujListView();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    private void aktualizujListView() {

        observableList.clear();
        observableList.addAll(obyvatele.dejDoObservableListu(kraj));

//        try {
//            Obec prvni = obyvatele.zpristupniObec(enumPozice.PRVNI, kraj);
//            Obec aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, kraj);
//            if (prvni != null) {
//                observableList.add(prvni.toString());
//
//                while (aktualni != prvni) {
//                    observableList.add(aktualni.toString());
//                    aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, kraj);
//                }
//            }
//        } catch (ObyvateleException x) {
//            //chybovaHlaska(x.getMessage());
//        }
    }

    private EventHandler<ActionEvent> vlozObec() {
        return EventHandler -> {
            try {
                Obec obec = dialogObec();
                if (obec == null) {
                    System.out.println("obec nebyla zadána");
                    return;
                }
                Pair<enumPozice, enumKraj> poziceAKraj = dialogPoziceAKraj();
                if (poziceAKraj == null) {
                    System.out.println("pozice,nebo kraj nebyl zadán");
                    return;
                }
                obyvatele.vlozObec(obec, poziceAKraj.getKey(), poziceAKraj.getValue());

                aktualizujListView();
            } catch (ObyvateleException x) {
                chybovaHlaska(x.getMessage());
            }
        };
    }

    //vrací buď null, nebo Obec dle zadaných parametrů
    private Obec dialogObec() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 10, 10, 10));
        gridPane.add(new Label("PSČ:"), 0, 2);
        gridPane.add(new Label("Název obce:"), 0, 3);
        gridPane.add(new Label("Počet mužů:"), 0, 4);
        gridPane.add(new Label("Počet žen:"), 0, 5);

        TextField pscTXT = new TextField();
        TextField nazevTXT = new TextField();
        TextField pocetMTXT = new TextField();
        TextField pocetZTXT = new TextField();

        gridPane.add(pscTXT, 1, 2);
        gridPane.add(nazevTXT, 1, 3);
        gridPane.add(pocetMTXT, 1, 4);
        gridPane.add(pocetZTXT, 1, 5);

        Dialog<Obec> dialog = new Dialog<>();


        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    int psc = Integer.parseInt(pscTXT.getText());
                    int pocetM = Integer.parseInt(pocetMTXT.getText());
                    int pocetZ = Integer.parseInt(pocetZTXT.getText());
                    return new Obec(psc, nazevTXT.getText(), pocetM, pocetZ);
                } catch (Exception x) {
                    chybovaHlaska("Chyba v zadávání hodnot. Zadávejte celá čísla");
                }
            }
            return null;
        });

        Optional<Obec> obec = dialog.showAndWait();
        return obec.orElse(null);
    }

    //vrací buď null, nebo pár enumPozice a enumKraj
    private Pair<enumPozice, enumKraj> dialogPoziceAKraj() {

        ChoiceBox<enumKraj> kraje = new ChoiceBox<>();
        kraje.getItems().addAll(enumKraj.HLAVNIMESTOPRAHA, enumKraj.JIHOCESKY,
                enumKraj.JIHOMORAVSKY, enumKraj.KARLOVARSKY, enumKraj.VYSOCINA, enumKraj.KRALOVEHRADECKY,
                enumKraj.LIBERECKY, enumKraj.MORAVSKOSLEZSKY, enumKraj.OLOMOUCKY, enumKraj.PARDUBICKY,
                enumKraj.PLZENSKY, enumKraj.STREDOCESKY, enumKraj.USTECKY, enumKraj.ZLINSKY);
        kraje.getSelectionModel().selectFirst();

        ChoiceBox<enumPozice> pozice = new ChoiceBox<>();
        pozice.getItems().addAll(enumPozice.PRVNI, enumPozice.POSLEDNI, enumPozice.NASLEDNIK,
                enumPozice.PREDCHUDCE, enumPozice.AKTUALNI);
        pozice.getSelectionModel().selectFirst();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 10, 10, 10));
        gridPane.add(new Label("Pozice:"), 0, 2);
        gridPane.add(new Label("Kraj:"), 0, 3);
        gridPane.add(pozice, 1, 2);
        gridPane.add(kraje, 1, 3);

        Dialog<Pair<enumPozice, enumKraj>> dialog = new Dialog<>();


        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                return new Pair<>(pozice.getSelectionModel().getSelectedItem(),
                        kraje.getSelectionModel().getSelectedItem());
            }
            return null;
        });

        Optional<Pair<enumPozice, enumKraj>> vysledek = dialog.showAndWait();
        return vysledek.orElse(null);
    }

    private Button newButton(String nazev, EventHandler<ActionEvent> handler) {
        Button button = new Button(nazev);
        button.setOnAction(handler);
        button.setPrefWidth(150);
        return button;
    }

    private void chybovaHlaska(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}
