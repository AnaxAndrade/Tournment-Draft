package cv.frameworkers.tourdraft.view;

import com.jfoenix.controls.JFXHamburger;
import cv.frameworkers.tourdraft.AppMain;
import cv.frameworkers.tourdraft.base.ChildScreenController;
import cv.frameworkers.tourdraft.model.Competidor;
import cv.frameworkers.tourdraft.model.Torneio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.text.resources.no.CollationData_no;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Criado por AnaxAndrade em 10-07-2016.
 */
public class NovoTorneioController extends ChildScreenController implements Initializable {

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private ListView<Competidor> inputsListView;

    @FXML
    private TextField tituloCompeticao;

    Torneio novoTorneio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuIcon.setOnMouseClicked(e -> getBaseScreenController().setScreen(AppMain.TELA_INICIAL));

        novoTorneio = new Torneio();
        inputsListView.setCellFactory(new Callback<ListView<Competidor>, ListCell<Competidor>>() {
            @Override
            public ListCell<Competidor> call(ListView<Competidor> param) {
                ListCell<Competidor> cel = new ListCell<Competidor>() {
                    @Override
                    protected void updateItem(Competidor item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getNome());
                        } else {
                            setText("");
                        }
                    }
                };

                return cel;
            }
        });

        inputsListView.setItems(this.novoTorneio.getCompetidores());

    }

    @FXML
    private void addCompetidor(ActionEvent e){
        Competidor ctal = new Competidor();

        //Caso Clicado em Ok
        if (showCompetidorEditDialog(ctal, "Novo Competidor")){
            this.novoTorneio.addCompetidor(ctal);
        }
    }

    @FXML
    private void removeCompetidor(ActionEvent e){
        // TODO Verificar que não prejudica na atribuição dos Ids de competidores
        novoTorneio.getCompetidores().remove(inputsListView.getSelectionModel().getSelectedItem());
    }

    public boolean showCompetidorEditDialog(Competidor person, String titulo) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AppMain.class.getResource("view/CompetidorDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(titulo);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(main.getMainStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CompetidorDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCompetidor(person);
            //controller.setHeader(titulo);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void btnGoMainClick(ActionEvent e){
        getBaseScreenController().setScreen(AppMain.TELA_INICIAL);
    }

    @FXML
    private void btnSaveCompeticaoClick(ActionEvent e){
        if (this.novoTorneio.getCompetidores().size() > 1){
            this.novoTorneio.setTitulo(tituloCompeticao.getText());
            this.getAppMain().adicionaTorneio(novoTorneio);

            getBaseScreenController().setScreen(AppMain.TELA_INICIAL);
        }else {
            //TODO MOSTRAR DIALOG DE COMPETIDORES INSUFICIENTE
        }
    }
}
