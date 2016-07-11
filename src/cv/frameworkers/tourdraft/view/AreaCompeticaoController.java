package cv.frameworkers.tourdraft.view;

import com.jfoenix.controls.JFXHamburger;
import cv.frameworkers.tourdraft.AppMain;
import cv.frameworkers.tourdraft.base.ChildScreenController;
import cv.frameworkers.tourdraft.base.RootScreenController;
import cv.frameworkers.tourdraft.model.Torneio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AnaxAndrade on 11-07-2016.
 */
public class AreaCompeticaoController extends ChildScreenController implements Initializable{

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private Label tituloCompeticao;

    private Torneio competicaoAtual;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuIcon.setOnMouseClicked(e -> getBaseScreenController().setScreen(AppMain.TELA_INICIAL));
    }

    @Override
    public void onResume() {
        super.onResume();

        competicaoAtual = getAppMain().getTorneioAtual();
        if (competicaoAtual != null)
            tituloCompeticao.setText(competicaoAtual.getTitulo());

        //TODO CARREGAR ESTADO ATUAL DO TORNEIO - Rondas
    }

    @FXML
    private void btnAvancarClick(ActionEvent e){}

    @FXML
    private void btnExportarClick(ActionEvent e){}

    @FXML
    private void btnTreeViewClick(ActionEvent e){}

    @FXML
    private void btnGuardarClick(ActionEvent e){}


}
