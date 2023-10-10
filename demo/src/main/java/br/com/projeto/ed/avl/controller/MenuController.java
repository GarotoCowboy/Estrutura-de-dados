package br.com.projeto.ed.avl.controller;

import java.io.IOException;

import br.com.projeto.ed.avl.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuController {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnRemover;

     @FXML
    private AnchorPane tela;

    
    public void inicializaJanela(String diretorio) throws IOException{
        MenuInserir menuInserir = new MenuInserir();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(diretorio));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                menuInserir.avl.escreverEmArquivoEntrada();
            }
            
        });
        MenuController menuController = new MenuController();
        
        Stage stage = new Stage();
        stage = (Stage)tela.getScene().getWindow();
        stage.close();
        


    }

    @FXML
    void abrirMenuBuscar(ActionEvent event) throws IOException {
        inicializaJanela("/fxml/menuBusca.fxml");
        
    }

    @FXML
    void abrirMenuInserir(ActionEvent event) throws IOException {
        inicializaJanela("/fxml/menuInserir.fxml");
    }

    @FXML
    void abrirMenuRemover(ActionEvent event) throws IOException {
        inicializaJanela("/fxml/menuRemover.fxml");
    }

}
