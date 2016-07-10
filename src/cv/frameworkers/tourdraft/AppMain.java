package cv.frameworkers.tourdraft;/**
 * Created by AnaxAndrade on 08-07-2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/TelaInicial.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            Scene scene = new Scene(pane);
            primaryStage.resizableProperty().setValue(false);
            primaryStage.setTitle("Taekwondo Draft");
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void irContinuar(){}
    public void irNovo(){}
    public void irSettings(){}
}
