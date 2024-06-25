package com.example;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaPrincipalController {
    private ObservableList<Gato> listaGatos = FXCollections.observableArrayList();

    @FXML
    private TextField nomeText;

    @FXML
    private TextField racaText;

    @FXML
    private TextField corText;
    
    @FXML
    private TextField sexoText;

    @FXML
    void CadastrarGato(ActionEvent event){
        Gato gato = new Gato(nomeText.getText(), racaText.getText(), corText.getText(), sexoText.getText());
        ConexaoBanco.cadastrarGato(gato);
        listaGatos.add(gato);
        nomeText.setText("");
        racaText.setText("");
        corText.setText("");
        sexoText.setText("");
    }

    @FXML
    void verGatos(ActionEvent event){
        List<Gato> gatos = ConexaoBanco.pegarGatos();
        listaGatos.setAll(gatos);

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telaListaGatos.fxml"));
            Parent root = loader.load();
            TelaListaGatosController controller = loader.getController();
            controller.initListaGatos(listaGatos);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Lista de gatinhos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
