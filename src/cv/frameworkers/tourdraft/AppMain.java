package cv.frameworkers.tourdraft;/**
 * Created by AnaxAndrade on 08-07-2016.
 */

import cv.frameworkers.tourdraft.base.RootScreenController;
import cv.frameworkers.tourdraft.model.Torneio;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {

    public static String TELA_INICIAL = "TELA_INICIAL";
    public static String TELA_NOVO_TORNEIO = "NOVO_TORNEIO";
    public static String TELA_CONTINUAR_TORNEIO = "TELA_CONTINUAR_TORNEIO";
    public static String TELA_AREA_TORNEIO = "TELA_AREA_TORNEIO";

    //ID do torneio ativo
    public int torneioAtual;

    public ObservableList<Torneio> torneios;

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage= primaryStage;
        torneios = FXCollections.observableArrayList();
        torneioAtual = -1;
        RootScreenController down = new RootScreenController();
        down.setMainApp(this);
        down.loadScreen(TELA_INICIAL, "view/TelaInicial.fxml");
        down.loadScreen(TELA_NOVO_TORNEIO, "view/NovoTorneio.fxml");
        down.loadScreen(TELA_AREA_TORNEIO, "view/AreaCompeticao.fxml");
        //down.loadScreen(TELA_CONTINUAR_TORNEIO, "view/ContinuarTorneio.fxml");

        //Carregar tela Inicial
        down.setScreen(AppMain.TELA_INICIAL);

        BorderPane root = new BorderPane();
        root.setCenter(down);
        root.setStyle("-fx-background-color: #262626");

        Scene scene = new Scene(root);
        primaryStage.resizableProperty().setValue(false);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Taekwondo Draft");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void adicionaTorneio(Torneio tor){
        int id = this.torneios.size();
        tor.setId(id);
        this.torneios.add(tor);
        torneioAtual = id;
    }

    public Torneio getTorneioAtual() {
        for(int i = 0; i < this.torneios.size(); i++){
            if (this.torneios.get(i).getId() == torneioAtual)
                return this.torneios.get(i);
        }
        return null;
    }

    public Stage getMainStage() {
        return primaryStage;
    }

}
