package br.com.projeto.ed.avl;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import br.com.projeto.ed.avl.controller.MenuInserir;


public class MainApp extends Application {
    private static Stage stage;

  
        MenuInserir menuInserir = new MenuInserir();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        primaryStage.setScene(new Scene(root));

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                menuInserir.avl.escreverEmArquivoEntrada();
            }
            
        });


        primaryStage.show();


    }

}
