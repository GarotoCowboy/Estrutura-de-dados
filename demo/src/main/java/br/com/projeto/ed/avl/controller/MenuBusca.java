package br.com.projeto.ed.avl.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.projeto.ed.avl.Aluno;
import br.com.projeto.ed.avl.No;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuBusca {
    MenuInserir menuInserir = new MenuInserir();

     @FXML
    private Button btnBuscar;

    @FXML
    private Button btnVoltar;

   @FXML
    private TableColumn<Aluno, Integer> tableFalta;

    @FXML
    private TableColumn<Aluno, String> tableNome;

    @FXML
    private TableColumn<Aluno, Integer> tableMatricula;

    @FXML
    private TableColumn<Aluno, Double> tableNota1;

    @FXML
    private TableColumn<Aluno, Double> tableNota2;

    @FXML
    private TableColumn<Aluno, Double> tableNota3;
     @FXML
    private TableColumn<Aluno, Double> tableMedia;

    @FXML
    private TableView<Aluno> tableViewInfo;

    @FXML
    private TextField textFieldMatricula;

     @FXML
    private AnchorPane tela;

    @FXML
    void fazerBusca(ActionEvent event) {
         ArrayList<No> alunos = menuInserir.avl.inorder();
        ObservableList<Aluno>dadosAluno = FXCollections.observableArrayList();
        dadosAluno.add(menuInserir.avl.buscarPorMatricula(Integer.parseInt(textFieldMatricula.getText())));

        tableMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableFalta.setCellValueFactory(new PropertyValueFactory<>("faltas"));
        tableNota1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        tableNota2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        tableNota3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        tableMedia.setCellValueFactory(new PropertyValueFactory<>("media"));

        tableViewInfo.setItems(dadosAluno);
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MenuInserir menuInserir = new MenuInserir();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
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

    public void initialize(){
        iniciarBusca();
        inicializaTabela();
    }

     public void inicializaTabela(){
        ArrayList<No> alunos = menuInserir.avl.inorder();
        ObservableList<Aluno>dadosAluno = FXCollections.observableArrayList();

        for(No aluno:alunos){
            dadosAluno.add(aluno.getChave());
        }

        tableMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tableNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableFalta.setCellValueFactory(new PropertyValueFactory<>("faltas"));
        tableNota1.setCellValueFactory(new PropertyValueFactory<>("n1"));
        tableNota2.setCellValueFactory(new PropertyValueFactory<>("n2"));
        tableNota3.setCellValueFactory(new PropertyValueFactory<>("n3"));
        tableMedia.setCellValueFactory(new PropertyValueFactory<>("media"));

        tableViewInfo.setItems(dadosAluno);
    }
     public void iniciarBusca(){
        try (BufferedReader reader = new BufferedReader(new FileReader(menuInserir.arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                try {
                    Aluno aluno = new Aluno(Integer.parseInt(dados[0].trim()), dados[1].trim(),
                            Integer.parseInt(dados[2].trim()), Double.parseDouble(dados[3].trim()),
                            Double.parseDouble(dados[4].trim()), Double.parseDouble(dados[5].trim()));
                    menuInserir.avl.inserir(aluno);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter número na linha: " + linha);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro durante operações de entrada/saída: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro desconhecido: " + e.getMessage());
        }
        for (No no : menuInserir.avl.inorder()) {
            System.out.println("Matrícula: " + no.getChave().getMatricula() + ", Nome: " + no.getChave().getNome()
                    + ", Faltas: " + no.getChave().getFaltas() + ", Nota 01: " + no.getChave().getN1() +
                    ", Nota 02: " + no.getChave().getN2() + ", Nota 03: " + no.getChave().getN3()
                    + ", Media das notas: " + no.getChave().getMedia());
        }
    }


}
