package cv.frameworkers.tourdraft.view;

import cv.frameworkers.tourdraft.AppMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AnaxAndrade on 09-07-2016.
 */
public class TelaInicialController implements Initializable{

    AppMain mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Obter Referência da Classe Principal
    public void setMainApp(AppMain mainApp) {
        this.mainApp = mainApp;

    }

    @FXML
    private void btnContinuarClick(ActionEvent e){
        mainApp.irContinuar();
    }

    @FXML
    private void btnNovoClick(ActionEvent e){
        mainApp.irNovo();
    }

    @FXML
    private void btnDefinicoesClick(ActionEvent e){
        mainApp.irSettings();
    }

    @FXML
    private void btnSairClick(ActionEvent e){
        Platform.exit();
    }
}
