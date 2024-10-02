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
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class ProgObyvatele extends Application {
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(observableList);
    private Obyvatele obyvatele = new Obyvatele();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        listView.setMouseTransparent(true);
        listView.setFocusTraversable(false);
        BorderPane root = new BorderPane();
        VBox vBox = new VBox();
        root.setCenter(listView);
        root.setRight(vBox);
        vBox.setPadding(new Insets(10, 10, 0, 10));
        vBox.setSpacing(5);

        vBox.getChildren().add(newButton("importuj data", importujData()));
        vBox.getChildren().add(newButton("vlož obec", vlozObec()));


        Scene scene = new Scene(root);
        stage.setTitle("Václavík - AbstrDoubleList");
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(800);
        stage.show();
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
        Obec prvni = obyvatele.zpristupniObec(enumPozice.PRVNI,enumKraj.HLAVNYMESTOPRAHA);
        Obec aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, enumKraj.HLAVNYMESTOPRAHA);
        observableList.add(prvni.toString());

        while (aktualni != prvni){
            observableList.add(aktualni.toString());
            aktualni = obyvatele.zpristupniObec(enumPozice.NASLEDNIK, enumKraj.HLAVNYMESTOPRAHA);
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
