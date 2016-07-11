package cv.frameworkers.tourdraft.view;

import com.jfoenix.controls.JFXHamburger;
import cv.frameworkers.tourdraft.AppMain;
import cv.frameworkers.tourdraft.base.ChildScreenController;
import cv.frameworkers.tourdraft.base.RootScreenController;
import cv.frameworkers.tourdraft.model.Competidor;
import cv.frameworkers.tourdraft.model.Confronto;
import cv.frameworkers.tourdraft.model.Ronda;
import cv.frameworkers.tourdraft.model.Torneio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by AnaxAndrade on 11-07-2016.
 */
public class AreaCompeticaoController extends ChildScreenController implements Initializable{

    @FXML
    private JFXHamburger menuIcon;

    @FXML
    private Label tituloCompeticao;

    @FXML
    private ScrollPane mainScroll;

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

        //CARREGAR ESTADO ATUAL DO TORNEIO - Rondas
        //Gerar Brackets caso ainda não esteja gerado
        if(competicaoAtual.getRondaAtual() < 1)
            competicaoAtual.gerarBaseBrackets(true);

        VBox vb = new VBox();
        vb.setFillWidth(true);
        for (int i=0; i < competicaoAtual.getRondas().size(); i++){
            Ronda ro = competicaoAtual.getRondas().get(i);

            //Criar Label
            Label lbl = new Label("Ronda "+ro.getId());
            lbl.setFont(new Font("Arial", 22));

            //Criar Tabela Com dados de todos os Confrontos
            TableView<Confronto> tbl = new TableView<>(ro.getConfrontos());
            TableColumn<Confronto, String> colP1 = new TableColumn<>("Player 1");
            colP1.setCellValueFactory(cellData -> cellData.getValue().getP1().nickProperty());


            TableColumn<Confronto, Number> colS1 = new TableColumn<>("");
            colS1.setCellValueFactory(cellData -> cellData.getValue().s1Property());
            colS1.setCellFactory(col -> new IntegerEditingCell());

            TableColumn<Confronto, Number> colS2 = new TableColumn<>("");
            colS2.setCellValueFactory(cellData -> cellData.getValue().s2Property());
            colS2.setCellFactory(col -> new IntegerEditingCell());

            TableColumn<Confronto, String> colP2 = new TableColumn<>("Player 2");
            colP2.setCellValueFactory(cellData -> cellData.getValue().getP2().nickProperty());

            tbl.getColumns().addAll(colP1, colS1, colS2, colP2);
            
            vb.getChildren().addAll(lbl, tbl);

            mainScroll.setContent(vb);

        }

    }

    @FXML
    private void btnAvancarClick(ActionEvent e){}

    @FXML
    private void btnExportarClick(ActionEvent e){}

    @FXML
    private void btnTreeViewClick(ActionEvent e){}

    @FXML
    private void btnGuardarClick(ActionEvent e){}


    public class IntegerEditingCell extends TableCell<Confronto, Number> {

        private final TextField textField = new TextField();
        private final Pattern intPattern = Pattern.compile("-?\\d+");

        public IntegerEditingCell() {
            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (! isNowFocused) {
                    processEdit();
                }
            });
            textField.setOnAction(event -> processEdit());
        }

        private void processEdit() {
            String text = textField.getText();
            if (intPattern.matcher(text).matches()) {
                commitEdit(Integer.parseInt(text));
            } else {
                cancelEdit();
            }
        }

        @Override
        public void updateItem(Number value, boolean empty) {
            super.updateItem(value, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                setText(null);
                textField.setText(value.toString());
                setGraphic(textField);
            } else {
                setText(value.toString());
                setGraphic(null);
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            Number value = getItem();
            if (value != null) {
                textField.setText(value.toString());
                setGraphic(textField);
                setText(null);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().toString());
            setGraphic(null);
        }

        // This seems necessary to persist the edit on loss of focus; not sure why:
        @Override
        public void commitEdit(Number value) {
            super.commitEdit(value);
            ((Confronto)this.getTableRow().getItem()).setS1(value.intValue());
        }
    }
}
