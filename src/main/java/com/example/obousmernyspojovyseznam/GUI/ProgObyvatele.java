package com.example.obousmernyspojovyseznam.GUI;

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
import javafx.stage.Stage;


public class ProgObyvatele extends Application {
    private ObservableList<String> observableList = FXCollections.observableArrayList();
    private ListView<String> listView = new ListView<>(observableList);
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

        vBox.getChildren().add(newButton("vloz prvni", vlozPrvni()));
        vBox.getChildren().add(newButton("vloz poslední", vlozPosledni()));


        Scene scene = new Scene(root);
        stage.setTitle("Václavík - AbstrDoubleList");
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(800);
        stage.show();
    }

    private EventHandler<ActionEvent> vlozPosledni() {
        return EventHandler -> {

        };
    }

    private EventHandler<ActionEvent> vlozPrvni() {
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
