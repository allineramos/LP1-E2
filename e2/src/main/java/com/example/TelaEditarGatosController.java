package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaEditarGatosController {

    @FXML
    private TextField nomeText;
    @FXML
    private TextField racaText;
    @FXML
    private TextField corText;
    @FXML
    private TextField sexoText;

    @FXML
    private Button salvarButton;

    private Stage stage;
    private Gato gatoSelecionado;

    public void initData (Gato gatoSelecionado){
        this.gatoSelecionado = gatoSelecionado;
        nomeText.setText(gatoSelecionado.nome);
        racaText.setText(gatoSelecionado.raca);
        corText.setText(gatoSelecionado.cor);
        sexoText.setText(gatoSelecionado.sexo);
    }

    @FXML
    public void salvarEditar (){
        String nome = nomeText.getText();
        String raca = racaText.getText();
        String cor = corText.getText();
        String sexo = sexoText.getText();
            if (nome.isEmpty() || raca.isEmpty() || cor.isEmpty() || sexo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Campos vazios");
                alert.setContentText("Todos os campos devem ser preenchidos");
                alert.showAndWait();
                return;
            }
        gatoSelecionado.nome = nome;
        gatoSelecionado.raca = raca;
        gatoSelecionado.cor = cor;
        gatoSelecionado.sexo = sexo;
        
        ConexaoBanco.editarGato(gatoSelecionado, gatoSelecionado.nome);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Gatinho salvo com sucesso!");
        alert.showAndWait();

        stage.close();
    }

    public void setStage(@SuppressWarnings("exports") Stage stage) {
        this.stage = stage;
    }
}

