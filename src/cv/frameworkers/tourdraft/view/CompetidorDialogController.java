package cv.frameworkers.tourdraft.view;

import com.jfoenix.controls.JFXTextField;
import cv.frameworkers.tourdraft.model.Competidor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AnaxAndrade on 11-07-2016.
 */
public class CompetidorDialogController implements Initializable{
    @FXML
    private TextField competidorNome;

    @FXML
    private TextField competidorNick;

    Competidor c;

    boolean isOk;
    Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCompetidor(Competidor comp){
        this.c = comp;
        competidorNome.setText(comp.getNome());
        competidorNick.setText(comp.getNick());
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    @FXML
    private void handleOk(){
        if (validate()){
            c.setNick(competidorNick.getText());
            c.setNome(competidorNome.getText());
            isOk = true;
            dialogStage.close();
        }
    }

    private boolean validate(){
        return true;
    }

    public boolean isOkClicked() {
        return isOk;
    }
}
