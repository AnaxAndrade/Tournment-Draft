package cv.frameworkers.tourdraft.view;

import cv.frameworkers.tourdraft.AppMain;
import cv.frameworkers.tourdraft.base.ChildScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Criado por AnaxAndrade on 09-07-2016.
 */
public class TelaInicialController extends ChildScreenController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void btnContinuarClick(ActionEvent e){
        getBaseScreenController().setScreen(AppMain.TELA_CONTINUAR_TORNEIO);
    }

    @FXML
    private void btnNovoClick(ActionEvent e){
        getBaseScreenController().setScreen(AppMain.TELA_NOVO_TORNEIO);
    }

    @FXML
    private void btnDefinicoesClick(ActionEvent e){
        getBaseScreenController().setScreen("Defs");
    }

    @FXML
    private void btnSairClick(ActionEvent e){
        Platform.exit();
    }
}
