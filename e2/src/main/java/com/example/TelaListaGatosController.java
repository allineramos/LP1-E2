package com.example;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TelaListaGatosController {
    @FXML
    private ListView<Gato> listView;

    public void initialize(){}

    public void initListaGatos(ObservableList<Gato> gatos){
        listView.setItems(gatos);

        listView.setCellFactory(param -> new ListCell<Gato>(){
            @Override
            protected void updateItem(Gato gato, boolean empty){
                super.updateItem(gato, empty);
                if (empty || gato == null){
                    setGraphic(null);
                } else {
                    setText(gato.nome + " - " + gato.raca + " - " + gato.cor + " - " + gato.sexo);
                }
            }
        });
    }

    @FXML
    void excluirGato (){
        Gato gatoSelecionado = listView.getSelectionModel().getSelectedItem();
        if (gatoSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar exclusão");
            alert.setHeaderText("Deseja excluir esse gatinho?");
            alert.setContentText("Uma vez excluido esse gatinho não pode voltar.");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK){
                    ConexaoBanco.deletarGato(gatoSelecionado);
                    listView.getItems().remove(gatoSelecionado);
                }
            });
        }
    }

    @FXML
    void editarGato (){
        Gato gatoSelecionado = listView.getSelectionModel().getSelectedItem();
        if (gatoSelecionado != null) {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("telaEditarGatos.fxml"));
                Parent root = loader.load();
                TelaEditarGatosController controller = loader.getController();
                controller.initData(gatoSelecionado);

                Stage stage = new Stage();
                controller.setStage(stage);
                stage.setTitle("Editar gatinho");
                stage.setScene(new Scene(root));
                stage.showAndWait();

                List<Gato> gatos = ConexaoBanco.pegarGatos();
                ObservableList<Gato> observableGatos = FXCollections.observableArrayList(gatos);
                initListaGatos(observableGatos);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
